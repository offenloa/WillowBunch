public class Chess {

    static Server myServer;
    static Client myClient;

    public static void main(String[] args) {
        
        Board b = new Board();
        b.reset();
        GUI myUI = new GUI(b);
        Thread t1 = new Thread(myUI);
        t1.run();

    }


    public static void startHosting(GUI myUI){
        myServer = new Server(5555, 1, myUI);
        System.out.println("You are host.");
        myServer.startServer();
        myServer.listen();
    }

    public static void joinGame(GUI myUI){
        myClient = new Client("127.0.0.1", 5555, myUI);
        myClient.connectToServer();
    }

    public static void sendMove(boolean isHost, int oldx, int oldy, int x, int y){
        String message = String.valueOf(oldx) + String.valueOf(oldy) + String.valueOf(x) + String.valueOf(y);
        System.out.println("sending " + message);
        if(isHost){
            myServer.myCC.sendStringMessageToClient(message);
        }
        else{
            myClient.sendStringMessageToServer(message);
        }
    }
}
