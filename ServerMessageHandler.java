
public class ServerMessageHandler {


    Client myClient;
    String fullMessage = "";

    public ServerMessageHandler(Client myClient) {
        this.myClient = myClient;
    }
    
    public void handleServerMessage(Client myClient, String msg) {
        
        if (msg.charAt(0)!=0xFFFF) { //Character.toString((char(-1)) = 0xFFFF
            fullMessage += msg;
        } else {
            //System.out.println(fullMessage);
            if(fullMessage.contains("hello")){
                myClient.disconnectFromServer();
            }
            else{
                myClient.sendMessageToUI(fullMessage);
            }
            fullMessage = "";
        }
        
    }

}
