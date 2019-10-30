package Optional;

import java.util.Optional;

import org.junit.Test;

/**
 * Optional类
 * 
 * 为了避免程序中出现空指针异常而创建的。
 * 常用方法：ofNullable
 * 
 * **/

public class OptionalClass {

	/*Optional.of(T t)，创建一个 Optional 实例，t必须非空*/
	/*Optional.empty()，创建一个空的 Optional 实例*/
	/*Optional.ofNullable(T t)：t可以为null*/
	@Test
	public void test1() {
		
		Girl girl = new Girl();
		//girl = null;//of不能传空指针对象
		Optional<Girl> optionalGirl = Optional.of(girl);
		
	}
	
	@Test
	public void test2() {
		
		Girl girl = new Girl();
		girl = null;
		Optional<Girl> optionalGirl = Optional.ofNullable(girl);
		System.out.println(optionalGirl);
		/*orElse*/
		//如果当前的optional内部封装的t是非空的，则返回内部的t
		//如果当前的t是空的，则返回orElse方法中的的参数
		Girl orElseGirl = optionalGirl.orElse(new Girl("mm"));
		System.out.println(orElseGirl);
		
	}
	
	//没有Optional类的时候
	public String getGirlName1(Boy boy) {
		
		if(boy != null) {
			Girl girl = boy.getGirl();
			if(girl != null) {
				return girl.getName();
			}
		}
		return null;
		
	}
	@Test
	public void test3() {
		
		Boy boy = new Boy();
		boy = null;
		String girlName = getGirlName1(boy);
		System.out.println(girlName);
		
	}
	
	//应用Optional类
	public String getGirlName2(Boy boy) {
		
		Optional<Boy> optionalBoy = Optional.ofNullable(boy);
		Boy orElseBoy = optionalBoy.orElse(new Boy(new Girl("迪丽热巴")));
		Girl girl = orElseBoy.getGirl();
		Optional<Girl> optionalGirl = Optional.ofNullable(girl);
		Girl orElseGirl = optionalGirl.orElse(new Girl("古力娜扎"));
		return orElseGirl.getName();
		
	}
	@Test
	public void test4() {
		
		Boy boy = new Boy();
		boy = null;
		String girlName1 = getGirlName2(boy);
		System.out.println(girlName1);
		
		boy = new Boy();
		String girlName2 = getGirlName2(boy);
		System.out.println(girlName2);
		
		boy = new Boy(new Girl("马尔扎哈"));
		String girlName3 = getGirlName2(boy);
		System.out.println(girlName3);
		
	}

}
