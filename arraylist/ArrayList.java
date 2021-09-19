import java.util.NoSuchElementException;

public class ArrayList<T> {


    public static final int INITIAL_CAPACITY = 9;

    private T[] backingArray;
   
    private int size;

    public ArrayList() {
        this.backingArray =  (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void addAtIndex(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Enter a valid index. "
                    + "Index can't be negative or more than the current size.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure.");
        }
        if (this.size == this.backingArray.length) {
            T[] newArray = (T[]) new Object[this.backingArray.length * 2];
            for (int i = 0; i < index; i++) {
                newArray[i] = this.backingArray[i];
            }
            newArray[index] = data;
            for (int i = index + 1; i < backingArray.length + 1; i++) {
                newArray[i] = this.backingArray[i - 1];
            }
            this.backingArray = newArray;
            this.size++;
        } else {
            for (int i = size; i > index; i--) {
                this.backingArray[i] = this.backingArray[i - 1];
            }
            this.backingArray[index] = data;
            this.size++;
        }
    }

    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure.");
        }
        if (this.size == this.backingArray.length) {
            T[] newArray = (T[]) new Object[this.backingArray.length * 2];
            for (int i = 0; i < this.backingArray.length; i++) {
                newArray[i + 1] = this.backingArray[i];
            }
            this.backingArray = newArray;
            this.backingArray[0] = data;
            this.size++;
        } else {
            for (int i = size; i > 0; i--) {
                this.backingArray[i] = this.backingArray[i - 1];
            }
            this.backingArray[0] = data;
            this.size++;
        }
    }

    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure.");
        }
        if (this.size < this.backingArray.length) {
            this.backingArray[size] = data;
            this.size++;
        } else {
            T[] newArray = (T[]) new Object[this.backingArray.length * 2];
            for (int i = 0; i < this.backingArray.length; i++) {
                newArray[i] = this.backingArray[i];
            }
            this.backingArray = newArray;
            addToBack(data);
        }
    }


    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Enter a valid index. "
                    + "Index can't be negative or more than the current size.");
        } else {
            T removed = this.backingArray[index];
            for (int i = index + 1; i < this.size; i++) {
                this.backingArray[i - 1] = this.backingArray[i];
            }
            this.backingArray[size - 1] = null;
            this.size--;
            return removed;
        }
    }

    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("The ArrayList is empty.");
        } else {
            T removed = this.backingArray[0];
            for (int i = 1; i < this.size; i++) {
                this.backingArray[i - 1] = this.backingArray[i];
            }
            this.backingArray[size - 1] = null;
            this.size--;
            return removed;
        }
    }

    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("The ArrayList is empty.");
        } else {
            T removed = this.backingArray[this.size - 1];
            this.backingArray[this.size - 1] = null;
            this.size--;
            return removed;
        }
    }

    public T get(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Enter a valid index. "
                    + "Index can't be negative or more than the current size.");
        }
        return this.backingArray[index];
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void clear() {
        T[] newArray = (T[]) new Object[INITIAL_CAPACITY];
        this.backingArray = newArray;
        this.size = 0;
    }

    public T[] getBackingArray() {
        return backingArray;
    }

    public int size() {
        return size;
    }
}
