package com.testproject.clients.orderClients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by bobyk on 28/04/16.
 */
public class OrderClientPut {

    public static void main(String[] args) {

        try {

            Client client = Client.create();

            long val = 1346524199000l;

            WebResource webResource = client
                    .resource("http://localhost:8080/rest/orders/update");

            String input = "{\"id\":2, " +
                    "\"userId\":10," +
                    "\"cashType\":\"USD\"," +
                    "\"operationType\":\"buy\"," +
                    "\"creationDate\":\""+val+"\"," +
                    "\"amount\":\"100500\"," +
                    "\"status\":\"publish\"}";
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
