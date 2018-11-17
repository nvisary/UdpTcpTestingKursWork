package com.nvisary.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    private String serverIp;
    private int serverPort;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public TcpClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void runClient() throws IOException, InterruptedException, ClassNotFoundException {
        Socket socket = new Socket(serverIp, serverPort);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());

        while (true) {
            float sendingTime;
            float receivingTime;
            System.out.println("Try to send...");
            sendingTime = System.nanoTime();
            objectOutputStream.writeObject(new byte[10000000]);
            System.out.println("Send.");
            receivingTime = System.nanoTime();
            System.out.println("Try to receiving...");
            objectInputStream.readObject();
            System.out.println("Receive.");
            System.out.println("Packet time: " + (receivingTime - sendingTime) * 1000 + " seconds");
            System.out.println("Thread sleep...");
            Thread.sleep(3000);
        }

        /*Thread receiveMessageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean isMessagingRunning = true;
                    while (isMessagingRunning) {
                        String message = (String) objectInputStream.readObject();
                        System.out.println("Server: " + message);
                        isMessagingRunning = !message.equals("/stop");
                    }


                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread sendMessageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean isMessaging = true;
                    while (isMessaging) {
                        Scanner scanner = new Scanner(System.in);
                        String message = scanner.nextLine();
                        sendingTime = System.nanoTime();
                        objectOutputStream.writeObject(message);
                        isMessaging = !message.equals("/stop");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        sendMessageThread.start();
        receiveMessageThread.start();*/
    }
}
