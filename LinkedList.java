import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

public class LinkedList {



    private int length=0;
    ListNode head=null;

    public LinkedList(){
        length=0;
    }



    public synchronized ListNode getHead(){
        return head;
    }

    public synchronized void insertAtBegin(ListNode node){
        node.setNext(head);
        head=node;
        length++;
    }

    public synchronized void insertAtEnd(ListNode node){

        node.setNext(null);
        if(head==null){
            head=node;
            return;
        }
        ListNode temp=head;
        while(temp.getNext()!=null){
            temp=temp.getNext();
        }
        temp.setNext(node);
        length++;
    }

    public synchronized void insert(int data, int position){
        if(position<0){
            position=1;
        }
        if(position>length){
            position=length+1;
        }
        ListNode node=new ListNode(data);
        int count=1;
        if(head==null){
            head=node;
            node.setNext(null);
        }else if(position==1){
            node.setNext(head);
            head=node;
        }
        else {
            ListNode temp = head;
            while (temp.getNext() != null) {
                if (count + 1 == position) {
                    break;
                }
                count++;
                temp = temp.getNext();
            }

                node.setNext(temp.getNext());
                temp.setNext(node);

        }
        length++;

    }

    public synchronized ListNode insertIntoSortedList(int data){
        ListNode temp=head;
        ListNode node=new ListNode(data);
        if(head==null){
            return node;
        }
        if(temp.getData()>data){
            node.setNext(head);
            head=node;
        }else {
            while (temp.getNext().getData() <= data) {
                temp = temp.getNext();

            }
            node.setNext(temp.getNext());
            temp.setNext(node);
        }
        return head;
    }

    public ListNode insertNode(int data){
        ListNode node=new ListNode(data);
        if(head==null){
            return node;
        }
        ListNode temp=null,current=head;
        while(current!=null && current.getData()<data){
            temp=current;
            current=current.getNext();
        }
        node.setNext(current);
        temp.setNext(node);
        return head;
    }

    public synchronized ListNode removeFromBegin(){
        if(head==null){
            return head;
        }else{
            ListNode temp=head;
            head=temp.getNext();
            temp.setNext(null);
            length--;
            return temp;
        }
    }

    public synchronized ListNode removeFromEnd(){
        if(head==null){
            return head;
        }
        ListNode temp=head;
        while(temp.getNext().getNext()!=null){
            temp=temp.getNext();
        }
        ListNode endNode=temp.getNext();
        temp.setNext(null);
        length--;
        return endNode;
    }

    public synchronized void removeMatchedNode(ListNode node){
        if(head==null){
            return;
        }
        ListNode temp=head;
        if(head.equals(node)){
            head=head.getNext();
            return;
        }
        while(temp.getNext()!=null){
            if(temp.getNext().equals(node)){
               temp.setNext(temp.getNext().getNext());
               break;
            }
            temp=temp.getNext();
        }
        length--;

    }

    public  synchronized void removeAtPosition(int position){
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
            return;
        }
        int count=1;
        ListNode temp=head;
        while(temp.getNext()!=null){
            if(count+1==position){
                break;
            }
            count++;
            temp=temp.getNext();
        }

        temp.setNext(temp.getNext().getNext());
        length--;
    }

    @Override
    public String toString() {
        StringBuffer listString=new StringBuffer();
        listString.append("[");
        if(head==null){
            listString.append("]");
            return listString.toString();
        }
        ListNode temp=head;
        listString.append(temp.getData());
        temp=temp.getNext();
        while(temp!=null){
            listString.append(",");
            listString.append(temp.getData());
            temp=temp.getNext();
        }
        listString.append("]");
        return listString.toString();
    }

    public int getLength() {
        return length;
    }

    public int getPosition(int data){
        int count=1;
        ListNode temp=head;
        while(temp!=null){
            if(temp.getData()==data){
                return count;
            }
            count++;
            temp=temp.getNext();
        }
        return Integer.MIN_VALUE;
    }

    public boolean isEmpty(){
        return length==0;
    }

    public void clearList(){
        head=null;
        length=0;
    }

    public int getNnodeFromEnd(int n){
        ListNode temp=head;
        int pos=1;
        if(length<n){
            return Integer.MIN_VALUE;

        }
        int m=length-n+1;


        while(temp!=null){
            if(pos==m){
                return temp.getData();
            }
                pos++;

            temp=temp.getNext();
        }
        return Integer.MIN_VALUE;

    }

    public int getNthNodeFromEnd2Ptrs(int n){
        ListNode t1,t2;
        if(length<n){
            return Integer.MIN_VALUE;
       }
        int pos=1;
        for(t1=head;t1.getNext()!=null;t1=t1.getNext()){
            if(pos==n){
                break;
            }
            pos++;
        }
        for(t2=head;t1.getNext()!=null;t1=t1.getNext(),t2=t2.getNext());
        return t2.getData();
    }

    //recursive definition..... code not working
 /*   int count=0;
    public ListNode getNthNodeFromEndRecursion(ListNode head,int n){

        if(head!=null){
            getNthNodeFromEndRecursion(head.getNext(),n);
            count++;
            if(count==n){
                return head;
            }
        }
        return head;
    }*/

    //Floyd alg: if fastptr and slowptr meet anywhere --> loop exists
    //doesn't work to find start node of loop as fastptr and slowptr can meet at a node that is not start node
    //works by extension that after finding loop exists, assign slowptr to head of LL and move both ptrs one step.
    //Their meeting place is start of loop
    public boolean findLoop(ListNode head){
        ListNode fast=head,slow=head;
        while(fast!=null && fast.getNext()!=null){
            fast=fast.getNext().getNext();
            slow=slow.getNext();
            if(fast==slow){
                System.out.println("loop exists");
                return true;
            }
        }
        return false;
    }

    //Floyd algo to get start of loop
    public ListNode startNodeOfLoop(ListNode head){
        ListNode fast=head,slow=head;
        boolean loop=false;
        while(fast!=null && fast.getNext()!=null){
            fast=fast.getNext().getNext();
            slow=slow.getNext();
            if(fast==slow){
                loop=true;
                break;
            }
        }
        if(loop){
            for(slow=head;fast!=slow;slow=slow.getNext(),fast=fast.getNext());
            return slow;
        }else
            return null;
    }

    //to get start node of loop
    public ListNode getStartNodeOfLoop(ListNode head){
        //Hashtable<Object,ListNode> htable=new Hashtable<>();
        HashMap<ListNode,Integer> hmap=new HashMap<>();
        ListNode temp=head;
        while(temp!=null){
            if(!hmap.containsKey(temp)){
                hmap.put(temp,temp.getData());
            }
            else{
                System.out.println("loop exists");
                return temp;
            }
            temp=temp.getNext();
        }
        return null;
    }

    public static ListNode reverseLL(ListNode head){
        ListNode prev=null,next=null,cur=head;
        while(cur!=null){
            next=cur.getNext();
            cur.setNext(prev);
            prev=cur;
            cur=next;
        }

        for(ListNode r=prev;r!=null;r=r.getNext()){
            System.out.print(r.getData()+"\t");
        }
        return prev;
    }

    //find merge point of 2 lists using stack
    public ListNode findMergeNode(LinkedList L1, LinkedList L2){
        Stack<ListNode> stack1=new Stack<>();
        Stack<ListNode> stack2=new Stack<>();
        ListNode node1=L1.head;
        ListNode node2=L2.head;
        while(node1.getNext()!=null){
            stack1.push(node1.getNext());
            node1=node1.getNext();
        }
        while(node2.getNext()!=null){
            stack2.push(node2.getNext());
            node2=node2.getNext();
        }
        ListNode t1=null,t2=null,t3=null,t4=null;
        while(!stack1.isEmpty() && !stack2.isEmpty()){
            t1=stack1.pop();
            t2=stack2.pop();
            if (t1.equals(t2)){
                t3=t1;
            }
            else {
                return t3;
            }
        }
        return null;
    }

    public ListNode checkForMergeNodeUsingHashMap(LinkedList L1, LinkedList L2){
        int l1=L1.length;
        int l2=L2.length;
        if(l1<l2){
            HashMap<ListNode,Integer> hmap1=new HashMap<>();
            ListNode temp1=L1.head;
            while(temp1.getNext()!=null){
                hmap1.put(temp1.getNext(),temp1.getNext().getData());
                temp1=temp1.getNext();
            }
            ListNode temp2=L2.head;
            while (temp2.getNext()!=null){
                if(hmap1.containsKey(temp2)){
                    return temp2;
                }else{
                    temp2=temp2.getNext();
                }
            }
        }else{
            HashMap<ListNode,Integer> hmap1=new HashMap<>();
            ListNode temp1=L2.head;
            while(temp1.getNext()!=null){
                hmap1.put(temp1.getNext(),temp1.getNext().getData());
                temp1=temp1.getNext();
            }
            ListNode temp2=L1.head;
            while (temp2.getNext()!=null){
                if(hmap1.containsKey(temp2)){
                    return temp2;
                }else{
                    temp2=temp2.getNext();
                }
            }
        }
        return null;
    }

    //using arrays sorting and searching
  /*  public ListNode checkMergeNodeUsingArrays(LinkedList L1, LinkedList L2){
        ListNode[] nodes1=new ListNode[L1.length];
        ListNode temp1=L1.head;
        for(int i=0;temp1.getNext()!=null;i++,temp1=temp1.getNext()){
            nodes1[i]=temp1.getNext();

        }

    }
*/
    //by taking length of 2 lists and d=l1-l2.
    public ListNode checkMergeNode(LinkedList L1, LinkedList L2){
        int l1=L1.length;
        int l2=L2.length;
        int d;
        ListNode temp1=L1.head;
        ListNode temp2=L2.head;
        if(l1>l2){
            d=l1-l2;
            while(d>0){
                temp1=temp1.getNext();
                d--;
            }
            while(temp1!= null && temp2!=null){
                if(temp1.equals(temp2)){
                    return temp1;
                }
                temp1= temp1.getNext();
                temp2=temp2.getNext();
            }
           // return temp1;
        }else{
            d=l2-l1;
            while(d>0){
                temp2=temp2.getNext();
                d--;
            }
            while(temp1!=null && temp2!=null){
                if(temp1.equals(temp2)){
                    return temp1;
                }
                temp1= temp1.getNext();
                temp2=temp2.getNext();
            }
            //return temp1;

        }
        return null;
    }

    public ListNode sort2Lists(LinkedList L1,LinkedList L2){
        if(L1.head==null){
            return L2.head;
        }
        if(L2.head==null){
            return L1.head;
        }
        LinkedList result=new LinkedList();
        ListNode temp1=L1.head;
        ListNode temp2=L2.head;
        int pos=1;
        while (temp1!=null && temp2!=null){
            if(temp1.getData()<temp2.getData()){

                result.insert(temp1.getData(),pos);
                temp1=temp1.getNext();
                pos++;
            }else{
                result.insert(temp2.getData(),pos);
                temp2=temp2.getNext();
                pos++;
            }
        }
        while(temp1!=null){
            result.insertAtEnd(temp1);
            temp1=temp1.getNext();
        }
        while(temp2!=null){
            result.insertAtEnd(temp2);
            temp2=temp2.getNext();
        }
        return result.head;
    }

    //See this version
    public  static ListNode sort2ListsSimplified(LinkedList L1,LinkedList L2){
        if(L1.head==null){
            return L2.head;
        }
        if(L2.head==null){
            return L1.head;
        }
        ListNode resHead=new ListNode(0);
        ListNode res=resHead;
        ListNode temp1=L1.head;
        ListNode temp2=L2.head;
        while(temp1!=null && temp2!=null){
            if(temp1.getData()<temp2.getData()){
                res.setNext(temp1);
                temp1=temp1.getNext();
                res=res.getNext();
            }else{
                res.setNext(temp2);
                temp2=temp2.getNext();
                res=res.getNext();
            }
        }
        while(temp1!=null){
                res.setNext(temp1);
                temp1=temp1.getNext();
        }
        while(temp2!=null){
            res.setNext(temp2);
            temp2=temp2.getNext();
        }
        return resHead.getNext();
    }

    public ListNode merge2ListsRecursively(ListNode head1, ListNode head2){
        if(head1==null){
            return head2;
        }
        if(head2==null){
            return head1;
        }
        ListNode resHead=new ListNode(0);
        if(head1.getData()<=head2.getData()){
            resHead=head1;
            resHead.setNext(merge2ListsRecursively(head1.getNext(),head2));
        }else{
            resHead=head2;
            resHead.setNext(merge2ListsRecursively(head1,head2.getNext()));
        }
        return resHead;
    }

    //Recursive
    public static ListNode reverseInPairs(ListNode head1){
         if(head1==null || head1.getNext()==null){ //only one element left....no 2nd element to reverse in pair
             return null;
         }
         ListNode temp=head1.getNext();
         head1.setNext(temp.getNext());
         temp.setNext(head1);
         head1=temp;
         head1.getNext().setNext(reverseInPairs(head1.getNext().getNext()));
         return head1;
    }

    //Iterative reverse in pairs
    public static ListNode reverseInPairsIterative(ListNode head1){
        ListNode temp1=null,newHead=null;
        while(head1!=null && head1.getNext()!=null){
            if(temp1!=null){
                temp1.getNext().setNext(head1.getNext());
            }
             temp1=head1.getNext();
             head1.setNext(head1.getNext().getNext());
             temp1.setNext(head1);
            if(newHead==null){
                newHead=temp1;
            }
             head1=head1.getNext();

        }
        return newHead;

    }

    //Reverse LL: L1->L2->.....->Ln-1->Ln to L1->Ln->L2->Ln-1->...
    //TC: O(n sqaure)
    public static ListNode reverseInPairsEnds(ListNode head){
        if(head==null){
            return head;
        }
        ListNode temp=head,temp2=temp.getNext();

       while(temp.getNext()!=null){
           temp2=temp.getNext();
           ListNode pair=LastButOneNode(temp);
           if(pair==temp){
               break;
           }
           pair.getNext().setNext(temp.getNext());
           temp.setNext(pair.getNext());
           pair.setNext(null);
           temp=temp2;
       }


         return head;
    }

    //with splitting list using 2 pointers at half and then reversing second list. Then mering two lists alternatively
    public static ListNode reverseAlternateEndsWithMerging(ListNode head){

        ListNode fast=head,slow=head,temp=head;
        while(fast.getNext()!=null && fast.getNext().getNext()!=null){
            fast=fast.getNext().getNext();
            slow=slow.getNext();
        }
        ListNode revNode=slow.getNext();
        slow.setNext(null);

       revNode=reverseLL(revNode);


        while(temp.getNext()!=null && revNode.getNext()!=null){
            ListNode h1=temp.getNext();
            ListNode r1=revNode.getNext();
            temp.setNext(revNode);
            revNode.setNext(h1);
            temp=h1;
            revNode=r1;
        }
        if(temp.getNext()!=null){
            revNode.setNext(temp.getNext());
            temp.setNext(revNode);
        }
        return head;
    }

    public static ListNode LastButOneNode(ListNode head){
        ListNode temp=head;
       /* while(fast.getNext()!=null && fast.getNext().getNext()!=null){
            fast=fast.getNext().getNext();
        }
        return fast;*/
        while(temp.getNext().getNext()!=null){
            temp=temp.getNext();
        }

        return temp;
    }

    public static void checkPalindrome(LinkedList linkedList){
        int[] a=new int[linkedList.length];
        int i=0;
        ListNode temp=linkedList.head;
        while(temp!=null){
            a[i]=temp.getData();
            i++;
            temp=temp.getNext();
        }
        i=0;
        int j=a.length-1;
        boolean palindrome=true;
        while(i<j){
            if(a[i]==a[j]){
                i++;
                j--;
            }else{
                palindrome=false;
                break;
            }
        }
        if(palindrome){
            System.out.println("Palindrome");
        }else {
            System.out.println("Not Palindrome");
        }
    }

    public static void checkPalindromeByReverse(ListNode head1){
        ListNode fast=head1,slow=head1;
        while(fast.getNext()!=null && fast.getNext().getNext()!=null){
            slow=slow.getNext();
            fast=fast.getNext().getNext();
        }
        ListNode cur=slow.getNext(),next,prev=null;
        while (cur!=null){
            next=cur.getNext();
            cur.setNext(prev);
            prev=cur;
            cur=next;
        }
        slow.setNext(prev);
        ListNode t1=head1,t2=slow.getNext();
        boolean palin=true;
        while(t2!=null && t1!=slow.getNext()){
            if(t1.getData()==t2.getData()) {
                t1 = t1.getNext();
                t2 = t2.getNext();
            }else{
                palin=false;
                break;
            }
        }
        if(palin){
            System.out.println("Palindrome");
        }else {
            System.out.println("Not Palindrome");

        }

        cur=slow.getNext();
        prev=null;
        while (cur!=null){
            next=cur.getNext();
            cur.setNext(prev);
            prev=cur;
            cur=next;
        }
        slow.setNext(prev);
    }
/*
//same as pairwise rversal
    public static ListNode exchangeAdjNodes(ListNode head1){

    }*/

    public static ListNode reverseKNodes(ListNode head1,int k){
        int count=1;
        ListNode temp1=head1,temp2=head1,temp3=null,newHead=null;
        ListNode temp4=new ListNode(0);
        while(temp1!=null) {

            while (temp1!=null) {
                temp1 = temp1.getNext();
                count++;
                if (count >= k) {
                    break;
                }
            }
            if(count<k ||temp1==null){
                break;
            }
            temp3=temp1.getNext();
            temp1.setNext(null);
            temp2 = reverseLL(temp2);
            temp4.setNext(temp2);
            if(newHead==null){
                newHead=temp4.getNext();
            }
            while (temp2.getNext() != null) {
                temp2 = temp2.getNext();
            }
            temp2.setNext(temp3);
            temp4=temp2;
            temp1=temp3;
            temp2=temp3;
            count = 1;
        }
        return newHead;
    }

    //Find modular node from beginning
    //find last modular node from beginning whose n%k==0 where n is #elements and k is some integer
    /*
    E.g.: If n=19 and k=3, return 18
     */

    public static ListNode modularNode(ListNode head, int k){
        if(k<=0){
            return null;
        }
        int i=1;
        ListNode temp=head;
        ListNode modular=null;
        while(temp!=null){
            if(i%k==0){
                modular=temp;
            }
            temp=temp.getNext();
            i++;
        }
        return modular;
    }

    /*
    Finding modular node (lsat element from beginning of the list) is similar to finding kth element from end of list.
    So another way is to find kth node from end
     */
    public static ListNode modularNodeFromEnd(ListNode head,int k){
        if(k<=0){
            return null;
        }
        int i=1;
        ListNode temp=head,modularNode=head;
        for(i=1;i<=k;i++){
            temp=temp.getNext();
        }
        for(modularNode=head;temp!=null;temp=temp.getNext(),modularNode=modularNode.getNext());
        return modularNode;
    }

    //Find n/k th element where n not known in advance
    // we find only the first fraction
    public static ListNode fractionalNode(ListNode head,int k){
        int i=1;
        if(k<=0){
            return null;
        }
        ListNode temp=head,fractionNode=null;
        for(temp=head;temp!=null;temp=temp.getNext()){
            if(i%k==0){
                if(fractionNode==null){
                    fractionNode=temp;
                }
            }
            i++;
        }
        return fractionNode;
    }

    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        LinkedList l1=new LinkedList();
        LinkedList l2=new LinkedList();
        LinkedList l3=new LinkedList();
        l3.insert(1,1);
        l3.insert(2,2);
        l3.insert(3,3);
        l3.insert(0,4);
        l3.insert(3,5);
        l3.insert(2,6);
        l3.insert(1,7);
        System.out.println(l3);
        System.out.println(PalindromeCheck.checkPalindrome(l3.head));

        l1.insert(3,1);
        l1.insert(4,2);
        l1.insert(3,3);
        l2.insert(5,1);
        l2.insert(6,2);
        l2.insert(4,3);
        ListNode sumHead=AddDigits2Lists.addTwoNumbers(l1.head,l2.head);

        for(ListNode t=sumHead;t!=null;t=t.getNext()){
            System.out.println(t.getData());
        }

        ListNode node1=new ListNode(1);
        linkedList.insertAtBegin(node1);
        ListNode node2=new ListNode(2);
        linkedList.insertAtEnd(node2);
        //ListNode node3=new ListNode(3);
        linkedList.insert(9,2);
        linkedList.insert(3,5);
        linkedList.insert(4,5);
        linkedList.insert(5,5);
        linkedList.insert(7,5);
        linkedList.insert(8,5);
        System.out.println(linkedList);
        ListNode temp;
        System.out.println("removing duplicates from l1");
        l1.head=RemoveDuplicatesFromLL.removeDuplicates(l1.head);
        System.out.println(l1);
        System.out.println("segregate even odd nodes");
        linkedList.head=SeparateEvenOdd.EvenOdd(linkedList.head);
        System.out.println(linkedList);
        System.out.println("Rearrange List based on k valued node");
        linkedList.head=RearrangeLLByknode.partitionAtKNode(linkedList.head,4);
        System.out.println(linkedList);
        System.out.println("Rotate by 4 places");
        linkedList.head=RotateList.rotateListByKPlaces1(linkedList.head,5);
        System.out.println(linkedList);
        System.out.println("Insertion sort");
        temp=InsertionSort_LinkedList.insertionSortLL(linkedList.head);
        linkedList.head=temp;
        System.out.println(linkedList);
    /*    System.out.println("modular node");
        temp=modularNodeFromEnd(linkedList.head,3);
        System.out.println(temp.getData());
        System.out.println("fractional node");
        temp=fractionalNode(linkedList.head,3);
        System.out.println(temp.getData());

        temp=reverseAlternateEndsWithMerging(linkedList.head);
        System.out.println("reversing at ends");
        for(ListNode n=temp;n!=null;n=n.getNext()){
            System.out.println(n.getData());
        }
        System.out.println();
        System.out.println(linkedList);
        System.out.println("Reverse k nodes");
        ListNode temp2=reverseKNodes(linkedList.head,4);
         temp=temp2;
        while (temp!=null){
            System.out.print(temp.getData()+" ");
            temp=temp.getNext();
        }
        System.out.println();
        checkPalindromeByReverse(linkedList.head);
        System.out.println("reverse in pairs");
        ListNode newH=reverseInPairsIterative(linkedList.head);
        temp=newH;
        while (temp!=null){
            System.out.print(temp.getData()+" ");
            temp=temp.getNext();
        }
        System.out.println();
        System.out.println(linkedList);
        LinkedList L2=new LinkedList();
        L2.insert(2,3);
        L2.insert(3,3);
        L2.insert(9,4);
        System.out.println("L2 "+L2);
        System.out.println("Merge");
        ListNode merge=linkedList.merge2ListsRecursively(linkedList.head,L2.head);
        //ListNode merge=linkedList.sort2Lists(linkedList,L2);
        //ListNode merge=sort2ListsSimplified(linkedList,L2);
        ListNode tempH=merge;
        while (tempH!=null){
            System.out.print(tempH.getData()+" ");
            tempH=tempH.getNext();
        }
        System.out.println();

        System.out.println("length is "+linkedList.length);
        linkedList.insert(5,5);
        System.out.println(linkedList);
        System.out.println("position of 3 is "+linkedList.getPosition(3));
        System.out.println("2nd node from end "+linkedList.getNnodeFromEnd(2));
        System.out.println("3 node from end using 2 ptrs "+linkedList.getNthNodeFromEnd2Ptrs(3));
        System.out.println("3 node from end using recursion "+linkedList.getNthNodeFromEndRecursion(linkedList.head,3).getData());
        System.out.println("reverse LL ");
        ListNode revHead=linkedList.reverseLL(linkedList.head);
        while(revHead!=null){
            System.out.print(revHead.getData()+" ");
            revHead=revHead.getNext();
        }
        System.out.println();
        linkedList.clearList();
        System.out.println("ll is"+linkedList);
        System.out.println(linkedList.findLoop(linkedList.head));
*/
    }
}
