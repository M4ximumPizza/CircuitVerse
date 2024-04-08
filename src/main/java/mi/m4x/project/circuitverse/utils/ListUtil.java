/*
 * Copyright (c) 2024, M4ximumPizza and/or its contributors. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is licensed under the PolyForm Shield License 1.0.0; you can use, modify,
 * and distribute this code for any purpose that does not compete with the software or
 * any product the licensor or any of its affiliates provides using the software.
 * M4ximumPizza designates this particular file as subject to the "Classpath" exception
 * as provided by M4ximumPizza in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the PolyForm Shield License 1.0.0 for more details
 * (a copy is included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the PolyForm Shield License 1.0.0 along with this work;
 * if not, here is the license [https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt].
 */

package mi.m4x.project.circuitverse.utils;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class ListUtil<T> {
    private T[] array;
    public ListUtil(T[] array) {
        this.array = array;
    }

    public T get(int index) { return array[index]; }

    public T[] toArray() { return array; }
    public int getLength() {
        return array.length;
    }

    public T[] append(T ...objects) {
        T[] e = (T[]) Array.newInstance(array.getClass().componentType(), array.length+objects.length);
        if (array.length != 0) {
            int Index = 0; for (T item: array) { e[Index] = item; Index++; };
        }
        int sIndex = array.length;
        for (T object: objects) { e[sIndex] = object; sIndex++; }
        array = e;
        return array;
    }

    public T[] reverse() {
        T[] e = (T[]) Array.newInstance(array.getClass().componentType(), array.length);
        if (array.length != 0) {
            int Index = array.length-1; for (T item: array) { e[Index] = item; Index--; };
        }
        array = e; return array;
    }

    public T Get(int index) { return array[index]; }
    
    public T Get3D(int x, int y, int z, int XMax, int YMax) {
        return array[ListUtil._3DCoordsToIndex(x, y, z, XMax, YMax)];
    }

    public void Set(T value, int index) { array[index] = value; }

    public void Set3D(T value, int x, int y, int z, int XMax, int YMax) {
        array[ListUtil._3DCoordsToIndex(x, y, z, XMax, YMax)] = value;
    }
    
    public static int _3DCoordsToIndex(int x, int y, int z, int XMax, int YMax) {
        return (z * XMax * YMax) + (y * XMax) + x;
    }

    public static int[] toIntPrimative(Integer[] array) {

		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].intValue();
		}
		return result;
	}

    public static char[] toCharPrimative(Character[] array) {

		char[] result = new char[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].charValue();
		}
		return result;
	}

    public static long[] toLongPrimative(Long[] array) {

		long[] result = new long[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].longValue();
		}
		return result;
	}

    public static double[] toDoublePrimative(Double[] array) {

		double[] result = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].doubleValue();
		}
		return result;
	}
}
