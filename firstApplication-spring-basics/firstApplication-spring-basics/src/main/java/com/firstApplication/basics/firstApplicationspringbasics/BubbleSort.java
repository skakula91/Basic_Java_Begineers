package com.firstApplication.basics.firstApplicationspringbasics;

import org.springframework.stereotype.Component;

// make the dependent library as beans - by placing component tag
@Component
public class BubbleSort implements SortAlgorithm{
    public int[] Sort(int[] arr)
    {
        boolean isSorted = true;
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = 0; j < arr.length - i - 1; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted)
                break;
        }
        return arr;
    }
}
