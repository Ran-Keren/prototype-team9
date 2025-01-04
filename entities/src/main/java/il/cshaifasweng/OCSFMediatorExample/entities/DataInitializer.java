package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataInitializer {

    public static void initializeData(Session session) {
        Transaction transaction = session.beginTransaction();
        try {
            // רוקן את הטבלה
            session.createQuery("delete from MenuItem").executeUpdate();

            // אתחל את ה-AUTO_INCREMENT
            session.createSQLQuery("ALTER TABLE MenuItems AUTO_INCREMENT = 1").executeUpdate();

            // הוסף נתונים חדשים
            MenuItem item1 = new MenuItem("Salad", 35.00, "Tomatoes, cucumbers, lettuce", "Low calorie", null);
            MenuItem item2 = new MenuItem("Pizza", 55.00, "Mushrooms, onions, tomatoes", "Includes vegan option", null);
            MenuItem item3 = new MenuItem("Pasta", 6000.00, "Mushroom cream sauce", "Available gluten-free", null);
            MenuItem item4 = new MenuItem("Hamburger", 80.00, "Meatball, pickle, tomato, lettuce", "Choice of meat or plant-based", null);
            MenuItem item5 = new MenuItem("Edamame", 30.00, "Edamame", "Served with sea salt", null);

            session.save(item1);
            session.save(item2);
            session.save(item3);
            session.save(item4);
            session.save(item5);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
