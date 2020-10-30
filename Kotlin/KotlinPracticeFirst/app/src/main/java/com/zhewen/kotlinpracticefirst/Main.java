package com.zhewen.kotlinpracticefirst;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void sort(int a[], int low, int hight) {
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        index = a[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && a[j] >= index)
                j--;
            if (i < j)
                a[i++] = a[j];// 用比基准小的记录替换低位记录
            while (i < j && a[i] < index)
                i++;
            if (i < j) // 用比基准大的记录替换高位记录
                a[j--] = a[i];
        }
        a[i] = index;// 将基准数值替换回 a[i]
        sort(a, low, i - 1); // 对低子表进行递归排序
        sort(a, i + 1, hight); // 对高子表进行递归排序

    }

    public static void quickSort(int a[]) {
        sort(a, 0, a.length - 1);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {

        long indexX = 2;
        long indexY = -2;
        long resultNum = 0;


        long absIndexX = Math.abs(indexX);
        long absIndexY = Math.abs(indexY);
        long maxIndex = Math.max(absIndexX,absIndexY);
        long pow1 = (2 * maxIndex + 1) * (2 * maxIndex + 1);
        long pow2 = pow1 - 2 * maxIndex;
        long pow3 = pow1 - 4 * maxIndex;
        long pow4 = pow1 - 6 * maxIndex;
        if (indexY == maxIndex) {
            resultNum = pow1 - (maxIndex - indexX);
        } else if (indexX == -maxIndex) {
            resultNum = pow1 - 2 * maxIndex - (maxIndex - indexY);
        } else if (indexY == - maxIndex) {
            resultNum = pow1 - 4 * maxIndex - (maxIndex - indexX);
        } else {
            resultNum = pow1 - 6 * maxIndex - (maxIndex - indexY);
        }
        System.out.println(resultNum);




    }
}
