package model;

public class Worker {
    protected String name;
    protected int salary;
    protected String email;
    protected Email workerEmail;
    protected String phoneNumber;
    protected String id;
    protected String birthDate;
   // protected String job;
    protected String password;
    protected int hours;


    public Worker (String email){
        setEmail(email);
    }

    public Worker() {

    }

    private void setEmail(String email){
        this.email = email;
        this.workerEmail = new Email(this.email);
    }

    public void sendCodeEmail(){
        workerEmail.sendCodeByEmail();
    }

    public void sendTextEmail(String text){
        workerEmail.sendTextByEmail(text);
    }
}
