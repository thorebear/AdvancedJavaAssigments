package assignment1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class MapParallel<A> implements Map<A> {
    @Override
    public void map(Mutation<A> m, List<A> l) {
        List<Thread> threads = new ArrayList<Thread>();
        for(A item : l){
            Thread t = new Thread(new RunnableMutation(m,item));
            t.start();
            threads.add(t);
        }

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    class RunnableMutation implements Runnable {

        private final Mutation<A> m;
        private final A item;

        RunnableMutation(Mutation<A> m, A item) {
            this.item = item;
            this.m = m;
        }

        @Override
        public void run() {
            m.mutate(item);
        }
    }
}
