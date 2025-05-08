package dsa;

import models.Case;
import java.util.LinkedList;
import java.util.Queue;

public class CaseQueue {
    private final Queue<Case> queue = new LinkedList<>();


    public void enqueue(Case newCase) {
        queue.add(newCase);
    }

    public Case dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void displayQueue() {
        for (Case c : queue) {
            System.out.println("Case ID: " + c.getCaseId() + ", Title: " + c.getTitle());
        }
    }
}