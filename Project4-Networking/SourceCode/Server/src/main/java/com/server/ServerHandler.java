/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyBao
 */
public class ServerHandler implements Runnable {

    private final int port;
    public Socket s = null;
    public static String nameClient;
    public static ServerSocket ss = null;

    public ServerHandler(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(port);
            while (true) {
                s = ss.accept();
                Server.listSocketClient.add(s);
                String name = "Machine " + s.getPort();
                Server.listModel.addElement(name);
                Server.Name_Socket.put(name, s);
                new Thread(new ServerReceiver(Server.Name_Socket.get(name))).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
