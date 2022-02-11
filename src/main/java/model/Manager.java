package model;

import com.mongodb.MongoException;
import javafx.scene.control.ListView;
import java.util.ArrayList;

public class Manager extends Worker{

    public Manager(String name,String id,String phoneNumber, String birthDate, String email) {
        super(name,id,phoneNumber,birthDate,email);
        this.salary = 10000; //monthly
        this.job = "Manager";
    }

    public static String getEmployeeDeets(){
        ArrayList<String> employee = DataBaseManager.getDBInstance().getWorkerDeatils();
        String allEmployees = "";
        for (int i=0; i< employee.size(); i++){
            allEmployees += employee.get(i) +"\n";
        }
        return allEmployees;
    }

    public Manager(String email) {
        super(email);
    }

    public static void sendMess(ArrayList<String> ss,String f) throws MongoException {
        DataBaseManager manager = DataBaseManager.getDBInstance();
        manager.sendMessage(ss,f);
    }

    public static void updateSal(String worker, String sala) throws MongoException{
        DataBaseManager.getDBInstance().updateSalary(worker,sala);
    }

    public static void shiftAdderMaster(int first, int second, ListView<String> button) throws MongoException{
        DataBaseManager manager = DataBaseManager.getDBInstance();
        manager.addWorkersToShift(first,second,button.getItems().stream().toList());
    }
}
