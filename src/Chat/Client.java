package Chat;

import java.net.Socket;

public class Client {
    private Socket socket;
    private String name;

    public Client(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
    
}
