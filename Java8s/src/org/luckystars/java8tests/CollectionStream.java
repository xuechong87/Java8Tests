package org.luckystars.java8tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 * 集合数组的流式操作 {@link java.util.stream.Stream }<br>
 * 新增了java.util.stream包<br><br>
 * <b>{@link java.lang.Iterable} 接口增加了:</b><br><br>
 * {@link Iterable#forEach(Consumer)}<br>
 * {@link Iterable#spliterator()}
 * 
 * <br><br>
 * <b>{@link java.util.Collection} 接口增加了:</b><br><br>
 * {@link Collection#stream()}<br>
 * {@link Collection#parallelStream()}<br><br>
 * 
 * {@link java.util.Arrays}也增加了一些支持流式操作的方法
 * 
 * @author xuechong
 */
@SuppressWarnings("serial")
public class CollectionStream {
	//for test
	static List<Content> _list = null;
	
	static void println(Object o){
		System.out.println(o);
	}
	
	static {
		_list = new ArrayList<Content>(){{
			Predicate<Object[]> addCon = x->{
				Content con = new Content();
				con.setName((String) x[0]);
				con.setNum((Integer) x[1]);
				return add(con);
			};
			for(int i = 0;i<20;i++){
				addCon.test(new Object[]{String.valueOf((char)(i+97)),i});
			}
			
		}};
		
		_list.forEach(c->System.out.println(c.getName() + ":" + c.getNum()));
	}
	
	
	public static void main(String[] args) {
		streamOp();
		origin();
	}
	
	
	
	static void streamOp(){
		Content result = null;
		Content theMax = _list.stream()
		.filter(c->c.getNum()<10)
		.reduce(result,(r,e)->{return r!=null && r.getNum()>e.getNum()?r:e;});
		System.out.println(theMax.getName());
	}
	
	static void streamReduce(){
		Integer totalNum1 = _list.stream()
				.map(Content::getNum).reduce(0, (c1,c2)->c1+c2);
		
		println(totalNum1);
		
		
		Integer totalNum2 = _list.stream()
				.mapToInt(Content::getNum).sum();
		println(totalNum2);
	}
	
	static void origin(){
		Integer times = 1000000;
		originLoop(times);		
		streamLoop(times);
	}
	
	static void originLoop(Integer times){
		Supplier<Long> curTime = System::currentTimeMillis;
		long c1 = curTime.get();
		for (int i = 0; i < times; i++) {
			for (Content content : _list) {
				content.setNum(content.getNum());
			}
		}
		
		long c2 = curTime.get();
		System.out.println("origin" + (c2-c1));
	}
	
	static void streamLoop(Integer times){
		Supplier<Long> curTime = System::currentTimeMillis;
		long c3 = curTime.get();
		for (int i = 0; i < times; i++) {
			_list.parallelStream().forEach(content->content.setNum(content.getNum()));
		}
		long c4 = curTime.get();
		System.out.println("stream" + (c4-c3));
	}
	
	
}

/**
 * for test
 * @author xuechong
 */
class Content {
	private String name;
	private Integer num;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
