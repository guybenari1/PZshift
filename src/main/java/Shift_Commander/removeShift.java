package Shift_Commander;

public class removeShift implements Request {
    private Shift myShift;

    public removeShift(Shift mShift){
        this.myShift= mShift;
    }

    @Override
    public void execute() {
        myShift.remove();
    }
}
