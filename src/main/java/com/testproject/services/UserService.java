package com.testproject.services;

import com.testproject.DAO.Impl.UserDAOImpl;
import com.testproject.objects.UserObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 27/04/16.
 */

@Path("/users")
public class UserService {

    static UserDAOImpl userDAO = new UserDAOImpl();
    static List<UserObject> users = new ArrayList<UserObject>();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserObject> getAllUsers(){
        try{
            users = (List<UserObject>) userDAO.getAllUsers();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserObject getUserByLogin(@PathParam("username") String username){
        return null;
    }

    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserObject usr){

        String result = "Added new " + usr;
        try {
            userDAO.addUser(usr);
        } catch (SQLException e) {
            e.printStackTrace();
            Response.status(404).build();
        }

        return Response.status(201).entity(result).build();
    }
}
