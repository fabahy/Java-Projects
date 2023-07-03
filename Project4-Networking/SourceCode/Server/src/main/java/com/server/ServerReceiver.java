/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HuyBao
 */
public class ServerReceiver implements Runnable {

    private final Socket socket;
    private int count = 0;

    public ServerReceiver(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String message = bufferedReader.readLine();
                String[] arrayStrings = message.split(",");

                // Split the message to handle
                String date = arrayStrings[0];
                String name = arrayStrings[1];
                String log = arrayStrings[2];
                String path = arrayStrings[3];
                String action = arrayStrings[4];

                Server.pathDirectory = path;
                Server.Name_Socket.put(name, socket);
                DefaultTableModel tableModel = (DefaultTableModel) Server.tableLogs.getModel();
                tableModel.addRow(new Object[]{
                    ++count, date, name, log, path, action
                });
                ServerSender serverSender = new ServerSender(socket, Server.pathDirectory);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
