package org;

import client.MyWebSockerClient;
import server.MyWebSockerServer;

import java.net.URI;

public class Main {
    public static void main(String[] args) throws Exception{
        MyWebSockerServer server = new MyWebSockerServer(8080);
        server.start();

        MyWebSockerClient client = new MyWebSockerClient(new URI("ws://localhost:8080"));
        client.connect();

        while (!client.isOpen()) {
            Thread.sleep(100);
        }

        while (true) {
            client.send("Hello");
            Thread.sleep(1000);
        }
    }
}