/**
 * Created by Анастасия on 29.09.2017.
 */
import java.math.*;
import java.util.Scanner;

public class SqrtOperation extends Operation {
    public void perform(Stack stack, Scanner sc){
        stack.arr[stack.getTop()] = Math.sqrt(stack.arr[stack.getTop() ] );
    }
}
