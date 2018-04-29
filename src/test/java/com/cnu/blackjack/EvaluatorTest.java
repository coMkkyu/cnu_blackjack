package com.cnu.blackjack;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EvaluatorTest {

    Game game;

    @Before
    public void setUp(){
        game = new Game(new Deck(2));
        //2장의 카드덱으로 게임을 진행
    }

    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        //모든플레이어이므로 2인이상의 플레이어로 테스트
        game.addPlayer("player1", 3000);
        game.addPlayer("player2", 5000);
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        Player player1 = game.getPlayerList().get("player1");
        Player player2 = game.getPlayerList().get("player2");
        assertThat(player1.getHand().getCardList().size(), is(2));
        assertThat(player2.getHand().getCardList().size(), is(2));
    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {
        game.addPlayer("player1", 3000);
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        Player player1 = game.getPlayerList().get("player1");
        int player1_score = evaluator.getPlayerScore(player1);

        if(player1_score <= 16) {
            player1.hitCard();
            assertThat(player1.getHand().getCardList().size(), is(3));
        }else{
            assertThat(player1.getHand().getCardList().size(), is(2));
        }
    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {
        game.addPlayer("player1", 3000);
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        Player player1 = game.getPlayerList().get("player1");
        int player1_score = evaluator.getPlayerScore(player1);
        int player1_balance = player1.getBalance();
        if(player1_score == 21){
            assertThat(player1.getBalance(), is(2*player1_balance));
        }
    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {
        game.addPlayer("player1", 3000);
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        game.getPlayerList().forEach((name, player)-> {
            //17이상일 경우에는 stay했으므로 카드가 2장일 것이다.
           if(evaluator.getPlayerScore(player) >= 17){
               assertThat(player.getHand().getCardList().size(), is(2));
           }
           //hit했을때는 카드가 3장이다.
            else{
               assertThat(player.getHand().getCardList().size(), is(2));
           }
        });
    }
}
