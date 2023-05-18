import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.StringTokenizer;


public class MainScreen extends Application implements Comparator<String> {
     Locations l = new Locations();
    Scene sceneLocation = new Scene(l.getLocations() , 800,600);
     Stage stageLocation = new Stage();

     Martyrs martyrs = new Martyrs();
    Scene sceneMartyr = new Scene(martyrs.martyrs() , 800,600);
    Stage stageMartyr = new Stage();
    //private File selectedFile;


    Statistics statistics =new Statistics();
    Scene sceneStatistic = new Scene(statistics.statistic() , 800,600);
    Stage stageStatistic = new Stage();

    Save save = new Save();
    Scene sceneSave = new Scene(save.save() , 800,600);
    Stage stageSave =save.stage;

     String fileName;
    Button fileChooserButton = new Button("Choose File");
     Button readBT = new Button("read");
     Button locationBT = new Button("Locations");
    Button martyrsBT = new Button("Martyrs");
    Button statisticsBT = new Button("Statistics");
    Button saveBT = new Button("Save");


    TextField fileChooserField = new TextField();
    static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    static Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    private final TableView<Martyrs> table = new TableView<>();
    private final ObservableList<Martyrs> data = FXCollections.observableArrayList();

   static final DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
    static final LinkedList linkedList =new LinkedList();

    public static void main(String[] args) {
        launch(args);
    }
    BorderPane mainBorder = new BorderPane();
    @Override
    public void start(Stage stage)  {




        // add the img in the top of the border pain
        Image m = new Image("logo.jpg");
        ImageView mView = new ImageView(m);
        mView.setFitWidth(850);
        mView.setFitHeight(450);
        HBox imgHbox = new HBox();
        imgHbox.getChildren().add(mView);
        imgHbox.setAlignment(Pos.CENTER);
       mainBorder.setTop(imgHbox);




        // add the explain and options of this project in the center of the border pane

        //define grid pane to regular the explain and options
        GridPane mainGridBane = new GridPane();
            Label labelExplanet = new Label(" * This project aims to store and display the martyrs and their information\n" +
                    " It is important for us to keep the names of our heroic martyrs always among us\n" +
                    " May God have mercy on them all….");
            labelExplanet.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            mainGridBane.add(labelExplanet,0,0);



            Label labelLocation = new Label(" * If you want to display the cities of the martyrs, click on ");
            labelLocation.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            mainGridBane.add(labelLocation,0,2);mainGridBane.add(locationBT,1,2);

            Label labelMartyr = new Label(" * If you want to display the names of the martyrs, click on ");
            labelMartyr.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            mainGridBane.add(labelMartyr,0,3);mainGridBane.add(martyrsBT,1,3);

            Label labelStatistics = new Label(" * If you want to display the  statistics of the martyrs, click on ");
            labelStatistics.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            mainGridBane.add(labelStatistics,0,4);mainGridBane.add(statisticsBT,1,4);


            Label labelNoteSave = new Label(" (*) Don't forget to save your changes before exiting");
            labelNoteSave.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
            mainGridBane.add(labelNoteSave,0,5);mainGridBane.add(saveBT,1,5);

             Image danger = new Image("danger.jpg");
             ImageView dangerView = new ImageView(danger);
             dangerView.setFitWidth(120);
             dangerView.setFitHeight(80);
             mainGridBane.add(dangerView,5,6);
        HBox centerHBox=new HBox();
        centerHBox.getChildren().add(mainGridBane);
        centerHBox.setAlignment(Pos.CENTER);
         mainBorder.setCenter(centerHBox);


        //  add  note to user in the bottom of border pane



        // Set up the table columns

        Label labelChooseFile = new Label(" * * * you have to choose a file has the martyrs name , after that click read \n");
        labelChooseFile.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
        HBox hBox=new HBox();
        hBox.getChildren().addAll(labelChooseFile,fileChooserField,fileChooserButton,readBT);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMinHeight(100);
        mainBorder.setBottom(hBox);


        Tab tab1 = new Tab("Location Screen");
        tab1.setContent(new TextField("This is tab 1"));


        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();
        fileChooserButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File selected = fileChooser.showOpenDialog(stage);
            if (selected != null) {
                fileChooserField.setText(selected.getAbsolutePath());
                 fileName=selected.getAbsolutePath();
            }
        });

        readBT.setOnAction(mainButtonsEvent);
        locationBT.setOnAction(mainButtonsEvent);
        martyrsBT.setOnAction(mainButtonsEvent);
        statisticsBT.setOnAction(mainButtonsEvent);
        saveBT.setOnAction(mainButtonsEvent);




        Scene scene = new Scene(mainBorder, 850, 720);
        stage.setTitle("martyrs data");
        stage.setScene(scene);
        stage.show();


    }
    public  void read_File(String file) {
        int count = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    // Use StringTokenizer to split the martyr by comma delimiter
                    StringTokenizer st = new StringTokenizer(line, ",");
                    String name = st.nextToken();
                    String age = st.nextToken();
                    String city = st.nextToken();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(st.nextToken());

                    char gender = st.nextToken().charAt(0);
                    count++;
                    // Create a new Statement object and add it to the linked list
                    Martyrs martyr = new Martyrs(name, age, date, gender);
                    martyr.setLocation(city);
                    linkedList.addLast(martyr); // to use it in the Statistic class
                    Locations locations = new Locations(city);

                    if (doubleLinkedList.search(locations.getCity())) {
                        doubleLinkedList.addList(locations.getCity().trim(), martyr);
                        //martyrs.add(martyr,MainScreen.doubleLinkedList.first.headerList);
                    } else {
                        doubleLinkedList.addFirst(locations.getCity());
                        // add2(locations.getCity());
                        //  sort(locations.getCity());
                        doubleLinkedList.addList(locations.getCity(), martyr);
                        //martyrs.add(martyr,MainScreen.doubleLinkedList.first.headerList);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // doubleLinkedList.sortList();
        doubleLinkedList.displaylistFull();
        doubleLinkedList.displayList();
        System.out.println(doubleLinkedList.count);
        System.out.println(linkedList.count);
    }

void add2(String x ){
    if (doubleLinkedList.first==null) {//check if the headr is null
        doubleLinkedList.first=new DoubleNode(x);
    }else {
        DoubleNode current =  doubleLinkedList.first;
       // DoubleNode current = doubleLinkedList.first.next;
       // current.previous=pre;
        if(((String) current.element).compareTo(x)>=0){//check the first node, new is less than or equal the pre
            DoubleNode node = new DoubleNode(x);
            node.next=current;
            current.previous=node;
            node.previous=null;
          //  doubleLinkedList.first=node;
        }/*else {*/
            while (current.next!= null) {//check the pre after this loop
                int res = ((String) current.element).compareTo(x);
                DoubleNode node = new DoubleNode(x);
                if (res > 0) {
                    node.next=current;
                  //  pre.next=node;
                    current.previous.next=node;
                    current.previous=node;
                }
                if (res <= 0) {
                  //  DoubleNode node=new DoubleNode(x);
                    current.next = node;
                    node.previous=current;
                    node.next=null;
                }
                current = current.next;
              //  current.previous=pre;
            }
            doubleLinkedList.displayList();
        //}
    }
}


    @Override
    public int compare(String o1, String o2) {
        if(o1==(null)||o2==(null)) {
            return (-0);
        }else
            return o1.compareTo(o2);

    }

    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() ==  locationBT){

                 stageLocation.setScene(sceneLocation);
            stageLocation.show();
            }else if (event.getSource() ==  readBT){
                  if(fileName!=null){

                      read_File(fileName);
                      alertInformation.setContentText("For information .,, we avoid the martyrs doesn't has full information ");
                      alertInformation.setHeaderText("the read is Done");
                      alertInformation.showAndWait();
                 }else {
                alertWarning.setTitle("Warning ");
                alertWarning.setHeaderText("the file is not found !!");
                alertWarning.showAndWait();
            }
            }else if (event.getSource() == martyrsBT ){
                // in insert store the data in martyr Obj

                stageMartyr.setScene(sceneMartyr);
                stageMartyr.show();

            }else if (event.getSource() ==  statisticsBT){

                    stageStatistic.setScene(sceneStatistic);
                    stageStatistic.show();
            }else if (event.getSource() ==  saveBT){

                stageSave.setScene(sceneSave);
                stageSave.show();
            }
        }
    }


}