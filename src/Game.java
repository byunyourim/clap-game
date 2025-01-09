import java.util.List;

public class Game {
    List<Player> players;
    int currentNumber;
    GameRule rule;

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
        try {
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
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during the game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}