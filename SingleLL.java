public class SingleLL {
    private int data;
    private SingleLL next;
    private SingleLL head=null;



    SingleLL(int data){
        this.data=data;
    }

    public void setData(int data)
    {
        this.data=data;
    }

    public int getData(){
        return data;
    }

    public void setNext(SingleLL next){
        this.next=next;
    }

    public SingleLL getNext(){
        return next;
    }

    public static void main(String arg[]){
        SingleLL sl=new SingleLL(1);
    }
}
