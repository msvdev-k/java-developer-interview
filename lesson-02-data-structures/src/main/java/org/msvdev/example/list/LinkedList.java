package org.msvdev.example.list;

public class LinkedList<T> implements List<T> {

    private ListItem<T> first;
    private ListItem<T> last;

    private int count;

    public LinkedList() {
        first = null;
        last = null;
        count = 0;
    }

    @Override
    public void append(T obj) {
        ListItem<T> newItem = new ListItem<>(last, null, obj);

        if (first == null) {
            first = newItem;
        }

        if (last != null) {
            last.next = newItem;
        }

        last = newItem;
        count++;
    }

    @Override
    public T get(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return first.get(index);
    }

    @Override
    public void remove(T obj) {
        if (first == null) {
            return;
        }

        ListItem<T> item = first.find(obj);

        if (item == null) {
            return;
        }

        if (first != item && last != item) {
            ListItem<T> previous = item.previous;
            ListItem<T> next = item.next;
            previous.next = next;
            next.previous = previous;
        }

        if (first == item) {
            first = first.next;
            first.previous = null;
        }

        if (last == item) {
            last = last.previous;
            last.next = null;
        }

        count--;
    }

    @Override
    public String toString() {
        return "LinkedList - [" +
                first.walkToString() +
                ']';
    }


    private class ListItem<T> {
        public ListItem<T> previous;
        public ListItem<T> next;
        public T item;

        public ListItem(ListItem<T> previous, ListItem<T> next, T item) {
            this.previous = previous;
            this.next = next;
            this.item = item;
        }

        public T get(int index) {
            if (index == 0) {
                return item;
            }
            return next.get(index - 1);
        }

        public ListItem<T> find(T obj) {
            if (item.equals(obj)) {
                return this;
            }
            if (next != null) {
                return next.find(obj);
            }
            return null;
        }

        public String walkToString() {
            if (next == null) {
                return item.toString();
            }
            return item.toString() + ", " + next.walkToString();
        }
    }
}
