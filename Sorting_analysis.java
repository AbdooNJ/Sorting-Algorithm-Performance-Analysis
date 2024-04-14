import java.util.Random;

/**
 * This class implements sorting algorithms (Merge Sort and Quick Sort) 
 * and measures their execution time for various scenarios.
 * 
 * author: Abdulhakeeem AL-Najadi
 */
public class Sorting_analysis {

    /**
     * The main method initializes arrays of different sizes, generates random numbers,
     * and measures the execution time of sorting algorithms under different conditions.
     * 
     * @param args the command line arguments (unused)
     */
    public static void main(String[] args) {
        int N1 = 40000;
        int N2 = 100000;
        int N3 = 200000;
        
        // Create arrays of size N.
        int[] arr1 = generator(N1);
        int[] arr2 = generator(N2);
        int[] arr3 = generator(N3);
        
        // Compute time to sort unsorted arrays
        double t1 = System.currentTimeMillis();
        MergeSort(arr1, 0, 10000);
        double t2 = System.currentTimeMillis();
        System.out.println("Merge Sort time for unsorted list:");   
        System.out.println(t2 - t1);
        
        System.out.println("=================================="); 
        double t3 = System.currentTimeMillis();
        QuickSort(arr1, 0, 10000);
        double t4 = System.currentTimeMillis();
        System.out.println("Quick Sort time for unsorted list, pivot is first element:");   
        System.out.println(t4 - t3);
        
        System.out.println("=================================="); 
        double t5 = System.currentTimeMillis();
        QuickSort(arr1, 5000, 10000);
        double t6 = System.currentTimeMillis();
        System.out.println("Quick Sort time for unsorted list, pivot is middle element:");   
        System.out.println(t6 - t5);
        
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------------------------------------------------");
        
        // Compute time to sort a sorted array
        System.out.println(SortList(arr1, 0, 10000));
        double t7 = System.currentTimeMillis();
        MergeSort(arr1, 0, 10000);
        double t8 = System.currentTimeMillis();
        System.out.println("Merge Sort time for sorted list:");   
        System.out.println(t8 - t7);
        
        System.out.println("=================================="); 
        double t9 = System.currentTimeMillis();
        QuickSort(arr1, 0, 10000);
        double t10 = System.currentTimeMillis();
        System.out.println("Quick Sort time for sorted list, pivot is first element:");   
        System.out.println(t10 - t9);
        
        System.out.println("=================================="); 
        double t11 = System.currentTimeMillis();
        QuickSort(arr1, 5000, 10000);
        double t12 = System.currentTimeMillis();
        System.out.println("Quick Sort time for sorted list, pivot is middle element:");   
        System.out.println(t12 - t11);       
    }
    
    /** 
     * Merge Sort
     */
    static void MergeSort(int[] arr, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            MergeSort(arr, first, mid);
            MergeSort(arr, mid + 1, last);
            Merge(arr, first, mid, last);
        }
    }
    
    /** 
     * Merge
     */
    static void Merge(int[] arr, int left, int mid, int right) {
        int[] aux = new int[arr.length];
        int i, j;
        for (i = mid + 1; i > left; i--)
            aux[i - 1] = arr[i - 1];
        for (j = mid; j < right; j++)
            aux[right + mid - j] = arr[j + 1];
        for (int k = left; k <= right; k++)
            if (aux[j] < aux[i])
                arr[k] = aux[j--];
            else
                arr[k] = aux[i++];
    }
    
    /** 
     * Quick Sort
     */
    static void QuickSort(int[] arr, int left, int right) {
        if (right <= left + 1) {
            if (right == left + 1)
                swap(arr, left, right);
            return;
        }
        int pivot = partitioning(arr, left, right);
        QuickSort(arr, left, pivot - 1);
        QuickSort(arr, pivot + 1, right);
    }

    private static void swap(int[] arr, int left, int right) {
        if (arr[right] < arr[left]) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }

    private static int partitioning(int[] arr, int left, int right) {
        int pivot = left, i = left + 1, j = right, temp;
        
        while (j > i) {
            while (arr[i] <= arr[pivot] && i < right)
                i++;
            while (arr[j] > arr[pivot] && j > left)
                j--;
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[pivot];
        arr[pivot] = arr[j];
        arr[j] = temp;
        return j;
    }
    
    //-------------------------------------------------------------
    
    /**
     * Generate random numbers
     * 
     * @param Len the length of the array to generate
     * @return an array of random integers
     */
    static int[] generator(int Len) {
        Random rand = new Random();
        int[] arr = new int[Len];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(Len);
        return arr;
    }
    
    /**
     * Sorts a given list using Merge Sort
     * 
     * @param arr the array to be sorted
     * @param first the starting index of the array
     * @param last the ending index of the array
     * @return the sorted array
     */
    static int[] SortList(int[] arr, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            MergeSort(arr, first, mid);
            MergeSort(arr, mid + 1, last);
            Merge(arr, first, mid, last);
        }
        return arr;
    }
}
