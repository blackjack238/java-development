package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread0 =new Thread(()->{
            int i=0;
    while (true){
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        i++;
        System.out.println(i);
    }
        });
        Thread thread1 =new Thread(()->{

            while (true){
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("минуло 5 секунд");
            }
        });

    thread0.start();
    thread1.start();


    }}