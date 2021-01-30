


public class ClientMessageHandler {

    Server myServer;
    String theCommand = "";

    public ClientMessageHandler(Server myServer) {
        this.myServer = myServer;
    }

    public void handleClientMessage(ClientConnection myClientConnection, String msg) {
        if (msg.charAt(0)!=0xFFFF) { //Character.toString((char)-1)) = 0xFFFF
            theCommand += msg;
        } else {
            handleCompleteClientMessage(myClientConnection, theCommand);
            theCommand = "";
        }
    }

    public void handleClientMessage(String theExceptionalEvent) {
        myServer.sendMessageToUI(theExceptionalEvent);
    }

    public void handleCompleteClientMessage(ClientConnection myClientConnection, String theCommand) {

        switch (theCommand) {
            //send command to the other client
        }

            
    }
    

}


