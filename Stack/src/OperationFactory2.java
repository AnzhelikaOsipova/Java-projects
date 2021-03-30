import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class OperationFactory2 {

    public DefArrays D = new DefArrays();
    public boolean frun = true;

    public OperationFactory2() {

    }

    public void createOperation(Stack S, String op, Scanner sc)
    {
        try {
            Class<?> cls = null;
            Method meth;
            switch(op)
            {
                case "+":
                    cls = Class.forName("StackPlus");
                    meth = cls.getMethod("perform", Stack.class, DefArrays.class, Scanner.class);
                    meth.invoke(new StackPlus(), S, D, sc);
                    break;
                case "-":
                    cls = Class.forName("StackMinus");
                    meth = cls.getMethod("perform", Stack.class, DefArrays.class, Scanner.class);
                    meth.invoke(new StackMinus(), S, D, sc);
                    break;
                case "*":
                    cls = Class.forName("StackMull");
                    meth = cls.getMethod("perform", Stack.class, DefArrays.class, Scanner.class);
                    meth.invoke(new StackMull(), S, D, sc);
                    break;
                case "/":
                    cls = Class.forName("StackDel");
                    meth = cls.getMethod("perform", Stack.class, DefArrays.class, Scanner.class);
                    meth.invoke(new StackDel(), S, D, sc);
                    break;
                case "sqrt":
                    cls = Class.forName("StackSqrt");
                    meth = cls.getMethod("perform", Stack.class, DefArrays.class, Scanner.class);
                    meth.invoke(new StackSqrt(), S, D, sc);
                    break;
                case "DEFINE":
                    cls = Class.forName("StackDefine");
                    meth = cls.getMethod("perform", Stack.class, DefArrays.class, Scanner.class);
                    meth.invoke(new StackDefine(), S, D, sc);
                    break;
                case "PUSH":
                    cls = Class.forName("StackPush");
                    meth = cls.getMethod("perform", Stack.class, DefArrays.class, Scanner.class);
                    meth.invoke(new StackPush(), S, D, sc);
                    break;
                case "POP":
                    cls = Class.forName("StackPop");
                    meth = cls.getMethod("perform", Stack.class, DefArrays.class, Scanner.class);
                    meth.invoke(new StackPop(), S, D, sc);
                    break;
                case "EXIT":
                    frun = false;
                    return;
                    
           }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
