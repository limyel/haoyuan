package com.limyel.haoyuan.ds.list;

public interface List<E> {

    /**
     * 获取元素个数
     * @return
     */
    int getSize();

    /**
     * 获取列表容量
     * @return
     */
    int getCapacity();

    /**
     * 列表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 向列表尾部添加元素
     * @param e
     */
    void addLast(E e);

    /**
     * 指定位置插入
     * @param index
     * @param e
     */
    void insert(int index, E e);

    E get(int index);

    void set(int index, E e);

    int index(E e);

    E remove(int index);

    boolean delete(E e);

    void resize(int newCapacity);

}
