package model;

public class Manager extends Worker{

    public Manager(String name, int salary, String email, String phoneNumber, String id, String birthDate, String job, String password) {
        super();

    }

    public Manager(String email) {
        super(email);
    }

    //send mail to manager with special code
}
