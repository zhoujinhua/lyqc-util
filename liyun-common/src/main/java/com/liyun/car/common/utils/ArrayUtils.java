package com.liyun.car.common.utils;

/**
 * 数组的增删改
 * @author zhoufei
 *
 */
public class ArrayUtils {

	// 增
	public static Object[] add(Object[] array, int num, int index) {
		Object[] result = new Object[array.length + 1];
		for (int i = 0, j = 0; i < array.length; i++, j++) {
			if (j == index) {
				result[j] = num;
				i--;
			} else {
				result[j] = array[i];
			}
		}
		return result;
	}

	// 删
	public static Object[] delete(Object[] array, int index) {
		Object[] result = new Object[array.length - 1];
		for (int i = 0, j = 0; i < array.length; i++, j++) {
			if (i == index) {
				j--;
			} else {
				result[j] = array[i];
			}
		}
		return result;
	}

	// 查
	/*public static void select(Object[] array, Object num) {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == num) {
				System.out.println("index" + i + "的值为" + num);
				count++;
			}
		}
		if (count == 0)
			System.out.println("找不到");
	}*/

	// 改
	public static Object[] change(Object[] array, int index, Object num) {
		array[index] = num;
		return array;
	}

	// 打印数组
	public static void print(Object[] array) {
		for (Object i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
