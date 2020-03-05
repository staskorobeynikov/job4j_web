package ru.job4j.memory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.Place;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DBStore implements Store {

    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final DBStore INSTANCE = new DBStore();

    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/Cinema");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Place> getPlacesHall() {
        List<Place> result = new ArrayList<>();
        String query = "SELECT * FROM halls;";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement allPlacesPr = connection.prepareStatement(query)) {
            ResultSet resultSet = allPlacesPr.executeQuery();
            while (resultSet.next()) {
                Place place = new Place(
                        resultSet.getString("name"),
                        resultSet.getInt("row"),
                        resultSet.getInt("place")
                );
                result.add(place);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Set<Place> getOccupiedPlaces() {
        Set<Place> result = new HashSet<>();
        String query = "SELECT h.id, h.name, h.row, h.place FROM halls AS h\n"
                + "INNER JOIN accounts AS a ON a.place_id = h.id";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement occupiedPr = connection.prepareStatement(query)) {
            ResultSet resultSet = occupiedPr.executeQuery();
            while (resultSet.next()) {
                Place place = new Place(
                        resultSet.getString("name"),
                        resultSet.getInt("row"),
                        resultSet.getInt("place")
                );
                result.add(place);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void addVisitorPlace(String fio, String phone, int id) {
        String query = "INSERT INTO accounts(fio, phone, place_id) values (?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement insertPr = connection.prepareStatement(query)) {
            insertPr.setString(1, fio);
            insertPr.setString(2, phone);
            insertPr.setInt(3, id);
            insertPr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
