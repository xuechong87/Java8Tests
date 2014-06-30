package org.luckystars.java8tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;


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
public class CollectionStream {
	//for test
	static List<Content> _list = null;
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
	}
	
	static void streamOp(){
		Content result = null;
		Content theMax = _list.stream()
		.filter(c->c.getNum()<10)
		.reduce(result,(r,e)->{return r!=null && r.getNum()>e.getNum()?r:e;});
		System.out.println(theMax.getName());
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
