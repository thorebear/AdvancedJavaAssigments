package assignment1;

import java.util.List;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class AggregationSequential<A> implements Aggregation<A> {
    @Override
    public int aggregate(Combination<A> c, List<A> l) {
        int agg = c.neutral();
        for(A item : l) {
            agg += c.combine(agg,c.projectInt(item));
        }
        return agg;
    }
}
