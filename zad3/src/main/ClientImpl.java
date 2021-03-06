package main;

import interfaces.Client;
import interfaces.Publisher;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientImpl implements Client, Publisher {

	TopicConnection topicConnection;
	Context context;
	TopicSession topicSession;
	Set<Auction> auctions;

	
	public static void main(String[] args) throws NamingException,
			JMSException, IOException {

		Client client = new ClientImpl(args[0]);
		Command c = new Command(client);
		c.start();

	}

	public ClientImpl(String ip) throws NamingException, JMSException {
		initialize(ip);
		auctions = Collections.synchronizedSet(new HashSet<Auction>());
	}

	private void initialize(String ip) throws NamingException, JMSException {
		// context
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.exolab.jms.jndi.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "tcp://" + ip + ":3035/");
		context = new InitialContext(props);
		System.out.println("Context OK");

		// connection
		TopicConnectionFactory factory = (TopicConnectionFactory) context
				.lookup("ConnectionFactory");
		topicConnection = factory.createTopicConnection();
		System.out.println("Connection OK");

		// session
		topicSession = topicConnection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);
		
		topicConnection.start();
	}

	@Override
	public String[] getTopics() {
		return topics;
	}

	@Override
	public TopicSubscriber subscribe(String topicName) {
		TopicSubscriber receiver = null;
		try {
			// Topic
			Topic topic = (Topic) context.lookup(topicName);

			// CONSUMER
			receiver = topicSession.createSubscriber(topic, "bid IS NULL",false);
			receiver.setMessageListener(new TopicMessageListenerImpl());
			System.out.println("Subscribed to topic " + topicName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return receiver;
	}

	@Override
	public TopicPublisher publish(String topicName, String auctionName,
			String startingPrice, String description, String secondsToEnd) {
		TopicPublisher returnValue = null;
		try {
			// Topic
			Topic topic = (Topic) context.lookup(topicName);

			// PRODUCER
			final TopicPublisher sender = topicSession.createPublisher(topic);
			// CONSUMER
			TopicSubscriber receiver = topicSession.createSubscriber(topic,
					"bid = true", false);
			receiver.setMessageListener(new BidMessageListenerImpl(this));

			final Auction a = new Auction(topicName, auctionName,
					startingPrice, description, secondsToEnd);
			if (auctions.add(a)) {
				System.out.println("You published an auction in " + topicName
						+ " category.");
				TextMessage message = topicSession.createTextMessage("New auction:\n"
								+ a.printDescription());
				sender.send(message);

				new Timer(true).schedule(new TimerTask() {
					public void run() {
						try {
							TextMessage message = topicSession.createTextMessage("Auction ended !:\n"
											+ a.printDescription());
							sender.send(message);
						} catch (Exception e) {
							System.out.println(e);
						}
					}
				}, a.getEndTime());
			} else {
				System.out.println("Auction with that name already exists !");
			}
			returnValue = sender;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return returnValue;
	}
	
	
	@Override
	public TopicPublisher bid(String categoryName, String auctionName, String price) {
		TopicPublisher sender = null;
		try {
			// Topic
			Topic topic = (Topic) context.lookup(categoryName);

			// PRODUCER
			sender = topicSession.createPublisher(topic);

			Auction a = new Auction(categoryName, auctionName, price);
			System.out.println("You made a bid in " + auctionName + " auction.");
			ObjectMessage message = topicSession.createObjectMessage(a);
			message.setBooleanProperty("bid", true);
			sender.setPriority(6);
			sender.send(message);
//			TextMessage txtMessage = topicSession.createTextMessage(
//					"New bid in auction: " + auctionName + " in category " + categoryName);
//			sender.send(txtMessage);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sender;
		
	}

	@Override
	public void newHighestBid(Auction auction) {
		try {
			// Topic
			Topic topic = (Topic) context.lookup(auction.getCategory());
	
			// PRODUCER
			TopicPublisher sender = topicSession.createPublisher(topic);
			
			TextMessage message = topicSession.createTextMessage("New highest bid in auction !:\n" + auction.printDescription());
			sender.setPriority(5);
			sender.send(message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Set<Auction> getAuctions() {
		return auctions;
	}



}
