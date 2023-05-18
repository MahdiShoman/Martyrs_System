import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Buttons extends Button{
    Button insertButton=new Button("insert");
    Button updateButton=new Button("update");
    Button deleteButton=new Button("delete");
    Button searchButton = new Button("Search");




    Label labelInsertResult = new Label();
    Label labelInsert = new Label();
    TextField insertField = new TextField();

    Label labelUpdateResult = new Label();
    Label labelUpdate = new Label();
    TextField beforeUpdateField = new TextField();
    TextField afterUpdateField = new TextField();

    Label labelDeleteResult = new Label();
    Label labelDelete = new Label();
    TextField deleteField = new TextField();


    Alert alertWarning = new Alert(Alert.AlertType.WARNING);
     Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    Buttons(){

    }


}
