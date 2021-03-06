package DiningPhilosopher;

import java.util.Collections;
import java.util.Comparator;

public class DiningPhilosopher extends Philosopher {

    public DiningPhilosopher(int id, Fork... forks) {
        super(id, forks);
        Collections.sort(this.forks, Comparator.comparingInt(o -> o.id));
    }

    public void acquire() {
        for (Fork fork : forks) {
            fork.P();
            System.out.println("Philosopher " + id + " acquired fork " + fork.id + ".");
        }
    }

    public void release() {
        for (Fork fork : forks) {
            System.out.println("Philosopher " + id + " released fork " + fork.id + ".");
            fork.V();
        }
    }

    public static void main(String args[]) {
        int numPhilosophers = 5;

        Fork[] forks = new Fork[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Fork(i);
        }

        Philosopher[] phils = new Philosopher[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            phils[i] = new DiningPhilosopher(i, forks[i], forks[(i + 1) % numPhilosophers]);
            new Thread(phils[i]).start();
        }
    }
}
