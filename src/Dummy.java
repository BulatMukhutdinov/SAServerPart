import java.util.Random;
import java.util.TimerTask;

public class Dummy extends TimerTask {
    public static Double consumptionPercentage;

    private Double consumptionRate;
    private Double consumption;

    public Dummy(Double consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

    public void run() {
        consumptionRate = 100d;
        double rangeMin = 80;
        double rangeMax = 150;
        Random random = new Random();
        consumption = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
        consumptionPercentage = consumption / consumptionRate * 100;
    }

    public Double getConsumptionRate() {
        return consumptionRate;
    }
}
