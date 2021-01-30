import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.atomic.*;

public class Client implements Runnable {

    InputStream input;
    OutputStream output;
    Socket mySocket = null;
    ServerMessageHandler myServerMessageHandler;
    GUI myUI;
    Thread myClientThread;
    private AtomicBoolean stoppedThread = new AtomicBoolean(true);
    String address;
    int portNumber;

    public Client(String address, int portNumber, GUI myUI) {
        this.address = address;
        this.portNumber = portNumber;
        this.myUI = myUI;
        this.myServerMessageHandler = new ServerMessageHandler(this);
    }

    public void connectToServer() {
        try {
            this.mySocket = new Socket(this.address, this.portNumber);
            input = mySocket.getInputStream();
            output = mySocket.getOutputStream();

            myClientThread = new Thread(this);
            myClientThread.start();
            stoppedThread.set(false);
            //myUI.update("Connected to server.");
            System.out.println("Connected to server");
        } catch (Exception e) {
            //myUI.update("Error in connecting");
            System.out.println("Error in connecting");
        }

    }

    public boolean isConnected() {
        return (stoppedThread.get() == false);
    }

    public void disconnectFromServer() {
        try {
            stoppedThread.set(true);
            mySocket.close();
            mySocket = null;
            input = null;
            output = null;
            //myUI.update("Disconnected from Server.");
            System.out.println("Disconnected from server");
        } catch (Exception e) {
            //myUI.update("Cannot disconnect. Exiting program");
            System.out.println("cannot disconnect");
            System.exit(0);
        }

    }

    public void sendMessagetoServer(byte msg) {
        try {
            output.write(msg);
            output.flush();
        } catch (IOException e) {
            sendMessageToUI("cannot send to socket; exiting program.");
            System.exit(0);
        } finally {
        }
    }

    public void sendStringMessageToServer(String theMessage) {
        for (int i = 0; i < theMessage.length(); i++) {
            byte msg = (byte) theMessage.charAt(i);
            sendMessagetoServer(msg);
        }
        sendMessagetoServer((byte)(0xFFFF));
    }

    public void setPort(int portNumber) {
        this.portNumber = portNumber;
        //myUI.update("Port number updated to " + this.portNumber);
    }

    public int getPort() {
        return this.portNumber;
    }

    public void setAddress(String address) {
        this.address = address;
        //myUI.update("Address updated to " + this.address);
    }

    public String getAddress() {
        return this.address;
    }

    public void sendMessageToUI(String theString) {
        //myUI.update(theString);
    }

    @Override
    public void run() {

        byte msg;
        String theString;
        stoppedThread.set(false);
        while (stoppedThread.get() == false) {
            try {
                msg = (byte) input.read();
                theString = Character.toString((char) msg);
                myServerMessageHandler.handleServerMessage(this, theString);
            } catch (SocketException se) {
            } catch (IOException e) {
                System.out.println(e);
                if (e.toString().contains("Connection reset"))
                    sendMessageToUI(
                            "Connection was unexpectedly reset by remote host; stopping thread and disconnecting client.");
                else
                    sendMessageToUI("Cannot read from socket; stopping thread and disconnecting client.");
                disconnectFromServer();
            }
        }
    }
}

