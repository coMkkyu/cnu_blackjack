package com.cnu.blackjack;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(new Deck(2));
        game.addPlayer("한선규", 4000);
        game.addPlayer("정성철", 6000);
        game.placeBet("한선규", 2000);
        game.placeBet("정성철", 3000);
        game.start();
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        evaluator.start();
    }

}
