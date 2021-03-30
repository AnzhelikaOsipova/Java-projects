import java.util.Scanner;

public class StackDefine implements Operation {

    @Override
    public void perform(Stack S, DefArrays f, Scanner sc) {
        String line1 = sc.next();
        f.Def1[f.cntW] = line1;
        line1 = sc.next();
        f.Def2[f.cntW++] = Double.valueOf(line1);
    }
}
