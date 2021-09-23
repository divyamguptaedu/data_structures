public class DoublyLinkedList<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else if (index < (size + 1) / 2) {
            LinkedListNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            LinkedListNode<T> newNode = new LinkedListNode<T>(currentNode.
                    getPrevious(), data, currentNode);
            currentNode.setPrevious(newNode);
            currentNode = currentNode.getPrevious();
            currentNode = currentNode.getPrevious();
            currentNode.setNext(newNode);
            size++;
        } else {
            LinkedListNode<T> currentNode = tail;
            for (int i = 0; i < size - index; i++) {
                currentNode = currentNode.getPrevious();
            }
            LinkedListNode<T> newNode = new LinkedListNode<T>(currentNode,
                    data, currentNode.getNext());
            currentNode.setNext(newNode);
            currentNode = currentNode.getNext();
            currentNode = currentNode.getNext();
            currentNode.setPrevious(newNode);
            size++;
	} 

    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (head == null) {
            LinkedListNode<T> newNode = new LinkedListNode<T>(null, data, null);
            head = newNode;
            tail = newNode;
            size++;
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(null, data, head);
            head = newNode;
            head.getNext().setPrevious(newNode);
            size++;
        }
    } 

    public void addToBack(T data) {
        size++;
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (head == null) {
            LinkedListNode<T> newNode = new LinkedListNode<T>(null, data, null);
            head = newNode;
            tail = newNode;
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(tail, data, null);
            tail = newNode;
            tail.getPrevious().setNext(newNode);
        }
    } 

    public T removeAtIndex(int index) {
        T out = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            out = this.removeFromFront();
        } else if (index == size - 1) {
            out = this.removeFromBack();
        } else if (index < (size + 1) / 2) {
            LinkedListNode<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            LinkedListNode<T> missingNode = currentNode.getNext();
            LinkedListNode<T> nextNextNode = missingNode.getNext();
            currentNode.setNext(nextNextNode);
            nextNextNode.setPrevious(currentNode);
            size = size - 1;
            out = missingNode.getData();
        } else {
            LinkedListNode<T> currentNode = tail;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getPrevious();
            }
            LinkedListNode<T> missingNode = currentNode.getPrevious();
            LinkedListNode<T> prePreNode = missingNode.getPrevious();
            currentNode.setNext(prePreNode);
            prePreNode.setPrevious(currentNode);
            size = size - 1;
            out =  missingNode.getData();
        }
        return out;
    } 

    public T removeFromFront() {
        T out = null;
        if (size == 1) {
            out = head.getData();
            head = null;
            tail = null;
            size = size - 1;
        } else if (size > 1) {
            out = head.getData();
            head = head.getNext();
            head.setPrevious(null);
            size = size - 1;
        } 
        return out;
    } 

    public T removeFromBack() {
        T out = null;
        if (size == 1) {
            out = tail.getData();
            size = size - 1;
            head = null;
            tail = null;
        } else if (size > 1) {
            out = tail.getData();
            tail = tail.getPrevious();
            tail.setNext(null);
            size = size - 1;
        } 
        return out; 
    } 

    public int lastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (tail.getData().equals(data)) {
            return size - 1;
        } else {
            LinkedListNode<T> currentNode = tail;
            int index = size - 1;
            while (currentNode.getPrevious() != null) {
                if (currentNode.getData().equals(data)) {
                    return index;
                } else { 
                    index = index - 1;
                }
                currentNode = currentNode.getPrevious();
            }
            return -1;
        } 
    } 

    public T get(int index) {
        T out;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index < (size + 1) / 2) {
            LinkedListNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            out = currentNode.getData();
        } else {
            LinkedListNode<T> currentNode = tail;
            for (int i = 0; i < size - index - 1; i++) {
                currentNode = currentNode.getPrevious();
            }
            out = currentNode.getData();
        }
        return out;
    } 

    public Object[] toArray() {
        Object[] newArray = new Object[size];
        LinkedListNode<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            newArray[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return newArray;
    }

    public boolean isEmpty() {
        return size == 0;
    } 

    public void clear() {
        size = 0;
        head = null; 
        tail = null; 
    } 

	public int size() {
    	return size;
	}

	public LinkedListNode<T> getHead() {
    	return head;
	} 

	public LinkedListNode<T> getTail() {
    	return tail;
	}
} 
