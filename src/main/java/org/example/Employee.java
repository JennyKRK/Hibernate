package org.example;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Computer> computers = new HashSet<>();

    @ManyToMany(mappedBy = "employees", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Project> projects= new HashSet<>();

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        project.getEmployees().add(this);
        projects.add(project);
    }

    public void removeProject(Project project) {
        project.getEmployees().remove(this);
        projects.remove(project);
    }

    public Set<Computer> getComputers() {
        return computers;
    }

    public void setComputers(Set<Computer> computers) {
        this.computers = computers;
    }

    public void addComputer(Computer a){
        this.computers.add(a);
    }

    public void removeComputer(Computer a){
        this.computers.remove(a);
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void getComputerPrinted(){
        for (Computer a:getComputers()){
            System.out.println(a);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department_id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
