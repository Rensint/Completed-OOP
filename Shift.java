public class Shift {
    private String shiftID;
    private String shiftName;
    private String startTime;
    private String endTime;   

    public Shift(String shiftID, String shiftName, String startTime, String endTime) {
        this.shiftID = shiftID;
        this.shiftName = shiftName;
        setStartTime(startTime);
        setEndTime(endTime);    
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
    if (isValidTime(startTime)) {
        this.startTime = startTime;
    } else {
        throw new IllegalArgumentException("Invalid start time format. Please use HH:mm format.");
    }
}

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
    if (isValidTime(endTime)) {
        this.endTime = endTime;
    } else {
        throw new IllegalArgumentException("Invalid end time format. Please use HH:mm format.");
    }
}


    private boolean isValidTime(String time) {
        return time.matches("^([01]\\d|2[0-3]):([0-5]\\d)$");
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

