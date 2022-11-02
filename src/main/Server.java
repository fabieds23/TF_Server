package main;

import javafx.scene.control.Label;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph; //

import java.awt.*;
import static main.Bubblesort.bubbleSort;
import static main.Quicksort.quicksort;
import static main.Radix.radixSort;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Clase del servidor
 */
public class Server {
    // Atributos //
    private ServerSocket serverSocket; // servidor //
    private Socket socket;
    private BufferedReader readerServer; // lector //
    private BufferedWriter writerServer; // escritor //
    ListaArchivos listaUtilizable = new ListaArchivos();

    Integer c = 0;
    /**
     * Método para crear el servidor
     */
    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.readerServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writerServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor");
        }
    }
    /**
     * Método para enviar mensajes al cliente
     */
    public void sendMessagetoClient(String serverMessage) {
        try {
            writerServer.write(serverMessage);
            writerServer.newLine(); // para esperar otro mensaje //
            writerServer.flush(); // mandar el mensaje //
        } catch (IOException e) {
            System.out.println("Error enviando el mensaje");
        }
    }
    /**
     * Método para recibir mensajes del cliente
     */
    public void receiveMessageFromClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        c += 1;
                        ArrayList<String> fabiux = new ArrayList<>();
                        String messageFromClient = readerServer.readLine();
                        if (Objects.equals(messageFromClient,"VAMOS A CORRER ESTA VARA")){
                            sendMessagetoClient(listaUtilizable.parseParseamos("viva").toString());
                        }
                        if (messageFromClient.contains("buscaQ:")){
                            String pop = messageFromClient.substring(8);
                            listaUtilizable.parseParseamos(pop);
                            String[] pepe = new String[listaUtilizable.parseParseamos(pop).size()];
                            for (int i = 0; i < listaUtilizable.parseParseamos(pop).size();i++){
                                pepe[i] = listaUtilizable.parseParseamos(pop).get(i);
                            }
                            quicksort(pepe, 0, pepe.length-1);
                            sendMessagetoClient(Arrays.toString(pepe));
                        }
                        if (messageFromClient.contains("buscaB:")){
                            String pop = messageFromClient.substring(8);
                            listaUtilizable.parseParseamosB(pop);
                            String[] nombres = new String[listaUtilizable.parseParseamosB(pop).size()];
                            int[] pepo = new int[listaUtilizable.parseParseamosB(pop).size()];
                            for (int i = 0; i < listaUtilizable.parseParseamosB(pop).size();i++){
                                pepo[i] = Integer.parseInt(listaUtilizable.parseParseamosB(pop).get(i).substring(0,1));
                            }
                            bubbleSort(pepo);
                            sendMessagetoClient(Arrays.toString(pepo));
                        }
                        if (messageFromClient.contains("buscaR:")){
                            String pop = messageFromClient.substring(8);
                            listaUtilizable.parseParseamosR(pop);
                            String[] nombres = new String[listaUtilizable.parseParseamosR(pop).size()];
                            int[] peps = new int[listaUtilizable.parseParseamosR(pop).size()];
                            for (int i = 0; i < listaUtilizable.parseParseamosR(pop).size();i++){
                                peps[i] = Integer.parseInt(listaUtilizable.parseParseamosR(pop).get(i).substring(0,1));
                            }
                            radixSort(peps, peps.length);
                            System.out.println(Arrays.toString(nombres));
                            sendMessagetoClient(Arrays.toString(peps));
                        }
                        if(messageFromClient.contains(",")){
                            String[] parts = messageFromClient.split(",");
                            listaUtilizable.appendItem(parts[0], parts[1], parts[2], parts[3]);
                            System.out.println(messageFromClient);
                            listaUtilizable.showList();
                        }

                        if(messageFromClient.contains("abrir:")){
                            String pop = messageFromClient.substring(7);
                            File objetoFile = new File(pop.replace(" ",""));
                            Desktop.getDesktop().open(objetoFile);
                        }
                        if (messageFromClient.contains("borrar:")){
                            String pop = messageFromClient.substring(8, messageFromClient.length()-1);
                            listaUtilizable.delete(pop);
                        }




                    } catch (IOException e) {
                        System.out.println("Error recibiendo mensaje");
                        break; // si hay error, sale del buckle //
                    }
                }
            } 
        }).start();
    }
}
