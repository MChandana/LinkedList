public class CircularLL {

   private CLLNode head;
   private int length;

   public CircularLL(){
       length=0;
       head=null;
   }

   public synchronized void addToHead(int data){
        CLLNode temp=new CLLNode(data);
        if(head==null){
            head=temp;
            length++;
            return;
        }
        //temp.setNext(temp);
       temp.setNext(head);
       CLLNode temp1=head;
       while(temp1.getNext()!=head){
           temp1=temp1.getNext();
       }
       temp1.setNext(temp);
       head=temp;
       length++;
   }

   public void addToTail(int data){
       CLLNode temp=new CLLNode(data);
       if(head==null){
           head=temp;
           length++;
           return;
       }
       CLLNode temp1=head;
       while(temp1.getNext()!=head){
           temp1=temp1.getNext();
       }
       temp.setNext(temp1.getNext());
       temp1.setNext(temp);
       length++;
   }

   //returns data at head of list
   public int peek(){
       return head.getData();
   }

   public int removeFromHead(){
       if(head==null){
           return Integer.MIN_VALUE;
       }
       CLLNode temp=head;
       CLLNode temp1=head;
       if(head.getNext()==head){
           head=null;
       }else {
           while (temp1.getNext() != head) {
               temp1 = temp1.getNext();
           }
           temp1.setNext(temp1.getNext().getNext());
           head = head.getNext();
       }
       length--;
       temp.setNext(null);
       return temp.getData();
   }

   public int removeFromTail(){
       CLLNode temp1=head;
       CLLNode temp=null;
       if(head==null){
           return Integer.MIN_VALUE;
       }
       if(head.getNext()==head){
           head=null;
       }else{
           //CLLNode temp1=head;
           while(temp1.getNext().getNext()!=head){
               temp1=temp1.getNext();
           }
           temp=temp1.getNext();
           temp1.setNext(temp1.getNext().getNext());
           temp.setNext(null);
       }
       length--;
       return temp.getData();
   }

   public boolean contains(int data){
       CLLNode temp1=head;
       if(head==null){
           return false;
       }
       if(head.equals(head.getNext())){
           if(head.getData()==data){
               return true;
           }
       }
       while(temp1.getNext()!=head){
           if(temp1.getData()==data){
               return true;
           }
           temp1=temp1.getNext();
       }
       return false;
   }

   public int remove(int data){
       CLLNode temp1=head;
       CLLNode temp=null;
       if(head==null){
           return Integer.MIN_VALUE;
       }
       if(head.equals(head.getNext()) && head.getData()==data){
               int val=head.getData();
               head=null;
               length--;
               return val;

       }

       if(head.getData()==data){
           while(temp1.getNext()!=head){
               temp1=temp1.getNext();
           }
           temp=temp1.getNext();
           temp1.setNext(temp.getNext());
           temp.setNext(null);
           return temp.getData();
       }

       while(temp1.getNext()!=head){
           if(temp1.getNext().getData()==data){
               temp=temp1.getNext();
               temp1.setNext(temp1.getNext().getNext());
               temp.setNext(null);
               return temp.getData();
           }
       }
       return Integer.MIN_VALUE;
   }

   public int getLength(){
       return length;
   }

   public int size(){
       return length;
   }

   public boolean isEmpty(){
       //return (length==0)?true:false;
       return head==null;
   }

   public void clear(){
       head=null;
       length=0;
   }

   public String toString(){
       StringBuffer sb=new StringBuffer("[");
       //String s="[";
       CLLNode temp=head;
       if(!head.equals(null)){
           sb.append(temp.getData());
           //s=s+temp.getData();
            temp=temp.getNext();
           while(temp!=head){
               sb.append(","+temp.getData());
               //s=s+","+temp.getData();
               temp=temp.getNext();
           }
       }
       sb.append("]");
       String s=sb.toString();
       return s;
   }

   public static void splitCLL(CLLNode head1){
        CLLNode fast=head1,slow=head1,newHead=null;
        while(fast.getNext().getNext()!=head1 && fast.getNext()!=head1){
            slow=slow.getNext();
            fast=fast.getNext().getNext();
        }
        if(fast.getNext().getNext()==head1) {
            fast.getNext().setNext(slow.getNext());
            slow.setNext(head1);

        }
        if(fast.getNext()==head1){
            fast.setNext(slow.getNext());
            newHead=fast.getNext();
            slow.setNext(head1);
        }
        CLLNode h1=head1;
        CLLNode h2=newHead;
       System.out.print(h1.getData()+" ");
       h1=h1.getNext();
        while(h1!=head1){
            System.out.print(h1.getData()+" ");
            h1=h1.getNext();
        }
       System.out.println();
       System.out.print(h2.getData());
       h2=h2.getNext();
       while(h2!=newHead){
           System.out.print(h2.getData()+" ");
           h2=h2.getNext();
       }
       System.out.println();
   }

   //n=no.of nodes in CLL.
    //m=no.of nodes to skip
    //O(n)
   public static CLLNode JosephusCircle(CLLNode head1,int n,int m){
       int count=1;
       CLLNode temp=head1,t1=head1;
       while (temp.getNext()!=temp){
           if(count<m){
               t1=temp;
               temp=temp.getNext();
               count++;
           }else{
                t1.setNext(temp.getNext());
                count=1;
                temp=t1.getNext();
           }
       }
       return t1;
   }

   //O(mn)
   public static CLLNode JosephusCircle2Loops(CLLNode head1,int n,int m){
       int i;
       CLLNode temp1=head1;
       while(temp1.getNext()!=temp1){
           for(i=1;i<m-1;i++){
               temp1=temp1.getNext();
           }
           temp1.setNext(temp1.getNext().getNext());
           temp1=temp1.getNext();
       }
       return temp1;
   }

    public static void main(String[] args) {
        CircularLL clist=new CircularLL();
        clist.addToHead(1);
        clist.addToTail(2);
        clist.addToTail(3);
        clist.addToTail(4);
        clist.addToTail(5);
        System.out.println("Josephus winner");
       // CLLNode winner=JosephusCircle2Loops(clist.head,clist.length,2);
        //System.out.println(winner.getData());
        System.out.println("split");
        splitCLL(clist.head);
        System.out.println("clist is "+clist);
        clist.removeFromHead();
        System.out.println("clist is "+clist);
//        clist.removeFromTail();
        System.out.println("clist is "+clist);
        clist.addToTail(7);
        System.out.println("clist is "+clist);

        clist.remove(7);
        System.out.println("clist is "+clist);

    }
}
