package Store.Database;

import java.io.*;
import java.net.Socket;

public class SocketData {
    private Socket socket;
    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private String clientAddress;

    public SocketData(Socket socket) {
        this.socket = socket;
        try {
            this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clientAddress = socket.getInetAddress() + ":" + socket.getPort();
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getInputStream() {
        return inputStream;
    }

    public PrintWriter getOutputStream() {
        return outputStream;
    }

    public String getClientAddress() {
        return clientAddress;
    }
}