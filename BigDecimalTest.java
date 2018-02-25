import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal value=new BigDecimal(-0);
        if(value.compareTo(BigDecimal.ZERO)>0){
            System.out.println("positive");
        }
        if(value.compareTo(BigDecimal.ZERO)<0){
            System.out.println("negative");
        }
        if(value.compareTo(BigDecimal.ZERO)==0){
            System.out.println("zero");
        }
    }
}
