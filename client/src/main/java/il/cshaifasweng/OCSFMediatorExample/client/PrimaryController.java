package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;

//
public class PrimaryController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label HomePageLabel;

	@FXML
	private Button MenutBtn;

	@FXML
	private Label WelcomeLabel;

	@FXML
	void displayMenuFunc(ActionEvent event) throws IOException {
		SimpleClient.getClient().displayMenu();
	}
@FXML
void sendWarning(ActionEvent event) {
	try {
		SimpleClient.getClient().sendToServer("#warning");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	@FXML
	void initialize(){
		assert HomePageLabel != null : "fx:id=\"HomePageLabel\" was not injected: check your FXML file 'primary.fxml'.";
		assert MenutBtn != null : "fx:id=\"MentBtn\" was not injected: check your FXML file 'primary.fxml'.";
		assert WelcomeLabel != null : "fx:id=\"WelcomeLabel\" was not injected: check your FXML file 'primary.fxml'.";
		try {
			SimpleClient.getClient().sendToServer("add client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void editMenu(ActionEvent event) throws IOException {
        try {
            SimpleClient.getClient().updateMenu("1","50");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
