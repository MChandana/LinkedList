public class RearrangeLLByknode {


    //TC: O(nk) 2 loops
    public static ListNode partitionByKthNode(ListNode head,int k){
        if(head==null || head.getNext()==null){
            return head;
        }
        ListNode temp=head,newHead=null;
        if(head.getData()==k){
            newHead=new ListNode(head.getData());
            head=head.getNext();
        }else {
            while (temp != null) {
                if (temp.getNext().getData() == k) {
                    newHead = new ListNode(temp.getNext().getData());
                    temp.setNext(temp.getNext().getNext());
                    break;
                }
                temp = temp.getNext();
            }
        }
        ListNode index=newHead,temp2,t;
        temp=head;
        while(temp!=null){
            temp2=temp.getNext();
            temp.setNext(null);
            if(temp.getData()<index.getData()){
                if(index==newHead){
                    temp.setNext(index);
                    newHead=temp;
                }else {
                    for (t = newHead; t.getNext() != index; t = t.getNext()) ;
                    temp.setNext(index);
                    t.setNext(temp);
                }


            }else{
                t=index;
                for(;t.getNext()!=null;t=t.getNext());
                t.setNext(temp);
                temp.setNext(null);
            }
           temp=temp2;
        }
        return newHead;
    }

    //TC: O(n)
    public static ListNode partitionAtKNode(ListNode head, int k){
        if(head==null || head.getNext()==null){
            return head;
        }
        ListNode newHead=new ListNode(k);
        ListNode pivot=newHead;
        ListNode temp=head,start=pivot,end=pivot;
        boolean first=true;
        while(temp!=null){
            ListNode temp2=temp.getNext();
           if(temp.getData()==pivot.getData() && first){
                first=false;
            }
            else if(start==pivot && temp.getData()<k){
                temp.setNext(newHead);
                newHead=temp;
                start=temp;
            }
            else if (temp.getData() < k) {
                    temp.setNext(start.getNext());
                    start.setNext(temp);
                    start = temp;
                } else {
                    end.setNext(temp);
                    end.getNext().setNext(null);
                    end=end.getNext();

                }
                temp=temp2;
        }
        return newHead;
    }
}
