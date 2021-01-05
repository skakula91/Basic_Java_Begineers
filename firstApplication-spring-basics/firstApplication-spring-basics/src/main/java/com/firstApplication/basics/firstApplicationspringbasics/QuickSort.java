package com.firstApplication.basics.firstApplicationspringbasics;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component
@Primary
public class QuickSort implements SortAlgorithm{

    public int[] Sort(int[] arr)
    {
        SortArr(arr,0,arr.length-1);
        return arr;
    }

    public void SortArr(int[] arr, int start, int end)
    {
        if (start >= end)
            return;
        int pIndex = partition(arr, start, end);
        SortArr(arr, start, pIndex-1);
        SortArr(arr, pIndex + 1, end);
    }

    public static int partition(int[] arr, int start, int end)
    {
        int pivot = arr[end];
        int pIndex = start;
        for(int i = start; i < end; i++)
        {
            if(arr[i] <= pivot)
            {
                int temp = arr[i];
                arr[i] = arr[pIndex];
                arr[pIndex] = temp;
                pIndex++;
            }
        }
        int temp1 = pivot;
        arr[end] = arr[pIndex];
        arr[pIndex] = temp1;
        return pIndex;
    }
}
