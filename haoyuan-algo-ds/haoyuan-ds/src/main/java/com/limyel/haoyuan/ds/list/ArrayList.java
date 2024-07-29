package com.limyel.haoyuan.ds.list;

public class ArrayList<E> implements List<E> {

    private Object[] data;
    private int size;

    public ArrayList(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public ArrayList() {
        this(10);
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCapacity() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addLast(E e) {
        if (size == data.length) {
            throw new IllegalArgumentException("添加失败，列表已满。");
        }
        data[size++] = e;
    }

    @Override
    public void insert(int index, E e) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E e) {

    }

    @Override
    public int index(E e) {
        return 0;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean delete(E e) {
        return false;
    }

    @Override
    public void resize(int newCapacity) {

    }

}
