package model;

public class Manager extends Worker{

    public Manager(String name,String id,String phoneNumber, String birthDate, String email) {
        super(name,id,phoneNumber,birthDate,email);
        this.salary = 10000; //monthly
        this.job = "Manager";
    }

    protected void addEmployee(){



    }

    public Manager(String email) {
        super(email);
    }

    //send mail to manager with special code
}
