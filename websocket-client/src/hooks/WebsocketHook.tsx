import { Client } from "@stomp/stompjs";
import { useRef, useState } from "react";

export const useWebSocket = () => {
    const [connected, setConnected] = useState(false);
    const [messages, setMessages] = useState<string[]>([]);
    const stompClient = useRef<Client | null>(null);

    const connect = () => {
        const client = new Client({
            brokerURL: 'ws://localhost:8080/websocket',
            onConnect: () => {
                setConnected(true);
                client.subscribe('/topic/response', (message) => {
                    const payload = JSON.parse(message.body);
                    setMessages((prev) => [...prev, payload.message]);
                });
            },
            onWebSocketError: (error) => {
                console.error('WebSocket error', error);
            },
            onStompError: (frame) => {
                console.error('STOMP error', frame);
            }
        });
        stompClient.current = client;
        client.activate();
    };

    const disconnect = () => {
        if (stompClient.current) {
            stompClient.current.deactivate();
            setConnected(false);
        }
    };

    const sendMessage = (name: string) => {
        if (stompClient.current && connected) {
            stompClient.current.publish({
                destination: '/app/hello',
                body: JSON.stringify({ name })
            });
        }
    };

    return {
        connected,
        messages,
        connect,
        disconnect,
        sendMessage
    };
}