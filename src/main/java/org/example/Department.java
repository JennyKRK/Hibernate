package org.example;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Set<Employee> employees = new HashSet<>();

    public long getId() {
        return id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployees(Employee e){
        employees.add(e);
    }

    public void removeEmployees(Employee e) {
        employees.remove(e);
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




    @Override
    public String toString() {
        return "Department{" +
                "department_id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
