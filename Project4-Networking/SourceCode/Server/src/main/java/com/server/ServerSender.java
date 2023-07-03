/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author HuyBao
 */
public class ServerSender {

    public ServerSender(Socket s, Object message) throws IOException {
        PrintWriter pwOut = new PrintWriter(s.getOutputStream(), true);
        pwOut.println(message);
    }
}
