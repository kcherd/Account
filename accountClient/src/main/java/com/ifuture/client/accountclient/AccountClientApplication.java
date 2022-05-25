package com.ifuture.client.accountclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountClientApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AccountClientApplication.class, args);

        Client client = new Client(args);
        client.callServer();
    }

}
