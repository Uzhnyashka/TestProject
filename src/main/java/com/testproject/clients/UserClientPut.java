package com.testproject.clients;

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
                    .resource("http://localhost:8080/rest/users/add");

            String input = "{\"name\":\"Egor\",\"login\":\"EG0R\",\"password\":\"06090512be\", \"role\":\"admin\", \"phone\":\"0664171432\"}";

            ClientResponse response = webResource.type("application/json")
                    .put(ClientResponse.class, input);

            if (response.getStatus() != 201) {
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
