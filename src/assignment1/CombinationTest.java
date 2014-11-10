package assignment1;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CombinationTest {

    Random r = new Random();

    private Employee genEmployee(){
        return new Employee("John Doe", 20 + r.nextInt(41), 3000 + r.nextInt(2001));
    }

    @Test
    public void testAddSalaryNeutral(){
        AddSalary as = new AddSalary();
        int x = 1000;
        while (x-- > 0) {
            Employee e = genEmployee();
            assertEquals(e.getSalary(), as.combine(e.getSalary(), as.neutral()));
            assertEquals(e.getSalary(), as.combine(as.neutral(), e.getSalary()));
        }
    }

    @Test
    public void testAddSalaryCombine(){
        AddSalary as = new AddSalary();
        Employee e1, e2, e3;

        int x = 1000;
        while (x-- > 0) {
            e1 = genEmployee();
            e2 = genEmployee();
            e3 = genEmployee();
            assertEquals(as.combine(e1.getSalary(), as.combine(e2.getSalary(), e3.getSalary())),
                    as.combine(as.combine(e1.getSalary(), e2.getSalary()), e3.getSalary()));
        }
    }

    @Test
    public void testMinAgeNeutral(){
        MinAge ma = new MinAge();
        int x = 1000;
        while (x-- > 0) {
            Employee e = genEmployee();
            assertEquals(e.getAge(), ma.combine(e.getAge(), ma.neutral()));
            assertEquals(e.getAge(), ma.combine(ma.neutral(), e.getAge()));
        }
    }

    @Test
    public void testMinAgeCombine(){
        MinAge ma = new MinAge();
        Employee e1, e2, e3;

        int x = 1000;
        while (x-- > 0) {
            e1 = genEmployee();
            e2 = genEmployee();
            e3 = genEmployee();
            assertEquals(ma.combine(e1.getAge(), ma.combine(e2.getAge(), e3.getAge())),
                    ma.combine(ma.combine(e1.getAge(), e2.getAge()), e3.getAge()));
        }
    }


}