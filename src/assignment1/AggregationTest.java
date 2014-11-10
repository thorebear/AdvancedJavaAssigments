package assignment1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by thorbjorn on 11/10/14.
 */
public class AggregationTest {

    private List<Employee> dataSet(){
        return new ArrayList<Employee>() {{
            add(new Employee("John P", 98, 3000));
            add(new Employee("Karla Sø", 18, 12000));
            add(new Employee("Christian Poulsen", 34, 0));
            add(new Employee("Peter Hansen", 32, 999));
            add(new Employee("Karl Hansen Karlsen", 39, 120));
            add(new Employee("Agent P", 35, 2000));
            add(new Employee("Poul Hansen", 32, 90000));
            add(new Employee("Sigrid Hansen", 9, 3200));
            add(new Employee("Peter Petersen", 44, 1000));
            add(new Employee("Nielsen", 30, 1000));
        }};
    }

    @Test
    public void testAggregationSequential() {
        testAggregation(new AggregationSequential<Employee>());
    }

    @Test
    public void testAggregationParallel() {
        testAggregation(new AggregationParallel<Employee>());
    }

    private void testAggregation(Aggregation<Employee> a) {
        List<Employee> list = dataSet();
        a.aggregate(new AddSalary(), list);
        int sum = 0;
        for (Employee e : list){
            sum += e.getSalary();
        }
        assertEquals(113319, sum);
    }
}
