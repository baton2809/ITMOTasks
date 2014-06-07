package lab1;
import java.util.*;
/**
 * Created with IntelliJ IDEA.
 * User: Artiom
 * Date: 11.03.14
 * Time: 19:31
 *
 * The ArrayList class implementation.
 *
 * @author Artiom Butomov
 */
public class ArrayList<T> extends AbstractCollection<T> implements Collection<T> {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 1;

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    private T[] data;

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * Constructs an empty list with an initial capacity of one.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(){
        data=(T[])new Object[DEFAULT_CAPACITY];
        size=0;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param t element to be appended to this list
     * @return  true if element is appended
     */
    @Override
    public boolean add(T t) {
        if(size==data.length){
            increaseSize();
        }
        data[size]=t;
        size++;
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public T get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        return data[index];
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    public T remove(int index) {
        if(size==0) throw new EmptyStackException();
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        T temp = data[index];
        size--;
        for(int x=index; x<data.length-1;x++){
            data[x]=data[x+1];
        }
        return temp;
    }//remove method

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.
     * @param o element to be removed from this list, if present
     * @return "true" if this list contained the specified element
     */
    @Override
    public boolean remove(Object o){
        for (int index = 0; index < size; index++)
            if (o.equals(data[index])) {
                fastRemove(index);
                return true;
            }
        return false;
    }

    /**
     * Private remove method that skips bounds checking and does not
     * return the value removed.
     * @param index the index of the element to be removed
     */
    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(data, index+1, data, index,
                    numMoved);
        /**
         src -- This is the source array.
         srcPos -- This is the starting position in the source array.
         dest -- This is the destination array.
         destPos -- This is the starting position in the destination data.
         length -- This is the number of array elements to be copied.
         */
        data[--size] = null;// Let gc do its work
    }

    /**
     * Returns an iterator over the elements in this list in direct sequence.
     * @return an iterator over the elements in this list in direct sequence
     */
    @Override
    public ListIterator<T> iterator(){
        return new myIterator();
    }//iterator method

    public class myIterator implements ListIterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException("Iterator was ended in the ending");
            T temp = data[cursor++];
            return temp;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) throw new NoSuchElementException("Iterator was ended in the beginning");
            int i = cursor - 1;
            T[] objectData = ArrayList.this.data;
            cursor=i;
            return (T) objectData[cursor];
        }

        @Override
        public int nextIndex() {
            return cursor++;
        }

        @Override
        public int previousIndex() {
            return cursor--;
        }

        @Override
        public void remove() {
            ArrayList.this.remove(cursor);
            cursor--;
        }

        @Override
        public void set(T t) {
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new java.lang.UnsupportedOperationException();
        }
    }// class myIterator

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void increaseSize() {
        int capacity =data.length*2;
        T[] newData=(T[]) new Object[capacity];
        for(int i=0; i<data.length;i++){
            newData[i]=data[i];
        }
        data=newData;
    }//increaseSize method

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            data[i] = null;// GC do its work
        size = 0;
    }// clear
}