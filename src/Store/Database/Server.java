package Store.Database;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

public class Server {
    public static void main(String[] args)  throws IOException{
        final ServerSocket server = new ServerSocket(7000);
        System.out.println(new Date() + " --> Server waits for clients ....");

        while(true)
        {
            final Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    SocketData currentSocketData = new SocketData(socket);
                    System.out.println(new Date() + " --> Client connected from "+ currentSocketData.getClientAddress());

                    // Connected - Main Logic
                    
                }
            }).start();
        }
    }
}
