package assignment1;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class AddSalary implements Combination<Employee> {
    @Override
    public int neutral() {
        return 0;
    }

    @Override
    public int combine(int x, int y) {
        return x + y;
    }

    @Override
    public int projectInt(Employee x) {
        return x.getSalary();
    }
}
