import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MainClass {
    public static void main(String[] args)
    {
        List<Integer> a = new LinkedList<>();
        a.add(1);
        a.add(34);
        a.add(45);
        System.out.println(a.toString());

        MyList b = new MyList();
        b.add(1);
        b.add(34);
        b.add(45);
        System.out.println(b.toString());

    }
}
