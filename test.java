public class test {
    public static void main(String[] args) {
        Board b = new Board();
        b.reset();
        GUI myUI = new GUI(b);
        Thread t1 = new Thread(myUI);
        t1.run();
    }
}
