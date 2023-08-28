package Store.Database;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketData {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String clientAddress;

    public SocketData(Socket socket) {
        this.socket = socket;
        try{
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        this.clientAddress = socket.getInetAddress() +":" + socket.getPort();
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public String getClientAddress() {
        return clientAddress;
    }
}
