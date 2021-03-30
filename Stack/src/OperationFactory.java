import java.util.Scanner;

class DefArrays {
    public String Def1[] = new String[20];
    public double Def2[] = new double[20];
    public int cntW = 0;
}

public class OperationFactory {
    public DefArrays D = new DefArrays();
    public boolean frun = true;

    public OperationFactory() {

    }

    public Operation createOperation(String op, Scanner sc)
    {
        switch(op)
        {
            case "+":
                return new StackPlus();
            case "-":
                return new StackMinus();
            case "*":
                return new StackMull();
            case "/":
                return new StackDel();
            case "sqrt":
                return new StackSqrt();
            case "DEFINE":
                return new StackDefine();
            case "PUSH":
                return new StackPush();
            case "POP":
                return new StackPop();
            case "EXIT":
                frun = false;

        }
        return null;
    }
}
