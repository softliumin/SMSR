package cc.sharper.mq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Test
{
	public static void main(String[] args) throws JMSException
	{
		String url = "tcp://localhost:61616";
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        // 设置用户名和密码，这个用户名和密码在conf目录下的credentials.properties文件中，也可以在activemq.xml中配置
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 创建Session，参数解释：
        // 第一个参数是否使用事务:当消息发送者向消息提供者（即消息代理）发送消息时，消息发送者等待消息代理的确认，没有回应则抛出异常，消息发送程序负责处理这个错误。
        // 第二个参数消息的确认模式：
        // AUTO_ACKNOWLEDGE ： 指定消息提供者在每次收到消息时自动发送确认。消息只向目标发送一次，但传输过程中可能因为错误而丢失消息。
        // CLIENT_ACKNOWLEDGE ： 由消息接收者确认收到消息，通过调用消息的acknowledge()方法（会通知消息提供者收到了消息）
        // DUPS_OK_ACKNOWLEDGE ： 指定消息提供者在消息接收者没有确认发送时重新发送消息（这种确认模式不在乎接收者收到重复的消息）。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建目标，就创建主题也可以创建队列
        Destination destination = session.createQueue("test");
        // 创建消息生产者
        MessageProducer producer = session.createProducer(destination);
        // 设置持久化，DeliveryMode.PERSISTENT和DeliveryMode.NON_PERSISTENT
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 创建消息
        String text = "Hello ActiveMQ!";
        TextMessage message = session.createTextMessage(text);
        // 发送消息到ActiveMQ
        producer.send(message);
        System.out.println("Message is sent!");
        // 关闭资源
        session.close();
        connection.close();
        
        
        
		

	}
}
