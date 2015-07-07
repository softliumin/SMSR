package cc.sharper.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Test2
{

	public static void main(String[] args)throws JMSException
	{
		String url = "tcp://localhost:61616";
	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	    // 设置用户名和密码，这个用户名和密码在conf目录下的credentials.properties文件中，也可以在activemq.xml中配置
	    connectionFactory.setUserName("admin");
	    connectionFactory.setPassword("admin");
	    // 创建连接
	    Connection connection = connectionFactory.createConnection();
	    connection.start();
	    // 创建Session
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    // 创建目标，就创建主题也可以创建队列
	    Destination destination = session.createQueue("test");
	    // 创建消息消费者
	    MessageConsumer consumer = session.createConsumer(destination);
	    // 接收消息，参数：接收消息的超时时间，为0的话则不超时，receive返回下一个消息，但是超时了或者消费者被关闭，返回null
	    Message message = consumer.receive(1000);
	    if (message instanceof TextMessage) {
	        TextMessage textMessage = (TextMessage) message;
	        String text = textMessage.getText();
	        System.out.println("Received: " + text);
	    } else {
	        System.out.println("Received: " + message);
	    }
	    consumer.close();
	    session.close();
	    connection.close();

	}

}
