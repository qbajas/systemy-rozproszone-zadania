package main;
import javax.jms.Message;
import javax.jms.MessageListener;


public class MessageListenerImpl implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		System.out.println(arg0);		
	}    


}
