/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Fabrícia
 */
public class SocketServer {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        try{
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Servidor inicializado na porta 12345");

            while(true){

                Socket cliente = server.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                new ThreadSocketClient(cliente).start();

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
