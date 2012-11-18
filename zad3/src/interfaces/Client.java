package interfaces;

import javax.jms.TopicPublisher;
import javax.jms.TopicSubscriber;

public interface Client {
	
	String[] topics = { "cars", "computers", "toys" };

	public String[] getTopics();
	
	public TopicSubscriber subscribe(String topicName);

	public TopicPublisher publish(String categoryName, String auctionName, String startingPrice, String description, String secondsToEnd);

	public TopicPublisher bid(String categoryName, String auctionName, String price);
	
}
