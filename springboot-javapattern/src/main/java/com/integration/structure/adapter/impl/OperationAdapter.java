package com.integration.structure.adapter.impl;

import com.integration.structure.adapter.BinarySearch;
import com.integration.structure.adapter.QuickSort;
import com.integration.structure.adapter.ScoreOperation;

/**
 * Created by ZhangGang on 2019/6/5.
 */
//操作适配器：适配器
public class OperationAdapter implements ScoreOperation {
    private QuickSort sortObj; //定义适配者QuickSort对象
    private BinarySearch searchObj; //定义适配者BinarySearch对象

    public OperationAdapter() {
        sortObj = new QuickSort();
        searchObj = new BinarySearch();
    }

    public int[] sort(int array[]) {
        return sortObj.quickSort(array); //调用适配者类QuickSort的排序方法
    }

    public int search(int array[], int key) {
        return searchObj.binarySearch(array, key); //调用适配者类BinarySearch的查找方法
    }
}
