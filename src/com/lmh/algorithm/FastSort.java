package com.lmh.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序算法
 * 
 * @author Administrator
 *
 */
public class FastSort {

	// 待排序的数组
	private static int[] data = new int[10];

	public static void main(String[] args) {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			data[i] = random.nextInt(100);
		}

		System.err.println("排序前：");
		System.err.println(Arrays.toString(data));
		sort(0, data.length - 1);
		System.err.println("排序后：");
		System.err.println(Arrays.toString(data));
	}

	private static int partition(int sortArray[], int low, int hight) {
		int key = sortArray[low];
		while (low < hight) {
			// 从右向左查找第一个比key小的元素，注意这里的等于号，比如要有否则会出现死循环
			while (low < hight && sortArray[hight] >= key)
				hight--;
			sortArray[low] = sortArray[hight];

			// 从左向右查找第一个比key大的元素，注意这里的等于号，比如要有否则会出现死循环
			while (low < hight && sortArray[low] <= key)
				low++;
			sortArray[hight] = sortArray[low];
		}
		sortArray[low] = key;
		return low;
	}

	/**
	 * 快速排序思想： 
	 * 1.选择一个基准元素，基本都是第一个或者最后一个元素 
	 * 2.定义一个变量保存这个选择的基准元素
	 * 3.从右向左(如果选择第一个元素作为基准点的话，相反就是从左往右)找到第一个比基准元素小的元素 
	 * 4.把这个元素跟第一个元素进行交换
	 * 5.从左到右找到第一个比基准元素大的元素，然后把这个元素跟上一步从右向左的元素进行交换 
	 * 6.把2步骤保存的元素值设置到low下标的数组中去
	 * 7.根据返回的low值递归上面的步骤，直到满足递归结束条件为止
	 * 
	 * @param low
	 *            数组开始元素的坐标
	 * @param hight
	 *            数组结束元素的坐标
	 */
	public static void sort(int low, int hight) {
		if (low < hight) {
			//int result = partition(data, low, hight);
			int result = partitions(data, low, hight);
			sort(low, result - 1);
			sort(result + 1, hight);
		}
	}

	public static int partitions(int[] data, int start, int end){
		
		int temp = data[start];
		while(start<end){
			//先找最小的
			while(start<end && data[end]>=temp){
				end--;
			}
			data[start] = data[end];
			
			//再找最大的
			while(end>start && data[start]<=temp){
				start++;
			}
			data[end] = data[start];
		}
		data[start] = temp;
		return start;
	}
}
