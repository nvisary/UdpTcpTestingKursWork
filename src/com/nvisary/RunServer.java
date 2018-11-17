package com.nvisary;

import com.nvisary.tcp.TcpClient;
import com.nvisary.tcp.TcpServer;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;

public class RunServer {

    public static void main(String[] args) throws IOException {
        TcpServer server = new TcpServer(7777);
        server.startServer();
    }
}
