package StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import lambda.Employee;
import lambda.EmployeeData;

/*
 * StreamAPI 中间操作
 * */

public class StreamAPI_IntermediateOperatio {
	
	private List<Employee> employees = new EmployeeData().getEmployees();
	
	//1.筛选和切片
	@Test
	public void test1() {
		
		//1）filter——接收 Lambda ， 从流中排除某些元素。
		//Stream<T> filter(Predicate<? super T> predicate)
		//查询性别为男的员工信息（sex : 1）
		System.out.println("filter----------");
		employees.stream().filter(e -> e.getSex() == 1).forEach(System.out::println);
		
		//2）limit——截断流，使其元素不超过给定数量。
		//Stream<T> limit(long maxSize)
		//查询前两个
		System.out.println("limit----------");
		employees.stream().limit(2).forEach(System.out::println);
		
		//3）skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补。
		//Stream<T> skip(long n)
		//跳过前两个查询
		System.out.println("skip----------");
		employees.stream().skip(2).forEach(System.out::println);
		
		//4）distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素。
		//Stream<T> distinct()
		//去除重复元素
		System.out.println("distinct----------");
		employees.add(new Employee(20L, "Alice", 0));
		employees.add(new Employee(22L, "Alice", 0));
		employees.add(new Employee(20L, "Alice", 0));
		employees.stream().distinct().forEach(System.out::println);
		
	}
	
	//2.映射
	@Test
	public void test2() {
		
		//1）map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		//<R> Stream<R> map(Function<? super T, ? extends R> mapper)
		//映射新元素都变大写
		System.out.println("map1_Aa----------");
		List<String> list = Arrays.asList("aa","bb","cc","dd"); 
		list.stream().map(String::toUpperCase).forEach(System.out::println);
		System.out.println("map2_Employee----------");
		System.out.print("查询名字长度小于4的员工名字--");
		employees.stream().map(Employee::getName).filter(str -> str.length() <= 4).forEach(System.out::println);
		System.out.print("查询名字长度小于4的员工对象--");
		employees.stream().filter(e -> e.getName().length() <= 4).forEach(System.out::println);
		System.out.print("查询名字含有‘T’或者‘t’--");
		employees.stream().map(Employee::getName).filter(str -> str.contains("t") || str.contains("T")).forEach(System.out::println);
		System.out.println("字符串转字符map--");
		Stream<Stream<Character>> streamStream = list.stream().map(StreamAPI_IntermediateOperatio::fromStringToChar);
		streamStream.forEach(stream -> stream.forEach(System.out::println));
		//2）mapToDouble：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream。
		//3）mapToLong：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream。
		//4）mapToInt：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream。
		//5）flatmap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。(前提是类的静态方法返回值是小流)
		//<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
		System.out.println("flatmap----------");
		System.out.println("字符串转字符flatmap--");
		list.stream().flatMap(StreamAPI_IntermediateOperatio::fromStringToChar).forEach(System.out::println);
	}
	
	//String 转 字符流
	public static Stream<Character> fromStringToChar(String str){
		List<Character> characters = new ArrayList<Character>();
		char[] arr = str.toCharArray();
		for (char c : arr) {
			characters.add(c);
		}
		return characters.stream();
	}
	
	//3.排序
	@Test
	public void test3() {
		
		//自然排序
		//Stream<T> sorted()
		System.out.println("sorted()----------");
		System.out.println("自然排序--");
		List<Integer> list = Arrays.asList(2,1,3);
		list.stream().sorted().forEach(System.out::println);
		
		//自定义排序
		//Stream<T> sorted(Comparator<? super T> comparator)
		//先根据名字长度排序，名字长度一样根据性别排序
		System.out.println("sorted(Comparator)----------");
		System.out.println("自定义：名字长度正序，性别正序--");
		employees.stream().sorted((e1,e2) -> {
			int i = Integer.compare(e1.getName().length(),e2.getName().length());
			if(i == 0) {
				return Integer.compare(e1.getSex(), e2.getSex());
			}else{
				return i;
			}
		}).forEach(System.out::println);
		//先根据名字长度排序，名字长度一样根据性别倒序排序
		System.out.println("sorted(Comparator)----------");
		System.out.println("自定义：名字长度正序，性别倒序--");
		employees.stream().sorted((e1,e2) -> {
			int i = Integer.compare(e1.getName().length(),e2.getName().length());
			if(i == 0) 
				return -Integer.compare(e1.getSex(), e2.getSex());//倒序
			else
				return i;
		}).forEach(System.out::println);
		
	}
	
}
