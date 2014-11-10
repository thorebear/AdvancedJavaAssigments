package assignment1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class MapChunked<A> implements Map<A> {

    @Override
    public void map(Mutation<A> m, List<A> l) {
        int count = l.size()/3;
        List<A> part1 = l.subList(0, count);
        List<A> part2 = l.subList(count, count*2);
        List<A> part3 = l.subList(count*2, l.size());

        List<Thread> threads = new ArrayList<Thread>();
        Thread t1 = new Thread(new RunnableSequentialMutation(m, part1));
        t1.start();
        threads.add(t1);

        Thread t2 = new Thread(new RunnableSequentialMutation(m, part2));
        t2.start();
        threads.add(t2);

        Thread t3 = new Thread(new RunnableSequentialMutation(m, part3));
        t3.start();
        threads.add(t3);

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    class RunnableSequentialMutation implements Runnable {

        private final Mutation<A> m;
        private final List<A> items;

        RunnableSequentialMutation(Mutation<A> m, List<A> items) {
            this.items = items;
            this.m = m;
        }

        @Override
        public void run() {
            new MapSequential<A>().map(m, items);
        }
    }
}
