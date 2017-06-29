package com.sapir.stack;

/**
 * Created by i062070 on 28/06/2017.
 */
public class Stack {

    private final int MAX_CAPACITY;
    private int size;
    private int[] values;

    public Stack(int size) {
        MAX_CAPACITY = size;
        values = new int[MAX_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int value) throws OverFlowException {
        if (size == MAX_CAPACITY)
            throw new OverFlowException();
        values[size++] = value;
    }

    public int pop() throws Exception {
        if (isEmpty())
            throw new UnderFlowException();
        return values[--size];
    }


}
