package assignment1;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class LowerCaseName implements Mutation<Employee> {

    @Override
    public void mutate(Employee x) {
        x.setName(x.getName().toLowerCase());
    }
}
