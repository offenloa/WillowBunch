


public class ClientMessageHandler {

    Server myServer;
    String theCommand = "";
    GUI myUI;

    public ClientMessageHandler(Server myServer, GUI myUI){
        this.myServer = myServer;
        this.myUI = myUI;
    }

    public void handleClientMessage(ClientConnection myClientConnection, String msg) {
        if (msg.charAt(0)!=0xFFFF) { //Character.toString((char)-1)) = 0xFFFF
            theCommand += msg;
        } else {
            handleCompleteClientMessage(myClientConnection, theCommand);
            theCommand = "";
        }
    }


    public void handleCompleteClientMessage(ClientConnection myClientConnection, String fullMessage) {

        if(fullMessage.equals("9999")){
            myUI.reset();
        }
        else{
            System.out.println("Message recieved: " + fullMessage);
            
            int oldx, oldy, newx, newy;
            oldx = Integer.parseInt(fullMessage.substring(0, 1));
            oldy = Integer.parseInt(fullMessage.substring(1, 2));
            newx = Integer.parseInt(fullMessage.substring(2, 3));
            newy = Integer.parseInt(fullMessage.substring(3, 4));

            myUI.forceMove(oldx, oldy, newx, newy);
        }

            
    }
    

}


