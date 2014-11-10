package assignment1;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class IncreaseSalary implements Mutation<Employee> {

    @Override
    public void mutate(Employee x) {
        if (x.getAge() > 40){
            x.setSalary(x.getSalary() + (x.getAge()/2));
        }
    }
}
