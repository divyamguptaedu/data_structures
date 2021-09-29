 import java.util.NoSuchElementException;

    public class MinHeap<T extends Comparable<? super T>> {
        implements HeapInterface<T> {
        private T[] backingArray;
        private int size;

        public MinHeap() {
            backingArray = (T[]) new Comparable[INITIAL_CAPACITY]; 
            size = 0; 

        }
        @Override
        public void add(T item) {
            if (item == null) {
                throw new IllegalArgumentException();
            }
            if (size == backingArray.length - 1) {
                regrowArray();
            }
            size++;
            backingArray[size] = item;
            if (size > 1) {
                int index = size;
                int parentIndex = index / 2;
                while (parentIndex > 0) {
                    if (backingArray[index].compareTo(backingArray[parentIndex])
                                < 0) {
                        T obj = backingArray[index];
                        backingArray[index] = backingArray[parentIndex];
                        backingArray[parentIndex] = obj;
                        if (index != 1) {
                            index = parentIndex;
                            parentIndex = parentIndex / 2;
                        }
                    } else {
                        parentIndex = -1;
                    } 
                }
            } 
        }

        private void regrowArray() {
            T[] temp = (T[]) new Comparable[backingArray.length * 2];
            for (int x = 1; x < backingArray.length; x++) {
                temp[x] = backingArray[x];
            }
            backingArray = temp;
        }

        public T remove() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            int index = 1;
            T obj = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            int child = childHelper(index);
            while (child >= 0) {
                if (backingArray[child].compareTo(backingArray[index]) < 0) {
                    T temp = backingArray[index];
                    backingArray[index] = backingArray[child];
                    backingArray[child] = temp;
                    index = child;
                    child = childHelper(index);
                } else {
                    child = -1; 
                }
            }
            return obj; 
        }

        private int childHelper(int index) {
            if (index * 2 > size) {
                return -1;
            } else if ((index * 2) + 1 > size) {
                return index * 2;
            } else if (backingArray[(index * 2) + 1]
                    .compareTo(backingArray[index * 2]) > 0) {
                return index * 2;
            } else {
                return (index * 2) + 1;
            }
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }
        @Override
        public int size() {
            return size;
        }
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0; 
    }

    @Override
    public Comparable[] getBackingArray() {
        return backingArray;
    }
}