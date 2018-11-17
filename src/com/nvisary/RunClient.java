package com.nvisary;

import com.nvisary.tcp.TcpClient;

import java.io.IOException;
import java.util.Scanner;

public class RunClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Please input server ip and port.");
        System.out.print("Server ip: ");
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        System.out.println("Server port: ");
        int port = scanner.nextInt();
        TcpClient client = new TcpClient(ip, port);
        client.runClient();
    }
}
