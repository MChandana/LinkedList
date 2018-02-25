import java.util.Stack;

public class PalindromeCheck
{
    //Str 1210121
    //using stack
    public static boolean checkPalindrome(ListNode head){
        boolean palindrome=true;
        Stack<Integer> stack=new Stack<>();
        ListNode temp=head;
        while (temp.getData()!=0){
            stack.push(temp.getData());
            temp=temp.getNext();
        }
        temp=temp.getNext();
        while(temp!=null && !stack.isEmpty()){
            int compare=stack.pop();
            if(compare!=temp.getData()){
                 return false;
            }
            temp=temp.getNext();

        }
        return true;
    }
}
