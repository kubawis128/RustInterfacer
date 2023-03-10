package com.kubawis128;

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
            Socket socket = new Socket("127.0.0.1", 7878);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"chat\",\"player\":\"" + event.getPlayer().getName() + "\",\"content\":\"" + event.getMessage() + "\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
    }

    @EventHandler
    public void joinMessage(PlayerJoinEvent event){
        try {
            Socket socket = new Socket("127.0.0.1", 7878);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"join\",\"player\":\""+ event.getPlayer().getName() +"\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
    }

    @EventHandler
    public void leaveMessage(PlayerQuitEvent event){
        try {
            Socket socket = new Socket("127.0.0.1", 7878);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"leave\",\"player\":\""+ event.getPlayer().getName() +"\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
    }

    @EventHandler
    public void deathMessage(PlayerDeathEvent event){
        try {
            Socket socket = new Socket("127.0.0.1", 7878);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"death\",\"player\":\""+ event.getPlayer().getName() +"\",\"content\":\"" + event.getDeathMessage() +"\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
    }

    @EventHandler
    public void playerCommand(PlayerCommandPreprocessEvent event){
        try {
            Socket socket = new Socket("127.0.0.1", 7878);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("{\"type_of_event\":\"command\",\"player\":\""+ event.getPlayer().getName() +"\",\"content\":\"" + event.getMessage() +"\"}");
            writer.close();
            socket.close();
        }catch (Exception e){
            // Zjebało sie
        }
    }
}
