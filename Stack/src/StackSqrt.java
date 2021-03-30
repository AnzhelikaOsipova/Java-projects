import java.util.Scanner;

import static java.lang.Math.sqrt;

public class StackSqrt implements Operation{

    public StackSqrt(){

    }

    @Override
    public void perform(Stack S, DefArrays f, Scanner sc) {
        String a1 = S.pop();
        double a;
        int i = S.corrDef(a1, f);
        if(i != -1)
            a = f.Def2[i];
        else
            a = Double.valueOf(a1);
        S.push(String.valueOf(sqrt(a)));
    }
}