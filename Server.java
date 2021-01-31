
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {

    InputStream input;
    OutputStream output;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    ClientMessageHandler myClientCommandHandler;
    GUI myUI;
    int portNumber = 5555, backlog = 500;
    boolean doListen = false;
    ClientConnection myCC;

    public Server(int portNumber, int backlog, GUI myUI) {
        this.portNumber = portNumber;
        this.backlog = backlog;
        this.myUI = myUI;
        this.myClientCommandHandler = new ClientMessageHandler(this, myUI);
    }

    public synchronized void setDoListen(boolean doListen) {
        this.doListen = doListen;
    }

    public void startServer() {
        if (serverSocket != null) {
            stopServer();
        } else {
            try {
                serverSocket = new ServerSocket(portNumber, backlog);
                serverSocketStarted();
            } catch (IOException e) {
                System.exit(0);
            } finally {
            }
        }

    }

    public void serverSocketStarted() {
        System.out.println("Server socket started");
    }

    public void serverStartedListening() {
        System.out.println("Server listening");
    }

    public void stopServer() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Cannot close ServerSocket, because " + e + ". Exiting program.");
                System.exit(0);
            } finally {
            }

        }
    }

    public void listen() {
        try {
            setDoListen(true);
            serverSocket.setSoTimeout(500);
            Thread myListenerThread = new Thread(this);
            myListenerThread.start();
            serverStartedListening();
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopListening() {
        setDoListen(false);
    }

    @Override
    public void run() {
        while (true) {
            if (doListen == true) {
                try {
                    clientSocket = serverSocket.accept();
                    myCC = new ClientConnection(clientSocket, myClientCommandHandler, this);
                    Thread myCCthread = new Thread(myCC);
                    myCCthread.start();
                    clientConnected(clientSocket.getRemoteSocketAddress().toString());
                } catch (IOException e) {
                    //check doListen.
                } finally {
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    private void clientConnected(String clientIPAddress) {
        System.out.println("Client connected");
    }

    public void clientDisconnected(String clientIPAddress) {
        System.out.println("\tClient " + clientIPAddress + " has been disconnected.");
    }

    public void setPort(int portNumber) {
        this.portNumber = portNumber;
    }

    public int getPort() {
        return this.portNumber;
    }

}
