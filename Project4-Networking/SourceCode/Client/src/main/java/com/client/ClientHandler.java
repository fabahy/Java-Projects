/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.client;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HuyBao
 */
public class ClientHandler {

    public static Socket socket = null;
    public static String nameClient = "Client";
    public static ArrayList<String> listNameClient = new ArrayList<>();
    public static String pathDirectory = "";


    public ClientHandler(int port, String address) {
        if (socket != null && socket.isConnected()) {
            JOptionPane.showMessageDialog(null, "Connected!");
        } else {
            try {
                socket = new Socket(address, port);
                Client.btnConnect.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Connect successfully !!!");
                new Thread(new ClientReceiver(socket)).start();
            } catch (IOException e2) {
                JOptionPane.showMessageDialog(null, "<html><b style=\"font-size:10px;\">The server has not started<br>Please ensure to start the server</b></html>", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
