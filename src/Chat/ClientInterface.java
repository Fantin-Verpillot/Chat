package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientInterface extends Thread {

    private Socket socket;

    public ClientInterface(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader isr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = "";
            while (message != null) {
                message = isr.readLine();
                if (message != null) {
                    System.out.println(message);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
