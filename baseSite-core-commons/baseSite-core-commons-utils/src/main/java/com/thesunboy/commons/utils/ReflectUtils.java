/**
 * 
 */
package com.thesunboy.commons.utils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * 反射方面的工具包
 * 
 * @author hx940929
 * @CreateDate 2016-3-25 - 下午3:48:11
 * @ModifiedDate 2016-3-25 - 下午3:48:11
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public class ReflectUtils
{
	private static final MyLogger logger = MyLogFactory.getLogger(ReflectUtils.class, LogImplEnum.ConsoleLogImpl);

	/**
	 * 使用bean对象中的set方法.将map集合中的数据设置进bean对象中,成功设置一项则从map中移除该项
	 * 
	 * @param bean
	 *            要被设置的bean
	 * @param mapData
	 *            数据来源
	 * @param autoCapital
	 *            对于变量对应的set方法,是否变量名首字母自动大写
	 * @return 返回成功设置到bean对象的数量
	 * @Author hx940929
	 * @Date 2016-3-25 - 下午7:34:39
	 */
	public static int putDataToBean(final Object bean, final Map<String, ? extends Object> mapData, boolean autoCapital)
	{
		Class<?> clazz = bean.getClass();
		int SuccessCount = 0;
		// 使用bean中的数据结构遍历,成功设置一条,就移除一条,逻辑调用开发人员负责,判断该map是否还有值未能成功从map中解析至bean对象,另行做特殊处理
		// TODO 另一个版本的写法
		String fiedName = null;
		for (Field item : clazz.getDeclaredFields())
		{
			fiedName = item.getName();
			if(mapData.containsKey(fiedName))
			{
				try
				{
					Method targetMethod = null;
					if(autoCapital)
					{
						char c = fiedName.charAt(0);
						fiedName = c >= 'a' && c <= 'z' ? (c -= 32) + fiedName.substring(1, fiedName.length())
								: fiedName;
						targetMethod = clazz.getDeclaredMethod("set" + fiedName, item.getType());
					}
					else
					{
						targetMethod = clazz.getDeclaredMethod("set" + fiedName, item.getType());
					}
					targetMethod.invoke(bean, mapData.get(item.getName()));
					mapData.remove(item.getName());
					SuccessCount++;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		return SuccessCount;
	}

	/**
	 * 使用bean对象中的字段.将map集合中的数据设置进bean对象中,成功设置一项则从map中移除该项
	 * 
	 * @param bean
	 *            要被设置的bean
	 * @param mapData
	 *            数据来源
	 * @return
	 * @Author hx940929
	 * @Date 2016-3-29 - 下午2:18:32
	 */
	public static int putDataToBean(final Object bean, final Map<String, ? extends Object> mapData)
	{
		Class<?> clazz = bean.getClass();
		int SuccessCount = 0;
		for (Field item : clazz.getDeclaredFields())
		{
			try
			{
				ReflectUtils.writeField(item, bean, mapData.get(item.getName()), false);
			}
			catch (IllegalAccessException e)
			{
				logger.error("请描述异常信息", e, e.getClass());
			}
		}
		return SuccessCount;
	}

	/**
	 * 使用反射为对象中的字段属性赋值
	 * 
	 * @param field
	 * @param target
	 * @param value
	 * @param forceAccess
	 *            如果该字段为private,是否自动使用setAccessible方法获得访问权限
	 * @throws IllegalAccessException
	 * @Author hx940929
	 * @Date 2016-3-29 - 下午2:17:11
	 */
	public static void writeField(Field field, Object target, Object value, boolean forceAccess)
			throws IllegalAccessException
	{
		if(field == null) { throw new IllegalArgumentException("The field must not be null"); }
		if(forceAccess && !field.isAccessible())
		{
			field.setAccessible(true);
		}
		else
		{
			// MemberUtils.setAccessibleWorkaround(field);
		}
		field.set(target, value);
	}

	/**
	 * 把bean对象中的数据,设置进map集合中,入参的线程安全性由外部保证
	 * 
	 * @param map
	 *            要被设置的map,如果为null,抛出异常//则不会创建新的对象,因为要返回一个int类型的值
	 * @param bean
	 * @return
	 * @Author hx940929
	 * @Date 2016-3-26 - 下午11:53:56
	 */
	public static int putDataToMap(final Map<Object, Object> map, Object bean)
	{
		// ArgsUtil.notNull(bean, "BeanObject ");
		// ArgsUtil.notNull(map, "Map");

		return 0;
	}

	/**
	 * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz, final int index)
	{

		Type genType = clazz.getGenericSuperclass();

		if(!(genType instanceof ParameterizedType))// 判断该类是否是参数化的泛型类
		{
			logger.warn("该类没有实现父类的参数化泛型对象,或者该super类不是一个有效的参数化泛型类:=>" + clazz.getSimpleName());
			return Object.class;
		}
		// 获得泛型参数类列表
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		// 判断index是否超出数组索引
		if(index >= params.length || index < 0)
		{
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if(!(params[index] instanceof Class))
		{
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}

	/**
	 * 使用反射调用bean对象的set方法,为对象赋值
	 * 
	 * @param clazz
	 *            bean对象的class
	 * @param FieldName
	 *            要设置的变量名,如果变量名第一个字母是小写,需要调用者自行保证变量名第一个字母为大写,比如
	 *            要为一个变量name赋值,调用其set的方法
	 *            ,而该方法名为setName.此方法并不会对该变量第一个字母做大写处理,因此会找不到该方法
	 *            .抛出nosuchMethod异常,
	 *            如果需要自动转换大写,请换另一个方法,或者自己约束.(为了性能考虑.此方法是为,约定好的行为适用)
	 * @param bean
	 *            bean对象
	 * @param data
	 *            要设置的数据
	 * @return 返回是否成功
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @Author hx940929
	 * @Date 2016-3-31 - 下午4:26:26
	 */
	public static boolean invokeSetMethod(Class clazz, String FieldName, Object bean, Object data) throws Exception
	{
		@SuppressWarnings("unchecked")
		Method targetMethod = clazz.getDeclaredMethod("set" + FieldName, data.getClass());
		targetMethod.invoke(bean, data);
		return true;
	}

	/**
	 * 调用该对象的get方法(只限于调用无参数的方法),用于获取该对象的一些基本信息,一般用于调试对象时使用的
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2016-5-14 - 下午8:20:38
	 */
	public static String invokeGetMethod(Object bean, StringBuilder builder)
	{
		ArgsUtil.notNull(bean, "Bean Object");
		Class clazz = bean.getClass();
		Method[] methods = clazz.getMethods();
		builder = builder != null ? builder : new StringBuilder();
		for (Method method : methods)
		{ /* && method.getModifiers() == Method.PUBLIC */
			if(method.getName().startsWith("get") && method.getParameterTypes().length == 0)
			{
				Object object = null;
				try
				{
					object = method.invoke(bean);
				}
				catch (Exception e)
				{
					logger.error("使用invokeGetMethod方法进行反射调用方法[ " + method.getName() + " ]发生异常", e, e.getClass());
				}
				builder.append("调用方法: [" + clazz.getName() + "].[" + method.getName() + "]=>结果是: "
						+ (object != null ? object.toString() : "") + "\r\n");

			}
		}
		return builder.toString();
	}

	/**
	 * 获得包下面的所有的class
	 * 
	 * @param pack
	 *            package完整名称
	 * @return List包含所有class的实例
	 * @author http://www.tuicool.com/articles/y2eqem
	 */
	public static List<Class> getClasssFromPackage(String pack)
	{
		List<Class> clazzs = new ArrayList<Class>();

		// 是否循环搜索子包
		boolean recursive = true;

		// 包名字
		String packageName = pack;
		// 包名对应的路径名称
		String packageDirName = packageName.replace('.', '/');

		Enumeration<URL> dirs;

		try
		{
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements())
			{
				URL url = dirs.nextElement();

				String protocol = url.getProtocol();

				if("file".equals(protocol))
				{
					// System.out.println("file类型的扫描" + url.toString());
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findClassInPackageByFile(packageName, filePath, recursive, clazzs);
				}
				else if("jar".equals(protocol))
				{
					System.out.println("jar类型的扫描");
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return clazzs;
	}

	/**
	 * 在package对应的路径下找到所有的class
	 * 
	 * @param packageName
	 *            package名称
	 * @param filePath
	 *            package对应的路径
	 * @param recursive
	 *            是否查找子package
	 * @param clazzs
	 *            找到class以后存放的集合
	 * @author http://www.tuicool.com/articles/y2eqem
	 */
	public static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive,
			List<Class> clazzs)
	{
		File dir = new File(filePath);
		if(!dir.exists() || !dir.isDirectory()) { return; }
		// 在给定的目录下找到所有的文件，并且进行条件过滤
		File[] dirFiles = dir.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File file)
			{
				boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
				boolean acceptClass = file.getName().endsWith("class");// 接受class文件
				return acceptDir || acceptClass;
			}
		});

		for (File file : dirFiles)
		{
			if(file.isDirectory())
			{
				findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
			}
			else
			{
				String className = file.getName().substring(0, file.getName().length() - 6);
				try
				{
					clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param name
	 *            set方法的方法名
	 * @param props
	 *            bean对象的描述数组,该数组中存放着该bean对象的结构信息==>字段名-字段类型-get方法的结构</br＞
	 *            <b>比如:</b></br>
	 *            <p>
	 *            字段: String UserName;</br>其PropertyDescriptor对象为:
	 *            java.beans.PropertyDescriptor[name=UserName;
	 *            propertyType=String java.lang.String; readMethod=public String
	 *            全限定包名.getUserName()]
	 *            </p>
	 * @return
	 * @Author 来源开源框架Quratz-2.2.1 StdSchedulerFactory.java
	 * @Date 2016-4-8 - 下午4:28:58
	 */
	@SuppressWarnings("unused")
	private Method getSetMethod(String name, PropertyDescriptor[] props)
	{
		for (int i = 0; i < props.length; i++)
		{
			java.lang.reflect.Method wMeth = props[i].getWriteMethod();
			if(wMeth != null && wMeth.getName().equals(name)) { return wMeth; }
		}

		return null;
	}

	/**
	 * 暂时不可用 ,还没研究
	 * 
	 * @param obj
	 * @param props
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 * @throws IntrospectionException
	 * @throws SchedulerConfigException
	 * @Author 来源开源框架Quratz-2.2.1 StdSchedulerFactory.java
	 * @Date 2016-4-8 - 下午4:43:17
	 */
	/*
	 * private void setBeanProps(Object obj, Properties props) throws
	 * NoSuchMethodException, IllegalAccessException,
	 * java.lang.reflect.InvocationTargetException, IntrospectionException,
	 * SchedulerConfigException { props.remove("class");
	 * 
	 * BeanInfo bi = Introspector.getBeanInfo(obj.getClass());
	 * PropertyDescriptor[] propDescs = bi.getPropertyDescriptors();
	 * PropertiesParser pp = new PropertiesParser(props);
	 * 
	 * java.util.Enumeration<Object> keys = props.keys(); while
	 * (keys.hasMoreElements()) { String name = (String) keys.nextElement();
	 * String c = name.substring(0, 1).toUpperCase(Locale.US); String methName =
	 * "set" + c + name.substring(1);
	 * 
	 * java.lang.reflect.Method setMeth = getSetMethod(methName, propDescs);
	 * 
	 * try { if(setMeth == null) { throw new NoSuchMethodException(
	 * "No setter for property '" + name + "'"); }
	 * 
	 * Class<?>[] params = setMeth.getParameterTypes(); if(params.length != 1) {
	 * throw new NoSuchMethodException( "No 1-argument setter for property '" +
	 * name + "'"); }
	 * 
	 * // does the property value reference another property's value? // If so,
	 * swap to look at its value //
	 * 这段代码的意思是:如果这个properties的key的值是$@这个开头的,说明这个值也是引用一个其他地方的值
	 * ,是一个引用关系,将会取出这个value作为key来找出其真正要的value PropertiesParser refProps = pp;
	 * String refName = pp.getStringProperty(name); if(refName != null &&
	 * refName.startsWith("$@")) { refName = refName.substring(2); // refProps =
	 * cfg;此处的这个cfg就是自己配置的quartz的properties配置文件 //
	 * 他这里也就是说将从这个properties文件进行拿出真正的引用的值了. // 此处的写法值得借鉴学习 } else refName =
	 * name;
	 * 
	 * if(params[0].equals(int.class)) { setMeth.invoke(obj, new Object[] {
	 * Integer.valueOf(refProps .getIntProperty(refName)) }); } else
	 * if(params[0].equals(long.class)) { setMeth.invoke(obj, new Object[] {
	 * Long.valueOf(refProps .getLongProperty(refName)) }); } else
	 * if(params[0].equals(float.class)) { setMeth.invoke(obj, new Object[] {
	 * Float.valueOf(refProps .getFloatProperty(refName)) }); } else
	 * if(params[0].equals(double.class)) { setMeth.invoke(obj, new Object[] {
	 * Double.valueOf(refProps .getDoubleProperty(refName)) }); } else
	 * if(params[0].equals(boolean.class)) { setMeth.invoke(obj, new Object[] {
	 * Boolean.valueOf(refProps .getBooleanProperty(refName)) }); } else
	 * if(params[0].equals(String.class)) { setMeth.invoke( obj, new Object[] {
	 * refProps.getStringProperty(refName) }); } else { throw new
	 * NoSuchMethodException( "No primitive-type setter for property '" + name +
	 * "'"); } } catch (NumberFormatException nfe) { throw new
	 * SchedulerConfigException("Could not parse property '" + name +
	 * "' into correct data type: " + nfe.toString()); } } }
	 */

}
