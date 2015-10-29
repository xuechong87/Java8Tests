package org.luckystars.java8tests;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalLong;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class AboutStream {
	
	static List<Integer> intList = 
			Stream.generate(()->(int)(Math.random()*100)).limit(100).collect(Collectors.toList());
//	Stream.generate(()->(int)(Math.random()*100)).limit(10000000).collect(Collectors.toList());
	
	public static void main(String[] args) throws RunnerException {
		 Options opt = new OptionsBuilder()
         .include(AboutStream.class.getSimpleName())
         .warmupIterations(10)
         .measurementIterations(20)
         .forks(1)
         .build();

		new Runner(opt).run();
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
