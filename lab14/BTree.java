public class BTree<T extends Comparable<T>> {

    /* Root of the tree. */
    TwoThreeFourNode<T> root;

    public static class TwoThreeFourNode<T> implements Node<T> {

        private final T[] items;
        private final Node<T>[] children;

        @SuppressWarnings("unchecked")
        public TwoThreeFourNode(T... items) {
            this.items = (T[]) new Object[items.length];
            System.arraycopy(items, 0, this.items, 0, items.length);
            this.children = (Node<T>[]) new Node[items.length + 1];
        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        @Override
        public int getChildrenCount() {
            return children.length;
        }

        @Override
        public T getItemAt(int i) {
            if (i < 0 || i >= items.length) {
                throw new IllegalArgumentException("No item at index: " + i);
            }
            return items[i];
        }

        @Override
        public Node<T> getChildAt(int i) {
            return children[i];
        }

        @Override
        public void setChildAt(int i, Node<T> node) {
            if (i < 0 || i >= children.length) {
                throw new IllegalArgumentException(
                    "Child index out of bounds: " + i);
            }
            if (!(node instanceof TwoThreeFourNode)) {
                throw new IllegalArgumentException(
                    "Children of 2-3-4 node must be a 2-3-4 node.");
            }
            children[i] = node;
        }
    }
}
