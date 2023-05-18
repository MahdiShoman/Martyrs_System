public class LinkedList {
     Node first;
    private Node last;
    public double count;

    public LinkedList() {

    }

    public Object getFirst() {
        if (count != 0)
            return first.element;
        else
            return null;
    }

    public Object getLast() {
        if (count != 0)
            return last.element;
        else
            return null;
    }

    public void addFirst(Object x) {
        if (count == 0)
            first = last = new Node(x);
        else {
            Node temp = new Node(x);
            temp.next = first;
            first = temp;
        }
        count++;
    }

    public void addLast(Object x) {
        if (count == 0)
            first = last = new Node(x);
        else {
            Node temp = new Node(x);
            last.next = temp;
            last = temp;
            //from me
            last.next=null;
        }
        count++;
    }

    public void add(Object x, int index) {
        if (index == 0)
            addFirst(x);
        else {
            if (index >= count)
                addLast(x);
            /*if (count-index<=2)
                addLast(x);*/
            else {
                Node temp = new Node(x);
                Node current = first;
                for (int i = 0; i < index - 1; i++) {
                    current = current.next;
                }
                temp.next = current.next;
                current.next = temp;
                count++;

            }
        }
    }
    public void add (Martyrs x , Node header){


            Node current = header;
            int res =x.getDateOfDeath().compareTo((((Martyrs) current.next.element).getDateOfDeath()));

        Node node = new Node(x);
            if((x.getDateOfDeath().compareTo((((Martyrs) current.element).getDateOfDeath())))>0){
                addFirst(x);
            }
        while(current.next!=null){
                if (res<=0) {
                    node.next = current.next.next;
                    current.next.next = node;
                    break;
                }else {
                    node.next=current.next;
                    current.next=node;
                }current=current.next;
            }
            if(res<=0){
             //   node.next = current.next.next;
                current.next=node;
              node.next=null;
            }

    }

    public boolean removeFirst() {
        if (count == 0)
            return false;
        else {
            if (count == 1)
                first = last = null;
            else
                first = first.next;
            count--;
            return true;
        }
    }

    public boolean removeLast() {
        if (count == 0)
            return false;
        else {
            if (count == 1)
                first = last = null;
            else {
                Node current = first;
                for (int i = 0; i < count - 2; i++)
                    current = current.next;
                last = current;
                current.next = null;
            }
            count--;
            return true;

        }
    }

    public boolean remove(int index) {
        if (count == 0)
            return false;
        else if (count == 1) {
            first = last=null;
            return true;
        } else if (index==0) {
            return removeFirst();
        } else if (index == count-1)
            return removeLast();
        else if (index < 0 || index > count)
            return false;
        else {
            Node current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = (current.next).next;
            count--;
            return true;
        }
    }

    public boolean removeValue(Object x) {

      if (count == 0)
            return false;
        else {
            if (first.element.equals(x))
                return removeFirst();
            else {
                Node previous = first;
                Node current = first.next;
                while (current != null && !current.element.equals(x)) {
                    previous = current;
                    current = current.next;
                }
                if (current != null) {
                    previous.next = current.next;
                    count--;
                    return true;
                }
            }
            return false;
        }
    }
    public boolean isEmpty(){
        return (first==null);
    }


    public void displaylist(){
        if (!isEmpty()){
            Node current = first;
            while (current!=null){
                System.out.print(current.element);
                System.out.print(" ");
                current=current.next;
            }
        }else
            System.out.println("the list is empty");
    }

    public void removeAll(LinkedList list2){

        if(!list2.isEmpty() && !isEmpty()){

            Node current = list2.first;
            while (current!=null){
                search_delete(current.element);
                current=current.next;
            }

        }else
            System.out.println("the list is empty");
    }

    public void search_delete(Object x ) {
        if (first.element.equals(x)){
            if (count == 1)
                first = last = null;
            else
                first = first.next;
            count--;
        }
       // Node current = first;
        Node previous = first;
        Node current = first.next;
        while (current != null && !current.element.equals(x)) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
            count--;
        }
      /*  while (current!=null){
            if (current.element.equals(x)) {
                removeValue(x);
            }
            current = current.next;

        }*/
    }
    public  boolean search(Object x ) {

        Node current = first;
        for (int i = 0; i < count ; i++) {
            if (current.element.equals(x)) {
               return true;
            }
            current = current.next;

        }
        return false;
    }

}