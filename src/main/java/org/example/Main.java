package org.example;

public class Main {

    public static void main(String[] args) {
        // Create a thread to count up from 0 to 20
        Thread countUpThread = new Thread(new Runnable() {
            @Override
            public void run() {
                count(0, 20, 1);  // Counting up from 0 to 20
            }
        });

        // Create a thread to count down from 20 to 0
        Thread countDownThread = new Thread(new Runnable() {
            @Override
            public void run() {
                count(20, 0, -1);  // Counting down from 20 to 0
            }
        });

        // Start the count up thread first
        countUpThread.start();

        // Use join to ensure the count down thread starts after the count up thread finishes
        try {
            countUpThread.join();  // Wait for the countUpThread to finish
            countDownThread.start();  // Start the count down thread after the first one finishes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Restore interrupted status
            e.printStackTrace();
        }
    }

    // Method to count either up or down depending on the step
    public static void count(int start, int end, int step) {
        for (int i = start; (step > 0 ? i <= end : i >= end); i += step) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(500);  // Pause for 500 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restore interrupted status
                e.printStackTrace();
            }
        }
    }
}
