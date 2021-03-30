import java.util.Scanner;

/**
 * Created by Анастасия on 07.10.2017.
 */
public class DefineOperation extends  Operation{
    static int b=0;
    static String arr[][];
    public void perform(Stack stack, Scanner sc){
        if (b==0);
            //def(stack);
        b++;

    }
    DefineOperation(String s1,String s2){
        arr=new String[2][10];
        arr[0][b]=s1;
        arr[1][b]=s2;
    }
    static public void def(Stack stack){
        arr=new String[2][stack.getLenght()];
    }
}
