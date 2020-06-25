import static org.junit.Assert.*;

public class CounterTest {

    @org.junit.Test
    public void testConstructor() {
        ModNCounter c = new ModNCounter();
        assertTrue(c.value() == 0);
    }

    @org.junit.Test
    public void testIncrement() throws Exception {
        ModNCounter c = new ModNCounter();
        c.increment();
        assertTrue(c.value()  == 1);
        c.increment();
        assertTrue(c.value() == 2);
    }

    @org.junit.Test
    public void testReset() throws Exception {
        ModNCounter c = new ModNCounter();
        c.increment();
        c.reset();
        assertTrue(c.value() == 0);
    }
}