package com.example.demo.work6;

import com.example.demo.work4.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class StudentService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private Connection getConnection() throws SQLException {
        if (jdbcTemplate.getDataSource() == null) {
            throw new SQLException();
        }
        return jdbcTemplate.getDataSource().getConnection();
    }

    public Student selectByPrimaryKey(int id) {
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ) {

            ResultSet rs = statement.executeQuery("select * from student where id = " + id);
            if (rs.next()) {
                return Student.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Student selectById(int id) {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("select * from student where id = ?");
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Student.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Student selectByPrimaryId(int id) {
        return jdbcTemplate.queryForObject("select * from student where id = ?", (resultSet, i) -> Student.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .build(), id);
    }
}
