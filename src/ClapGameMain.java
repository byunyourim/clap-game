import java.util.*;
import java.io.*;

/**
 * 1 단계
 * 369 게임 구현
 *
 * 2 단계
 * - 오답률 계산 및 게임 종료
 * - 사용자 입력을 받아 동작
 * - 클래스 구조화
 *
 * 3 단계
 * - 지역별 다른 규칙의 게임을 위해 추상화 및 다형성 적용
 *
 * 4 단계
 * - 다양한 지역 동시 게임 진행을 위한 동시성 적용
 */
public class ClapGameMain {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Amy", 0.1));
        players.add(new Player("Benny", 0.3));

        Game game = new Game(players, new SeoulGame());
        game.play();

        System.out.println("=====================");

        Game game2 = new Game(players, new BusanGame());
        game2.play();
    }
}

abstract class GameRule {
    // 언제 행동을 할지
    abstract boolean playGame(int n);

    // action
    abstract String playAction(int n);
}

class SeoulGame extends GameRule {
    @Override
    boolean playGame(int n) {
        String s = String.valueOf(n);
        return s.contains("3") || s.contains("6") || s.contains("9");
    }

    @Override
    String playAction(int n) {
        String s = String.valueOf(n);
        int count = (int) s.chars()
            .filter(c -> c == '3' || c == '6' || c == '9')
            .count();
        return "짝".repeat(count);
    }

}

class BusanGame extends GameRule {
    @Override
    boolean playGame(int n) {
        String s = String.valueOf(n);
        return s.contains("2") || s.contains("4") || s.contains("6");
    }

    @Override
    String playAction(int n) {
        String s = String.valueOf(n);
        int count = (int) s.chars()
            .filter(c -> c == '2' || c == '4' || c == '6')
            .count();
        return "쿵".repeat(count);
    }
}

class Game {
    List<Player> players;
    int currentNumber;
    GameRule rule;

    Game(List<Player> players, GameRule rule) {
        this.players = players;
        this.currentNumber = 1;
        this.rule = rule;

    }

    public void play() {
        while (true) {
            for (Player p : players) {
                String res = p.takeTurn(currentNumber, rule);

                if (res.equals("오답")) {
                    System.out.println(p.getName() + " 오답: 게임 종료!");
                    return;
                }
                System.out.println(p.getName() + " 응답 : " + res);
                currentNumber++;
            }
        }
    }
}

class Player {
    String name;
    double errorRate;
    Random random;

    Player(String name, double errorRate) {
        this.name = name;
        this.errorRate = errorRate;
        this.random = new Random();
    }

    public String takeTurn(int number, GameRule rule) {
        if (random.nextDouble() < errorRate) {
            return failAction(number);
        }
        return correctAction(number, rule);
    }

    public String correctAction(int number, GameRule rule) {
        if (rule.playGame(number)) {
            return rule.playAction(number);
        }
        return String.valueOf(number);
    }

    public String failAction(int number) {
        return "오답";
    }

    public String getName() {
        return name;
    }
}