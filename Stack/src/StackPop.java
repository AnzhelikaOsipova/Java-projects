import java.util.Scanner;

public class StackPop implements Operation{

    public StackPop(){

    }

    @Override
    public void perform(Stack S, DefArrays f, Scanner sc) {
        String line1 = S.pop();
        System.out.println(line1);
    }
}