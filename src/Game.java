import java.util.List;

public class Game {
    private static final String WRONG_ANSWER = "오답";

    private  List<Player> players;
    private  int currentNumber;
    private  GameRule rule;

    Game(List<Player> players, GameRule rule) throws Exception {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("players cannot be null");
        }
        if (rule == null) {
            throw new IllegalArgumentException("rule cannot be null");
        }

        this.players = players;
        this.currentNumber = 1;
        this.rule = rule;
    }

    public void play() {
        while(true) {
            for (Player p : players) {
                if (!processTurn(p)) {
                    System.out.println("게임이 종료되었습니다.");
                    return;
                }
            }
        }
    }

    private boolean processTurn(Player p) {
        try {
            String res = p.takeTurn(currentNumber, rule);

            if (res.equals(WRONG_ANSWER)) {
                System.out.println(p.getName() + " 오답");
                return false;
            }
            System.out.println(p.getName() + " 응답 : " + res);
            currentNumber++;
            return true;

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;

        } catch (RuntimeException e) {
            System.err.println("알 수 없는 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}