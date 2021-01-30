
public class ServerMessageHandler {


    Client myClient;
    String fullMessage = "";
    GUI myUI;

    public ServerMessageHandler(Client myClient, GUI myUI) {
        this.myClient = myClient;
        this.myUI = myUI;
    }
    
    public void handleServerMessage(Client myClient, String msg) {
        
        if (msg.charAt(0)!=0xFFFF) { //Character.toString((char(-1)) = 0xFFFF
            fullMessage += msg;
        } else {
            System.out.println("Message recieve: " + fullMessage);
            int oldx, oldy, newx, newy;
            oldx = Integer.parseInt(fullMessage.substring(0, 1));
            oldy = Integer.parseInt(fullMessage.substring(1, 2));
            newx = Integer.parseInt(fullMessage.substring(2, 3));
            newy = Integer.parseInt(fullMessage.substring(3, 4));


            myUI.forceMove(oldx, oldy, newx, newy);
            
            fullMessage = "";
        }
        
    }

}
