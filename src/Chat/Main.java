package Chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 2 && args[1].equals("server")) {
            try {
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
                System.out.println("Lemurantin's server has started.");
                int countClient = 0;
                while (true) {
                    Socket clientSocket = null;
                    clientSocket = serverSocket.accept();
                    Client client = new Client(clientSocket, "Guest_" + (++countClient));
                    (new ClientConnection(client)).start();
                    System.out.println("User <" + countClient + "> has join the channel.");
                    Chat.getInstance().newMessage("An user as join the channel.");
                    Chat.getInstance().add(client);
                }
            } catch (IOException ex) {
                System.err.println("Error starting Lemurantin's server.");
            }
        } else if (args.length == 3 && args[1].equals("client")) {
            System.out.println("You have been connected to Lemurantin's server ("+args[2]+").");
            Scanner scanner = new Scanner(System.in);
            boolean availablePseudo = false;
            String pseudo = "Guest";
            while (!availablePseudo) {
                System.out.println("Choose an available name (3 characters minimum):");
                pseudo = scanner.nextLine();
                if (pseudo.length() >= 3 && Chat.getInstance().pseudoAvailable(pseudo)) {
                    availablePseudo = true;
                }
            }
            Socket serverSocket = new Socket(args[2], Integer.parseInt(args[0]));
            PrintStream os = new PrintStream(serverSocket.getOutputStream());
            os.println(pseudo);
            System.out.println("Welcome in the #1 room, there is " + (Chat.getInstance().getSize()) + " users connected.");
            new ClientInterface(serverSocket).start();
            while (true) {
                String message = scanner.nextLine();
                //if egal a /rename, ou exit
                os.println(message);
            }
        } else {
            System.out.println("Lemurantin's server usage: PORT [client HOST|server]");
        }
    }
}
