
import java.util.Properties;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;


public class Consumer {

    public static void main(String[] args){
        try {       
     	
            System.out.println("CONSUMER");
            
            // context
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, 
                    "org.exolab.jms.jndi.InitialContextFactory");    
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            Context context = new InitialContext(props);
            System.out.println("Context OK");
            
            // connection
            TopicConnectionFactory factory = 
                    (TopicConnectionFactory)context.lookup("ConnectionFactory");
            TopicConnection topicConnection = factory.createTopicConnection();
            System.out.println("Connection OK");
            
            // session & topic
            TopicSession topicSession = 
                    topicConnection.createTopicSession(false, 
                        Session.AUTO_ACKNOWLEDGE);            
            Topic topic = (Topic)context.lookup("topic1");                        
            System.out.println("Topic OK");
            
            // CONSUMER
            TopicSubscriber receiver = topicSession.createSubscriber(topic);
            receiver.setMessageListener(new MessageListenerImpl());
            topicConnection.start();
            System.out.println("Connection started, listener set.");            
            
//            while(true){
//                System.out.println("Waiting for message...");
//                TextMessage message = (TextMessage) receiver.receive();         // blocking
//                System.out.println("Received message: " + message.getText());
//            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
