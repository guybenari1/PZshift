package model;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;

public class DataBaseManager {

    public void DB() {
        MongoClient client = MongoClients.create("mongodb+srv://GuyBenAri:1qaz2WSX3edc@pzshift.sxxqo.mongodb.net/PZshift?retryWrites=true&w=majority");
        MongoDatabase database = client.getDatabase("PZshiftDB");
        MongoCollection weeksReqCol = database.getCollection("WeeksRequest");
        Document blankWeeksReqDoc = new Document().append("week", 2);
//        weeksReqCol.insertOne(blankWeeksReqDoc);

        MongoCollection hoursCol = database.getCollection("Hours");
        MongoCollection messagesCol = database.getCollection("Messages");
        MongoCollection usersCol = database.getCollection("Users");
        MongoCollection workSchCol = database.getCollection("WorkSchedule");
        MongoCollection workersCol = database.getCollection("Workers");
        Document blankHoursDoc = new Document("_id", 1).append("name", "none").append("total hours", 0);
        Document blankMessagesDoc = new Document().append("recipient", "enter workers list").append("date", "00/00/0000").append("content", "none");
        Document blankUsersDoc = new Document("_id", 1).append("name", "none").append("password", "0000");
        Document blankWorkSchDoc = new Document().append("week", 0);
        Document blankWorkersDoc = new Document("_id", 1).append("name", "none").append("salary", 0).append("email", "aa@aa.com").append("phone number", 0000000).append("id", 000).append("birthday", "00/00/0000").append("job", "job title");
        Document dayInWeek = new Document("_id", 1).append("name", "Sunday");

//        hoursCol.insertOne(blankHoursDoc);
//        messagesCol.insertOne(blankMessagesDoc);
//        usersCol.insertOne(blankUsersDoc);
//        workSchCol.insertOne(blankWorkSchDoc);
//        workersCol.insertOne(blankWorkersDoc);
//        weeksReqCol.insertOne(blankWeeksReqDoc);

        try (
                MongoCursor<Document> cur = weeksReqCol.find().iterator()) {
            while (cur.hasNext()) {
                Document doc = cur.next();
                ArrayList<String> days = (ArrayList<String>) doc.get("week");
                for (int i = 0; i < days.size() - 1; i++)
                    System.out.println(days.get(i));
            }
        }
    }
}
