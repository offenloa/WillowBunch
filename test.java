import java.io.*;


public class test {
    public static void main(String[] args) {
        Board b = new Board();
        b.reset();
        GUI myUI = new GUI(b);
        Thread t1 = new Thread(myUI);
        t1.run();

        System.out.println("Type 's' for Host, 'c' for Client");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        String testMessage = "This is a test message";

        while(true){

            try{
                input = reader.readLine();
                
            }catch(IOException e){
                System.out.println("IO error");
            }

            if(input.equals("s")){
                Server myServer = new Server(5555, 1, myUI);
                System.out.println("You are host.");
                myServer.startServer();
                myServer.listen();
            }
            else if(input.equals("c")){
                Client myClient = new Client("127.0.0.1", 5555, myUI);
                System.out.println("You are client.");
                myClient.connectToServer();
                myClient.sendStringMessageToServer("theMessage");
            }

        }

    }
}
