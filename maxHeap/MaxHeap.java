import java.util.ArrayList;
import java.util.HashMap;

public class MaxHeap {
    int size;
    int capacity;
    int[] backingArray;

    public MaxHeap(int capacity) {
        backingArray = new int[capacity];
        size = 0;
    }

    public MaxHeap(ArrayList<Integer> data) {
        size = data.size();
        capacity = data.size();
        backingArray = new int[size + 1];
        for (int i = 0; i < size; i++) {
            backingArray[i + 1] = data.get(i);
        }
        for (int i = size / 2; i > 0; i--) {
            buildHeapGoDown(backingArray[i], i);
        }
    }

    public void buildHeapGoDown(int number, int index) {
        if (index > size / 2) {
            return;
        }
        int maximum;
        int tempIndex;
        if (backingArray[index * 2] > backingArray[(index * 2) + 1]) {
            maximum = backingArray[index * 2];
            tempIndex = index * 2;
        } else {
            maximum = backingArray[(index * 2) + 1];
            tempIndex = index * 2 + 1;
        }
        if (maximum > number) {
            swap(tempIndex, index);
            buildHeapGoDown(backingArray[tempIndex], tempIndex);
        }
    }

    public void add(int number) {
        if (size == backingArray.length - 1) {
            int[] newArray = new int[size * 2];
            for (int i = 1; i < size + 1; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }
        backingArray[size + 1] = number;
        size++;
        buildHeapGoUp(number, size);
    }

    public void buildHeapGoUp(int number, int index) {
        if (index == 1) {
            return;
        }
        if (number < backingArray[index / 2]) {
            return;
        } else {
            swap(index, index / 2);
        }
        buildHeapGoUp(number, index / 2);
    }

    public int remove() {
        int removedData = backingArray[1];
        backingArray[1] = backingArray[size];
        size--;
        buildHeapGoDown(backingArray[1], 1);
        return removedData;
    }

    public void swap(int i, int j) {
        int temp = backingArray[i];
        backingArray[i] = backingArray[j];
        backingArray[j] = temp;
    }
}