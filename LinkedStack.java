import java.util.EmptyStackException;

public class LinkedStack<T> {

    private ListNode top;
    private int length;

    LinkedStack(){
        top=null;
        length=0;
    }

    public void push(int data){
        ListNode temp=new ListNode(data);
        temp.setNext(top);
        top=temp;
        length++;
    }

    public boolean isEmpty(){
        return length==0;
    }

    public int pop() throws EmptyStackException{
        if(isEmpty()){
            throw new EmptyStackException();
        }

        ListNode temp=top;
        top=top.getNext();
        temp.setNext(null);
        length--;
        return temp.getData();
    }

    public int peek() throws EmptyStackException{
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.getData();
    }

    public int size(){
        return length;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        if(top!=null){
            sb.append(top.getData());
            ListNode temp=top.getNext();
            while(temp!=null){
                sb.append(","+temp.getData());
                temp=temp.getNext();
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedStack stack=new LinkedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);

    }
}
