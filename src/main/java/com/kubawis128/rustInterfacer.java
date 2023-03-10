package com.kubawis128;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class rustInterfacer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Thread thread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(7879);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String line = in.readLine();
                    // Tu z "Discord>" zmienic na np "@KubaWis#7288>"
                    Bukkit.broadcastMessage("Discord> " + line);
                }
            } catch (IOException e) {
            }
        });
        thread.start();
    }

        @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
