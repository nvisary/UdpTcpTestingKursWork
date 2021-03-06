package com.nvisary.tcp;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpServer {
    private int port = 7777;
    private ServerSocket serverSocket;
    private boolean isServerRun = false;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public TcpServer(int listenPort) throws IOException {
        this.port = listenPort;
        this.serverSocket = new ServerSocket(port);
    }

    public void startServer() throws IOException {
        System.out.println("Server started.");
        isServerRun = true;

        while (isServerRun) {
            Socket socket = serverSocket.accept();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Client [" + socket.getInetAddress() + "] connected.");

            Thread receiveMessageThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        boolean isMessagingRunning = true;
                        while (isMessagingRunning) {
                            byte[] message = (byte[]) objectInputStream.readObject();
                            System.out.println(message.length / 1000 + " Kbytes");
                            //isMessagingRunning = !message.equals("/stop");
                            objectOutputStream.writeObject(message);
                        }

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

            /*Thread sendMessageThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        boolean isMessaging = true;
                        while (isMessaging) {
                            Scanner scanner = new Scanner(System.in);
                            String message = scanner.next();
                            objectOutputStream.writeObject(message);
                            isMessaging = !message.equals("/stop");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });*/
            //sendMessageThread.start();
            receiveMessageThread.start();
        }


    }
}
