package Shift_Commander;

import model.DataBaseManager;

public class Shift {
    public enum Shifttype {SUNMORN,SUNEVE,MONMORN,MONEVE,TUMORN,TUEVE,WEMORN,WEEVE,THMORN,THEVE,FRIMORN,FRIEVE,SATMORN,SATEVE};
    private final Shifttype[] eArr = {Shifttype.SUNMORN,Shifttype.SUNEVE,Shifttype.MONMORN,Shifttype.MONEVE,Shifttype.TUMORN,Shifttype.TUEVE,Shifttype.WEMORN,Shifttype.WEEVE,Shifttype.THMORN,Shifttype.THEVE,Shifttype.FRIMORN,Shifttype.FRIEVE,Shifttype.SATMORN,Shifttype.SATEVE};
    private Shifttype myType;
    private String workerName;


    public Shift(int index, String name){
        this.myType =eArr[index];
        this.workerName =name;
    }

    public void add(){
        DataBaseManager manager = DataBaseManager.getDBInstance();
        manager.changeShift(this.myType,this.workerName,1);
    }

    public void remove(){
        DataBaseManager manager = DataBaseManager.getDBInstance();
        manager.changeShift(this.myType,this.workerName,0);
    }
}
