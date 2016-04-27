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
        UserObject userObject = null;
        try{
            userObject = userDAO.getUserByLogin(username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userObject;
    }

    @POST
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

    @DELETE
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String username){
        UserObject userObject = null;
        try{
            userObject = userDAO.getUserByLogin(username);
        }catch (Exception e){
            e.printStackTrace();
        }

        String result = "Deleted " + userObject;
        try {
            userDAO.deleteUser(userObject);
        } catch (SQLException e) {
            e.printStackTrace();
            Response.status(404).build();
        }

        return Response.status(201).entity(result).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserObject updateUser(UserObject usr){
        try {
            userDAO.updateUser(usr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usr;
    }
}
