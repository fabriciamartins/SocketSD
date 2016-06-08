/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 *
 * @author Fabrícia
 */
public class SocketClient {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.net.SocketTimeoutException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, SocketTimeoutException, ClassNotFoundException {

        try{
            
            String palavra;
            final int TEMPO_TIMEOUT = 10000;
            
            do{
                System.out.println("Digite a palavra que deseja saber o significado ou FIM para sair: \n");
                Scanner sc = new Scanner(System.in);
                palavra = sc.nextLine();
                
                //se não for digitado nenhum caracter, a palavra é solicitada novamente
                if(palavra.isEmpty()){
                    continue;
                }

                //instancia um SocketAddress passando o ip do cliente e a porta que o servidor esta ouvindo
                SocketAddress socketAddress = new InetSocketAddress("localhost",12345);

                Socket cliente = new Socket();
                cliente.connect(socketAddress, TEMPO_TIMEOUT);

                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                saida.writeObject(palavra);
                saida.flush();

                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                String resposta = (String)entrada.readObject();

                System.out.println(formatarResposta(palavra,resposta));

                entrada.close();
                saida.close();
                cliente.close();
                
            }while(!palavra.equalsIgnoreCase("fim"));
            
            System.out.println("Programa encerrado.");
        }
        catch(SocketTimeoutException e){
            System.out.println("Erro Timeout \n"+e.getMessage());
        }
        catch(IOException e2){
            System.out.println("Servidor Socket não encontrado. \n"+e2.getMessage());
        }
        catch(ClassNotFoundException e3){
            System.out.println(e3.getMessage());
        }

    }
    
    private static String formatarResposta(String palavra, String resposta){
        StringBuilder stringResposta = new StringBuilder();
        stringResposta.append("\n");
        stringResposta.append(palavra);
        stringResposta.append(": \n\n");
        stringResposta.append(resposta);
        stringResposta.append("\n\n");
        return stringResposta.toString();
    }
    
}
