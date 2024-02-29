import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread{
    private static Integer count  = 0;
    private Integer number;
    private Integer countFood;
    private Fork firstFork;
    private Fork secondFork;
    public Philosopher(Fork first, Fork second){
        countFood = 0;
        number = count++;
        this.firstFork = first;
        this.secondFork = second;
    }

    @Override
    public String toString() {
        return "Philosopher № " + number;
    }
    public void eat(Fork first, Fork second){
        if (countFood < 3){
            System.out.println(this + " ест с помощью: " + first + ", " + second);
            countFood++;
        }
    }

    public void dream(Fork first, Fork second){
        System.out.println(this + " размышляет, вернув при этом " + first + ", " + second);
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
            synchronized (firstFork){
                synchronized (secondFork){
                    eat(firstFork, secondFork);
                    dream(firstFork, secondFork);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
