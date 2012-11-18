package main;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public class TopicMessageListenerImpl implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		if (arg0 instanceof TextMessage) {
			System.out.println(arg0);
		}
	}

}
