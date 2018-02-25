public class DoubleLL {

    private DoubleListNode head;
    private DoubleListNode tail;
    private int length;

    public DoubleLL(){
        length=0;
        head=new DoubleListNode(Integer.MIN_VALUE,null,null);
        tail=new DoubleListNode(Integer.MIN_VALUE,head,null);
        head.setNext(tail);
    }

    //get value at given position
    public int get(int position){
        return Integer.MIN_VALUE;
    }

    public int getPosition(int data){
        DoubleListNode temp=head.next;
        int pos=0;
        while(temp!=tail){
            pos++;
            if(temp.getData()==data){
               return pos;
            }
            temp=temp.getNext();

        }
            return Integer.MIN_VALUE;
    }

    public int getLength(){
        return length;
    }

    //add value to front of list
    public synchronized void insertAtBegin(int data){
        DoubleListNode temp=new DoubleListNode(data,head,head.getNext());
        temp.getNext().setPrev(temp);
        head.setNext(temp);
        length++;
    }

    public synchronized void insert(int data,int position){
        DoubleListNode temp=new DoubleListNode(data);
        int pos=1;
        DoubleListNode temp1=head.next;
        if(position<0){
            position=1;
        }
        if(position>length){
            position=length;
        }

        if(head==null){
            head=temp;
            tail=head;
        }

        if(position==1){
            temp.setNext(head);
            head=temp;
        }
        else {

            temp1 = temp1.getNext();
            while (temp1!= tail) {
                pos++;
                if (pos == position) {
                    temp.setNext(temp1);
                    temp.setPrev(temp1.getPrev());
                    temp.getPrev().setNext(temp);
                    temp1.setPrev(temp);
                    break;
                }
                temp1=temp1.getNext();
            }
        }
        length++;

    }

    public synchronized void insertAtTail(int data){
        DoubleListNode temp=new DoubleListNode(data,tail.getPrev(),tail);
        temp.getPrev().setNext(temp);
        tail.setPrev(temp);
        length++;
    }

    public void remove(int position){
        if(position<0){
            position=1;
        }
        if(position>length){
            position=length;
        }
        if(head==null){
            return;
        }
        if(position==1){
            head=head.getNext();
            if(head==null)
            {
            tail=null;
            }
            return;
        }
        int pos=0;
        DoubleListNode temp=head.next;
        //temp=temp.getNext();
        while(temp!=tail){
            pos++;
            if(pos==position){
               temp.getNext().setPrev(temp.getPrev());
               temp.getPrev().setNext(temp.getNext());
               return;
            }
            temp=temp.getNext();
        }
        length--;
    }

    public synchronized void removeMathedNode(DoubleListNode node){
        if(head==null){
            return;
        }
         DoubleListNode temp=head.next;
        if(head.equals(node)){
            head=head.getNext();
            if(head==null){
                tail=null;
            }
            length--;
            return;
        }
        while(temp!=tail){
            if(temp.equals(node)){
                temp.getNext().setPrev(temp.getPrev());
                temp.getPrev().setNext(temp.getNext());
                return;
            }
            temp=temp.getNext();
        }
        length--;
    }

    public synchronized void removeHead(){
        if(head==null){
            return;
        }
        head=head.next;
        if(head==null){
            tail=null;
        }
        length--;
    }

    public int removeHeadReturnData(){
        if(head==null){
            return Integer.MIN_VALUE;
        }
        DoubleListNode temp=head;
        head.next=head.next.next;
        temp.next.prev=head;
        length--;
        return temp.getData();
    }

    public int removeTail(){
        if(tail==null){
            return Integer.MIN_VALUE;
        }
        DoubleListNode temp=tail;
        tail.prev=tail.prev.prev;
        temp.prev.next=tail;
        length--;
        return temp.getData();
    }

    public String toString(){
        StringBuffer sb=new StringBuffer("[");
        if(length!=0){

            DoubleListNode temp=head.next;
            sb.append(temp.getData());
            temp=temp.next;
            while(temp!=tail){
                sb.append(","+temp.getData());
                temp=temp.next;
            }

        }
        String result=sb.toString();
        return result+"]";
    }

    public void clearList(){
        head=null;
        tail=null;
        length=0;
    }

    public static void main(String[] args) {
        DoubleLL dlist=new DoubleLL();
        dlist.insertAtBegin(1);
        dlist.insertAtTail(4);
        dlist.insert(3,2);
        dlist.insert(5,3);
        System.out.println("length is "+dlist.getLength());
        System.out.println("dlist is "+dlist);
        dlist.remove(3);
        System.out.println("dlist is "+dlist);
        dlist.removeHead();
        System.out.println("dlist is "+dlist);
        dlist.removeTail();
        System.out.println("dlist is "+dlist);

    }


}
