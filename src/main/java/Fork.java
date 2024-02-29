public class Fork {
    private static Integer count  = 0;
    private Integer number;
    private boolean isUsed;
    public Fork(){
        number = count++;
        isUsed = false;
    }
    @Override
    public String toString() {
        return "Fork № " + number;
    }

    public Integer getNumber(){
        return number;
    }
    public void used(){
        isUsed = true;
    }
    public void unused(){
        isUsed = false;
    }
    public boolean isUsed(){
        return isUsed;
    }
}
