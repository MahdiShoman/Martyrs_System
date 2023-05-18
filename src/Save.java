import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    Save(){

    }
    Stage stage = new Stage();
    TextField fileChooserField = new TextField();
    Button fileChooserButton = new Button("Choose File");
    Button saveButton = new Button("Save");
    String fileName;

    static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    static Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    BorderPane p = new BorderPane();
    Pane save(){
        HBox h = new HBox();
        fileChooserField.setPromptText("Set Resource File /  Choose File");
        h.getChildren().addAll(fileChooserField,fileChooserButton,saveButton);
        h.setAlignment(Pos.CENTER);
       h.setMinHeight(450);
        p.setTop(h);
        //p.setMinWidth(200);



        fileChooserButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File selected = fileChooser.showOpenDialog(stage);
            if (selected != null) {
                fileChooserField.setText(selected.getAbsolutePath());
                fileName=selected.getAbsolutePath();
            }
        });
        saveButton.setOnAction(actionEvent -> {
            try {
            FileWriter f = new FileWriter(fileName);
            Martyrs martyr ;
            DoubleNode current = MainScreen.doubleLinkedList.first;
            while (current!= null) { // O(n^2)
                Node currentCheck = current.headerList;
                while (currentCheck.next!=null){
                    martyr=((Martyrs) currentCheck.element);
                    f.write ( "\n"+martyr.getName()+","+martyr.getAge()+","+ current.element+","+martyr.getDateOfDeath()+","+martyr.getGender());
                    currentCheck=currentCheck.next;
                }

                current = current.next;
            }
                alertInformation.setContentText("Save is Done");
                alertInformation.showAndWait();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }});

        return p;
    }
}
