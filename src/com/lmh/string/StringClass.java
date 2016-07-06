package com.lmh.string;

import java.util.Arrays;

/**
 * 字符串类相关的一些源码笔记
 * 1.String类是一个final类，所以该类不能被继承。
 * 2.String类实现了序列号接口Serializable,比较接口Comparable,以及字符序列接口CharSequence
 * 3.String底层实现的结果是数组，由一个数组value对字符进行操作。
 * 4.String类有三个属性字段，分别是offset，相对首字符第一个字符被引用的偏移值；count该字符串中字符的总数；
 * 	 hash当前字符的hash值，默认值为0。
 * @author Administrator
 *
 */
public class StringClass {
	
	public final static String TEST_CODE = "123456";
	public final static Integer OFF_SET = 0;

	public static void main(String[] args) {
		//使用默认的0值来创建一个字符串
		String test = new String();	
		
		//如果给定了字符串进行创建，实际是对形参字符串进行拷贝生成。
		test = new String(TEST_CODE); 
		
		//从偏移量开始，取指定长度的字符串
		test = new String(TEST_CODE.toCharArray(), OFF_SET, TEST_CODE.toCharArray().length);
		
		System.err.println(test.intern());

		int[] array1 = {1,2,3};
		testChangeArray(array1);
		System.err.println(Arrays.toString(array1));
	}
	
	public static void testChangeArray(int[] array){
		array[1] = 8;
		int[] d = {3,2,1};
		array = d;
		array[0] = 10;
		System.err.println(Arrays.toString(array));
	}

	public static final String String1(int[] codePoints, int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }
        // Note: offset or count might be near -1>>>1.
        if (offset > codePoints.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }

        final int end = offset + count;

        // Pass 1: Compute precise size of char[]
        int n = count;
        for (int i = offset; i < end; i++) {
            int c = codePoints[i];
            if (Character.isBmpCodePoint(c))
                continue;
            else if (Character.isValidCodePoint(c)){
            	System.err.println("isValidCodePoint");
                n++;
            }else throw new IllegalArgumentException(Integer.toString(c));
        }

        // Pass 2: Allocate and fill in char[]
        final char[] v = new char[n];

        for (int i = offset, j = 0; i < end; i++, j++) {
            int c = codePoints[i];
            if (Character.isBmpCodePoint(c))
                v[j] = (char)c;
            else{
            	System.err.println("Allocate");
            	toSurrogates(c, v, j++);
            }
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < v.length; i++) {
			buffer.append(v[i]).append(",");
		}
        return buffer.toString();
    }
	
	public static void toSurrogates(int codePoint, char[] dst, int index) {
        // We write elements "backwards" to guarantee all-or-nothing
        dst[index+1] = (char) ((codePoint & 0x3ff) + '\uDC00');
        dst[index] = (char) ((codePoint >>> 10)
                + ('\uD800' - (0x010000 >>> 10)));
    }
}

