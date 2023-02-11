package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>{
    // Circular implementation
    private class Node{
        T data;
        Node prev;
        Node next;

        public Node(){
            //this.data = data;
            this.prev = null;
            this.next = null;
        }

    }

    private Node dummyHead;
    private int size;

    public LinkedListDeque(){
        dummyHead = new Node();
        dummyHead.next = dummyHead;
        dummyHead.prev = dummyHead;
        size = 0;
    }

    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node node;

        public LinkedListIterator(){
            node = dummyHead.next;
        }

        public boolean hasNext(){
            return node != dummyHead;
        }

        public T next(){
            T returnData = node.data;
            node = node.next;
            return returnData;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
        {
            return true;
        }
        if (o instanceof LinkedListDeque other){
            if (this.size() != other.size())
            {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (!this.get(i).equals(other.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void addFirst(T item){
        // Note that the front is the node after the dummyHead node
        Node firstNode = dummyHead.next;
        Node newFirstNode = new Node();
        newFirstNode.data = item;
        newFirstNode.next = firstNode;
        newFirstNode.prev = dummyHead;
        firstNode.prev = newFirstNode;
        dummyHead.next = newFirstNode;

        size++;
    }

    public void addLast(T item){
        // Note that the front is the node after the dummyHead node
        Node lastNode = dummyHead.prev;
        Node newLastNode = new Node();
        newLastNode.data = item;
        newLastNode.next = dummyHead;
        newLastNode.prev = lastNode;
        lastNode.next = newLastNode;
        dummyHead.prev = newLastNode;

        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int count = 0;
        Node node = dummyHead;

        while(count < size){
            count++;
            node = node.next;
            System.out.print(node.data);
            if (node.next != dummyHead){
                System.out.print(" ");
            }
        }

        System.out.println("");
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        size--;
        Node removeNode = dummyHead.next;
        Node newFirstNode = removeNode.next;

        dummyHead.next = newFirstNode;
        newFirstNode.prev = dummyHead;
        removeNode.prev = null;
        removeNode.next = null;

        return removeNode.data;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        size--;
        Node removeNode = dummyHead.prev;
        Node newLastNode = removeNode.prev;

        dummyHead.prev = newLastNode;
        newLastNode.next = dummyHead;
        removeNode.prev = null;
        removeNode.next = null;

        return removeNode.data;
    }

    public T get(int index){
        if (index >= size || index < 0){
            return null;
        }

        Node node = dummyHead.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public T getRecursive(int index){
        if (index >= size || index < 0){
            return null;
        }
        return getRecursiveHelper(dummyHead.next, index);
    }
    private T getRecursiveHelper(Node node, int index){
        if (index == 0){
            return node.data;
        }
        return getRecursiveHelper(node.next, index-1);

    }

}
