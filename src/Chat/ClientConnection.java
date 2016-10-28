package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConnection implements Runnable {

    private Socket socket;
    private Thread thread;
    private Chat chat;
    private BufferedReader isr;
    private Client client;

    public ClientConnection(Client client) throws IOException {
        this.client = client;
        this.thread = new Thread(this);
        this.chat = Chat.getInstance();
        this.isr = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
    }

    public void start() {
        thread.start();
    }

    public void run() {
        try {
            client.setName(isr.readLine());
            String message = "";
            while (message != null) {
                message = isr.readLine();
                if (message != null && message.length() > 0) {
                    chat.newMessage(client, message);
                }
            }
            System.out.println("<"+client.getName() + "> has disconnected.");
            chat.newMessage("<"+client.getName() + "> has disconnected.");
        } catch (IOException ex) {
            System.out.println("<"+client.getName() + "> has been interrupted.");
            chat.newMessage("<"+client.getName() + "> has been interrupted.");
        }
    }
}
