import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RandomList {

    private RandomListNode head=null;
    private int length=0;

    public RandomList(){
        this.length=0;
    }

    //TC: O(n) SC: O(n)
    //cloning random list
    //wee do 2 iterations because to do random pointers in the first iteration nodes that are pointed by random aren't present yet
    public RandomListNode clone(RandomListNode head){
        Map<RandomListNode,RandomListNode> map=new HashMap<>();
        RandomListNode temp=head;
        while(temp!=null){
            RandomListNode tempNode=new RandomListNode(temp.getData());
            tempNode.setNext(null);
            tempNode.setRandom(null);
            map.put(temp,tempNode);
            temp=temp.getNext();
        }
      /*  Set<Map.Entry<RandomListNode,RandomListNode>> set=map.entrySet();
        Iterator<Map.Entry<RandomListNode,RandomListNode>> itr=set.iterator();
        RandomListNode newHead=null;
        while(itr.hasNext()){
            Map.Entry<RandomListNode,RandomListNode> entry=itr.next();
            RandomListNode x=entry.getKey();
            RandomListNode y=entry.getValue();
            y.setNext(x.getNext());
            y.setRandom(x.getRandom());
            if(x.equals(head)){
                newHead=y;
            }
        }
        */
        temp=head;
        RandomListNode newHead=null;
        while(temp!=null){
            RandomListNode y=map.get(temp);
            y.setNext(map.get(temp.getNext()));
            y.setRandom(map.get(temp.getRandom()));
           /* if(temp.equals(head)){
                newHead=y;
            }
            temp=temp.getNext();  */
        }
        //return newHead;
        return map.get(head);

    }

    public static void main(String[] args) {
        //Assume creation of Random List with next pointing to next node in the list and random pointing to any random node in the list
    }
}
