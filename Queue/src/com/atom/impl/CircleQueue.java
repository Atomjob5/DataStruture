package com.atom.impl;


import com.atom.IQueue;

import java.util.Queue;

/**
 *
 * 实现环形队列
 * @author Atom
 * @version 1.0
 * @date 2020/3/9 - 17:52
 */
public class CircleQueue extends IQueue {

    public CircleQueue(int size) {
        this.size = size;
        this.data = new int[size];
    }

    /**
     * 从队列尾添加数据
     *
     * @param val
     */
    @Override
    public void add(int val) {
        if (isFull()) {
            throw new RuntimeException("队列已经满了");
        }
        data[rear] = val;
        rear = (rear + 1) % size;
    }

    /**
     * 从队列头取出数据
     *
     * @return
     */
    @Override
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列还是空的");
        }
        int tmp = data[front];
        front = (front + 1) % size;
        return tmp;
    }

    /**
     * 打印队列数据
     */
    @Override
    public void show() {
        int num = (rear + size - front) % size;
        if (num == 0){
            throw new RuntimeException("队列还是空的");
        }
        for (int i = 0; i < num; i++) {
            System.out.printf("data[%d] => %d\n", (front+i) % size-1, data[(front+i) % size]);
        }
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    @Override
    public boolean isFull() {
        if ((this.rear + 1) % this.size == this.front){
            return true;
        }
        return false;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        if (this.front == this.rear){
            return true;
        }
        return false;
    }
}
