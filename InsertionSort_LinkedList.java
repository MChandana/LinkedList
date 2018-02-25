public class InsertionSort_LinkedList {



    public static ListNode insertionSortLL(ListNode head){
        if(head==null || head.getNext()==null){
            return head;
        }
        ListNode newHead=new ListNode(head.getData());
        ListNode insert=head.getNext();
        while(insert!=null){
            ListNode nextN=insert.getNext();
            if(insert.getData()<=newHead.getData()){
                insert.setNext(newHead);
                newHead=insert;

            }else{
                ListNode temp=newHead;
                while(temp.getNext()!=null){
                    if(insert.getData()>temp.getData() && insert.getData()<=temp.getNext().getData()){
                        insert.setNext(temp.getNext());
                        temp.setNext(insert);
                        break;
                    }
                    temp=temp.getNext();
                }
                if(temp.getNext()==null && insert.getData()>temp.getData()){
                    temp.setNext(insert);
                    insert.setNext(null);
                }

            }
            insert=nextN;

        }

        return newHead;

    }
}
