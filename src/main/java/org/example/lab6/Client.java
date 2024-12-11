package org.example.lab6;

public class Client {
    private ITarget client;

    public Client(ITarget client) {
        this.client = client;
    }

    public void Show() {
        client.ClientDouble(2.88);
        client.ClientInt(39);
        client.ClientChar('f');
    }
}