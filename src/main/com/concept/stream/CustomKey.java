package src.main.com.concept.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.*;

class Base {
    public void m1(int a, int b) throws Exception {
        if(a < 0 && b < 0){
            throw new Exception("Arguments cannot be negative");
        }
        System.out.println(a + b);

    }
}

class Derived extends Base {
    @Override
    public void m1(int a, int b) {
        System.out.println(a - b);
    }

    public Derived() throws Exception{
        System.out.println("Derived class constructor");
        throw new Exception("weqwe");
    }

    public static void main(String[] args) throws Exception {
        Derived b = new Derived();
        b.m1(1, 2);
    }
}

public class CustomKey {
    public static void main(String[] args) {
        HashMap<Employee,String> employeeMap = new HashMap<Employee,String>();
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("Sachin");
        employee1.setDateOfBirth(new Date(1987,2,1));
        employee1.setSalary(new BigDecimal(100000));
        // Step 1
        employeeMap.put(employee1,"India");

        for (Map.Entry<Employee, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().hashCode());
        }
        // Step 2
        // Mutating the Employee Object
        employee1.setName("Rahul");

        for (Map.Entry<Employee, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().hashCode());
        }
        // The HashMap key is mutated and in the wrong bucket for that hashcode.
        // Step 3
        System.out.println(employeeMap.get(employee1));
        // Returns null

        Employee employee2 = new Employee();
        employee2.setId(1);
        employee2.setName("Sachin");
        employee2.setDateOfBirth(new Date(1987,2,1));
        employee2.setSalary(new BigDecimal(100000));
        //Since we changed the name
        System.out.println("Is employee1 and employee2 equal: " + (employee1.equals(employee2)));
        System.out.println(employeeMap.get(employee2));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(4);




        try(BufferedReader br = new BufferedReader(Reader.nullReader())){
            br.readLine();
        } catch (IOException io){
            io.printStackTrace();
            System.out.println("Exception");
        }

        System.out.println("\nSplitIterator: ");
        Spliterator<Integer> spliterator = list.spliterator();
        Spliterator<Integer> spliterator1 = spliterator.trySplit();
        spliterator.forEachRemaining(System.out::println);
        System.out.println("\nSplitIterator1: ");
//        spliterator1.forEachRemaining(System.out::println);
        System.out.println("TryAdvance");
        spliterator1.tryAdvance(System.out::println);
    }
}

class Employee {
    private long id;
    private String name;
    private Date dateOfBirth;
    private BigDecimal salary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    //Getter and Setters
    // to String
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        if (id != employee.id) return false;
        if (!Objects.equals(name, employee.name)) return false;
        if (!Objects.equals(dateOfBirth, employee.dateOfBirth)) return false;
        return Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }
}
