package main;

import java.io.ObjectInputStream;

import interfaces.Publisher;

import javax.jms.JMSException;
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

			Auction auction = null;
			ObjectMessage message = (ObjectMessage) arg0;
			try {
				auction = (Auction) message.getObject();
			} catch (JMSException e) {
				e.printStackTrace();
			}

			if (publisher.getAuctions().contains(auction)) {
				System.out.println("Received a new bid.");
				for (Auction setAuction : publisher.getAuctions()) {
					if (setAuction.equals(auction)) {
						if (setAuction.getPrice() < auction.getPrice()) {
							setAuction.setPrice(auction.getPrice());
							auction = setAuction;
							System.out.println("New highest bid in Your auction !:\n" + auction.printDescription());
							publisher.newHighestBid(auction);
						}
						break;
					}
				}
			}

		}
	}

}
