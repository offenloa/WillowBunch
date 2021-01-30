
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientConnection implements Runnable {

    InputStream input;
    OutputStream output;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    ClientMessageHandler myClientCommandHandler;
    Server myServer;
    boolean stopThisThread = false;

    public ClientConnection(Socket clientSocket, ClientMessageHandler myClientCommandHandler, Server myServer) {
        this.clientSocket = clientSocket;
        this.myClientCommandHandler = myClientCommandHandler;
        this.myServer = myServer;
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            myServer.sendMessageToUI("Cannot create IO streams; exiting program.");
            System.out.println("Cannot make IO stream");
            System.exit(0);
        }
    }

    @Override
    public void run() {
        byte msg;
        String theClientMessage = "";
        while (stopThisThread == false) {
            try {
                msg = (byte) input.read();
                theClientMessage = Character.toString((char)msg);
                myClientCommandHandler.handleClientMessage(this, theClientMessage);
            } catch (IOException e) {
                myClientCommandHandler.handleClientMessage("IOException: "
                        + e.toString()
                        + ". Stopping thread and disconnecting client: "
                        + clientSocket.getRemoteSocketAddress());
                disconnectClient();
                stopThisThread = true;
            }
        }
    }


    public void sendMessageToClient(byte msg) {
        try {
            output.write(msg);
            output.flush();
        } catch (IOException e) {
            myServer.sendMessageToUI("cannot send to socket; exiting program.");
            System.exit(0);
        } finally {
        }
    }

    public void sendStringMessageToClient(String theMessage) {
        for (int i = 0; i < theMessage.length(); i++) {
            byte msg = (byte) theMessage.charAt(i);
            sendMessageToClient(msg);
        }
        sendMessageToClient((byte)(0xFFFF));
    }

    public void clientQuit() {
        disconnectClient();
    }

    public void clientDisconnect() {
        disconnectClient();
    }

    public void disconnectClient() {
        try {
            stopThisThread = true;
            clientSocket.close();
            clientSocket = null;
            input = null;
            output = null;
        } catch (IOException e) {
            myServer.sendMessageToUI("cannot close client socket; exiting program.");
            System.exit(0);
        } finally {
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
