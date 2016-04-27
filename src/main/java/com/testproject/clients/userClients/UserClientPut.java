package com.testproject.clients.userClients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by bobyk on 27/04/16.
 */
public class UserClientPut {

    public static void main(String[] args) {

        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:8080/rest/users/update");

            String input = "{\"id\":8, \"name\":\"Egor\",\"login\":\"EG0R\",\"password\":\"Egor\", \"role\":\"admin\", \"phone\":\"12331221312\"}";
           // String input = "{\"name\":\"Andrash\",\"login\":\"Andris\",\"password\":\"pswrd\", \"role\":\"user\", \"phone\":\"13213\"}";

            ClientResponse response = webResource.type("application/json")
                    .put(ClientResponse.class, input);

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
