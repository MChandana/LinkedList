
public class RotateList {

    //TC: O(nk)
    public static ListNode rotateListByKPlaces(ListNode head, int k){
        if(head==null || head.getNext()==null){
            return head;
        }
        while(k>0){
            ListNode t=head;
            for(;t.getNext().getNext()!=null;t=t.getNext());
            t.getNext().setNext(head);
            head=t.getNext();
            t.setNext(null);
            k--;
        }
        return head;
    }

    //TC: O(n) with find kth node
    public static ListNode rotateListByKPlaces1(ListNode head,int k){
        if(head==null || head.getNext()==null){
            return head;
        }
        ListNode temp1=head,temp2=head;
        while(k>0){
            temp1=temp1.getNext();
            if(temp1==null){
                temp1=head;
            }
            k--;
        }
        if(temp1==temp2){
            return head;
        }
        for(;temp1.getNext()!=null;temp1=temp1.getNext(),temp2=temp2.getNext());
        ListNode newHead=temp2.getNext();
        temp1.setNext(head);
        temp2.setNext(null);
        return newHead;
    }
}
