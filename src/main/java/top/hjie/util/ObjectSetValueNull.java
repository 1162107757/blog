package top.hjie.util;

import java.lang.reflect.Field;

public class ObjectSetValueNull {
	@SuppressWarnings("rawtypes")
	public static Object changeToNull(Object o) {
		Class c = o.getClass();
		Class sc = c.getSuperclass();
		if (null != sc) {
			recursionNull(sc, o);
		}
		try {
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				String st = f.get(o) + "";
				String str = st.replaceAll(" ", "");
				if (str.equals("") || str == null || str.equals("null")) {
					f.set(o, null);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 判断是否有超类,若有则继续将超类的空值置空
	 * 
	 * @param sc
	 * @param o
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object recursionNull(Class sc, Object o) {
		Class c = sc.getSuperclass();
		if (null != c) {
			recursionNull(c, o);
		}
		try {

			Field[] fs = sc.getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				String st = f.get(o) + "";
				String str = st.replaceAll(" ", "");
				if (str.equals("") || str == null || str.equals("null")) {
					f.set(o, null);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
}
