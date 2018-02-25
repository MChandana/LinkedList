import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoveDuplicatesFromLL {
    public static ListNode removeDuplicates(ListNode head){
        if(head==null || head.getNext()==null){
            return head;
        }
        Set<Integer> set=new HashSet<>();
        ListNode temp=head;
        set.add(temp.getData());
        while(temp.getNext()!=null){
            if(!set.contains(temp.getNext().getData())){
                set.add(temp.getNext().getData());
                temp=temp.getNext();
            }else{
                temp.setNext(temp.getNext().getNext());
            }
        }
        return head;
    }

    //using Map with boolean
    //not really useful... same as Set implementation
    public static ListNode removeDuplicatesWithMap(ListNode head){
        Map<Integer,Boolean> map=new HashMap<>();
        ListNode temp=head;
        map.put(temp.getData(),true);
        while(temp.getNext()!=null){
            if(!map.containsKey(temp.getNext().getData())){
                map.put(temp.getNext().getData(),true);
                temp=temp.getNext();
            }else {
                temp.setNext(temp.getNext().getNext());
            }
        }
        return head;
    }
}
