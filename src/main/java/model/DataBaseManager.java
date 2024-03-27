package model;

import Shift_Commander.Shift;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DataBaseManager {
    private static DataBaseManager _Instance = null;
    private final MongoDatabase databaseConnection;
    private static String _User;
    private static LocalDate _CurrentWeek;

    private DataBaseManager(){
        MongoClient client = MongoClients.create("mongodb+srv://GuyBenAri:1qaz2WSX3edc@pzshift.sxxqo.mongodb.net/PZshift?retryWrites=true&w=majority");
        databaseConnection = client.getDatabase("PZshiftDB");
    }

    public static DataBaseManager getDBInstance() {
        if(_Instance == null){
            _Instance = new DataBaseManager();
        }
        return _Instance;
    }

    public void get_CurrentWeek() {
        MongoCollection<Document> WorkSchedule = _Instance.databaseConnection.getCollection("WorkSchedule");
        Document check = WorkSchedule.find().sort(Sorts.descending("week start date")).first();
        if (check == null) {
            _CurrentWeek = LocalDate.now();
        } else {
            Date d;
            _CurrentWeek = Instant.ofEpochMilli(check.get("week start date", Date.class).getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

    public void insertEmployee(Worker temp){
        MongoCollection<Document> Workers = _Instance.databaseConnection.getCollection("Workers");
        Document workerDoc = new Document("_id",temp.getId()).append("name",temp.getName()).append("salary",temp.getSalary()).append("email",temp.getEmail()).append("phone number",temp.getPhoneNumber()).append("id",temp.getId()).append("birthday",temp.getBirthDate()).append("job",temp.job);
        MongoCollection<Document> Users = _Instance.databaseConnection.getCollection("Users");
        Document userDoc = new Document("_id", temp.getId()).append("name",temp.getName()).append("password",temp.getPassword());
        Workers.insertOne(workerDoc);
        Users.insertOne(userDoc);
    }

    public boolean doesEmployeeExist(String id){
        MongoCollection<Document> Workers = _Instance.databaseConnection.getCollection("Workers");
        Document target = Workers.find(Filters.eq("id",id)).first();
        return target != null;
    }

    private String getMonth(){
        String res = LocalDate.now().getMonth().toString();
        return res.toLowerCase();
    }

    public void insertHours(String workerID,String workerName, int newHours ){
        MongoCollection<Document> hours = _Instance.databaseConnection.getCollection("Hours");
        Document target = hours.find(Filters.eq("_id",workerID)).filter(Filters.eq("Month",getMonth())).first();
        if(target == null){
            Document HoursDoc = new Document("_id",workerID).append("name",workerName).append("total hours: ", newHours).append("Month",getMonth());
            hours.insertOne(HoursDoc);
        } else {
            Bson projectionFields = Projections.fields(Projections.include("total hours"),Projections.exclude("_id","name"));
            int f = Integer.getInteger(hours.find(Filters.eq("_id",workerID)).filter(Filters.eq("Month",getMonth())).projection(projectionFields).toString());
            Bson updates = Updates.combine(
                    Updates.set("total hours", f+newHours)
            );
            hours.updateOne(target,updates);
        }
    }

    private boolean needNewDoc(){
        MongoCollection<Document> weeksRequest =_Instance.databaseConnection.getCollection("WeeksRequest");
        Bson projectionFields =Projections.fields(Projections.include("Final change date"),Projections.excludeId(),Projections.exclude("week"));
        Document target = weeksRequest.find().projection(projectionFields).sort(Sorts.descending("Final change date")).first();
        if(target != null){
            LocalDate f = LocalDate.parse(target.toString());
            return LocalDate.now().isAfter(f);
        }
       return true;
    }

    public void addWorkersToShift(int dayOfWeek, int shiftTime, List<String> workers) throws MongoException {
        String[] shift = {"Morning","Evening"};
        Iterator<String> workerIt= workers.iterator();
        MongoCollection<Document> WorkSchedule= _Instance.databaseConnection.getCollection("WorkSchedule");
        Document target = WorkSchedule.find(Filters.eq("week start date",_CurrentWeek.plusWeeks(1))).first();
        List<Document> week = target.getList("week",Document.class);
        Document targetDay = week.get(dayOfWeek);
        List<String> targetShift = targetDay.getList(shiftTime,String.class);
        while(workerIt.hasNext()){
            Bson update = Updates.addToSet(shift[shiftTime],workerIt.next());
            WorkSchedule.updateOne(targetDay,update);
        }
    }


    public void addWeekFinalChart(){
        MongoCollection<Document> WorkSchedule= _Instance.databaseConnection.getCollection("WorkSchedule");
        Document document = new Document();
        List<Document> week = new ArrayList<>();
        Document Sunday = new Document();
        List<String> SundayMorning  = new ArrayList<>();
        List<String> SundayEvening = new ArrayList<>();
        Sunday.put("Morning",SundayMorning);
        Sunday.put("Evening",SundayEvening);
        week.add(Sunday);
        Document Monday = new Document();
        List<String> MondayMorning  = new ArrayList<>();
        List<String> MondayEvening = new ArrayList<>();
        Monday.put("Morning",MondayMorning);
        Monday.put("Evening",MondayEvening);
        week.add(Monday);
        Document Tuesday = new Document();
        List<String> TuesdayMorning  = new ArrayList<>();
        List<String> TuesdayEvening = new ArrayList<>();
        Tuesday.put("Morning",TuesdayMorning);
        Tuesday.put("Evening",TuesdayEvening);
        week.add(Tuesday);
        Document Wednesday = new Document();
        List<String> WednesdayMorning  = new ArrayList<>();
        List<String> WednesdayEvening = new ArrayList<>();
        Wednesday.put("Morning",WednesdayMorning);
        Wednesday.put("Evening",WednesdayEvening);
        week.add(Wednesday);
        Document thursday = new Document();
        List<String> thursdayMorning  = new ArrayList<>();
        List<String> thursdayEvening = new ArrayList<>();
        thursday.put("Morning",thursdayMorning);
        thursday.put("Evening",thursdayEvening);
        week.add(thursday);
        Document friday = new Document();
        List<String> fridayMorning  = new ArrayList<>();
        List<String> fridayEvening = new ArrayList<>();
        friday.put("Morning",fridayMorning);
        friday.put("Evening",fridayEvening);
        week.add(friday);
        Document saturday = new Document();
        List<String> saturdayMorning  = new ArrayList<>();
        List<String> saturdayEvening = new ArrayList<>();
        saturday.put("Morning",saturdayMorning);
        saturday.put("Evening",saturdayEvening);
        week.add(saturday);
        document.put("Week", week);
        LocalDate f = _CurrentWeek.plusWeeks(1);
        document.put("week start date", f);
        WorkSchedule.insertOne(document);
    }


    private void insertWeekRequestChart(){
        MongoCollection<Document> weeksRequest =_Instance.databaseConnection.getCollection("WeeksRequest");
        Document document = new Document();
        List<Document> week = new ArrayList<>();
        Document Sunday = new Document();
        List<String> SundayMorning  = new ArrayList<>();
        List<String> SundayEvening = new ArrayList<>();
        Sunday.put("Morning",SundayMorning);
        Sunday.put("Evening",SundayEvening);
        week.add(Sunday);
        Document Monday = new Document();
        List<String> MondayMorning  = new ArrayList<>();
        List<String> MondayEvening = new ArrayList<>();
        Monday.put("Morning",MondayMorning);
        Monday.put("Evening",MondayEvening);
        week.add(Monday);
        Document Tuesday = new Document();
        List<String> TuesdayMorning  = new ArrayList<>();
        List<String> TuesdayEvening = new ArrayList<>();
        Tuesday.put("Morning",TuesdayMorning);
        Tuesday.put("Evening",TuesdayEvening);
        week.add(Tuesday);
        Document Wednesday = new Document();
        List<String> WednesdayMorning  = new ArrayList<>();
        List<String> WednesdayEvening = new ArrayList<>();
        Wednesday.put("Morning",WednesdayMorning);
        Wednesday.put("Evening",WednesdayEvening);
        week.add(Wednesday);
        Document thursday = new Document();
        List<String> thursdayMorning  = new ArrayList<>();
        List<String> thursdayEvening = new ArrayList<>();
        thursday.put("Morning",thursdayMorning);
        thursday.put("Evening",thursdayEvening);
        week.add(thursday);
        Document friday = new Document();
        List<String> fridayMorning  = new ArrayList<>();
        List<String> fridayEvening = new ArrayList<>();
        friday.put("Morning",fridayMorning);
        friday.put("Evening",fridayEvening);
        week.add(friday);
        Document saturday = new Document();
        List<String> saturdayMorning  = new ArrayList<>();
        List<String> saturdayEvening = new ArrayList<>();
        saturday.put("Morning",saturdayMorning);
        saturday.put("Evening",saturdayEvening);
        week.add(saturday);
        document.put("Week", week);
        LocalDate f = LocalDate.now().plusWeeks(1);
        document.put("Final change date", f);
        weeksRequest.insertOne(document);
    }

    private void addShiftHelper(int day, String time, String workerName,int addOrRemove){
        MongoCollection<Document> weeksRequest = _Instance.databaseConnection.getCollection("WeeksRequest");
        Document target= weeksRequest.find().sort(Sorts.descending("Final change date")).first();
        Document target2 =  target.getList("Week",Document.class).get(day);
        if(addOrRemove > 0){
            Bson updates = Updates.combine( Updates.addToSet(time,workerName));
            weeksRequest.updateOne(target2,updates);
        } else {
            Bson updates2 = Updates.combine((Updates.pull(time,workerName)));
            weeksRequest.updateOne(target2,updates2);
        }
        MongoCollection<Document> Workers  =_Instance.databaseConnection.getCollection("Workers");
        Document idHelper = Workers.find(Filters.eq("name", workerName)).first();
        String id = idHelper.getString("id");
        insertHours(id,workerName,8);
    }

    public void changeShift(Shift.Shifttype type,String workerName, int addOrRemove){
        if(needNewDoc()){
            insertWeekRequestChart();
        }
        switch (type){
            case SUNMORN -> addShiftHelper(0,"Morning",workerName,addOrRemove);
            case SUNEVE -> addShiftHelper(0,"Evening",workerName,addOrRemove);
            case MONMORN -> addShiftHelper(1,"Morning",workerName,addOrRemove);
            case MONEVE -> addShiftHelper(1,"Evening",workerName,addOrRemove);
            case TUMORN -> addShiftHelper(2,"Morning",workerName,addOrRemove);
            case TUEVE -> addShiftHelper(2,"Evening",workerName,addOrRemove);
            case WEMORN -> addShiftHelper(3,"Morning",workerName,addOrRemove);
            case WEEVE -> addShiftHelper(3,"Evening",workerName,addOrRemove);
            case THMORN -> addShiftHelper(4,"Morning",workerName,addOrRemove);
            case THEVE -> addShiftHelper(4,"Evening",workerName,addOrRemove);
            case FRIMORN ->addShiftHelper(5,"Morning",workerName,addOrRemove);
            case FRIEVE -> addShiftHelper(5,"Evening",workerName,addOrRemove);
            case SATMORN -> addShiftHelper(6,"Morning",workerName,addOrRemove);
            case SATEVE -> addShiftHelper(6,"Evening",workerName,addOrRemove);
        }
    }

    public boolean validLogin(String email, String password){
        MongoCollection<Document> Users = _Instance.databaseConnection.getCollection("Users");
        Document target = Users.find(Filters.eq("Email",email)).filter(Filters.eq("password",password)).first();
        if(target!= null){
            return true;
        }
        return false;
    }

    public boolean isManager(String email){
        MongoCollection<Document> Workers = _Instance.databaseConnection.getCollection("Workers");
        Document target = Workers.find(Filters.eq("email",email)).first();
        String job = target.getString("job");
        if(job.equals("Manager")){
            return true;
        }
        return false;
    }

    public String getEmployeeName(String email) {
        MongoCollection<Document> Workers = _Instance.databaseConnection.getCollection("Workers");
        Document target = Workers.find(Filters.eq("email", email)).first();
        if (target != null) {
            return target.getString("name");
        }
        return null;
    }

    public void changeUser(String name){
        _User =name;
    }

    public String getUser(){return _User;}

    public ArrayList<String> getPhoneBook(){
        MongoCollection<Document>Workers =_Instance.databaseConnection.getCollection("Workers");
        MongoCursor<Document> cursor = Workers.find().sort(Sorts.descending("name")).iterator();
        ArrayList<String> resultSet = new ArrayList<String>();
        while(cursor.hasNext()){
            String s =  "";
            Document curr = cursor.next();
            s  += curr.getString("name");
            s += "- ";
            s += curr.getString("phone number");
            resultSet.add(s);
        }
        return  resultSet;
    }

    public String getHoursForEmp(String workerName){
        MongoCollection<Document> Hours = _Instance.databaseConnection.getCollection("Hours");
        Document target = Hours.find(Filters.eq("name",workerName)).filter(Filters.eq("Month",getMonth())).first();
        if (target == null){
            return null;
        }
        String hours = target.getString("total hours");
        return  hours;
    }

    public ArrayList<String> getNextWeek(String workerName){
        String[] days ={"Sunday", "Monday", "Tuesday", "Wendsday","Thursday","Friday","Saturday"};
        String[] times = {"Morning","Evening"};
        String s = "";
        ArrayList<String> fin =new ArrayList<String>();
        MongoCollection<Document> WorkSchedle =_Instance.databaseConnection.getCollection("WorkSchedule");
        Document target = WorkSchedle.find(Filters.eq("week start date", _CurrentWeek.plusWeeks(1))).first();
        if (target == null){
            return null;
        }
        List<Document> week = target.getList("week",Document.class);
        for(int i=0; i<week.size();i++){
            for (int j=0; j<2;j++){
                if(week.get(i).getList(j,String[].class).contains(workerName)){
                    s += days[i] + " " + times[j];
                    fin.add(s);
                }
            }

        }
        return fin;
    }

    public boolean nextWeekExists(){
        MongoCollection<Document> WorkSchedle =_Instance.databaseConnection.getCollection("WorkSchedule");
        Document target = WorkSchedle.find(Filters.eq("week start date",_CurrentWeek.plusWeeks(1))).first();
        if(target == null){
            return false;
        }
        return true;
    }

    public boolean noUsers(){
        MongoCollection<Document>Workers =_Instance.databaseConnection.getCollection("Workers");
        Document target = Workers.find().first();
        if(target== null){
            return true;
        }
        return false;
    }

    public ArrayList<String> getWorkerNames() {
        MongoCollection<Document> Workers = _Instance.databaseConnection.getCollection("Workers");
        MongoCursor<Document> workIt = Workers.find(Filters.eq("job","Worker")).iterator();
        ArrayList<String> resultSet = new ArrayList<String>();
        while(workIt.hasNext()){
            Document curr = workIt.next();
            String name = curr.getString("name");
            resultSet.add(name);
        }
        return resultSet;
    }
    public void updatePassword(String workerName, String pass){
        MongoCollection<Document> users = _Instance.databaseConnection.getCollection("Users");
        Document person  = users.find(Filters.eq("name",workerName)).first();
        Bson updatesC = Updates.combine( Updates.set("password",pass));
        users.updateOne(person,updatesC);
    }

    public void updateSalary(String workerName, String Newsalary){
        MongoCollection<Document> workers= _Instance.databaseConnection.getCollection("Workers");
        Document person = workers.find(Filters.eq("name",workerName)).first();
        Bson updates = Updates.set("salary",Newsalary);
        workers.updateOne(person,updates);
    }

    public ArrayList<String> shiftSignUp(int day, int shift){
        String[] days ={"Sunday", "Monday", "Tuesday", "Wendsday","Thursday","Friday","Saturday"};
        String[] times = {"Morning","Evening"};
        ArrayList<String> fin =new ArrayList<String>();
        MongoCollection<Document> WorkSchedle =_Instance.databaseConnection.getCollection("WorkSchedule");
        Document target = WorkSchedle.find(Filters.eq("week start date", _CurrentWeek.plusWeeks(1))).first();
        List<Document> week = target.getList("week",Document.class);
        List<String> temp = week.get(day).getList(times[shift],String.class);
        for(int i=0; i<temp.size();i++){
            String s = temp.get(i);
            fin.add(s);
        }
        return fin;
    }

    public ArrayList<String> getMessages(){
        String workerName = _User;
        ArrayList<String> messagesFinal = new ArrayList<String>();
        MongoCollection<Document> messages = _Instance.databaseConnection.getCollection("Messages");
        MongoCursor<Document> mine = messages.find().iterator();
        while(mine.hasNext()){
            Document curr = mine.next();
            List<String> targets = curr.getList("recipient",String.class);
            if (targets!=null) {
                if (targets.contains(workerName)) {
                    messagesFinal.add(curr.getString("content"));
                }
            }
        }
        return messagesFinal;
    }

    public void sendMessage(List<String> recivers, String body){
        MongoCollection<Document> messages = _Instance.databaseConnection.getCollection("Messages");
        Document newMessage = new Document();
        newMessage.put("Recipient",recivers);
        newMessage.put("date",LocalDate.now());
        newMessage.put("content",body);
        messages.insertOne(newMessage);
    }

    public ArrayList<String> getWorkerDeatils(){
        MongoCollection<Document> Workers = _Instance.databaseConnection.getCollection("Workers");
        MongoCursor<Document> workerSet = Workers.find(Filters.eq("job","Worker")).iterator();
        ArrayList<String> resultSet = new ArrayList<String>();
        int temp=0;
        while(workerSet.hasNext()){
            String s = "";
            Document curr = workerSet.next();
            s += "Name: ";
            s += curr.getString("name");
            s += ", Salary per hour: ";
            temp = curr.getInteger("salary");
            s+=Integer.toString(temp);
            s += ", email: ";
            s += curr.getString("email");
            s += ", phone number: ";
            s += curr.getString("phone number");
            s += ", id: ";
            s += curr.getString("id");
            s += ", birth date: ";
            s += curr.getString("birthday");
            resultSet.add(s);
        }
        return  resultSet;
    }
}

