public class SeparateEvenOdd {

    //all even numbers appear at the beginning
    public static ListNode seperateEvenOddNodes(ListNode head){
        if(head==null || head.getNext()==null){
            return head;
        }
        ListNode temp=head,end=head;
        int count=1;
        while(end.getNext()!=null){
            end=end.getNext();
            count++;
        }
        System.out.println(count);
        while(count>1){
            ListNode temp2=temp.getNext();
            if(temp.getData()%2!=0 && temp==head){
                end.setNext(temp);
                temp.setNext(null);
                head=temp2;
                temp=head;
                end=end.getNext();
            }
            else if(temp.getNext().getData()%2!=0){
                end.setNext(temp.getNext());
                temp.setNext(temp.getNext().getNext());
                end.getNext().setNext(null);
                end= end.getNext();
            }else{
                temp=temp.getNext();
            }
            count--;
        }
        return head;
    }

    //separating odd nodes into different list
    public static ListNode EvenOdd(ListNode head){
        ListNode oddHead=null,temp=head,odd=null;
        while(temp.getNext()!=null){
            if(temp.getData()%2!=0 && temp==head){
                if(oddHead==null) {
                    oddHead = temp;
                    odd = oddHead;
                }else{
                    odd.setNext(temp);
                    odd=odd.getNext();
                }
                head = head.getNext();
                temp.setNext(null);
                    temp=head;
                }else if(temp.getNext().getData()%2!=0){
                    odd.setNext(temp.getNext());
                    temp.setNext(temp.getNext().getNext());
                    odd.getNext().setNext(null);
                    odd=odd.getNext();
                }else {
                    temp=temp.getNext();
                }
            }

        temp.setNext(oddHead);
        return head;
    }
}
