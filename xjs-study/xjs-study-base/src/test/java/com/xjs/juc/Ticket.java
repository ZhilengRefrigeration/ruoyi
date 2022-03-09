package com.xjs.juc;

/**
 * 练习juc
 * @author xiejs
 * @since 2022-03-02
 */
public class Ticket {

    private int number = 30;

    public synchronized void sale() {

        if (number > 0) {
            System.out.println(Thread.currentThread().getName()+":卖出:"+(number--)+"剩下:"+number);
        }
    }


}

class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() ->{

            //调用卖票方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }

        },"AA").start();

        new Thread(() ->{

            //调用卖票方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }

        },"BB").start();

        new Thread(() ->{

            //调用卖票方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }

        },"CC").start();
    }
}
