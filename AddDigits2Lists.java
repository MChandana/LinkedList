public class AddDigits2Lists {

    //assume same size for both lists
    public static ListNode addTwoNumbers(ListNode head1,ListNode head2){
        if(head1==null){
            return head1;
        }
        if(head2==null){
            return head2;
        }
        //recursive solution for numbers stored in list in same order and not in reverse order as this program does
      /*  ListNode newHead=null;
        if(head1.getNext()==null && head2.getNext()==null){
            return head1;
        }

       addTwoNumbers(head1.getNext(),head2.getNext());
        if(newHead==null){
            newHead=new ListNode(head1.getData()+head2.getData());
        }
        */
      ListNode newHead=null;
      int carry=0;
      while(head1!=null && head2!=null){
          int sum=head1.getData()+head2.getData();

          if(carry==1){
              sum=sum+1;
              carry=0;
          }
          ListNode temp;
          if(sum<=9){
              temp=new ListNode(sum);
          }else{
              int remainder=sum%10;
              temp=new ListNode(remainder);
              carry=1;

          }
          if(newHead==null){
              newHead=temp;
          }else {
              temp.setNext(newHead);
              newHead = temp;

          }
          head1 = head1.getNext();
          head2 = head2.getNext();
      }

      //  newHead=LinkedList.reverseLL(newHead);  //if you want end resukt to be reverse as well
      return newHead;
    }
}
