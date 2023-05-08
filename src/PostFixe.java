import java.util.ArrayList;

public class PostFixe implements GetPostFixe{
    @Override
    public String postFixe(String op1, String op2, String operand){
        if(operand.equals("!"))
            return op1 + " " + "!";
        else
            return op1 + " " + op2 + " " + operand;
    }

    @Override
    public String reverse(String s ){
        String[] tokens = s.split(" ");
        return "";
    }
}
