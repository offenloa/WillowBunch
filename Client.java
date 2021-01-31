import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

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
        this.myServerMessageHandler = new ServerMessageHandler(this, myUI);
    }

    public void connectToServer() {
        try {
            this.mySocket = new Socket(this.address, this.portNumber);
            input = mySocket.getInputStream();
            output = mySocket.getOutputStream();

            myClientThread = new Thread(this);
            myClientThread.start();
            stoppedThread.set(false);
            System.out.println("Connected to server");
        } catch (Exception e) {
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
            System.out.println("Disconnected from server");
        } catch (Exception e) {
            System.out.println("cannot disconnect");
            System.exit(0);
        }

    }

    public void sendMessagetoServer(byte msg) {
        try {
            output.write(msg);
            output.flush();
        } catch (IOException e) {
            System.out.println("cannot send to socket; exiting program.");
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
    }

    public int getPort() {
        return this.portNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
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
                disconnectFromServer();
            }
        }
    }
}

