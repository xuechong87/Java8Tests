package org.luckystars.java8tests;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalLong;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.RunnerException;

public class AboutStream {
	static List<Integer> intList =
			Stream.generate(()->(int)(Math.random()*100)).limit(100).collect(Collectors.toList());
//	Stream.generate(()->(int)(Math.random()*100)).limit(10000000).collect(Collectors.toList());
	
	public static void main(String[] args) throws RunnerException {
//		sun.nio.ch.FileChannelImpl;
//		 Options opt = new OptionsBuilder()
//         .include(AboutStream.class.getSimpleName())
//         .warmupIterations(10)
//         .measurementIterations(20)
//         .forks(1)
//         .build();
//
//		new Runner(opt).run();
//		processingOrder2();
	}
	
	/**
	 * 执行顺序为 : <br>
	 * filter: d2 <br>
	 * forEach: d2<br>
	 * filter: a2<br>
	 * forEach: a2<br>
	 * filter: b1<br>
	 * forEach: b1<br>
	 * filter: b3<br>
	 * forEach: b3<br>
	 * filter: c<br>
	 * forEach: c<br>
	 * 
	 * @author xc
	 */
	@Test
	public  void processingOrder(){
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .filter(s -> {
	        System.out.println("filter: " + s);
	        return true;
	    })
	    .forEach(s -> System.out.println("forEach: " + s));
	}
	
	/**
	 * 并行流会乱序执行
	 * @author xc
	 */
	@Test
	public void processingOrder2(){
		IntStream.range(1, 50).parallel()
	    .filter(s -> {
	        System.out.println("filter: " + s);
	        return true;
	    })
	    .forEach(s -> System.out.println("forEach: " + s));
	}
	
	@Test
	public void reuse(){
		Stream<String> st = Stream.of("d2", "a2", "b1", "b3", "c");
		st.forEach(System.out::println);
		st.forEach(System.out::println);
	}
	
	//并行流的效率问题
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public OptionalLong commonStream(){
		return intList.stream().mapToLong(AboutStream::getLong).reduce(Math::addExact);
		
	}
	
	//并行流的效率问题
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public OptionalLong parallelStream(){
		return  intList.parallelStream().mapToLong(AboutStream::getLong).reduce(Math::addExact);
	}
	
	static long getLong(int x){
//		return (long)x;
		String str = String.valueOf(x);
		return new BigDecimal(str).abs().longValue();
	} 
	
}
