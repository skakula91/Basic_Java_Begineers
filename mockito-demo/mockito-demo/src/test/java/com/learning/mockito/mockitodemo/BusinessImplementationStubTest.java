package com.learning.mockito.mockitodemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessImplementationStubTest {
    @Test
    public void testFindTheGreatestFromAllData()
    {
        BusinessImplementation bi = new BusinessImplementation(new DataServiceStub());
        int result = bi.findTheGreatestFromAllData();
        assertEquals(8,result);
    }
}
// Disadvantages of using stub is for every scenario we need to create a new stub
// if we need to test for different data values we need to create a new stub
class DataServiceStub implements DataService
{
    @Override
    public int[] retrieveAllData()
    {
        return  new int[]{1,4,6,7,8};
    }
}

