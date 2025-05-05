package server;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

@Slf4j
public class MyWebSockerServer extends WebSocketServer {

    public MyWebSockerServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onStart() {
        log.info("WebSocket server started successfully.");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Write a message to the board");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        log.info("Websocket closed: {}", reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        log.info("Received message: {}", message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        log.error("Error occurred: {}", ex.getMessage());
    }
}
