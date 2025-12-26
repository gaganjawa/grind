package org.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadStateDemo {
//    Thread.State; // all thread states are in here

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        Runnable status = () -> {
            try {
                while(true) {
                    Thread.sleep(5000);
                    printThreads(threads);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };

        Thread reporter = new Thread(status);
        reporter.start(); 

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n Prime number of n. Enter n: ");
            int n = sc.nextInt();
            if(n == 0) {
                reporter.interrupt();
                break;
            }

            Thread t = new Thread(() -> System.out.println("Entered " + n));
            threads.add(t);
            t.start();
        }
    }

    private static void printThreads(List<Thread> threads) {
        System.out.println("\n Threads status: ");
        threads.forEach(thread -> {
            System.out.println(thread.getState() + " ");
        });
    }
}
