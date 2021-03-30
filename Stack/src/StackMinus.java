import java.util.Scanner;

public class StackMinus implements Operation{

    public StackMinus(){

    }

    @Override
    public void perform(Stack S, DefArrays f, Scanner sc) {
        String a1 = S.pop(), b1 = S.pop();
        double a, b;
        int i = S.corrDef(a1, f);
        if(i != -1)
            a = f.Def2[i];
        else
            a = Double.valueOf(a1);
        i = S.corrDef(b1, f);
        if(i != -1)
            b = f.Def2[i];
        else
            b = Double.valueOf(b1);
        S.push(String.valueOf(b - a));
    }
}