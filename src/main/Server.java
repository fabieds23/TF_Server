package main;

import javafx.scene.control.Label;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // Atributos //
    private ServerSocket serverSocket; // servidor //
    private Socket socket;
    private BufferedReader readerServer; // lector //
    private BufferedWriter writerServer; // escritor //

    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.readerServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writerServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e) {
            System.out.println("Error al conectar con el servidor");
        }
    }
    public void sendMessagetoClient(String serverMessage) {
        try{
            writerServer.write(serverMessage);
            writerServer.newLine(); // para esperar otro mensaje //
            writerServer.flush(); // mandar el mensaje //
        }
        catch (IOException e) {
            System.out.println("Error enviando el mensaje");
        }
    }
    public void receiveMessageFromClient(Label temp) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()) {
                    try{
                        String messageFromClient = readerServer.readLine();
                        MainController.labelUpdate(messageFromClient, temp);
                    }
                    catch (IOException e) {
                        System.out.println("Error recibiendo mensaje");
                        break; // si hay error, sale del bucle //
                }
            }
        }).start();
    }
}
