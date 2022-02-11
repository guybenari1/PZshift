package model;


import com.mongodb.MongoException;

import javax.swing.*;

public class LoginMaster {
    private static LoginMaster _Instance = null;

    private LoginMaster(){}

    public static LoginMaster getInstance(){
        if(_Instance == null){
            _Instance = new LoginMaster();
        }
        return _Instance;
    }

    public String loginB(String email, String pass){
        String s = "";
        try{
            DataBaseManager manager = DataBaseManager.getDBInstance();
            boolean valid = manager.validLogin(email,pass);
            if(!valid){
                s = "incorrect values";
            }else{
                manager.changeUser(manager.getEmployeeName(email));
                manager.get_CurrentWeek();
                valid = manager.isManager(email);
                if(!valid){
                    s= "employee";
                }else{
                    s= "manager";
                }
            }
        }catch (MongoException err){
            System.err.println("Progarm ran into the error: " + err);
        }
        return s;
    }

    public String forgotPassword(String email){
        String s = "";
        try{
            DataBaseManager manager =DataBaseManager.getDBInstance();
            s = manager.getEmployeeName(email);
            if(s==null){
                JOptionPane.showMessageDialog(null, "employee doesnt exsist");
            }
            Email mailE = new Email(email);
            Worker temp=new Worker(email);
            mailE.sendCodeByEmail(temp);
            manager.updatePassword(s,temp.getPassword());
            s="New password sent to you";
        }catch (MongoException err){
            System.err.println("System ran into an error: " +err);
        }
       return s;
    }

}
