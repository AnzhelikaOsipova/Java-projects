import java.util.Scanner;

public class StackPush implements Operation{

    public StackPush(){

    }

    @Override
    public void perform(Stack S, DefArrays f, Scanner sc) {
        String line1 = sc.next();
        S.push(line1);
    }
}