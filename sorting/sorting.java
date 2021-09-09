import java.util.LinkedList;
import java.util.Random;

public class Sorting {

    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Bubble sort with two optimizations;
    // O(n) -> O(n^2) -> O(n^2) -> Stable -> Adaptive -> In-Place;
    public static void bubbleSort(Integer[] arr) {
        int stopIndex = arr.length - 1;
        boolean swapsMade = true; // optimization 1 - exits if no swaps were made;
        while (stopIndex != 0 && swapsMade) {
            swapsMade = false;
            int i = 0;
            int lastIndexSwapped = 0; // optimization 2 - notes the last swapped index to set a limit for next iteration
            while (i < stopIndex) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swapsMade = true;
                    lastIndexSwapped = i;
                }
                i++;
            }
            stopIndex = lastIndexSwapped;
        }
    }

    // Insertion sort;
    // O(n) -> O(n^2) -> O(n^2) -> Stable -> Adaptive -> In-Place;
    public static void insertionSort(Integer[] arr) {
        for (int n = 1; n < arr.length; n++) {
            int i = n;
            while (i > 0 && arr[i - 1] > arr[i]) {
                swap(arr, i - 1, i);
                i--;
            }
        }
    }

    // Selection sort;
    // O(n^2) -> O(n^2) -> O(n^2) -> Not Stable -> Not Adaptive -> In-Place;
    public static void selectionSort(Integer[] arr) {
        for (int n = arr.length - 1; n > 0; n--) {
            int maxIndex = n;
            for (int i = 0; i < n; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            swap(arr, maxIndex, n);
        }
    }

    // Cocktail Shaker sort;
    // O(n) -> O(n^2) -> O(n^2) -> Stable -> Adaptive -> In-Place;
    public static void cocktailSort(Integer[] arr) {
        boolean swapsMade = true;
        int startIndex = 0;
        int endIndex = arr.length - 1;
        int lastIndex = endIndex;
        int firstIndex = startIndex;
        while (swapsMade) {
            swapsMade = false;
            endIndex = lastIndex;
            for (int i = startIndex; i < endIndex; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swapsMade = true;
                    lastIndex = i;
                }
            }
            endIndex = lastIndex;
            if (swapsMade) {
                swapsMade = false;
                startIndex = firstIndex;
                for (int i = endIndex; i > startIndex; i--) {
                    if (arr[i - 1] > arr[i]) {
                        swap(arr, i, i - 1);
                        swapsMade = true;
                        firstIndex = i;
                    }
                }
                startIndex = firstIndex;
            }
        }
    }

    // Merge sort;
    // O(n log n) -> O(n log n) -> O(n log n) -> Stable -> Not Adaptive -> Out-Place;
    public static void mergeSort(Integer[] arr) {
        // base case
        if (arr.length == 1) {
            return;
        }

        //Middle formation;
        int midIndex = arr.length / 2;
        Integer[] leftHalf = new Integer[midIndex];
        Integer[] rightHalf = new Integer[arr.length - midIndex];

        //Left half formation;
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = arr[i];
        }
        //Right half formation;
        for (int i = midIndex; i < arr.length; i++) {
            rightHalf[i - midIndex] = arr[i];
        }

        //Break up and go left;
        if (leftHalf.length != 0) {
            mergeSort(leftHalf);
        }

        //Break up and go right;
        if (rightHalf.length != 0) {
            mergeSort(rightHalf);
        }

        mergeBack(leftHalf, rightHalf, arr);
    }

    private static void mergeBack(Integer[] leftHalf, Integer[] rightHalf, Integer[] arr) {
        int leftIndex = 0;
        int rightIndex = 0;
        int i = 0;
        while (leftIndex < leftHalf.length && rightIndex < rightHalf.length) {
            if (leftHalf[leftIndex] > rightHalf[rightIndex]) {
                arr[i++] = rightHalf[rightIndex++];
            } else {
                arr[i++] = leftHalf[leftIndex++];
            }
        }

        // add the left elements to the array
        while (leftIndex < leftHalf.length) {
            arr[i++] = leftHalf[leftIndex++];
        }

        // add the left elements to the array
        while (rightIndex < rightHalf.length) {
            arr[i++] = rightHalf[rightIndex++];
        }
    }

    // Quick sort;
    // O(n log n) -> O(n log n) -> O(n^2) -> Un-Stable -> Not Adaptive -> In-Place;
    public static void quickSort(Integer[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(Integer[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        Random rand = new Random();
        int pivotIndex = rand.nextInt(right - left + 1) + left;
        int i = left + 1;
        int j = right;
        swap(arr, left, pivotIndex);
        while (i <= j) {
            while (i <= j && (arr[i] <= arr[left])) {
                i++;
            }
            while (i <= j && (arr[j] >= arr[left])) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                j--;
                i++;
            }
        }
        swap(arr, left, j);
        if (left < j) {
            quickSortHelper(arr, left, j - 1);
        }
        if (right > i) {
            quickSortHelper(arr, i, right);
        }
    }

    //LSD Radix
    //Only on integers.
    //O(kn) complexity, stable, not adaptive, out of place.
    public static void lsdRadixSort(Integer[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The passed in array is null. Cannot perform action.");
        }

        LinkedList<Integer>[] tempList = new LinkedList[19];
        for (int i = 0; i < tempList.length; i++) {
            tempList[i] = new LinkedList<Integer>();
        }

        int longestLength = 1;
        int largestNumber = 0;

        for (int i : arr) {
            if (Math.abs((largestNumber / 10)) < (Math.abs(i) / 10)) {
                largestNumber = i;
            }
        }

        while (!(largestNumber == 0)) {
            largestNumber = largestNumber / 10;
            longestLength++;
        }

        for (int i = 0; i < longestLength; i++) {
            for (int j = 0; j < arr.length; j++) {
                int lsd = getInteger(arr[j], i) + 9;
                tempList[lsd].add(arr[j]);
            }
            int index = 0;
            for (int k = 0; k < tempList.length; k++) {
                while (!(tempList[k].isEmpty())) {
                    arr[index] = tempList[k].removeFirst();
                    index++;
                }
            }
        }
    }

    private static Integer getInteger(Integer number, Integer digit) {
        for (int i = digit; i > 0; i--) {
            number = number / 10;
        }
        return number % 10;
    }
    
}
