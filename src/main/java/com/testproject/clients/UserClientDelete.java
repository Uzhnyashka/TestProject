package com.testproject.clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by bobyk on 27/04/16.
 */
public class UserClientDelete {

    public static void main(String[] args) {

        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:8080");

            //String input = "{\"name\":\"Egor\",\"login\":\"EG0R\",\"password\":\"Egor\", \"role\":\"admin\", \"phone\":\"12312\"}";

            ClientResponse response = webResource.path("/rest/users/andris").
                    delete(ClientResponse.class);

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
