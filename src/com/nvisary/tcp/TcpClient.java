package com.nvisary.tcp;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
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

    public byte[] loadByteArrayFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        byte[] result = Files.readAllBytes(file.toPath());
        return result;
    }

    public void saveByteArrayToFile(byte[] arr, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(arr);
        fos.close();
    }

    public void runClient() throws IOException, InterruptedException, ClassNotFoundException {
        Socket socket = new Socket(serverIp, serverPort);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        int count = 0;
        String filename = "img/image.jpg";
        System.out.println("Load file..");
        byte[] fileToSend = loadByteArrayFromFile(filename);
        while (count < 10) {
            float sendingTime;
            float receivingTime;

            System.out.println("Try to send...");
            sendingTime = System.nanoTime();
            objectOutputStream.writeObject(fileToSend);
            System.out.println("Send.");
            receivingTime = System.nanoTime();

            System.out.println("Try to receiving...");
            byte[] tmp = (byte[]) objectInputStream.readObject();
            System.out.println("Receive.");
            System.out.println("Packet time: " + (receivingTime - sendingTime) * 1000 + " seconds");
            System.out.println("File size: " + fileToSend.length / 1000 + " Kbytes");
            String newFileName = "tmp" + count + "jpg";
            System.out.println("Saving file with name: " + newFileName);
            saveByteArrayToFile(tmp, newFileName);
            count++;
        }
    }
}
