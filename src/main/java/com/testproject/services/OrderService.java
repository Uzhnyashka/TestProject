package com.testproject.services;

import com.testproject.DAO.Impl.OrderDAOImpl;
import com.testproject.objects.OrderObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 27/04/16.
 */

@Path("/orders")
public class OrderService {

    static OrderDAOImpl orderDAO = new OrderDAOImpl();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderObject> getAllOrders(){
        List<OrderObject> orders = new ArrayList<OrderObject>();
        try{
            orders = (List<OrderObject>) orderDAO.getAllOrders();
        }catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(OrderObject order){
        String result = "Add " + order;
        try{
            orderDAO.addOrder(order);
        }catch (Exception e){
            e.printStackTrace();
            Response.status(404).build();
        }

        return Response.status(201).entity(result).build();
    }
}
