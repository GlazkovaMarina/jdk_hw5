public class Fork {
    private static Integer count  = 0;
    private Integer number;
    public Fork(){
        number = count++;
    }
    @Override
    public String toString() {
        return "Fork № " + number;
    }

    public Integer getNumber(){
        return number;
    }
}
