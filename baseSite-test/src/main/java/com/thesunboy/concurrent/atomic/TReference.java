/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.concurrent.atomic;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * <b>功能说明:</b><p>
 *一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p> 
 *一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
</p>
 * @author hx940929
 * @CreateDate 2017-8-10 - 下午7:30:36 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TReference
{
	private final static MyLogger logger = MyLogFactory.getLogger(TReference.class, LogImplEnum.ConsoleLogImpl);
	private Integer integer = new Integer(0);
	private final AtomicReference<Integer> atomicinteger = new AtomicReference<>(integer);

	private final StringBuilder mBuilder = new StringBuilder("init");
	private final AtomicReference<StringBuilder> atomicbuilder = new AtomicReference<>(mBuilder);

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 测试atomicinteger 实例所引用的对象是否在integer重新设置实例之后保持有效的应用
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @Author hx940929
	 * @Date 2017-8-10 - 下午7:34:02
	 */
	private void test1()
	{
		Integer initInteger = atomicinteger.get();
		logger.info("test1()==>初始值: " + initInteger);
		Integer newInteger = 1234;
		integer = 45678;
		boolean casResult = atomicinteger.compareAndSet(initInteger, newInteger);
		Integer changedInteger2 = atomicinteger.get();
		logger.info("test1()==>改变原子对象之后的值: " + changedInteger2+"   变更是否成功: "+casResult); 
		logger.info("test1()==>原生intger值: " + integer);
	}

	private void test2()
	{
		StringBuilder builder = atomicbuilder.get();
		logger.info("test1()==>初始值: " + builder.toString());
		StringBuilder newbuilder = new StringBuilder("changed");
		this.mBuilder.append("修改builder");
		boolean casResult = atomicbuilder.compareAndSet(builder, newbuilder);
		StringBuilder changedbuilder = atomicbuilder.get();
		logger.info("test1()==>改变原子对象之后的值: " + changedbuilder + "   变更是否成功: " + casResult);
		logger.info("test1()==>原生builder值: " + builder);
	}

	public static void main(String[] args) throws Exception
	{
		TReference tReference = TReference.class.newInstance();
		Method test = TReference.class.getDeclaredMethod("test2");
		test.invoke(tReference);
		logger.info("main()==>main-end");
	}

}
