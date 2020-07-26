public class SimpleNameMap {

    /* TODO: Instance variables here */

    public SimpleNameMap() {
        // TODO: YOUR CODE HERE
    }

    /* Returns the number of items contained in this map. */
    public int size() {
        // TODO: YOUR CODE HERE
        return 0;
    }

    /* Returns true if the map contains the KEY. */
    public boolean containsKey(String key) {
        // TODO: YOUR CODE HERE
        return false;
    }

    /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
    public String get(String key) {
        // TODO: YOUR CODE HERE
        return null;
    }

    /* Puts a (KEY, VALUE) pair into this map. If the KEY already exists in the
       SimpleNameMap, replace the current corresponding value with VALUE. */
    public void put(String key, String value) {
        // TODO: YOUR CODE HERE
    }

    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */
    public String remove(String key) {
        // TODO: YOUR CODE HERE
        return null;
    }

    private static class Entry {

        private String key;
        private String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}