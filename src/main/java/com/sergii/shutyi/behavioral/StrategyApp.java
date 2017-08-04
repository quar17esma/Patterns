package com.sergii.shutyi.behavioral;

import java.util.Arrays;

public class StrategyApp {
    public static void main(String[] args) {
        StrategyClient client = new StrategyClient();

        int[] arr0 = {1,3,2,1};
        client.setStrategy(new SelectionSort());
        client.executeStrategy(arr0);

        int[] arr1 = {1,3,2,1};
        client.setStrategy(new InsertionSort());
        client.executeStrategy(arr0);

        int[] arr2 = {1,3,2,1};
        client.setStrategy(new BubbleSort());
        client.executeStrategy(arr0);
    }
}

class StrategyClient {
    Sorting strategy;
    public void setStrategy(Sorting strategy){
        this.strategy = strategy;
    }
    public void executeStrategy(int[] arr){
        strategy.sort(arr);
    }
}

interface Sorting{
    void sort(int[] arr);
}

class BubbleSort implements Sorting{
    @Override
    public void sort(int[] arr) {
        System.out.println("Bubble sort");
        System.out.println("before: \t" + Arrays.toString(arr));
        for (int barrier = arr.length-1; barrier >=0; barrier--) {
            for (int i = 0; i < barrier; i++) {
                if (arr[i]>arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
        System.out.println("after: \t" + Arrays.toString(arr));
    }
}

class SelectionSort implements Sorting{
    @Override
    public void sort(int[] arr) {
        System.out.println("Selection sort");
        System.out.println("before: \t" + Arrays.toString(arr));
        for (int barrier = 0; barrier < arr.length-1; barrier++) {
            for (int i = barrier+1; i < arr.length; i++) {
                if (arr[i]<arr[barrier]){
                    int temp = arr[i];
                    arr[i] = arr[barrier];
                    arr[barrier] = temp;
                }
            }
        }
        System.out.println("after: \t" + Arrays.toString(arr));
    }
}

class InsertionSort implements Sorting{
    @Override
    public void sort(int[] arr) {
        System.out.println("Insertion sort");
        System.out.println("before: \t" + Arrays.toString(arr));
        for (int barrier = 1; barrier < arr.length; barrier++) {
            int index = barrier;
            while (index-1>=0 && arr[index]<arr[index-1]){
                int temp = arr[index];
                arr[index] = arr[index-1];
                arr[index-1] = temp;
                index--;
            }
        }
        System.out.println("after: \t" + Arrays.toString(arr));
    }
}