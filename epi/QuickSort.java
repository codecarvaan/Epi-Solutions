package epi;

public class QuickSort {

    public static void main(String args[]) {


    }

    public int partioning(int a[], int start, int end, int pivot) {
        int i = start;
        int j = end;
        while (start < end) {
            while (a[start] < pivot) {
                start++;
            }
            while (a[end] > pivot) {
                end--;
            }
            //swap


        }
        return pivot;
    }

    public void sort(int a[], int start, int end) {
        int pivot = a[end];   // end size-1
        pivot = a[partioning(a, start, end, pivot)];
        sort(a, start, pivot - 1);
        sort(a, pivot + 1, end);
    }
}
