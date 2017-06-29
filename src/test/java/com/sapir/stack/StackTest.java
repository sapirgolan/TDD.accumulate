package com.sapir.stack;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by i062070 on 28/06/2017.
 */
public class StackTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @InjectMocks
    private Stack stack;

    @Mock
    List<Integer> mockedList;

    @Before
    public void setUp() throws Exception {
        stack = new Stack(10);
        MockitoAnnotations.initMocks(this);
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
        assertThat(stack.pop(), is(5));
    }

    @Test
    public void pushXandY_popYandX() throws Exception {
        stack.push(4);
        stack.push(5);
        assertThat(stack.pop(), is(5));
        assertThat(stack.pop(), is(4));
    }

    @Test
    public void pushMoreThanSize_throwOverFlowException() throws Exception {
        expected.expect(OverFlowException.class);
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }
    }
}