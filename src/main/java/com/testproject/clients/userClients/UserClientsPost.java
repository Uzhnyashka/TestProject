package com.testproject.clients.userClients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.MediaType;

/**
 * Created by bobyk on 27/04/16.
 */
public class UserClientsPost {
    public static void main(String[] args) {

        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:8080/rest/users/add");

            //String input = "{\"name\":\"Egor\",\"login\":\"EG0R\",\"password\":\"Egor\", \"role\":\"admin\", \"phone\":\"12312\"}";
             String input = "{\"name\":\"Andrash\",\"login\":\"Andriska\",\"password\":\"pswrd\", \"role\":\"user\", \"phone\":\"13213\"}";

         /*   ClientResponse resp = webResource.get(ClientResponse.class);
            JSONObject entity = resp.getEntity(JSONObject.class);
            entity.put("name","Andrash");
            entity.put("login","Andriska");
            entity.put("password","asdasd");
            entity.put("role","user");
            entity.put("phone","017412");
            resp = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, entity);
            entity = resp.getEntity(JSONObject.class);
            System.out.println(entity);*/

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

            if (response.getStatus() >= 400) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Output from Server .... \n");
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
