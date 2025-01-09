import java.util.Set;

public class BusanGame extends GameRule {
    @Override
    protected Set<Character> getTargetNumber() {
        return Set.of('2', '4', '6');
    }

    @Override
    protected String getActionSymbol() {
        return "쿵";
    }
}