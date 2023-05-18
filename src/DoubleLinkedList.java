
public class DoubleLinkedList {
    DoubleNode first;
    DoubleNode last;


    public int count;

    public DoubleLinkedList() {

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

    public void addFirst(String x) {
        if (count == 0)
            first = last = new DoubleNode(x);
        else {
            DoubleNode temp = new DoubleNode(x);
            temp.next = first;
            first.previous = temp;
            first = temp;
        }
        count++;
    }

    public void addLast(String x) {
        if (count == 0)
            first = last = new DoubleNode(x);
        else {
            DoubleNode temp = new DoubleNode(x);
            last.next = temp;
            temp.previous = last;
            last = temp;
            //from me
            last.next = null;
        }
        count++;
    }

    public void add(String x, int index) {
        if (index == 0)
            addFirst(x);
        else {
            if (index >= count)
                addLast(x);
            else {
                DoubleNode temp = new DoubleNode(x);
                DoubleNode current = first;
                for (int i = 1; i < index - 1; i++) {
                    current = current.next;
                }
                temp.next = current.next;
                current.next.previous = temp;
                temp.previous = current;
                current.next = temp;
                count++;

            }
        }
    }

    public boolean removeFirst() {
        if (count == 0)
            return false;
        else {
            if (count == 1)
                first = last = null;
            else {
                first = first.next;
                first.previous = null;
            }
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
                DoubleNode current = first;
                for (int i = 0; i < count - 2; i++)
                    current = current.next;
                last = current;
                current.next.previous = null;
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
            first = last = null;
            return true;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == count - 1)
            return removeLast();
        else if (index < 0 || index > count)
            return false;
        else {
            DoubleNode current = first;
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
                DoubleNode previous = first;
                DoubleNode current = first.next;
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

    public boolean isEmpty() {
        return (first == null);
    }

    public void displayList() {
        if (!isEmpty()) {
            DoubleNode current = first;
            while (current != null) {
                System.out.print(current.element);
                System.out.println(" ");
                current = current.next;
            }
        } else
            System.out.println("the list is empty");
    }

    public void removeAll(LinkedList list2) {

        if (!list2.isEmpty() && !isEmpty()) {

            Node current = list2.first;
            while (current != null) {
                search_delete(current.element);
                current = current.next;
            }

        } else
            System.out.println("the list is empty");
    }

/*    public void addList(Object city, Martyrs martyr) { //for add martyrs to their locations( and check the existion of location and add it

        if (first == null) {
            first = new DoubleNode(city);
            martyr.add2(martyr, first);
        } else {
            DoubleNode pre = first;
            DoubleNode current = first.next;//to found a location if isExist
            if (((String) pre.element).compareTo((String) city) > 0) {//check the first node
                DoubleNode node = new DoubleNode(city);
                node.next = pre;
                pre.previous = node;
                first = node;
                martyr.add2(martyr, first);
            } else if (((String) pre.element).compareTo((String) city) == 0) {
                martyr.add2(martyr, pre);
            } else if (((String) pre.element).compareTo((String) city) < 0 && ((String) current.element).compareTo((String) (city)) > 0) {
                DoubleNode node = new DoubleNode(city);
                node.next = current;
                pre.next = node;
                node.previous = pre;
                current.previous = node;
                martyr.add2(martyr, node);
            } else {
                while (current != null) {
                    int res = ((String) current.element).compareTo((String) (city));
                    if (res > 0 && ((String) pre.element).compareTo(((String) city)) < 0) {
                        DoubleNode node = new DoubleNode(city);
                        node.next = current;
                        pre.next = node;
                        current.previous = node;
                        node.previous = pre;
                        martyr.add2(martyr, node);
                        break;
                    } else if (res == 0) {
                        martyr.add2(martyr, current);
                        break;
                    } else if (res < 0) {
                        DoubleNode node = new DoubleNode(city);
                        current.next = node;
                        node.previous = current;
                        martyr.add2(martyr, node);
                        break;
                    }
                    pre = current;
                    current = current.next;
                }
            }
        }

    }*/



    void addList(Object city,Martyrs martyrs){
        DoubleNode current = first;
        while(current!=null){
            if(current.element.equals(city)){
                martyrs.add2(martyrs,current);
            }
            current=current.next;

        }
    }

    public void search_delete(Object x ) {

        DoubleNode previous = first;
        DoubleNode current = first.next;
        while (current != null && !current.element.equals(x)) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
            count--;
        }
    }
    public Object search_return(Object x ) {

        DoubleNode current = first;
        for (int i = 0; i < count ; i++) {
            if (current.element.equals(x)) {
                System.out.println("yes");
               return (current);
            }
            current = current.next;
        }
        System.out.println("no");
        return ("there is no city has name :"+x);
    }

    public  boolean search(Object x ) {

        DoubleNode current = first;
        while (current!=null) {
            if ((current.element).equals(x) ){
                return true;
            }
            current = current.next;

        }
        return false;
    }



    public void displaylistFull(){ //to display the location and his list of martyr
        if (!isEmpty()){
            DoubleNode current = first;
            while (current!=null){
                System.out.println(current.element);
                displaylistMartyr(current);//display list of martyr
                System.out.print(" ");
                current=current.next;
            }
        }else
            System.out.println("the list is empty");
    }

    public void displaylistMartyr(DoubleNode city){
        Node current = city.headerList;
        if(current!=null) {
            while (current != null) {
                System.out.print(current.element);
                System.out.println(" ");
                current = current.next;
            }
        }else
            System.out.println("there is no martyr in" + city.element +"city");


    }
    public void search_displaylistOfMartyrs(Node city){ //to display the location and his list of martyr
        if (!isEmpty()){
            DoubleNode current = first;
            while (current!=null){
                if(current.element.equals(city)){
                    System.out.println(current.element);
                    displaylistMartyr(current);//display list of martyr
                    System.out.print(" ");
                }
                current=current.next;
            }
        }else
            System.out.println("the list is empty");
    }



}