package org.luckystars.rabbitmq.exchange;

import org.apache.thrift.TDeserializer;
import org.apache.thrift.TSerializer;

public class Proto {
	public static TDeserializer de = new TDeserializer();
	public static TSerializer ser = new TSerializer();
	
	public static TDeserializer getDe(){
		return new TDeserializer();
	}
	
	public static TSerializer getSer(){
		return new TSerializer();
	}
}
