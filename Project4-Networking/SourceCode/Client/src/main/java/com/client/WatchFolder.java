/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HuyBao
 */
public class WatchFolder implements Runnable {

    public static WatchService watchService;
    private Socket socket = null;

    public WatchFolder(Socket s) {
        this.socket = s;
    }

    public void dispose() throws IOException {
        watchService.close();
    }

    @Override
    public void run() {
        try {
            watchService = FileSystems.getDefault().newWatchService();

            Path directory = Path.of(ClientHandler.pathDirectory);
            Path directory_deleted = Path.of(ClientHandler.pathDirectory);
            WatchKey watchKey = directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

            while (true) {
                for (WatchEvent<?> event : watchKey.pollEvents()) {

                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;

                    Path fileName = pathEvent.context();
                    WatchEvent.Kind<?> kind = event.kind();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    String log = null;
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        if (Files.isDirectory(directory.resolve(fileName))) {
                            log = "A new folder is created : " + fileName.getFileName();
                        } else if (Files.isRegularFile(directory.resolve(fileName))) {
                            log = "A new file is created : " + fileName.getFileName();
                        }
                        ClientSender clientSender = new ClientSender(socket, dateFormat.format(date),
                                String.valueOf(socket.getLocalPort()),
                                log,
                                ClientHandler.pathDirectory,
                                "<html><font color=\"green\">Created</font></html>");
                    }
                    if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        log = "A folder or file is deleted : " + fileName.getFileName();
                        ClientSender clientSender = new ClientSender(socket, dateFormat.format(date),
                                String.valueOf(socket.getLocalPort()),
                                log,
                                ClientHandler.pathDirectory, "<html><font color=\"red\">Deleted</font></html>");
                    }
                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        log = "A folder is modified : " + fileName.getFileName();
                        ClientSender clientSender = new ClientSender(socket,
                                dateFormat.format(date),
                                String.valueOf(socket.getLocalPort()),
                                log,
                                ClientHandler.pathDirectory, "<html><font color=\"orange\">Modified</font></html>");
                    }
                }
                boolean valid = watchKey.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
