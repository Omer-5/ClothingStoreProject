package Store.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream fromNetInputStream;
        DataOutputStream toNetOutputStream;
        
        try{
            socket = new Socket("localhost", 7000);
            System.out.println(new Date() + "--> connected to server at " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            fromNetInputStream = new DataInputStream(socket.getInputStream());
            toNetOutputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println(fromNetInputStream.toString());

        } catch (Exception e)
        {
            System.err.println(e);
        }finally{
            try{
                socket.close();
                System.out.println("Client said goodbye...");
            }catch (IOException e){}
        }
    }
    
}
