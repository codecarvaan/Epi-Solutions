package epi;

import java.util.Arrays;
import java.util.List;

class QuickSort {

    public static int partition(List<Integer> list, int leftIndex, int rightIndex) {

        int partionIndex = rightIndex;

        while (leftIndex < rightIndex) {
            while (list.get(partionIndex) < list.get(rightIndex)) {   //move left
                rightIndex--;
            }
            while (list.get(partionIndex) > list.get(leftIndex)) {
                leftIndex++;
            }
            swap(list, rightIndex, leftIndex);
        }
        swap(list, partionIndex, leftIndex);
        return leftIndex;
    }

    public static void swap(List<Integer> list, int l, int r) {
        Integer temp = list.get(l);
        list.set(l, list.get(r));
        list.set(r, temp);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(23, 3456, 12, 346, 567, 34, 4, 231, 2);
        QuickSortRunner(list);
        System.out.println(list);
    }

    public static void QuickSortRunner(List<Integer> list) {
        int rightIndex = list.size() - 1;
        int leftIndex = 0;
        QuickSort(list, leftIndex, rightIndex);
    }

    public static void QuickSort(List<Integer> list, int left, int right) {
        if (left < right) {
            int part = partition(list, left, right);
            QuickSort(list, left, part - 1);
            QuickSort(list, part + 1, right);
        }
    }
}