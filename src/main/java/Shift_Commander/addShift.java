package Shift_Commander;

public class addShift implements Request{
    private Shift myShift;

    public addShift(Shift myShift){
        this.myShift = myShift;
    }

    @Override
    public void execute(){
        myShift.add();
    }

}
