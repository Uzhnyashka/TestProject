package com.testproject.DAO;

import com.testproject.objects.UserObject;
import org.springframework.security.access.annotation.Secured;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by bobyk on 27/04/16.
 */
public interface UserDAO {
    public void addUser(UserObject usr) throws SQLException;
    public void updateUser(UserObject usr) throws SQLException;
    public void deleteUser(UserObject usr) throws SQLException;
    @Secured("ROLE_ADMIN")
    public Collection getAllUsers() throws SQLException;
    public UserObject getUserByLogin(String login) throws SQLException;
}
