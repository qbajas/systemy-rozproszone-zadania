import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.JMSException;
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

public class Producer {

	String[] topics = { "Cars", "Computers", "Toys" };

	TopicConnection topicConnection;
	Context context;

	public static void main(String[] args) throws NamingException, JMSException, IOException {

		Producer p = new Producer();
		p.initialize(args[0]);
		p.printTopics();
		p.rest();

	}
	

	private void initialize(String ip) throws NamingException, JMSException {
		System.out.println("PRODUCER");

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

	}

	private void printTopics() {
		for (int i = 0; i < topics.length; i++) {
			System.out.println(i + 1 + ". " + topics[i]);
		}
	}

	private void rest() throws JMSException, NamingException, IOException {
		// session & Topic
		TopicSession topicSession = topicConnection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Topic topic = (Topic) context.lookup("topic1");
		System.out.println("Topic OK");

		// PRODUCER
		TopicPublisher sender = topicSession.createPublisher(topic);
		topicConnection.start();
		System.out.println("Connection started");

		System.out.println("CONSUMER");

		// CONSUMER
		TopicSubscriber receiver = topicSession.createSubscriber(topic);
		receiver.setMessageListener(new MessageListenerImpl());
		topicConnection.start();
		System.out.println("Connection started, listener set.");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("Type message: ");
			String msg = br.readLine();
			TextMessage message = topicSession.createTextMessage(msg);
			System.out.println("Message sent");
			sender.send(message);
		}
	}

}
