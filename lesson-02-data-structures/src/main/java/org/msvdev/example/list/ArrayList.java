package org.msvdev.example.list;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private Object[] array;
    private int count;

    public ArrayList() {
        array = new Object[1];
        count = 0;
    }

    @Override
    public void append(Object obj) {
        if (count == array.length) {
            Object[] newArray = new Object[2 * count];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[count] = obj;
        count++;
    }

    @Override
    public T get(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void remove(Object obj) {
        int index = 0;
        while (index != count) {
            if (obj.equals(array[index])) {
                System.arraycopy(array, index + 1, array, index, count - index);
                count--;
                break;
            }
            index++;
        }
    }

    @Override
    public String toString() {
        return "ArrayList - " + Arrays.toString(array);
    }
}
