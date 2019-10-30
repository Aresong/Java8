package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;
import lambda.Employee;
import lambda.EmployeeData;

/*
 * StreamAPI 创建
 * 
 * 1.Stream关注的是对数据的运算，与cpu打交道；
 * 	集合关注的是数据的存储，与内存打交道；
 * 
 * 2.
 * 	1）Stream 自己不会存储元素
 * 	2）Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * 	3）Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才会执行。
 * 
 * 3.Stream 执行流程
 * 	1）Stream的实例化
 * 	2）一系列的中间操作（过滤、映射…）
 * 	3）终止操作
 * 
 * 4.说明
 * 	1）一个中间操作链，对数据源的数据进行处理
 * 	2）一旦执行终止操作，就会执行中间操作链，并产生结果。之后，不会在被使用。
 * */

public class StreamAPI_Create {
	
	//创建 Stream 方式一：通过集合
	@Test
	public void test1() {
		
		List<Employee> employees = new EmployeeData().getEmployees();
		//default Stream<E> stream() ：返回一个顺序流
		Stream<Employee> stream = employees.stream();
		//default Stream<E> parallelStream() ：返回一个并行流
		Stream<Employee> stream2 = employees.parallelStream();
		
	}
	
	//创建 Stream 方式二：通过数组
	@Test
	public void test2() {
		
		int[] arr = new int[] {1,2,3,4,5,6};
		//调用Arrays类的static <T> stream<T> stream(T[] array)：返回一个流
		IntStream intStream = Arrays.stream(arr);
		
		Employee employee1 = new Employee(1L, "Tom", 1);
		Employee employee2 = new Employee(2L, "Scott", 0);
		Employee[] employees = new Employee[] {employee1,employee2};
		Stream<Employee> employeeStream = Arrays.stream(employees);
		
	}
	
	//创建 Stream 方式三：通过Stream的of()
	@Test
	public void test3() {
		Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
	}
	
	//创建 Stream 方式四 ：创建无限流
	@Test
	public void test4() {
		
		//迭代
		//public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
		//遍历前10个数
		Stream.iterate(0, t -> t+2).limit(10).forEach(System.out :: println);
		
		//生成
		//public static<T> Stream<T> generate(Supplier<T> s)
		Stream.generate(Math :: random).limit(10).forEach(System.out :: println);
		
	}
	
}
