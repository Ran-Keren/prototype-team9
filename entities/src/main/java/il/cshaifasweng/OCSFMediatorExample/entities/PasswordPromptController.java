package il.cshaifasweng.OCSFMediatorExample.entities;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class PasswordPromptController {

    @FXML
    private PasswordField passwordField;

    private static String databasePassword;

    @FXML
    private void handleSubmit(ActionEvent event) {
        // קבלת הסיסמה מהמשתמש
        databasePassword = passwordField.getText();

        if (databasePassword != null && !databasePassword.isEmpty()) {
            System.out.println("Password entered: " + databasePassword); // הדפסת הסיסמה למסוף (לבדיקות)
            closeWindow();
        } else {
            System.out.println("Password is required!");
        }
    }

    private void closeWindow() {
        // סגירת החלון
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.close();
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }
}
