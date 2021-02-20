package com.learning.mockito.mockitodemo;

public class BusinessImplementation {
    private DataService dataService;

    public BusinessImplementation(DataService dataService) {
        this.dataService = dataService;
    }

    int findTheGreatestFromAllData()
    {
        int[] data = dataService.retrieveAllData();
        int greatest = Integer.MIN_VALUE;

        for(int value :data)
        {
            if(value > greatest)
            {
                greatest = value;
            }
        }
        return  greatest;
    }
}



