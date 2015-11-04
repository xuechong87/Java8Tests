//package org.luckystars.rabbitmq.exchange;
//
//import java.util.function.Consumer;
//
//import com.coamctech.eastlending.activity.thrift.model.InvestRank;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//
//
//
//public class Sender {
//	static String TOPIC_NAME = "test_topic";
//	static String EXCHANGE_TYPE = "topic";
//	static boolean durable = true;//持久化
//	static boolean exclusive=false; 
//	static boolean autoDelete=false;
//	
//	public static void main(String[] args) throws Throwable {
//		ConnectionFactory factory = new ConnectionFactory();
//		factory.setUsername("guest");
//		factory.setPassword("guest");
////		factory.setVirtualHost(5672);
//		factory.setHost("123.59.72.126");
//		factory.setPort(5672);
//		factory.setAutomaticRecoveryEnabled(true);
//		factory.setConnectionTimeout(10000);
//		factory.setRequestedChannelMax(0);//zero for unlimited
//		
//		Connection conn = factory.newConnection();
//		
//		
//		final Consumer<String> sendMsg = x->{
//			new Thread(()->{
//				try {
//					for (int i = 0; i < 10; i++) {
//						Channel channel = conn.createChannel();
//						channel.exchangeDeclare(TOPIC_NAME, EXCHANGE_TYPE, durable, autoDelete, null);
//				    	String message ="thread:" + x +" send" + System.currentTimeMillis();
//				    	InvestRank iv = new InvestRank();
//				    	iv.setLoginName(message);
//					    channel.basicPublish(TOPIC_NAME, x, null, Proto.getSer().serialize(iv));
//					    System.out.println(" [x] Sent '" + message + "'");
//					    int max = channel.getConnection().getChannelMax();
//					    System.out.print(max);
//					    channel.close();
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}).start();
//		};
//		
//		
//		
//	    for (int i = 0; i < 2; i++) {
//	    	sendMsg.accept(i+"");
//		}
//	    
//	    
//	}
//}
