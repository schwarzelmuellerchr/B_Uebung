package serial;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Database {

    private LinkedList<Person> catalog;
    private ObservableList<Person> observablePerson;

    private static Database database;

    private Database() {
        this.catalog = new LinkedList<>();
        observablePerson  = FXCollections.observableList(catalog);
    }

    public static Database getInstance() {
        if(database==null){
            database = new Database();
        }
        return database;
    }

    public ObservableList<Person> getObservablePerson() {
        return observablePerson;
    }

    public void save(Person person) {
        //Speichern in Datenbank
    }

    public void refresh() {
        try{
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/C:/Pilger/B_Uebung2/database/B_Uebung", "B_Uebung", "B_Uebung");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select PERSON.ID,\"name\",WOHNORT\n" +
                    "from PERSON\n" +
                    "INNER JOIN ADRESSE A on A.ID = PERSON.ADRESSEID");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String wohnort = resultSet.getString("wohnort");
                Person person = new Person(id, name,wohnort);
                catalog.add(person);
                System.out.println(person.toString());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
