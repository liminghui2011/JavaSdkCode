package com.lmh.algorithm;

import java.util.Random;

public class InsertSort {

	public static void main(String[] args) {
		Random random = new Random();
		int[] array = new int[10];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(100);
		}
		print(array);
		sort(array);
		print(array);
	}

	/**
	 * 插入排序思想：
	 * 1.假定第i个元素之前的元素都是有序的
	 * 2.从第i个元素开始，拿下标i的元素逐个与i-1之前的元素进行比较，如果比i的值大，则i-1跟i进行值交换
	 * 3.重复2步骤，直到没有元素比i大后，把i的值插入到最后比较的下标上即可。
	 * @param array
	 */
	public static void sort(int[] array){
		int length = array.length;
		for(int i=1; i<length; i++){
			if (array[i-1]>array[i]) {
				int temp = array[i];
				int j = i;
				while (j>0 && array[j-1]>temp) {
					array[j] = array[j-1];
					j--;
				}
				array[j] = temp;
			}
		}
	}
	
	public static void print(int[] array){
		for (int i : array) {
			System.err.print(i+" ");
		}
		System.err.println();
	}
}
