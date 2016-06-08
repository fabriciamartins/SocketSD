/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import util.Dicionario;

/**
 *
 * @author Fabrícia
 */
public class ThreadSocketClient extends Thread {
    
    private Socket cliente;
    private Dicionario dicionario;
    
    /**
     *
     * @param cliente
     */
    public ThreadSocketClient(Socket cliente){
        this.cliente = cliente;
        this.dicionario = new Dicionario();
    }
    
    @Override
    public void run(){
        
        try{
            
            //objeto que recebe a palavra enviada pelo cliente
            ObjectInputStream entrada = new ObjectInputStream(this.cliente.getInputStream());
            //objeto que envia o significado da palavra para o cliente
            ObjectOutputStream saida = new ObjectOutputStream(this.cliente.getOutputStream());
            
            String chaveDicionario = (String)entrada.readObject();
            
            String valorDicionario = dicionario.pesquisarSignificado(chaveDicionario);
            
            saida.writeObject(valorDicionario);
            saida.flush();
            
            saida.close();
            cliente.close();
            
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    
}
