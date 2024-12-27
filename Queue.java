public class Queue {
    private Node front, rear;

    public Queue() {
        this.front = this.rear = null;
    }

    public void enqueue(Customer customer) {
        Node newNode = new Node(customer);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public Customer dequeue() {
        if (front == null) {
            return null;
        }
        Customer customer = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return customer;
    }

    public void display() {
        if (front == null) {
            System.out.println("No customers in the queue.");
            return;
        }
        Node temp = front;
        System.out.println("Queue of Customers:");
        while (temp != null) {
            System.out.println("ID: " + temp.data.id + ", Name: " + temp.data.name + ", Amount: " + temp.data.amount);
            temp = temp.next;
        }
    }
}
