import java.util.*;

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
 * - 서울 지역 3, 6, 9에 박수치기
 * - 부산 지역 2, 4, 6에 박수치기
 * - 예외 처리 ? 값이 아닌
 *
 * 4 단계
 * - 다양한 지역 동시 게임 진행을 위한 동시성 적용
 */
public class ClapGameMain {
    public static void main(String[] args) throws Exception {
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









