public class Testing {
    public static void main(String[] args) {
        Queue_LL queue_ll = new Queue_LL();
        queue_ll.enqueue(9);
        queue_ll.enqueue(8);
        queue_ll.enqueue(7);
        queue_ll.enqueue(6);
        queue_ll.enqueue(100);
        queue_ll.enqueue(4);
        System.out.println(queue_ll.dequeue());
        System.out.println(queue_ll.dequeue());
        System.out.println(queue_ll.dequeue());
        System.out.println(queue_ll.dequeue());
        System.out.println(queue_ll.dequeue());
        System.out.println(queue_ll.top());

        System.out.println("--------");

        Queue_Array queue_arr = new Queue_Array();
        queue_arr.enqueue(9);
        queue_arr.enqueue(8);
        queue_arr.enqueue(7);
        queue_arr.enqueue(6);
        queue_arr.enqueue(100);
        queue_arr.enqueue(4);
        System.out.println(queue_arr.dequeue());
        System.out.println(queue_arr.dequeue());
        System.out.println(queue_arr.dequeue());
        System.out.println(queue_arr.dequeue());
        System.out.println(queue_arr.dequeue());
        System.out.println(queue_arr.top());
    }
}
