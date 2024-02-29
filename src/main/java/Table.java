import java.util.ArrayList;
import java.util.List;

public class Table extends Thread{
    private List<Philosopher> philosopherList;
    private List<Fork> forkList;
    public Table(Integer count){
        forkList = new ArrayList<>(count);
        for(int i = 0; i < count; i++){
            forkList.add(new Fork());
        }
        philosopherList = new ArrayList<>(count);
        for (int i = 0; i < count; i++){
            if (i == 0){
                philosopherList.add(new Philosopher(this, forkList.get(0), forkList.get(count-1)));
            } else{
                philosopherList.add(new Philosopher(this, forkList.get(i), forkList.get(i-1)));
            }
        }
    }

    @Override
    public void run() {
        for (Philosopher philosopher : philosopherList) {
            philosopher.start();
        }
    }
    public synchronized boolean getForks(Fork first, Fork second){
        if (!first.isUsed() && !second.isUsed()){
            first.used();
            second.used();
            return true;
        }
        return false;
    }
    public void putForks(Fork first, Fork second){
        first.unused();
        second.unused();
    }
}
