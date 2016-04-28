package com.testproject.DAO.Impl;

import com.testproject.DAO.OrderDAO;
import com.testproject.objects.OrderObject;
import com.testproject.objects.UserObject;
import com.testproject.services.OrderService;
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
public class OrderDAOImpl implements OrderDAO{

    public void addOrder(OrderObject order) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "add() fail for Order", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void updateOrder(OrderObject order) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "update() fail for Order", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void deleteOrder(OrderObject order) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "delete() fail for Order", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public Collection getAllOrders() throws SQLException {
        Session session = null;
        List orders = new ArrayList<OrderService>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            orders = session.createCriteria(OrderObject.class).list();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "getAll() fail for Orders", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return orders;
    }

    public Collection getOrdersByLogin(String login) throws SQLException {
        List<OrderObject> orders = (List<OrderObject>) getAllOrders();
        UserDAOImpl userDAO = new UserDAOImpl();
        UserObject user = userDAO.getUserByLogin(login);
        List<OrderObject> ordersForUser = new ArrayList<OrderObject>();
        for (OrderObject order : orders){
            if (order.getUserId().equals(user.getId())){
                ordersForUser.add(order);
            }
        }
        return ordersForUser;
    }

    public OrderObject getOrderById(Long id) throws SQLException{
        Session session = null;
        OrderObject order = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            order = (OrderObject) session.load(OrderObject.class, id);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "getOrderById() fail for Orders", JOptionPane.OK_OPTION);
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return order;
    }

}
