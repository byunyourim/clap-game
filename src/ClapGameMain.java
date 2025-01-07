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
        players.add(new Player("A", 0.1));
        players.add(new Player("B", 0.3));

        Game game = new Game(players);

        game.play();
    }
}


class Game {
    List<Player> players;
    int currentNumber;

    Game(List<Player> players) {
        this.players = players;
        this.currentNumber = 1;
    }

    public void play() {
        while (true) {
            for (Player p : players) {
                String res = p.takeTurn(currentNumber);

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

    public String takeTurn(int number) {
        if (random.nextDouble() < errorRate) {
            return failAction(number);
        }
        return correctAction(number);
    }

    public String correctAction(int number) {
        String s = String.valueOf(number);

        long count = s.chars()
            .filter(c -> c == '3' || c == '6' || c == '9')
            .count();

        return count > 0 ? "짝".repeat( (int) count ) : s;
    }

    public String failAction(int number) {
        return "오답";
    }

    public String getName() {
        return name;
    }
}