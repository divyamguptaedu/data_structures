import java.util.NoSuchElementException;
import java.util.ArrayList;

public class CircularSinglyLinkedList<T> {

    private CircularSinglyLinkedListNode<T> head;
    private int size;

    public void addAtIndex(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index. Do not enter a negative number. Do not enter a number greater than the size."
            );
        }
        if (data == null) {
            throw new IllegalArgumentException("Removing a null data is not allowed.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else if (size > 0) {
            CircularSinglyLinkedListNode<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            CircularSinglyLinkedListNode<T> node = new CircularSinglyLinkedListNode<T>(data, currentNode.getNext());
            currentNode.setNext(node);
            size++;
        } else {
            head = new CircularSinglyLinkedListNode<T>(data, null);
            head.setNext(head);
            size++;
        }
    }

    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Removing a null data is not allowed.");
        } else if (size > 0) {
            CircularSinglyLinkedListNode<T> node = new CircularSinglyLinkedListNode<T>(head.getData(), head.getNext());
            head.setNext(node);
            head.setData(data);
        } else {
            head = new CircularSinglyLinkedListNode<T>(data, null);
            head.setNext(head);
        }
        size++;
    }


    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Removing a null data is not allowed.");
        } else if (size > 0) {
            CircularSinglyLinkedListNode<T> node = new CircularSinglyLinkedListNode<T>(head.getData(), head.getNext());
            head.setNext(node);
            head.setData(data);
            head = node;
        } else {
            head = new CircularSinglyLinkedListNode<T>(data, null);
            head.setNext(head);
        }
        size++;
    }

    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index. Do not enter a number less than zero or greater than or equal to the size."
            );
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == (size - 1)) {
            return removeFromBack();
        } else {
            CircularSinglyLinkedListNode<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            CircularSinglyLinkedListNode<T> nextNode = currentNode.getNext();
            currentNode.setNext(nextNode.getNext());
            size--;
            return nextNode.getData();
        }
    }

    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("The linked list is empty. Add the elements first to remove later.");
        } else if (size == 1) {
            T removedData = head.getData();
            head = null;
            size--;
            return removedData;
        } else {
            T removedData = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return removedData;
        }
    }

    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("The linked list is empty. Add the elements first to remove later.");
        } else if (size == 1) {
            T removedData = head.getData();
            head = null;
            size--;
            return removedData;
        } else {
            CircularSinglyLinkedListNode<T> currentNode = head;
            for (int i = 0; i < size - 2; i++) {
                currentNode = currentNode.getNext();
            }
            T removedData = currentNode.getNext().getData();
            currentNode.setNext(head);
            size--;
            return removedData;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index. Do not enter a number less than zero or greater than or equal to the size."
            );
        } else if (index == 0) {
            return head.getData();
        }
        CircularSinglyLinkedListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Removing a null data is not allowed.");
        }
        if (size == 0) {
            throw new NoSuchElementException("The linked list is empty. Add the elements first to remove later.");
        }
        if (head.getData().equals(data) && size == 1) {
            T removedData = head.getData();
            head = null;
            size--;
            return removedData;
        }

        CircularSinglyLinkedListNode<T> current = head;
        CircularSinglyLinkedListNode<T> temp = null;

        for (int i = 0; i < size; i++) {
            if (current.getData().equals(data)) {
                temp = current;
            }
            current = current.getNext();
        }

        if (temp == null) {
            throw new NoSuchElementException("This data does not exist in your linked list.");
        }

        if (temp == head) {
            return removeFromFront();
        }

        if (temp.getNext() == head) {
            return removeFromBack();
        }

        T removedData = temp.getData();
        temp.setData(temp.getNext().getData());
        temp.setNext(temp.getNext().getNext());
        size--;
        return removedData;
    }

    public T[] toArray() {
        T[] linkedListArray = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            linkedListArray[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return linkedListArray;
    }

    public CircularSinglyLinkedListNode<T> getHead() {
        return head;
    }

    public int size() {
        return size;
    }
}