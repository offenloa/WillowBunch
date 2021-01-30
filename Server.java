
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
        this.myClientCommandHandler = new ClientMessageHandler(this);
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
        //myUI.update("Server socket has started successfully, on port: "+portNumber);
        System.out.println("Server socket started");
    }

    public void serverSocketStopped() {
        //myUI.update("Server socket has stopped successfully, on port: "+portNumber);
    }

    public void serverStartedListening() {
        //myUI.update("Server socket now listening on port: "+portNumber);
        System.out.println("Server listening");
    }

    public void serverStoppedListening() {
        //myUI.update("Server socket has stopped listening on port: "+portNumber);
    }

    public void stopServer() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                serverSocketStopped();
            } catch (IOException e) {
                sendMessageToUI("Cannot close ServerSocket, because " + e + ". Exiting program.");
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
        serverStoppedListening();
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
        sendMessageToUI("Client connected:\n\tRemote Socket Address = " + clientIPAddress + "\n\tLocal Socket Address = " + clientSocket.getLocalSocketAddress());
        
        System.out.println("Client connected");
        
    }

    public void clientDisconnected(String clientIPAddress) {
        sendMessageToUI("\tClient " + clientIPAddress + " has been disconnected.");
    }

    public void setPort(int portNumber) {
        this.portNumber = portNumber;
    }

    public int getPort() {
        return this.portNumber;
    }

    public void sendMessageToUI(String theString) {
        //myUI.update(theString);
    }
}
