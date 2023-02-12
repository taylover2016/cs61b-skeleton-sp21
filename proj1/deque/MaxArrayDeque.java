package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> /*implements Comparable<T>*/{
    private Comparator<T> myComparator;

    public MaxArrayDeque(Comparator<T> c){
        super();
        myComparator = c;
    }

    public T max(){
        if (this.isEmpty()){
            return null;
        }
        T max_temp = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (myComparator.compare(this.get(i), max_temp) > 0){
                max_temp = this.get(i);
            }
        }
        return max_temp;
    }

    public T max(Comparator<T> c){
        if (this.isEmpty()){
            return null;
        }
        T max_temp = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (c.compare(this.get(i), max_temp) > 0){
                max_temp = this.get(i);
            }
        }
        return max_temp;
    }
}
