package com.learning.mockito.mockitodemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusinessImplementationMockTest {
    @Test
    public void testFindTheGreatestFromAllData()
    {
        DataService dataServiceMock =  mock(DataService.class);
        //dataServiceMock.retrieveAllData() => new int[]{1,2,3,8}
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{24,15,3});

        BusinessImplementation bi = new BusinessImplementation(dataServiceMock);
        int result = bi.findTheGreatestFromAllData();
        assertEquals(24,result);
    }

    @Test
    public void testFindTheGreatestFromAllData_OneData()
    {
        DataService dataServiceMock =  mock(DataService.class);
        //dataServiceMock.retrieveAllData() => new int[]{1,2,3,8}
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{15});

        BusinessImplementation bi = new BusinessImplementation(dataServiceMock);
        int result = bi.findTheGreatestFromAllData();
        assertEquals(15,result);
    }
}
