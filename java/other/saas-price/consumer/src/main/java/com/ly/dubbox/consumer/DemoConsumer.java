package com.ly.dubbox.consumer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
 
public class DemoConsumer {
 
    public static void demo(String[] args) {
        final String port = "8888";
 
        //测试Rest服务
        getUser("http://localhost:" + port + "/services/users/1.json");
        getUser("http://localhost:" + port + "/services/users/1.xml");
    }
 
 
    private static void getUser(String url) {
        System.out.println("Getting user via " + url);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Response response = target.request().get();
        try {
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
            }
            System.out.println("Successfully got result: " + response.readEntity(String.class));
        } finally {
            response.close();
            client.close();
        }
    }
}