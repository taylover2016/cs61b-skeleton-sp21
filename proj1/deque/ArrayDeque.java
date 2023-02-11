package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>{
    // Array implementation

    private T[] array;
    private int idx_start;
    private int idx_end;
    private int size;

    public ArrayDeque(){
        array = (T[]) new Object[8];
        idx_start = array.length / 2;
        idx_end = array.length / 2 - 1;
        size = 0;
    }

    public Iterator<T> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{
        private int idx;

        public ArrayIterator(){
            idx = idx_start;
        }

        public boolean hasNext(){
            return idx <= idx_end;
        }

        public T next(){
            T returnData = array[idx];
            idx++;
            return returnData;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
        {
            return true;
        }
        if (o instanceof ArrayDeque other){
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
        // Check if out of bounds
        if (idx_start == 0)
        {
            this.resize(2 * this.size());
        }
        idx_start--;
        array[idx_start] = item;

        size++;
    }

    public void addLast(T item){
        if (idx_end == 0)
        {
            this.resize(2 * this.size());
        }

        idx_end++;
        array[idx_end] = item;

        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(int newSize){
        T[] newArray = (T[]) new Object[newSize];
        int newIdxStart = newArray.length / 2;
        int newIdxEnd = newIdxStart + this.size() - 1;
        if (this.size() > 0)
        {
            // Copy the elements
            System.arraycopy(array, idx_start, newArray, newIdxStart, this.size());
        }
        idx_start = newIdxStart;
        idx_end = newIdxEnd;
        array = newArray;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int count = 0;

        while(count < size){
            System.out.print(array[idx_start+count]);
            if (count+1 != size){
                System.out.print(" ");
            }
            count++;
        }

        System.out.println("");
    }

    public T removeFirst(){
        if (size == 0) {
            return null;
        }

        T removeData = array[idx_start];
        size--;

        // When the array is small that doesn't require resizing
        // It is possible for the index to go out of bounds
        // e.g. array.length == 8, start == end == 0 or (array.length-1)
        // and then we call removeLast() or removeFirst()

        if(idx_start == array.length - 1){
            // Keep incrementing will go out of bounds
            // Reset the indices
            idx_start = array.length / 2;
            idx_end = array.length / 2 - 1;
        }
        else {
            idx_start++;
        }

        // Resize if necessary
        if (array.length >= 16 && size < array.length / 4){
            this.resize(array.length / 2);
        }

        return removeData;
    }

    public T removeLast(){
        if (size == 0) {
            return null;
        }

        T removeData = array[idx_end];
        size--;

        // When the array is small that doesn't require resizing
        // It is possible for the index to go out of bounds
        // e.g. array.length == 8, start == end == 0 or (array.length-1)
        // and then we call removeLast() or removeFirst()

        if(idx_end == 0){
            // Keep incrementing will go out of bounds
            // Reset the indices
            idx_start = array.length / 2;
            idx_end = array.length / 2 - 1;
        }
        else {
            idx_end--;
        }

        // Resize if necessary
        if (array.length >= 16 && size < array.length / 4){
            this.resize(array.length / 2);
        }

        return removeData;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        return array[index];
    }

}
