/*
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args)  {
        DoubleLinkedList locations = new DoubleLinkedList();
        DoubleLinkedList locations22 = new DoubleLinkedList();
        LinkedList L2 = new LinkedList();

  */
/*      String fileName = "C:\\\\Users\\\\ATIYA AMG\\\\Desktop\\\\Mahdi\\\\proj1_DS\\\\btselem.csv"; // replace with your file name
        LinkedList people = new LinkedList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int age=1;
                String name = getNextToken(line, ",");
                if(getNextToken(line, ",").equals(""))
                     age=0;
                    else
                 age = Integer.parseInt(getNextToken(line, ","));
                String city = getNextToken(line, ",");
                Date date = dateFormat.parse(getNextToken(line, ","));
                char gender = getNextToken(line, ",").charAt(0);

                Martyr person = new Martyr(name, age, city, date, gender);
                people.add(person ,count);
                count++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        System.out.println(count);

        // do something with the list of people
    }

    private static String getNextToken(String line, String delimiter) {
        int delimiterIndex = line.indexOf(delimiter);
        String token = line.substring(0, delimiterIndex);
        line = line.substring(delimiterIndex + delimiter.length());
        return token;
    }*//*


        //locations.displaylist();
        // System.out.println(count);
        int count = 0;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\ATIYA AMG\\Desktop\\Mahdi\\proj1_DS\\btselem.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
               // int age=1;

                // Use StringTokenizer to split the martyr by comma delimiter
                StringTokenizer st = new StringTokenizer(line, ",");
                String name = st.nextToken();
               // System.out.println(name);
                 String age = st.nextToken();
                String city = st.nextToken();
                Date date = new Date(st.nextToken());
                char gender = st.nextToken().charAt(0);
                count++;
                // Create a new Statement object and add it to the linked list
                Martyrs martyr = new Martyrs(name, age,  date, gender);
               // locations22.addLast(martyr);
               // DoubleNode martyrObj =new DoubleNode(martyr);
                if(locations.search(city)){
                 //    locations.addList(city,martyrObj,martyr);
                }else{
                    locations.addLast(city);
                   // locations.addList(city,martyrObj,martyr);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        locations.displaylistFull();
        locations.displaylist();
        locations22.displaylist();
        System.out.println(count);



       */
/* DoubleLinkedList L1 = new DoubleLinkedList();
        L1.addFirst(1);
        L1.add(3,1);
        L1.add(5,2);
        L1.addLast(7);
        L1.displaylist();

        LinkedList L2 = new LinkedList();
        L2.addFirst(1);
        L2.add(2,1);
        L2.add(3,2);
        L2.add(4,3);
        L2.addLast(5);



	     *//*
*/
/*   L1.removeAll(L2);
	        System.out.println("");
	        System.out.print("List1 :");
	        L1.displaylist();
	        System.out.println("");
	        System.out.print("List2 :");
	        L2.displaylist();*//*
*/
/*


       // L2.removeAll(L1);
        System.out.println("");
        System.out.print("List1 :");
        L1.displaylist();
        System.out.println("");
        System.out.print("List2 :");
        L2.displaylist();*//*

    }


}

*/


