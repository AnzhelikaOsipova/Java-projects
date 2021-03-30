import java.util.Map;
import java.util.Scanner;

/**
 * Created by Анастасия on 29.09.2017.
 */
public class OperationFactory {
    //static int i=0;
    String[] s;
    public  Operation createOp(String op){
             s = op.split(" ");


        int a=op.length();
        switch (s[0]){
            case "+":{
                return new PlusOperation();
            }
            case "-":{
                return  new MinOperation();
            }
            case "*":{
                return new MulOperation();
            }
            case "/":{
                return  new DelOperation();
            }
            case "sqrt":{
                return  new SqrtOperation();
            }
            case "DEFINE":{ //
                return  new DefineOperation(s[1],s[2]);

                //массив из 2 эл
            }
            case "PUSH":{ //
                return  new PushOperation(s[1]);
                //массив из 2 эл
            }
        }
        try {
            throw new MyExeption();
        }
        catch (MyExeption e){
            System.out.println("Указанная операция не существует");
            return null;
        }
        //return  null;
    }
}
