package com.testproject.DAO.Impl;

import com.testproject.DAO.UserDAO;
import com.testproject.objects.UserObject;
import com.testproject.utils.HibernateUtil;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bobyk on 27/04/16.
 */
public class UserDAOImpl implements UserDAO {

    public void addUser(UserObject usr) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(usr);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "add fail", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void updateUser(UserObject usr) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(usr);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "updage fail", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void deleteUser(UserObject usr) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(usr);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "delete fail", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public Collection getAllUsers() throws SQLException {
        Session session = null;
        List users = new ArrayList<UserObject>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(UserObject.class).list();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "getAll fail", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return users;
    }

    public UserObject getUserByLogin(String login) throws SQLException{
        List<UserObject> users = (List<UserObject>) getAllUsers();
        UserObject userObject = null;

        for (UserObject usr : users){
            if (login.equalsIgnoreCase(usr.getLogin())){
                userObject = usr;
                break;
            }
        }
        return userObject;
    }
}
