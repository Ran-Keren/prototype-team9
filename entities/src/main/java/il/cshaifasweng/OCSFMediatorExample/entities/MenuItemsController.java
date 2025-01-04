package il.cshaifasweng.OCSFMediatorExample.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MenuItemsController {
    @FXML
    private TableView<MenuItem> menuTable;

    @FXML
    private TableColumn<MenuItem, Integer> itemIdColumn;

    @FXML
    private TableColumn<MenuItem, String> nameColumn;

    @FXML
    private TableColumn<MenuItem, Double> priceColumn;

    @FXML
    private TableColumn<MenuItem, String> ingredientsColumn;

    private ObservableList<MenuItem> menuItems;

    @FXML
    public void initialize() {
        // הגדרת עמודות
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ingredientsColumn.setCellValueFactory(new PropertyValueFactory<>("ingredients"));

        // הבאת נתונים מבסיס הנתונים
        fetchMenuItems();
    }

    private void fetchMenuItems() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            List<MenuItem> items = session.createQuery("from MenuItem", MenuItem.class).list();
            menuItems = FXCollections.observableArrayList(items);
            menuTable.setItems(menuItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}