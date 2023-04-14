package com.kubawis128;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class EventListener implements Listener {
    @EventHandler
    public void chatMessage(AsyncPlayerChatEvent event){
        try {
            Socket socket = new Socket("192.168.1.201", 2139);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"chat\",\"player\":\"" + event.getPlayer().getName().replace("\"", "\\\"") + "\",\"content\":\"" + event.getMessage().replace("\"", "\\\"") + "\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
    }

    @EventHandler
    public void joinMessage(PlayerJoinEvent event){
        try {
            Socket socket = new Socket("192.168.1.201", 2139);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"join\",\"player\":\""+ event.getPlayer().getName().replace("\"", "\\\"") +"\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
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
    }

    @EventHandler
    public void leaveMessage(PlayerQuitEvent event){
        try {
            Socket socket = new Socket("192.168.1.201", 2139);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"leave\",\"player\":\""+ event.getPlayer().getName().replace("\"", "\\\"") +"\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
        try {
            Socket socket = new Socket("192.168.1.201", 2140);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(Bukkit.getOnlinePlayers().size()-1 + "/" + Bukkit.getServer().getMaxPlayers());
            writer.close();
            socket.close();
        }catch (Exception e){
            // A tam
        }
    }

    @EventHandler
    public void deathMessage(PlayerDeathEvent event){
        try {
            Socket socket = new Socket("192.168.1.201", 2139);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"death\",\"player\":\""+ event.getPlayer().getName().replace("\"", "\\\"") +"\",\"content\":\"" + event.getDeathMessage().replace("\"", "\\\"") +"\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
    }

    @EventHandler
    public void playerCommand(PlayerCommandPreprocessEvent event){
        try {
            if(event.getMessage().contains("/login") || event.getMessage().contains("/register") ){
                return;
            }
            Socket socket = new Socket("192.168.1.201", 2139);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"command\",\"player\":\""+ event.getPlayer().getName().replace("\"", "\\\"") +"\",\"content\":\"" + event.getMessage().replace("\"", "\\\"") +"\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
    }
}
