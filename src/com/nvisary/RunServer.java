package com.nvisary;

import com.nvisary.tcp.TcpClient;
import com.nvisary.tcp.TcpServer;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Scanner;

public class RunServer {

    public static void main(String[] args) throws IOException {
        System.out.print("Please input server running port: ");
        Scanner scanner = new Scanner(System.in);
        int port = scanner.nextInt();
        TcpServer server = new TcpServer(port);
        server.startServer();
    }
}
