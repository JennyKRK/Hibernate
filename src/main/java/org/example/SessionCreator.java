package org.example;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SessionCreator {

    private Session session;

    public SessionCreator() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void close() {
        session.close();
        HibernateUtil.shutdown();
    }

    public void printDepartments(){
        Query<Department> query = session.createQuery("from Department", Department.class);
        List<Department> results = query.list();
        for (Department d: results){
            System.out.println(d.toString());
        }
    }

    public void printDepartmentsWithEmployees(){
        Query<Department> query = session.createQuery("from Department", Department.class);
        List<Department> results = query.list();
        for (Department d:results){
            System.out.println(d.toString());
            for (Employee e:d.getEmployees()){
                System.out.println(e.toString());
                e.getComputerPrinted();
            }
        }
    }

    public void printComputer(){
        Query<Computer> query = session.createQuery("from Computer", Computer.class);
        List<Computer> results = query.list();
        for (Computer c:results){
            System.out.println(c.toString());
        }
    }

    public void countEmployees(){
        Query<Long> query = session.createQuery("select count(*) from Employee", Long.class);
        Long result = query.uniqueResult();
        System.out.println("number of employees " + result);
    }

    public void countEmployeesNew(String surname){
        Query<Long> query = session.createQuery("select count(*) from Employee where surname = :surname");
        query.setParameter("surname",surname);
        Long result = query.uniqueResult();
        System.out.println("number of employees named " + surname + " " + result);
    }


    public void FindDepartmentWithMoreEmployees(int number){
        String hql = "from Department d where size(d.employees)>" + number;
        Query<Department> query = session.createQuery(hql);
        List<Department> results = query.list();
        for (Department d:results){
            System.out.println(d);
        }
    }

    public void addOneDepartment(String name){
        Department d = new Department();
        d.setName(name);
        Transaction transaction = session.beginTransaction();
        session.save(d);
        transaction.commit();
    }

    public void addOneEmployee(String name, String surname){
        Employee e = new Employee();
        e.setName(name);
        e.setSurname(surname);
        Transaction transaction = session.beginTransaction();
        session.save(e);
        transaction.commit();

    }

    public void addOneDepartmentVersion2(){
        Department d = new Department();
        d.setName("Testing2");
        Employee e = new Employee();
        e.setName("Jordan");
        e.setSurname("Miller");
        d.getEmployees().add(e);
        Transaction transaction = session.beginTransaction();
        session.save(d);
        transaction.commit();
    }

    public void addOneDepartmentVersion3(){
        Department d = new Department();
        d.setName("TotallyRedundant");
        Employee e = new Employee();
        e.setName("Jessica");
        e.setSurname("Something");

        Computer c = new Computer();
        c.setName("XYZ");
        c.setValue(200);
        e.addComputer(c);


        d.addEmployees(e);
        Transaction transaction = session.beginTransaction();

        session.save(d);
        transaction.commit();
    }

    public void removeOneDepartment(){
        Query<Department> query = session.createQuery("from Department where id = 19", Department.class);
        Department result = query.uniqueResult();

        Transaction transaction = session.beginTransaction();
        session.delete(result);
        transaction.commit();
    }

    public void removeOneComputer(){
        Query<Computer> query = session.createQuery("from Computer where id = 7", Computer.class);
        Computer computer = query.uniqueResult();
        Query<Employee> queryE = session.createQuery("from Employee where id=20", Employee.class);
        Employee employee = queryE.uniqueResult();
        employee.removeComputer(computer);
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        session.delete(computer);
        transaction.commit();
    }

    public void printProjects(){
        Query<Project> projectQuery = session.createQuery("from Project", Project.class);
        List<Project> results = projectQuery.list();
        for (Project project: results){
            System.out.println(project.toString());
        }

    }

    public void addAProject(){
        Query<Employee> query = session.createQuery("from Employee where id > 15", Employee.class);
        List<Employee> results = query.list();
        Project p = new Project();
        p.setName("Rock and roll");
        Transaction transaction = session.beginTransaction();
        session.save(p);
        for (Employee e: results){
            e.addProject(p);
            session.save(e);
        }
        transaction.commit();
    }

    public void removeAProject(){
        Query<Project> query = session.createQuery("from Project where id = 1", Project.class);
        Project p = query.uniqueResult();
        Query<Employee> employeeQuery = session.createQuery("from Employee");
        List<Employee> employees =  employeeQuery.list();
        Transaction transaction = session.beginTransaction();
        for (Employee e: employees){
            if (e.getProjects().contains(p)){
                e.removeProject(p);
                }
        };
        session.delete(p);
        transaction.commit();
    }

    public void removeAnEmployee(){
        Query<Employee> queryE = session.createQuery("from Employee where id=5", Employee.class);
        Employee employee = queryE.uniqueResult();
        Query<Project> pQuery = session.createQuery("from Project", Project.class);
        List<Project> projects =  pQuery.list();
        Transaction transaction = session.beginTransaction();
        for (Project p: projects){
            if (p.getEmployees().contains(employee)){
                employee.removeProject(p);
                p.removeEmployee(employee);
                session.save(employee);
                session.save(p);
            }
        }
        session.delete(employee);
        transaction.commit();
    }

}