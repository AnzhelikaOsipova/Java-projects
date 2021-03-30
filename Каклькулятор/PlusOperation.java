import java.util.Scanner;

/**
 * Created by Анастасия on 28.09.2017.
 */
public class PlusOperation extends Operation {

    public PlusOperation(){

    }
    public PlusOperation(String  name){
        this();
    }

    public void perform(Stack stack, Scanner sc){
        try {
            if (stack.getTop()==0)
                throw new MyExeption();
        }
        catch (MyExeption e){
            System.out.println("Недостаточно операндов для выполнения действия");
            return;
        }
        double a = stack.arr[stack.getTop() - 1];
        stack.arr[stack.getTop() - 1] = stack.arr[stack.getTop() - 1] + stack.arr[stack.getTop()];
        stack.pop();
    }

}
