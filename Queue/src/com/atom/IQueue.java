package com.atom;

import com.atom.impl.CircleQueue;

import java.util.Scanner;

/**
 * @author Atom
 * @version 1.0
 * @date 2020/3/9 - 17:48
 */
public abstract class IQueue {
    protected int rear;  //队列尾
    protected int front;  //队列头
    protected int size; //队列容量
    protected int[] data; //存储数据的数组

    /**
     * 从队列尾添加数据
     * @param val
     */
    public abstract void add(int val);

    /**
     * 从队列头取出数据
     * @return
     */
    public abstract int pop();

    /**
     * 打印队列数据
     */
    public abstract void show();

    /**
     * 队列是否已满
     * @return
     */
    public abstract boolean isFull();

    /**
     * 队列是否为空
     * @return
     */
    public abstract boolean isEmpty();


    public static void main(String[] args) {
        IQueue queue = new CircleQueue(4);
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop){
            try {
                switch (scanner.next()){
                    case "a":  //添加数据
                        System.out.print("请输入值：");
                      queue.add(scanner.nextInt());
                      break;
                    case "p": //取出数据
                        System.out.println("queue.pop() = " + queue.pop());
                        break;
                    case "q":  //停止程序
                        stop = true;
                        break;
                    case "s":  //打印队列
                        queue.show();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
