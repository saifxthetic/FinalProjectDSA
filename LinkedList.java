class LinkedList {
    Node head;

    public void add(Customer customer) {
        Node newNode = new Node(customer);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public Customer search(int id) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.id == id) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public void display() {
        if (head == null) {
            System.out.println("No customers served yet.");
            return;
        }
        Node temp = head;
        System.out.println("List of Served Customers:");
        while (temp != null) {
            System.out.println("ID: " + temp.data.id + ", Name: " + temp.data.name + ", Amount: " + temp.data.amount);
            temp = temp.next;
        }
    }
}
