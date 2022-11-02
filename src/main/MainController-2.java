package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label servidorLabel;
    @FXML
    private Label esperaLabelServer;
    @FXML
    private Button botonServer;
    @FXML
    private TextField textServer;
    private Server server;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try{
            server = new Server(new ServerSocket(1234));
        }
        catch (IOException e) {
            System.out.println("Error al crear servidor");
        }

        server.receiveMessageFromClient();
        botonServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String serverMessage = textServer.getText();
                server.sendMessagetoClient(serverMessage);
            }
        });
    }
    public static void labelUpdate(String messageFromClient, Label temp2){
        temp2.setText(messageFromClient);
    }
}