import java.io.*;
import java.util.Scanner;

/**
 * Created by Анастасия on 28.09.2017.
 */
public class Stack {
    final int n = 15;
    double arr[] = new double[n];
    private int top_;

    public int getTop() {
        return top_;
    }
    public int getLenght(){return n;};
    public Stack() {
        top_ = -1;
    }

    public void push(double x) {
        if (top_ + 1 >= n) {
            System.out.println("Stack overflow");
            return;
        }
        top_ += 1;
        arr[top_] = x;

    }

    public void pop() {
        if (top_ - 1 < 0) {
            System.out.println("Stack underflow");
            return;
        }
        top_ -= 1;
    }

    public void print() {
        System.out.println(arr[top_]);
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        for (int i = 0; i < 2; i++) {
            stack.push((double) i+1);
            System.out.print(stack.arr[i] + " ");
        }
        System.out.println();
        Scanner sc = new Scanner(System.in);
        OperationFactory factory =new OperationFactory();

        FileOutputStream out=null;


            try{
                FileInputStream fstream =new FileInputStream("D://Универ//Программирование//Java//Projects//Calculator — копия//src//input.txt");
                FileOutputStream ostream =new FileOutputStream("D://Универ//Программирование//Java//Projects//Calculator — копия//src//output.txt");
                BufferedReader br =new BufferedReader(new InputStreamReader(fstream));
                String strLine;

                while((strLine=br.readLine())!=null){
                    byte[] buf;
                    String text;

                        Operation op = factory.createOp(strLine);
                        op.perform(stack, sc);
                        for (int i = 0; i <= stack.getTop(); i++) {
                             text= String.valueOf(stack.arr[i]+" ");
                             buf=text.getBytes();
                            ostream.write(buf,0,buf.length);
                            //System.out.print(stack.arr[i] + " ");
                        }
                    text=String.valueOf("\n");
                    buf=text.getBytes();
                    ostream.write(buf,0,buf.length);//не работает
                }
            }
            catch (IOException e){
                System.out.println("Файл не найден");
            }
            //String line = sc.next();
        }

}
