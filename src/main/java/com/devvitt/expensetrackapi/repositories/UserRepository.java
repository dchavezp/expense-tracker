package com.devvitt.expensetrackapi.repositories;

import com.devvitt.expensetrackapi.domain.User;
import com.devvitt.expensetrackapi.exceptions.EtAuthException;
import com.devvitt.expensetrackapi.repositories.interfaces.UserRepositoryI;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository implements UserRepositoryI {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String SQL_CREATE = "INSERT INTO ET_USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES(NEXTVAL('ET_USERS_SEQ'), ?, ?, ?, ?)";
    private static final String SQL_COUNT= "SELECT COUNT(*) FROM ET_USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM ET_USERS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM ET_USERS WHERE EMAIL = ?";
    private static final String SQL_GET_USERS = "SELECT * FROM ET_USERS";

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(7));
        try{
            KeyHolder keyHolder= new GeneratedKeyHolder();
            jdbcTemplate.update(connection->{
                PreparedStatement ps=connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,firstName);
                ps.setString(2,lastName);
                ps.setString(3,email);
                ps.setString(4,hashedPassword);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception e){
            throw new EtAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email},userRowMapper);

            if(!BCrypt.checkpw(password,user.getPassword())){
                throw new EtAuthException("Wrong Password");
            }
            return user;
        } catch (Exception e) {
            throw new EtAuthException("Invalid credentials");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId},userRowMapper);
    }

    @Override
    public List<User> getListOfUsers() {
        return jdbcTemplate.query(SQL_GET_USERS,userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum)-> new User(
            rs.getInt("USER_ID"),
            rs.getString("FIRST_NAME"),
            rs.getString("LAST_NAME"),
            rs.getString("EMAIL"),
            rs.getString("PASSWORD")));



}
