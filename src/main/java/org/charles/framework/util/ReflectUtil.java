package org.charles.framework.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * 反射工具类
 * @author YeChao
 */
public class ReflectUtil {
	
	/**
	 * 递归向上查找字段
	 * @author YeChao
	 * @param propertyName	属性名称
	 * @param beanClass		要查找的对象
	 * @return
	 */
	public static Field getField(String propertyName, Class<?> beanClass) throws NoSuchFieldException{
		Field field = null;
		while(true){
			//如果已经到Object都找不到该字段，认为就没有该字段了
			if(Object.class.equals(beanClass)){
				break;
			}
			try{
				field = beanClass.getDeclaredField(propertyName);
				break;
			}catch(NoSuchFieldException e){
				//取父类，看父类有没有该字段
				beanClass = beanClass.getSuperclass();
			}
		}
		if(field == null){
			throw new NoSuchFieldException();
		}
		return field;
	}
	
	/**
	 * 通过属性名获取set方法
	 * @author YeChao
	 * @param propertyName	属性名称
	 * @param beanClass		对象
	 * @return
	 * @throws NoSuchMethodException
	 */
	public static Method getWriteMethod(String propertyName, Class<?> beanClass) 
			throws NoSuchFieldException, NoSuchMethodException{
		Field field = null;
		while(true){
			if(Object.class.equals(beanClass)){
				break;
			}
			try{
				field = beanClass.getDeclaredField(propertyName);
				break;
			}catch(NoSuchFieldException e){
				beanClass = beanClass.getSuperclass();
			}
		}
		if(field == null){
			throw new NoSuchFieldException();
		}
		return beanClass.getDeclaredMethod("set" + StringUtil.toFirstUpperCase(field.getName()), field.getType());
	}

	/**
	 * 给指定对象属性设置参数（该方法只遵循标志JavaBean规范，需要有Get、Set方法）
	 * @author YeChao
	 * @param bean		对象
	 * @param propertyName	属性名称（区分大小写）
	 * @param propertyValue	属性值
	 * @throws IntrospectionException		无该属性或者无GET/SET方法抛出
	 * @throws IllegalAccessException	set方法不是public抛出
	 * @throws InstantiationException	实例化属性名对应的对象出错
	 * @throws InvocationTargetException	set方法内部抛出的异常
	 */
	public static void setFieldValue(Object bean, String propertyName,  Object propertyValue)
		throws IntrospectionException, IllegalAccessException,
		InstantiationException, InvocationTargetException{
		String curFieldName = null;
		String subFieldName = null;
		
		int slash = propertyName.indexOf(".");
		if(slash != -1){
			curFieldName = propertyName.substring(0, slash);
			subFieldName = propertyName.substring(slash + 1);
		}else{
			curFieldName = propertyName;
		}
		
		Class<?> beanClass = bean.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(curFieldName, beanClass);
		
		Class<?> propertyType = pd.getPropertyType();
		Method writeMethod = pd.getWriteMethod();
		Method readMethod = pd.getReadMethod();
		
		if(subFieldName != null){
			Object entity = readMethod.invoke(bean);
			if(entity == null){
				entity = propertyType.newInstance();
				writeMethod.invoke(bean, entity);
			}
			setFieldValue(entity, subFieldName, propertyValue);
		}else{
			writeMethod.invoke(bean, convertType(propertyValue, propertyType));
		}
	}
	
	/**
	 * 获取指定对象属性值（该方法只遵循标志JavaBean规范，需要有Get、Set方法）
	 * @param bean		对象
	 * @param propertyName	属性名称（区分大小写）
	 * @return	属性值
	 * @throws IntrospectionException		无该属性或者无GET/SET方法抛出
	 * @throws IllegalAccessException	get方法不是public抛出
	 * @throws InstantiationException	实例化属性名对应的对象出错
	 * @throws InvocationTargetException	get方法内部抛出的异常
	 */
	public static Object getFieldValue(Object bean, String propertyName)
		throws IntrospectionException, IllegalAccessException,
		InstantiationException, InvocationTargetException{
		
		Class<?> beanClass = bean.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, beanClass);
		
		Method readMethod = pd.getReadMethod();
		
		return readMethod.invoke(bean);
	}
	
	/**
	 * 类型转换，目前只支持String转Number、Number转String，其余的不作转换直接返回src对象
	 * @author YeChao
	 * @param src	需要转换的对象
	 * @param clazz	转换后期望的类型
	 * @return		转换后对象
	 */
	public static Object convertType(Object src, Class<?> clazz){
		
		if(src instanceof String){
			String data = (String) src;
			
			if(int.class == clazz || Integer.class == clazz){
				try{
					return Integer.valueOf(data);
				}catch(NumberFormatException e){
					return 0;
				}
			}else if(long.class == clazz || Long.class == clazz){
				try{
					return Long.valueOf(data);
				}catch(NumberFormatException e){
					return 0L;
				}
			}else if(double.class == clazz || Double.class == clazz){
				try{
					return Double.valueOf(data);
				}catch(NumberFormatException e){
					return 0.0;
				}
			}else if(BigDecimal.class == clazz){
				if(StringUtil.isBlank(data)){
					data = "0.00";
				}
				return new BigDecimal(data);
			}else if(java.sql.Date.class == clazz || java.util.Date.class == clazz){
				long time = 0L;
				try {
					time = Long.valueOf(data);
					if(time == 0){
						return null;
					}
				} catch (NumberFormatException e) {
					return null;
				}
				return new java.util.Date(time);
			}
		}else if(src instanceof String[]){
			if(String.class == clazz){
				return src.toString();
			}
		}else if(src instanceof Number){
			Number data = (Number) src;
			if(String.class == clazz){
				return data.toString();
			}else if(int.class == clazz || Integer.class == clazz){
				return data.intValue();
			}else if(long.class == clazz || Long.class == clazz){
				return data.longValue();
			}else if(float.class == clazz || Float.class == clazz){
				return data.floatValue();
			}else if(double.class == clazz || Double.class == clazz){
				return data.doubleValue();
			}else if(java.sql.Date.class == clazz || java.util.Date.class == clazz){
				return new java.util.Date(data.longValue());
			}
		}
		return src;
	}
	
}
