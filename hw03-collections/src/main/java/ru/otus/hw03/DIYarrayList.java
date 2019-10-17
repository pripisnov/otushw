package ru.otus.hw03;

import java.util.*;

public class DIYarrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] items;
    private int currentIndex;

    public DIYarrayList() {
        this.items = (T[]) new Object[INITIAL_CAPACITY];
    }

    public DIYarrayList(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    private void resize() {
        this.items = Arrays.copyOf(this.items, this.items.length * 2);
    }

    private void resize(int addSize) {
        this.items = Arrays.copyOf(this.items, (this.items.length + addSize) * 2);
    }

    @Override
    public int size() {
        return this.currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current;

            @Override
            public boolean hasNext() {
                T[] arr = DIYarrayList.this.items;
                return this.current < arr.length && arr[this.current] != null;
            }

            @Override
            public T next() {
                return DIYarrayList.this.items[this.current++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        System.arraycopy(this.items, 0, array, 0, this.size());
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (this.currentIndex == this.items.length) this.resize();
        this.items[this.currentIndex] = t;
        this.currentIndex++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int idx;
        while ((idx = this.indexOf(o)) > -1) {
            if (this.currentIndex - idx + 1 >= 0) {
                int srcPos = idx + 1;
                int len = this.currentIndex - srcPos;
                System.arraycopy(this.items, srcPos, this.items, idx, len);
            }
            this.items[this.currentIndex - 1] = null;
            this.currentIndex--;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (this.currentIndex + c.size() >= this.items.length)
            this.resize(this.currentIndex + c.size() - this.items.length);
        for (T t : c) {
            this.add(t);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        return this.items[index];
    }

    @Override
    public T set(int index, T element) {
        this.items[index] = element;
        return this.items[index];
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size(); i++) {
            if (this.items[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int idx = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.items[i].equals(o)) idx = i;
        }
        return idx;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            private int current;

            @Override
            public boolean hasNext() {
                T[] arr = DIYarrayList.this.items;
                return this.current < arr.length && arr[this.current] != null;
            }

            @Override
            public T next() {
                if (hasNext()) return DIYarrayList.this.items[this.current++];
                throw new NoSuchElementException();
            }

            @Override
            public boolean hasPrevious() {
                return this.current > 0;
            }

            @Override
            public T previous() {
                if (hasPrevious()) return DIYarrayList.this.items[--this.current];
                throw new NoSuchElementException();
            }

            @Override
            public int nextIndex() {
                return this.current;
            }

            @Override
            public int previousIndex() {
                return this.current - 1;
            }

            @Override
            public void remove() {
                DIYarrayList.this.remove(this.current);
            }

            @Override
            public void set(T t) {
                DIYarrayList.this.set(this.current - 1, t);
            }

            @Override
            public void add(T t) {
                DIYarrayList.this.add(t);
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("DIYarrayList[");
        for (int i = 0; i < this.size(); i++) {
            s.append(this.items[i]);
            if (i + 1 != this.size())
                s.append(", ");
        }
        s.append("]");
        return s.toString();
    }
}
