import java.util.Random;

/**
 * Created by Bulat on 30.01.2016.
 */
/*
 * Зашлушка для имитации электрического счетчика,
 * который вырабатывает показатели потребления электричества
 */
public class Dummy implements Runnable {
    public static Double consumptionPercentage;

    private Double consumptionRate;
    private Double consumption;

    public Dummy(Double consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

    @Override
    public void run() {
        consumptionRate = 100d;
        double rangeMin = 80;
        double rangeMax = 150;
        Random random = new Random();
        while (true) {
            consumption = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
            consumptionPercentage = consumption / consumptionRate * 100;
        }
    }

    public Double getConsumptionRate() {
        return consumptionRate;
    }
}
