import java.util.Set;

public class SeoulGame extends GameRule {
    @Override
    protected Set<Character> getTargetNumber() {
        return Set.of('3', '6', '9');
    }

    @Override
    protected String getActionSymbol() {
        return "짝";
    }
}