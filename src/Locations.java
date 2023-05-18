import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class Locations extends DoubleLinkedList{
    String city;

    public Locations() {
    }
    public Locations(String city){
        this.city=city;
    }

    public String getCity() {
        return city;
    }

    BorderPane borderLocations = new BorderPane();

    GridPane bottomGridPane = new GridPane();
    private final TableView<String> table = new TableView<>();


    Buttons button = new Buttons();

    TextField searchBox = new TextField();

    void update (String cityName , String newCityName){

            DoubleNode current = MainScreen.doubleLinkedList.first;
            while(current!=null) {
                if (current.element.equals(cityName)) {
                    current.element=newCityName;
                }
                current = current.next;
            }


    }

    public void search_delete(Object x ) {

        DoubleNode previous = MainScreen.doubleLinkedList.first;
        DoubleNode current = MainScreen.doubleLinkedList.first.next;
        if(((String)previous.element).equalsIgnoreCase((String)x)){
            previous=current;
            current=current.next;
          //  previous.next = current.next;
            MainScreen.doubleLinkedList. count--;

            first=previous;
            first.next=current;
            return;
        }
        while (current != null && !((String)current.element).equalsIgnoreCase((String)x)) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
            MainScreen.doubleLinkedList. count--;
        }
    }

    public BorderPane getLocations() {
        // add the field search and the button in the Top of border pane
        HBox topHbox = new HBox();

        TextField searchLocationField = new TextField();
        //??
        searchLocationField.setPromptText("search of location");


        topHbox.getChildren().addAll(searchLocationField, button.searchButton);

        topHbox.setAlignment(Pos.CENTER);

        borderLocations.setTop(topHbox);

        //add location of martyrs from double linkedlist in the center of border pane


        // Set up the table columns
        TableColumn<String, String> cityCol = new TableColumn<>("City Name");
        // catch column with obj
        cityCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));


        // Add the columns to the table
        table.getColumns().add(cityCol);
        setData(table);

        // Set up the search box and button

        searchBox.setPromptText("Enter search term");
        button.searchButton.setOnAction(event -> {
            if(searchLocationField.getText().isEmpty()){
                table.getItems().clear();
                setData(table);
            }else {
                if(!search(searchLocationField.getText())) {
                    table.getItems().clear();
                    System.out.println("before");
                    setAddedData(table, searchLocationField.getText());
                    System.out.println("after");
                }else {
                    button.alertWarning.setTitle("Warning ");
                    button.alertWarning.setHeaderText(" city does not exist ");
                    button.alertWarning.show();

                }
            }
        });
        borderLocations.setCenter(table);

        //add options in the bottom
        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();


        button.labelInsert.setText("* If you want to insert a location, click on ");
        bottomGridPane.add( button.labelInsert ,0,2);bottomGridPane.add(button.insertButton,1,2);
        button.insertButton.setOnAction(mainButtonsEvent);

        button.labelUpdate.setText(" * If you want to update the locations of the martyrs, click on  ");
        bottomGridPane.add(button.labelUpdate ,0,4);bottomGridPane.add(button.updateButton,1,4);
        button.updateButton.setOnAction(mainButtonsEvent);

        button.labelDelete.setText(" * If you want to delete a location, click on  ");
        bottomGridPane.add( button.labelDelete ,0,6);bottomGridPane.add(button.deleteButton,1,6);
        button.deleteButton.setOnAction(mainButtonsEvent);

       

        borderLocations.setBottom(bottomGridPane);

        return borderLocations;
    }
    private void setData(TableView<String> table ){
        DoubleNode current = MainScreen.doubleLinkedList.first;
       // System.out.println(current);
        while(current!=null){
            table.getItems().add((String) current.element);
            current=current.next;
        }
    }
    private void setAddedData(TableView<String> table , String s ){
       // s.toLowerCase();
        DoubleNode current = MainScreen.doubleLinkedList.first;
        System.out.println(current);
        while(current!=null ){
          //  System.out.println(current.element+s);
            ///((String)current.element).toLowerCase();
            if ((((String)current.element).toLowerCase()).equals(s.toLowerCase())){
                table.getItems().add((String) current.element);
            }
            current=current.next;
        }
    }

    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() ==  button.insertButton){
                // add method , check empty
                   Button conform = new Button("conform");
                if (!bottomGridPane.getChildren().contains(button.insertField)){
                    bottomGridPane.add(button.insertField,0,3);bottomGridPane.add(conform,1,3);bottomGridPane.add(button.labelInsertResult,2,3);
                    conform.setOnAction(e ->{
                       // Locations l=new Locations(button.insertField.getText());// to add  a location to list
                        if(button.insertField.getText().isEmpty()){
                           button.labelInsertResult.setText("the field is Empty");
                        }else if (!MainScreen.doubleLinkedList.search(button.insertField.getText())){
                            MainScreen.doubleLinkedList.addLast(button.insertField.getText());
                            button.labelInsertResult.setText("Done !");
                            button.alertWarning.setTitle("Warning ");
                            button.alertWarning.setHeaderText("Dont forget save your work before exit ");
                            button.alertWarning.show();
                        }else{
                            button.labelInsertResult.setText("the location is already  exist !!");
                        }
                    });

                }else{
                    button.labelInsertResult.setText("enter city to insert it !");
                }
                MainScreen.doubleLinkedList.displayList();
                System.out.println("_______");


            }else if (event.getSource() ==  button.updateButton){  //

                Button conformButton = new Button("conform");
               // manageEvent.setText("add city to update it , then click Conform");
                button.beforeUpdateField.setPromptText("Old Name");
                button.afterUpdateField.setPromptText("New Name");
                bottomGridPane.add(button.beforeUpdateField,0,5);bottomGridPane.add(button.afterUpdateField,1,5);
                bottomGridPane.add(conformButton,2,5);bottomGridPane.add(button.labelUpdateResult,3,5);
               // MainScreen.doubleLinkedList. displaylist();
                conformButton.setOnAction(e ->{
                    if (button.beforeUpdateField.getText().isEmpty()||button.afterUpdateField.getText().isEmpty()) {
                            button.labelUpdateResult.setText("the fields are Empty");
                    }else {
                        if (!search(button.beforeUpdateField.getText())) {
                            update(button.beforeUpdateField.getText(), button.afterUpdateField.getText());
                        }else {
                            button.labelUpdateResult.setText("the location doesn't  exist !!");
                        }
                    }
                MainScreen.doubleLinkedList.displayList();
                });
                //methode update  , check the empty , to lower case , trim


            }else if (event.getSource() ==  button.deleteButton){

                Button conformButton = new Button("conform");
                //manageEvent.setText("add city to delete it , then click Conform");
                bottomGridPane.add(button.deleteField,0,7);bottomGridPane.add(conformButton,1,7);
                //bottomGridPane.add(manageEvent,2, 7);

                conformButton.setOnAction(e ->{
                    search_delete(button.deleteField.getText());
                    MainScreen.doubleLinkedList.displayList();
                });
                //search_delete , check the empty  to lower case,trim

            }
        }
    }
}
