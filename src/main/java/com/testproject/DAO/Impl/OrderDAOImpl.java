package com.testproject.DAO.Impl;

import com.testproject.DAO.OrderDAO;
import com.testproject.objects.OrderObject;
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

    }

    public void updateOrder(OrderObject order) throws SQLException {

    }

    public void deleteOrder(OrderObject order) throws SQLException {

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

    public Collection getOrdersByLogin() throws SQLException {
        return null;
    }

}
