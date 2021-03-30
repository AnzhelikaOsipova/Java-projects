import java.util.Scanner;

/**
 * Created by Анастасия on 29.09.2017.
 */
public class DelOperation extends Operation {
    public void perform(Stack stack,Scanner sc){
        try {
            if (stack.getTop()==0)
                throw new MyExeption();
        }
        catch (MyExeption e){
            System.out.println("Недостаточно операндов для выполнения действия");
            return;
        }
        try {
            if (stack.arr[stack.getTop() - 1] == 0)
                throw new MyExeption();
        } catch (MyExeption e){
            System.out.println("Деление на ноль невозможно");
            return;
        }
        stack.arr[stack.getTop() - 1] = stack.arr[stack.getTop()] / stack.arr[stack.getTop() - 1];
        stack.pop();
    }
}
