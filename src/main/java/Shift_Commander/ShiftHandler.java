package Shift_Commander;

import java.util.ArrayList;
import java.util.List;

public class ShiftHandler {
    private List<Request> requestList=new ArrayList<Request>();

    public void takeRequest(Request request){
        requestList.add(request);
    }

    public void placeRequests(){
        for(Request request:requestList){
            request.execute();
        }
        requestList.clear();
    }
}
