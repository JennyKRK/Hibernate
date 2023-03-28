package org.example;

public class Main {

    public static void main(String[] args){
        SessionCreator sessionCreator = new SessionCreator();
//        sessionCreator.printDepartmentsWithEmployees();
//        sessionCreator.printComputer();
//        sessionCreator.countEmployees();
//        sessionCreator.countEmployeesNew("Smith");
//        sessionCreator.countEmployeesNew("Meier");
//        sessionCreator.FindDepartmentWithMoreEmployees(1);
//        //sessionCreator.addOneDepartment("RandomDepartment");
//        //sessionCreator.addOneEmployee("John","Green");
//        //sessionCreator.addOneDepartmentVersion3();
          //sessionCreator.removeOneDepartment();

//        //sessionCreator.removeOneComputer();
//        sessionCreator.printProjects();
        //sessionCreator.addAProject();
        sessionCreator.removeAProject();
        //sessionCreator.removeAnEmployee();
        sessionCreator.close();
    }
}
