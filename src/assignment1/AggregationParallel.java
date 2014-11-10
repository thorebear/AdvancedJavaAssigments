package assignment1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class AggregationParallel<A> implements Aggregation<A> {

    @Override
    public int aggregate(Combination<A> c, List<A> l) {
        /*
            A empty list have the neutral result.
         */
        if (l.size() == 0){
            return c.neutral();
        }

        List<Integer> values = new ArrayList<Integer>();
        for (A item : l){
            values.add(c.projectInt(item));
        }

        while(true){
            List<Thread> threads = new ArrayList<Thread>();
            /*
                We spawn a new thread for every two elements in the list.
             */
            while (values.size() > 1) {
                int item1 = values.remove(0);
                int item2 = values.remove(0);

                Thread t = new Thread(new RunnableAggregation(c, item1, item2, values));
                t.start();
                threads.add(t);
            }
            /*
                If we didn't spawn any thread in this iteration we are finish and returns the result
                Else we will wait for all the threads to finish.
             */
            if (threads.size() > 0){
                for (Thread t : threads){
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        // Do nothing
                    }
                }
            } else {
                break;
            }
        }
        /*
            The list will only contain one element at this time.
         */
        return values.get(0);
    }

    class RunnableAggregation implements Runnable {

        private final Combination<A> c;
        private final int item1;
        private final int item2;
        private final List<Integer> vals;

        /*
            Parameter vals: The list of values, where we will add the result.
         */
        RunnableAggregation(Combination<A> c, int item1, int item2, List<Integer> vals) {
            this.item1 = item1;
            this.item2 = item2;
            this.c = c;
            this.vals = vals;
        }

        @Override
        public void run() {
            int res = c.combine(item1, item2);
            vals.add(res);
        }
    }
}
