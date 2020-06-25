public class ModNCounter {

    private int myCount;

    public ModNCounter() {
        myCount = 0;
    }

    public void increment() {
        myCount++;
    }

    public void reset() {
        myCount = 0;
    }

    public int value() {
        return myCount;
    }

}
