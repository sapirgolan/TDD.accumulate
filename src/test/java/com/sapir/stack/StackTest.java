package com.sapir.stack;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by i062070 on 28/06/2017.
 */
public class StackTest {

    public static final int STACK_SIZE = 10;
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @InjectMocks
    private Stack stack;

    @Mock
    private List<Integer> values;

    @Before
    public void setUp() throws Exception {
        stack = Mockito.spy(new Stack(STACK_SIZE));
        MockitoAnnotations.initMocks(this);
        when(values.get(Mockito.anyInt())).thenReturn(7);
    }

    @Test
    public void newStack_isEmpty() throws Exception {
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void afterPush_notEmpty() throws Exception {
        stack.push(3);
        assertThat(stack.isEmpty(), is(false));
    }

    @Test (expected = UnderFlowException.class)
    public void popEmptyStack_throwUnderFlowException() throws Exception {
        stack.pop();
    }

    @Test
    public void afterPushAndPop_empty() throws Exception {
        stack.push(4);
        stack.pop();
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void afterPushTwice_popOnce_notEmpty() throws Exception {
        stack.push(3);
        stack.push(6);
        stack.pop();
        assertThat(stack.isEmpty(), is(false));
    }

    @Test
    public void pushX_popX() throws Exception {
        stack.push(5);
        when(values.get(Mockito.anyInt())).thenReturn(5);
        assertThat(stack.pop(), is(5));
    }

    @Test
    public void pushXandY_popYandX() throws Exception {
        stack.push(4);
        stack.push(5);
        when(values.get(Mockito.anyInt()))
                .thenReturn(5,4);
        assertThat(stack.pop(), is(5));
        assertThat(stack.pop(), is(4));
        Mockito.verify(values, Mockito.times(2))
                .add(Mockito.anyInt());
    }

    @Test
    public void pushMoreThanSize_throwOverFlowException() throws Exception {
        expected.expect(OverFlowException.class);
        for (int i = 0; i < (STACK_SIZE + 1); i++) {
            stack.push(i);
        }
    }

    @Test
    public void pushItemsTillStackIsFull() throws Exception {
        for (int i = 0; i < (STACK_SIZE); i++) {
            stack.push(i);
        }
        assertThat(Whitebox.<Integer>invokeMethod(stack, "getSize"),
                is(10));
        Mockito.verify(stack, Mockito.times(10))
                .push(Mockito.anyInt());
    }
}