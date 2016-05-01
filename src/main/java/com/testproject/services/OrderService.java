package com.testproject.services;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.testproject.DAO.Impl.OrderDAOImpl;
import com.testproject.DAO.Impl.UserDAOImpl;
import com.testproject.objects.OrderObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 27/04/16.
 */

@Path("/orders")
public class OrderService {

    static OrderDAOImpl orderDAO = new OrderDAOImpl();
    static UserDAOImpl userDAO = new UserDAOImpl();

    @GET//get all orders
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderObject> getAllOrders(){
        List<OrderObject> orders = new ArrayList<OrderObject>();
        try{
            orders = (List<OrderObject>) orderDAO.getAllOrders();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if (userDAO.getUserByLogin(CustomUserDetailService.getUserName()).getRole().equals("admin")) return orders;
            else {
                orders = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @GET//get order with id
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderObject getOrderById(@PathParam("id") Long id){
        OrderObject orderObject = null;
        try{
            orderObject = orderDAO.getOrderById(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return orderObject;
    }

    @GET
    @Path("/get/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderObject> getOrdersForUser(@PathParam("username") String login){
        List<OrderObject> orders = new ArrayList<OrderObject>();
        try{
            orders = (List<OrderObject>) orderDAO.getOrdersByLogin(login);
        }catch (Exception e){
            e.printStackTrace();
        }

        return orders;
    }


    @POST//add order
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(OrderObject order){
        String result = "Add " + order;
        System.out.println(result);
        try{
            orderDAO.addOrder(order);
        }catch (Exception e){
            e.printStackTrace();
            Response.status(404).build();
        }

        return Response.status(201).entity(result).build();
    }


    @DELETE//delete order with id
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("id") Long id){
        try{
            OrderObject order = orderDAO.getOrderById(id);
            orderDAO.deleteOrder(order);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(404).entity("Fail delete order with id:"+id).build();
        }
        return Response.status(201).entity("Delete order with id:"+id).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderObject updateOrder(OrderObject order){
        try{
            orderDAO.updateOrder(order);
        }catch (Exception e){
            e.printStackTrace();
        }

        return order;
    }
}
