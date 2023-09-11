package com.kubawis128;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class rustInterfacer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            Socket socket = new Socket("192.168.1.201", 2139);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"turn_on\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            try {
                Socket socket = new Socket("192.168.1.201", 2140);
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers());
                writer.close();
                socket.close();
            }catch (Exception e){
                // A tam
            }
        },0,6000);
        Thread thread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(2138);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String line = in.readLine();
                    // Tu z "Discord>" zmienic na np "@KubaWis#7288>"
                    System.out.println("Getting: " + line);
                    JsonObject incoming = new JsonParser().parse(line).getAsJsonObject();
                    String content = incoming.get("content").getAsString();
                    if(!content.isEmpty()){
                        Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "@" + incoming.get("author").getAsString() + "> "  + ChatColor.RESET + content);
                    }
                }
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e);
            }
        });
        thread.start();
    }

        @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
