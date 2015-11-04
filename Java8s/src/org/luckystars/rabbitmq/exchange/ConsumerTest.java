//package org.luckystars.rabbitmq.exchange;
//
//import java.io.IOException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.apache.thrift.TException;
//
//import com.coamctech.eastlending.activity.thrift.model.InvestRank;
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.Consumer;
//import com.rabbitmq.client.DefaultConsumer;
//import com.rabbitmq.client.Envelope;
//
//public class ConsumerTest {
//	static String TOPIC_NAME = "test_topic";
//	static String QUEUE_NAME = "all_tp";
//	static String EXCHANGE_TYPE = "topic";
//	static boolean durable = true;//持久化
//	static boolean exclusive=false; 
//	static boolean autoDelete=false;
//	
//	public static void main(String[] argv) throws Exception {
//
//		ConnectionFactory factory = new ConnectionFactory();
//		factory.setUsername("guest");
//		factory.setPassword("guest");
//		// factory.setVirtualHost(5672);
//		factory.setHost("123.59.72.126");
//		factory.setPort(5672);
//		Connection conn = factory.newConnection();
//		Channel channel = conn.createChannel();
//
//		channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, null);
//		channel.queueBind(QUEUE_NAME, TOPIC_NAME,"#");
//		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//		channel.basicQos(10, true);
//		final Consumer consumer = new DefaultConsumer(channel) {
//			public void handleDelivery(String consumerTag, Envelope envelope,
//					AMQP.BasicProperties properties, byte[] body)
//					throws IOException {
//				String routingKey = envelope.getRoutingKey();
//				String contentType = properties.getContentType();
//				long deliveryTag = envelope.getDeliveryTag();
//				InvestRank iv = new InvestRank();
//				try {
//					Proto.de.deserialize(iv, body);
//					System.out.print("suc body:");
//					System.out.println(body);
//					System.out.println("topic_q:" + iv.getLoginName() +"|" + iv);
//					this.getChannel().basicAck(deliveryTag, false);
//				} catch (TException e) {
//					System.out.print("err body:");
//					System.out.println(body);
//					e.printStackTrace();
//					this.getChannel().basicNack(deliveryTag, false, true);
//				}
//				System.out.println(Thread.currentThread().getName());
//				
//			}
//		};
//		
//		channel.basicConsume(QUEUE_NAME, false, consumer);
//	}
//
//}
