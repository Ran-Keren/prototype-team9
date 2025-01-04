package il.cshaifasweng.OCSFMediatorExample.entities;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // יצירת אובייקט SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            // אתחול הנתונים
            DataInitializer.initializeData(session);

            // טעינת תצוגת הטבלה
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuItemsView.fxml"));
            AnchorPane root = loader.load();

            // הגדרת הסצנה
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Restaurant Menu");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
