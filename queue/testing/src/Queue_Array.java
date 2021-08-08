public class Queue_Array {

    int firstIndex;
    int size;
    int capacity;
    int[] backingArray;

    public Queue_Array() {
        this.firstIndex = -1;
        this.capacity = 10;
        this.backingArray = new int[capacity];
        this.size = 0;
    }

    public void enqueue(int data) {
        if (size + 1 < capacity) {
            backingArray[size] = data;
            size++;
        } else {
            backingArray = resizeBackingArray(backingArray);
            enqueue(data);
        }
    }

    private int[] resizeBackingArray(int[] backingArray) {
        int[] newBackingArray = new int[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            newBackingArray[i] = backingArray[i];
        }
        backingArray = newBackingArray;
        capacity = capacity * 2;
        return backingArray;
    }

    public int dequeue() {
        firstIndex = firstIndex + 1;
        return backingArray[firstIndex];
    }

    public int top() {
        return backingArray[firstIndex + 1];
    }

}