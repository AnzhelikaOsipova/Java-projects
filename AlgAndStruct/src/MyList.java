public class MyList {

    Node listNodes = null;

    private class Node {

        public int val;
        public Node nextNode = null;

        Node(int nval){val = nval;}
    }

    public MyList(int nval) {listNodes = new Node(nval);}
    public MyList() {}

    public void add(int nval) {
        if(listNodes == null){
            listNodes = new Node(nval);
            return;
        }
        Node L = listNodes;
        while(L.nextNode != null) {
            L = L.nextNode;
        }
        L.nextNode = new Node(nval);
    }

    private Node find(int index){
        Node L = listNodes;
        int currInd = 0;
        while(L.nextNode != null && currInd < index) {
            L = L.nextNode;
            currInd++;
        }
        if(currInd == index){
            return L;
        }
        throw new NullPointerException("Index out of range.");
    }

    public int get(int index) {
        return find(index).val;
    }

    public String toString() {
        if(listNodes == null)
            return "";
        StringBuilder res = new StringBuilder();
        Node L = listNodes;
        res.append("[" + L.val);
        while(L.nextNode != null) {
            L = L.nextNode;
            res.append(", " + L.val);
        }
        res.append("]");
        return res.toString();
    }
}
