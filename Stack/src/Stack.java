public class Stack {

    private int MAX_STACK_EL = 20;
    private String S_[] = new String[MAX_STACK_EL];
    private int top_;

    Stack()
    {
        top_ = -1;
    }

    boolean isEmpty()
    {
        if (top_ == -1)
            return true;
        else
            return false;
    }

    String pop()
    {
        if (top_ == -1)
        {
            System.out.println("Нет элементов в стеке");
            return "0";
        }
        top_--;
        return S_[top_ + 1];
    }

    void push(String x)
    {
        if (top_ + 1 == MAX_STACK_EL)
        {
            System.out.println("Превышено допустимое количество элементов в стеке");
            return;
        }
        top_++;
        S_[top_] = x;
    }

    int corrDef(String a, DefArrays f)
    {
        for(int i = 0; i < f.cntW; i++)
        {
            if(a.compareTo(f.Def1[i]) == 0)
                return i;
        }
        return -1;
    }

}
