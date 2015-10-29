package org.luckystars.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Consumer {

	static boolean durable = true;//持久化
	static boolean exclusive=false; 
	static boolean autoDelete=false;
	
	static String QUEUE_NAME = "hello";
	
	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("rabbit");
		factory.setPassword("rabbit");
		// factory.setVirtualHost(5672);
		factory.setHost("118.192.74.246");
		factory.setPort(5672);
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();

		channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


		final QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true){
						Delivery d = consumer.nextDelivery();
						System.out.println("hello_q:" + new String(d.getBody()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

//		while (true) {
//			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//			String message = new String(delivery.getBody(), "UTF-8");
//
//			System.out.println(" [x] Received '" + message + "'");
//			doWork(message);
//			System.out.println(" [x] Done");
//
//			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//		}
	}

	private static void doWork(String task) throws InterruptedException {
		for (char ch : task.toCharArray()) {
			if (ch == '.')
				Thread.sleep(1000);
		}
	}
	
	
}
