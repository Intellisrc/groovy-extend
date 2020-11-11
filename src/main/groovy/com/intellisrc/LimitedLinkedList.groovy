package com.intellisrc

import groovy.transform.CompileStatic

/**
 * Mix between LinkedList and Queue
 * it has the option to set a limited number of elements
 * @since 2020/07/29.
 * @author A.Lepe
 */
@CompileStatic
class LimitedLinkedList<T> extends LinkedList<T> {
    protected int listLength

    /**
     * Constructor. Initialize with a specific length
     * @param length
     */
    LimitedLinkedList(int length = 0) {
        listLength = length
    }

    protected boolean trim(boolean trimLast = false) {
        if(listLength > 0) {
            while (size() > listLength) {
                if(trimLast) {
                    pollLast() // From the tail
                } else {
                    pop() //From the head
                }
            }
        }
        return true
    }

    void setLength(int length) {
        listLength = length
        trim()
    }

    boolean isFull() {
        return size() == listLength
    }

    @Override
    boolean add(T t) {
        return super.add(t) && trim()
    }

    @Override
    void add(int index, T element) {
        super.add(index, element)
        trim()
    }

    boolean addAll(Collection<? extends T> c, boolean trimLast) {
        return addAll(c) && trim(trimLast)
    }

    @Override
    boolean addAll(Collection<? extends T> c) {
        return super.addAll(c) && trim()
    }

    boolean addAll(int index, Collection<? extends T> c, boolean trimLast) {
        return super.addAll(index, c) && trim(trimLast)
    }
    @Override
    boolean addAll(int index, Collection<? extends T> c) {
        return super.addAll(index, c) && trim()
    }

    @Override
    void addFirst(T t) {
        super.addFirst(t)
        trim(true)
    }

    @Override
    void addLast(T t) {
        super.addLast(t)
        trim()
    }

    void leftShift(T t) {
        super.leftShift(t)
        trim()
    }
}
