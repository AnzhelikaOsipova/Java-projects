import java.util.Scanner;

/**
 * Created by Анастасия on 29.09.2017.
 */
public class MulOperation extends Operation{
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
        stack.arr[stack.getTop() - 1] = stack.arr[stack.getTop() ] *stack.arr[stack.getTop()-1];
        stack.pop();
    }
}
