import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class UnrolledLinkedList<E> extends AbstractList<E> implements List<E>,Serializable {

    //max elements to be stored in a sinlge node
    private int nodeCapacity;
    //current size of lise
    private int size;
    //first and last nodes of list
    private ULLNode firstNode,lastNode;


    private class ULLNode{
        ULLNode next,prev;
        int numOfElements=0;
        Object[] elements;
        ULLNode(){
            elements=new Object[nodeCapacity];
        }
    }

    private class ULLIterator implements ListIterator<E>{

        ULLNode curr;
        int ptr,index;
        private int expectedModCount=modCount;
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }

    public UnrolledLinkedList(int nodeCapacity) throws IllegalArgumentException{
        if(nodeCapacity<8){
            throw new IllegalArgumentException("node capacity<8");
        }
        this.nodeCapacity=nodeCapacity;
        firstNode=new ULLNode();
        lastNode=firstNode;
    }

    public UnrolledLinkedList(){
        this(16);
    }

   public boolean isEmpty(){
        return size==0;
   }

    public boolean contains(Object o){
       return indexOf(o)!=-1;
    }

   /* public Iterator<E> iterator(){
      //  return new ULLIterator(firstNode,0,0);
    }
*/




    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }
}
