public class Queue_LL {
    int size;
    Node root;

    public class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node() {}
    }

    public Queue_LL() {
        this.size = 0;
        this.root = null;
    }

    public void enqueue(int data) {
        if (root == null) {
            root = new Node(data, null);
            size++;
        } else {
            Node currentNode = root;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node(data, null);
            size++;
        }
    }

    public int dequeue() {
        int returnData = root.data;
        root = root.next;
        size--;
        return returnData;
    }

    public int top() {
        return root.data;
    }
}