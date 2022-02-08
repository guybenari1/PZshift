package model;

import java.time.LocalDate;

public class Worker {
    protected String name;
    protected int salary;
    protected String email;
    protected Email workerEmail;
    protected String phoneNumber;
    protected String id;
    protected String birthDate;
    protected String password;
    protected int hours;
    protected String job;


    public Worker (String email){
        setEmail(email);
    }

    public Worker(String name, String id, String phone,String birth,String email) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phone;
        this.birthDate = birth;
        this.salary = 30; //hourly
        setEmail(email);
        this.job = "Worker";
    }

    public boolean checkValid(Worker toCheck){
        int valid = 0;
        valid += validHelper(toCheck.getId(), 9);
        valid += validHelper(toCheck.getPhoneNumber(), 10);
        valid += yearHelper(toCheck.getBirthDate());
        return valid <= 0;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getEmail() {
        return email;
    }

    public Email getWorkerEmail() {
        return workerEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(int pass){
        this.password = Integer.toString(pass);
    }

    public int getHours() {
        return hours;
    }

    private int yearHelper(String date){
        int temp = Integer.getInteger(date.substring(0,3));
        int current = LocalDate.now().getYear();
        if(current-temp < 18){
            return 1;
        }
        return 0;
    }

    private int validHelper(String cc, int length){
        if(cc.length() != length){
            return 1;
        }
        char[] chars = cc.toCharArray();
        for(char c: chars){
            if(Character.isLetter(c)){
                return 1;
            }
        }
        return 0;
    }

    private void setEmail(String email){
        this.email = email;
        this.workerEmail = new Email(this.email);
    }


    public void sendTextEmail(String text){
        workerEmail.sendTextByEmail(text);
    }
}
