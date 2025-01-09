import java.util.Set;

abstract class GameRule {
    protected abstract Set<Character> getTargetNumber();
    protected abstract String getActionSymbol();

    boolean takeAction(int n) {
        String s = String.valueOf(n);
        return s.chars()
            .anyMatch(c -> getTargetNumber().contains((char) c));
    }

    String playAction(int n) {
        String s = String.valueOf(n);
        int count = (int) s.chars()
            .filter(c -> getTargetNumber().contains((char) c))  // 그냥 c는 어떤 값인지?
            .count();

        return getActionSymbol().repeat(count);
    }
}