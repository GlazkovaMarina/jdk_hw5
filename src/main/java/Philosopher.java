import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread{
    private static Integer count  = 0;
    private Integer number;
    private Integer countFood;
    private Fork firstFork;
    private Fork secondFork;
    private Table table;
    private Random random;
    public Philosopher(Table table, Fork first, Fork second){
        countFood = 0;
        number = count++;
        this.table = table;
        this.firstFork = first;
        this.secondFork = second;
        random = new Random();
    }

    @Override
    public String toString() {
        return "Philosopher № " + number;
    }
    public void eat(){
        if(table.getForks(firstFork, secondFork)){
            System.out.println(this + " ест с помощью: " + firstFork + ", " + secondFork);
            countFood++;
            try {
                Thread.sleep(random.nextInt(100,400));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            table.putForks(firstFork, secondFork);
            System.out.println(this + " вернул " + firstFork + ", " + secondFork);
        }
    }

    public void dream(){
        System.out.println(this + " размышляет");
        try {
            Thread.sleep(random.nextInt(100,400));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isStuffed(){
        if (countFood == 3){
            System.out.println(this + " наелся так, что сейчас лопнет");
            return true;
        }
        return false;
    }
    public Integer getNumber(){
        return number;
    }

    @Override
    public void run() {
        while (!isStuffed()){
            eat();
            dream();
        }
    }
}
