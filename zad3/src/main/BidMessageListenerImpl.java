package main;

import interfaces.Publisher;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BidMessageListenerImpl implements MessageListener {

	Publisher publisher;

	public BidMessageListenerImpl(Publisher publisher) {
		super();
		this.publisher = publisher;
	}

	@Override
	public void onMessage(Message arg0) {
		if (arg0 instanceof ObjectMessage) {
			System.out.println("Received a new bid: " + arg0);
//			TODO deserialize, check if in auctions, run newHighestBid()
		}
	}

}
