package StreamAPI;

import java.util.Arrays;
//import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import org.junit.Test;

import lambda.Employee;
import lambda.EmployeeData;

/*
 * StreamAPI 终止操作
 * */

public class StreamAPI_TerminationOperatio {
	
	private List<Employee> employees = new EmployeeData().getEmployees();
	
	//1.查找与匹配
	@Test
	public void test1() {
		
		//1）allMatch——检查是否匹配所有元素
		//boolean allMatch(Predicate<? super T> predicate)
		//员工是否都为男性（sex：1）
		System.out.println("allMatch----------");
		System.out.println("员工是否都为男性--");
		boolean allMatch = employees.stream().allMatch(e -> e.getSex() == 1);
		System.out.println(allMatch);
		
		//2）anyMatch——检查是否至少匹配一个元素
		//boolean anyMatch(Predicate<? super T> predicate)
		//员工是否存在女性（sex：0）
		System.out.println("anyMatch----------");
		System.out.println("员工是否存在女性--");
		boolean anyMatch = employees.stream().anyMatch(e -> e.getSex() == 0);
		System.out.println(anyMatch);
		
		//3）noneMatch——检查是否没有匹配的元素
		//boolean noneMatch(Predicate<? super T> predicate)
		//员工名字是否没有A开头的
		System.out.println("noneMatch----------");
		System.out.println("员工名字是否没有A开头的--");
		boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("A"));
		System.out.println(noneMatch);
		
		//4）findFirst——返回第一个元素
		//Optional<T> findFirst()
		//第一个员工
		System.out.println("findFirst----------");
		System.out.println("第一个员工--");
		Optional<Employee> employeeFirst = employees.stream().findFirst();
		System.out.println(employeeFirst);
		
		//5）findAny——返回当前流中的任意元素
		//Optional<T> findAny()
		//随意员工
		System.out.println("findAny----------");
		System.out.println("随意员工--");
		Optional<Employee> employeeAny = employees.parallelStream().findAny();//parallelStream
		System.out.println(employeeAny);
				
		//6）count——返回流中元素的总个数
		//long count()
		//男员工个数
		System.out.println("count----------");
		System.out.println("男员工个数员工--");
		long count = employees.stream().filter(e -> e.getSex() == 1).count();
		System.out.println(count);
		
		//7）max——返回流中最大值
		//Optional<T> max(Comparator<? super T> comparator)
		//id最大的员工
		System.out.println("max----------");
		System.out.println("id最大的员工--");
		Optional<Employee> employeeMax = employees.stream().max(
			(e1,e2) -> Long.compare(e1.getId(), e2.getId())
		);
		System.out.println(employeeMax);
		
		//8）min——返回流中最小值
		//Optional<T> min(Comparator<? super T> comparator)
		//id最小的员工
		System.out.println("min----------");
		System.out.println("id最小的员工--");
		Optional<Employee> employeeMin = employees.stream().min(
			(e1,e2) -> Long.compare(e1.getId(), e2.getId())
		);
		System.out.println(employeeMin);
		
//		List<Integer> asList = Arrays.asList(2,20,5,36,64,15,2,1);
//		Optional<Integer> max = asList.stream().max(Integer::compare);
//		System.out.println(max);
		
		//9）foreach——遍历流
		//void forEach(Consumer<? super T> action)
		//遍历（内部迭代）
		System.out.println("stream--forEach----------");
		employees.stream().forEach(System.out::println);
		//遍历，与上面的不一样（外部迭代）
		System.out.println("Iterable--forEach----------");
		employees.forEach(System.out::println);
		
	}
	
	//2.归约
	@Test
	public void test2() {
		
		//reduce(T identity,BinaryOperator)——可以将流中元素反复结合起来，得到一个值
		//T reduce(T identity, BinaryOperator<T> accumulator)
		//1-9的和
		System.out.println("T reduce(T identity, BinaryOperator<T> accumulator)----------");
		System.out.println("1-9的和--");
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
		Integer sum = list.stream().reduce(0,Integer::sum);
		System.out.println(sum);
		
		//reduce(BinaryOperator)——可以将流中元素反复结合起来，得到一个值
		//Optional<T> reduce(BinaryOperator<T> accumulator)
		//员工id和
		System.out.println("Optional<T> reduce(BinaryOperator<T> accumulator)----------");
		System.out.println("员工id和--");
		OptionalLong optionalSum = employees.stream().mapToLong(e -> e.getId()).reduce(Long::sum);
		System.out.println(optionalSum);
		
	}
	
	//3.收集
	@Test
	public void test3() {
		
		//collect()：将流转换成其他形式。接受一个Collector接口的实现，用于给Stream中元素做汇总的方法
		//<R, A> R collect(Collector<? super T, A, R> collector)
		//返回集合列表
		System.out.println("<R, A> R collect(Collector<? super T, A, R> collector)----------");
		System.out.println("返回集合列表--");
		List<Employee> employeeCollect = employees.stream().filter(e -> e.getSex() == 1).collect(Collectors.toList());
		System.out.println(employeeCollect);
		
	}
	
}
