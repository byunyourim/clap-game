import java.util.Random;

public class Player {
    String name;
    double errorRate;
    Random random;

    Player(String name, double errorRate) {
        if (errorRate < 0.0 || errorRate >= 1.0) {
            throw new IllegalArgumentException("Error rate must be between 0.0 and 1.0");
        }

        this.name = name;
        this.errorRate = errorRate;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public String takeTurn(int number, GameRule rule) {
        if (random.nextDouble() < errorRate) {
            return failAction();
        }
        return rule.takeAction(number) ? rule.playAction(number) : String.valueOf(number);
    }

    public String failAction() {
        return "오답";
    }
}

