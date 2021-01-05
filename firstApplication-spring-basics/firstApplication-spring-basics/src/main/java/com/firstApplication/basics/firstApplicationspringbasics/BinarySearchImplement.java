package com.firstApplication.basics.firstApplicationspringbasics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// make the dependent library as beans - by placing component tag
@Component
public class BinarySearchImplement {
    //tell spring framework that this is a dependency
    @Autowired
    private SortAlgorithm sortAlgorithm;

//    public void setSortAlgorithm(SortAlgorithm sortAlgorithm) {
//        this.sortAlgorithm = sortAlgorithm;
//    }
//    public BinarySearchImplement(SortAlgorithm sortAlgorithm)
//    {
//        super();
//        this.sortAlgorithm = sortAlgorithm;
//    }

    public int BinarySearch(int[] numbers, int searchElement)
    {
        //Arrays.sort(numbers);
        numbers = sortAlgorithm.Sort(numbers);
        System.out.println(sortAlgorithm);
        int left = 0;
        int right = numbers.length;

        while (left <= right)
        {
            int mid = left + (right-left)/2 ;

            if(numbers[mid] == searchElement)
            {
                return mid;
            }
            else if(searchElement < numbers[mid])
            {
                right = mid-1;
            }
            else
            {
                left = mid+1;
            }
        }
        return -1;
    }
}
