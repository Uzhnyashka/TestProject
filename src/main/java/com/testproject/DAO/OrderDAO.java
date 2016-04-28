package com.testproject.DAO;

import com.testproject.objects.OrderObject;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by bobyk on 27/04/16.
 */
public interface OrderDAO {
    public void addOrder(OrderObject order) throws SQLException;
    public void updateOrder(OrderObject order) throws SQLException;
    public void deleteOrder(OrderObject order) throws SQLException;
    public Collection getAllOrders() throws SQLException;
    public Collection getOrdersByLogin(String login) throws SQLException;
    public OrderObject getOrderById(Long id) throws SQLException;
}
