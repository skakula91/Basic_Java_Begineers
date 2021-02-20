package com.learning.mockito.mockitodemo;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {
    @Test
    public void test()
    {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(10);
        assertEquals(10, listMock.size());
    }
    // multiple returns
    @Test
    public void test_multiple()
    {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, listMock.size());
        assertEquals(20, listMock.size());
    }

    // call with specific parameter
    @Test
    public void test_specificparameter()
    {
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("testPassed");
        assertEquals("testPassed", listMock.get(0));
        assertEquals(null, listMock.get(1));
    }

    // call with any parameters
    @Test
    public void test_genericParameter()
    {
        List listMock = mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("testPassed");
        assertEquals("testPassed", listMock.get(0));
        assertEquals("testPassed", listMock.get(1));
    }
}
