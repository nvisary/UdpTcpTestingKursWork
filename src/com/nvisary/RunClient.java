package com.nvisary;

import com.nvisary.tcp.TcpClient;

import java.io.IOException;

public class RunClient {
    public static void main(String[] args) throws IOException {

        TcpClient client = new TcpClient("localhost", 7777);
        client.runClient();
    }
}
