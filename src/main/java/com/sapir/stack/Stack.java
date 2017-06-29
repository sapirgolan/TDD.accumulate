package com.sapir.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i062070 on 28/06/2017.
 */
public class Stack {

    private final int MAX_CAPACITY;
    private int size;
    private List<Integer> values;

    public Stack(int size) {
        MAX_CAPACITY = size;
        values = new ArrayList<Integer>(MAX_CAPACITY);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int value) throws OverFlowException {
        if (size == MAX_CAPACITY)
            throw new OverFlowException();
        size++;
        values.add(value);
    }

    public int pop() throws Exception {
        if (isEmpty())
            throw new UnderFlowException();
        return values.get(--size);
    }


}
