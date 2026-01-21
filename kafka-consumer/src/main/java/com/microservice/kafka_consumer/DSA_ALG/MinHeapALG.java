package com.microservice.kafka_consumer.DSA_ALG;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.PriorityQueue;

@Component
public class MinHeapALG {

    private final int[] priorityQueue = new int[6];
    private int size = 0;

    @PostConstruct
    public void init() {
        Arrays.fill(priorityQueue, -1);
        add(2);
        add(2);
        add(1);
        add(4);
        add(6);
        add(8);
        printInternal();
        printHeap();
    }

    private void add(int value) {

        if (priorityQueue.length == size) {
            System.out.println("Queue is full");
            return;
        }

        priorityQueue[size] = value;
        if (size > 0) {
            bubbleUp(size);
        }
        size++;
    }

    private int poll() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return 0;
        }
        int smallest = priorityQueue[0];
        priorityQueue[0] = priorityQueue[size - 1];
        size--;

        if (size > 0) {
            bubbleDown(0);
        }
        return smallest;
    }

    private void bubbleDown(int index) {
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        int smallest = index;

        if (leftIndex < size && priorityQueue[smallest] > priorityQueue[leftIndex]) {
            smallest = leftIndex;
        }

        if (rightIndex < size && priorityQueue[smallest] > priorityQueue[rightIndex]) {
            smallest = rightIndex;
        }

        if (smallest == index) {
            return;
        }

        int temp = priorityQueue[index];
        priorityQueue[index] = priorityQueue[smallest];
        priorityQueue[smallest] = temp;
        bubbleDown(smallest);
    }

    private void bubbleUp(int index) {

        int parent = (index - 1) / 2;
        if (priorityQueue[parent] > priorityQueue[index]) {
            int temp = priorityQueue[parent];
            priorityQueue[parent] = priorityQueue[index];
            priorityQueue[index] = temp;
            bubbleUp(parent);
        }
    }

    private void printInternal() {
        for (int i = 0; i < size; i++) {
            System.out.print(priorityQueue[i] + " ");
        }
        System.out.println();
    }

    private void printHeap() {
        System.out.println("Poll Heap :");
        for (int i = 0; i < priorityQueue.length; i++) {
            System.out.print(poll() + " ");
        }
        System.out.println();
    }
}
