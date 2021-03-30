import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;



public class MainClass {

    /*void DecToQ() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int Q = Integer.parseInt(s);
        s = br.readLine();
        int dec = Integer.parseInt(s);
        System.out.println(Integer.toString(dec, Q));
    }*/

    public static void main(String[] args) {
        Stack S = new Stack();
        Scanner sc = new Scanner(System.in);
        /*//фабрика без рефлексии
        OperationFactory factory = new OperationFactory();
        while(factory.frun) {
            String line = sc.next();
            Operation op = factory.createOperation(line, sc);
            if (op != null)
                op.perform(S, factory, sc);
        }*/
        /* рефлексия */
        try {
            OperationFactory2 f2 = new OperationFactory2();
            Class<?> cls = Class.forName("OperationFactory2");
            Method meth = cls.getMethod("createOperation", Stack.class, String.class, Scanner.class);
            Field field = cls.getField("frun");
            Boolean value = (Boolean)field.get(f2);

            while(value)
            {
                String line = sc.next();
                meth.invoke(f2, S, line, sc);
                value = (Boolean)field.get(f2);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
