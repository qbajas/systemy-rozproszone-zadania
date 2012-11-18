package main;

import javax.jms.TopicPublisher;

public interface Client {
	
	String[] topics = { "cars", "computers", "toys" };

	public String[] getTopics();
	
	public void subscribe(String topicName);

	public TopicPublisher publish(String topicName, String auctionName, String startingPrice, String description, String secondsToEnd);
	
}
