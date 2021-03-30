import java.util.Scanner;

/**
 * Created by Анастасия on 07.10.2017.
 */
public class PushOperation extends Operation {
    String line;
    public void perform(Stack stack, Scanner sc){
        //String line=sc.next();
        boolean f=false;
        for (int i=0;((i<DefineOperation.b)&&(f==false));i++){
            if (line.equals(DefineOperation.arr[0][i])) {
                stack.push(Integer.valueOf(DefineOperation.arr[1][i]));
                f=true;
            }
        }
        if (f==false)
            stack.push(Integer.valueOf(line));
    }
    PushOperation(String s1){
        line=s1;
    }
}
