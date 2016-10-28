package Chat;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat {

    private ArrayList<Client> clients = new ArrayList<>();
    private static Chat instance = new Chat();
    private int size = 0;

    private Chat() {

    }

    public static Chat getInstance() {
        return instance;
    }

    public void newMessage(Client poster, String message) throws IOException {
        for (Client client : clients) {
            if (!client.getSocket().isClosed() && !poster.getName().equals(client.getName())) {
                new PrintStream(client.getSocket().getOutputStream()).println("<" + poster.getName() + "> " + message);
            }
        }
    }

    public void newMessage(String message) {
        try {
            for (Client client : clients) {
                if (!client.getSocket().isClosed()) {
                    new PrintStream(client.getSocket().getOutputStream()).println(message);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public boolean pseudoAvailable(String pseudo) {
        for (Client client : clients) {
            if (client.getName().equals(pseudo)) { //TODO ne semble pas marcher
                return false;
            }
        }
        return !pseudo.equals("Lemurantin");
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    void add(Client client) {
        clients.add(client); //marche pas
        ++size;
    }
}
