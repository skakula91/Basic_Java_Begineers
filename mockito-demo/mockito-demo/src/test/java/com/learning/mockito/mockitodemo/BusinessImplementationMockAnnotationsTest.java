package com.learning.mockito.mockitodemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// when ever we use mock attribute we need to use runWith mockito junit runner
@RunWith(MockitoJUnitRunner.class)
public class BusinessImplementationMockAnnotationsTest {
    @Mock
    DataService dataServiceMock;

    @InjectMocks
    BusinessImplementation bi;

    @Test
    public void testFindTheGreatestFromAllData()
    {
        //DataService dataServiceMock =  mock(DataService.class);
        //dataServiceMock.retrieveAllData() => new int[]{1,2,3,8}
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{24,15,3});

        //BusinessImplementation bi = new BusinessImplementation(dataServiceMock);
        int result = bi.findTheGreatestFromAllData();
        assertEquals(24,result);
    }
}
