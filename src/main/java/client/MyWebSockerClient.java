package client;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Slf4j
public class MyWebSockerClient extends WebSocketClient {

    public MyWebSockerClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("Connected to the server!");
    }

    @Override
    public void onMessage(String message) {
        log.info("Received message: {}", message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Disconnected from server: {}", reason);
    }

    @Override
    public void onError(Exception ex) {
        log.error("Received error: {}", ex.getMessage());
    }
}
