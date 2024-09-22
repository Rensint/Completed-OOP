public class Shift {
    private String shiftID;
    private String shiftName;
    private int startTime;
    private int endTime;

    public Shift(String shiftID, String shiftName, int startTime, int endTime) {
        this.shiftID = shiftID;
        this.shiftName = shiftName;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getShiftID() {
        return shiftID;
    }

    public void setShiftID(String shiftID) {
        this.shiftID = shiftID;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

 
    public void addShift(Shift shift) {

        System.out.println("Shift added: " + shift.toString());
    }
	
 
    public void removeShift(Shift shift) {
     
        System.out.println("Shift removed: " + shift.toString());
    }

    
    public void updateProduct(Product product) {
    
        System.out.println("Product updated for shift: " + product.toString());
    }

    
    public Shift finishShiftById(String shiftID) {
        if (this.shiftID == shiftID) {
            System.out.println("Shift finished: " + this.toString());
            return this;
        }
        return null;
    }


    @Override
    public String toString() {
        return "ShiftID: " + shiftID +
                ", ShiftName: " + shiftName +
                ", StartTime: " + startTime +
                ", EndTime: " + endTime;
    }
}