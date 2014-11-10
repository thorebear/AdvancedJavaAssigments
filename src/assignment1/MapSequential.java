package assignment1;

import java.util.List;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class MapSequential<A> implements Map<A> {
    @Override
    public void map(Mutation<A> m, List<A> l) {
        for(A item : l){
            m.mutate(item);
        }
    }
}
