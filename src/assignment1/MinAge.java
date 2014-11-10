package assignment1;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class MinAge implements Combination<Employee> {

    @Override
    public int neutral() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int combine(int x, int y) {
        return Math.min(x,y);
    }

    @Override
    public int projectInt(Employee x) {
        return x.getAge();
    }
}
