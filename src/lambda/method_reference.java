package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

/*
 * Java8--方法引用
 * */

public class method_reference {
	
	//原函数
	@Test
	public void test1() {
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String t) {
				// TODO Auto-generated method stub
				System.out.println(t);
			}
		};
		consumer.accept("王五");
	}
	
	//对象 :: 方法
	@Test
	public void test2 (){
		//函数式接口
		Consumer<String> consumer = str -> System.out.println(str);
		consumer.accept("张三");
		//对象 :: 方法
		Consumer<String> consumer2 = System.out::println;
		consumer2.accept("李四");
	}
		
	//类 :: 静态方法
	@Test
	public void test3() {
		Comparator<Integer> comparator = Integer :: compare;
		System.out.println(comparator.compare(1, 2));
	}
	
	//类 :: 静态方法 之 Function 中 R apply(T t)
	@Test
	public void test4() {
		//函数式接口
		Function<Double, Long> function = t -> Math.round(t);
		System.out.println(function.apply(111.1));
		//类 :: 静态方法
		Function<Double, Long> function2 = Math::round;
		System.out.println(function2.apply(222.2));
	}
	
	//类 :: 实例方法
	@Test 
	public void test5() {
		//函数式接口
		Comparator<String> comparator = (t1,t2) -> t1.compareTo(t2);
		System.out.println(comparator.compare("abd", "abc"));
		//类 :: 实例方法
		Comparator<String> comparator2 = String::compareTo;
		System.out.println(comparator2.compare("abc", "abd"));
	}
	
	//构造器引用
	@Test
	public void test6() {
		//函数式接口
		BiFunction<String, Integer, Employee> biFunction = (name,sex) -> new Employee(name, sex);
		System.out.println(biFunction.apply("Tom", 1));
		//构造器引用
		BiFunction<String, Integer, Employee> biFunction2 = Employee :: new;
		System.out.println(biFunction2.apply("Scott", 0));
	}
	
	//数组引用
	@Test
	public void test7() {
		//函数式接口
		Function<Integer, String[]> function = length -> new String[length];
		System.out.println(Arrays.toString(function.apply(5)));
		//数组引用
		Function<Integer, String[]> function2 = String[] :: new;
		System.out.println(Arrays.toString(function2.apply(6)));
	}
}
