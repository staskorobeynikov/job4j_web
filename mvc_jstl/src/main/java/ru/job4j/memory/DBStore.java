package ru.job4j.memory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBStore implements Store {

    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final DBStore INSTANCE = new DBStore();

    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/UserStore");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DBStore getINSTANCE() {
        return INSTANCE;
    }


    @Override
    public void add(User user) {
        String query = "INSERT INTO users(id, name, login, email, create_date, photo) VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement addPr = connection.prepareStatement(query)) {
            addPr.setInt(1, Integer.parseInt(user.getId()));
            addPr.setString(2, user.getName());
            addPr.setString(3, user.getLogin());
            addPr.setString(4, user.getEmail());
            addPr.setString(5, user.getCreateDate());
            addPr.setString(6, user.getImage());
            addPr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, email = ?, create_date = ?, photo = ? WHERE id = ?;";
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement updatePr = connection.prepareStatement(query)) {
            updatePr.setString(1, user.getName());
            updatePr.setString(2, user.getLogin());
            updatePr.setString(3, user.getEmail());
            updatePr.setString(4, user.getCreateDate());
            updatePr.setString(5, user.getImage());
            updatePr.setInt(6, Integer.parseInt(user.getId()));
            updatePr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    @Override
    public void delete(User user) {
        String query = "DELETE FROM users WHERE id = ?;";
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement deletePr = connection.prepareStatement(query)) {
            deletePr.setInt(1, Integer.parseInt(user.getId()));
            deletePr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement findAllPr = connection.prepareStatement(query)) {
            ResultSet resultSet = findAllPr.executeQuery();
            result = getResult(resultSet);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public User findById(User user) {
        User result = null;
        String query = "SELECT * FROM users WHERE id = ?;";
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement findByIdPr = connection.prepareStatement(query)) {
            findByIdPr.setInt(1, Integer.parseInt(user.getId()));
            ResultSet resultSet = findByIdPr.executeQuery();
            List<User> list = getResult(resultSet);
            if (!list.isEmpty()) {
                result = list.get(0);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private List<User> getResult(ResultSet resultSet) {
        List<User> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User(
                        String.valueOf(resultSet.getInt("id")),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("create_date"),
                        resultSet.getString("photo")
                );
                result.add(user);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
