package model;

import com.mongodb.MongoException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Worker {
    protected String name;
    protected int salary;
    protected String email;
    protected Email workerEmail;
    protected String phoneNumber;
    protected String id;
    protected String birthDate;
    protected String password;
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

    public static String workerProcess(Worker temp){
        DataBaseManager manager = DataBaseManager.getDBInstance();
        boolean valWorker = temp.checkValid(temp);
        boolean existsAlready = manager.doesEmployeeExist(temp.getId());
        String s;
        if(Email.checkValidEmail(temp.getEmail()) && valWorker && !existsAlready){
            try{
                Email email2 = new Email(temp.getEmail());
                email2.sendCodeByEmail(temp);
                manager.insertEmployee(temp);
            }catch (MongoException err){
                System.err.println("Program ran into error: " + err);
            }
            s = "Password sent to: "+ temp.getEmail();
        } else {
            if(!valWorker){
                s = "Some data of the worker is invalid";
            } else if(!Email.checkValidEmail(temp.getEmail())){
                s = "The email is not valid";
            } else{
                s = "This worker is already employeed!";
            }
        }
        return s;
    }

    public static String getPhoneBook(){
        String contacts = "";
        try {
            DataBaseManager manager = DataBaseManager.getDBInstance();
            ArrayList<String> phoneBook = manager.getPhoneBook();
            for (String s : phoneBook) {
                contacts += s+"\n";
            }
        } catch (MongoException  err){
            System.err.println("Progarm ran into the error: " + err);
        }
        return contacts;
    }

    public static String getHours() throws MongoException{
        DataBaseManager manager = DataBaseManager.getDBInstance();
        String result = manager.getHoursForEmp(manager.getUser());
        return result;
    }

    public static String getNextWeek() {
        String s = "";
        if (!DataBaseManager.getDBInstance().nextWeekExists()) {
            s = "there are no weeks for next week yet";
        } else {
            try {
                DataBaseManager manager = DataBaseManager.getDBInstance();
                ArrayList<String> toDisplay = manager.getNextWeek(manager.getUser());
                for (String d : toDisplay) {
                    s += d + "\n";
                }
            } catch (MongoException err) {
                System.err.println("System ran into an error " + err);
            }
        }
        return s;
    }


    public boolean checkValid(Worker toCheck){
        int valid = 0;
        valid += validHelper(toCheck.getId(), 9);
        valid += validHelper(toCheck.getPhoneNumber(), 10);
        try{
            valid += yearHelper(toCheck.getBirthDate());
        } catch (NullPointerException e){
            return false;
        }
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

    private int yearHelper(String date) throws NullPointerException{
        int temp = Integer.parseInt(date.substring(0,4));
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
}
