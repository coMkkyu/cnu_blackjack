package com.cnu.blackjack;

import java.util.Map;

public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;
    private int dealerscore;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public void start() {
        System.out.println("Game Start");
        dealerscore = dealer.getDealerScore();
        playerMap.forEach((name, player)-> {
            selectHitOrStay(player);
        });
        playerMap.forEach((name, player)-> {
            int playerscore = getPlayerScore(player);
            if(playerscore == 21) {
                if(dealerscore == 21){
                    System.out.println("DRAW");
                    player.drawBet();
                }else {
                    System.out.println(name + "플레이어의 블랙잭 입니다.");
                    player.winBet();
                }
            }
            else if ((playerscore > dealerscore) && (playerscore < 21)){
                System.out.println(name + " 플레이어의 승리");
                player.winBet();
            }
            else if(playerscore == dealerscore){
                System.out.println("DRAW");
                player.drawBet();
            }
            else if(dealerscore > 21){
                if(playerscore <= 21){
                    System.out.println(name + " 플레이어의 승리");
                    player.winBet();
                }
                else{
                    System.out.println("DRAW");
                    player.drawBet();
                }
            }
            else System.out.println(name + " 플레이어의 패배");

            System.out.println(name + " 플레이어의 현재 남은 balance : " + player.getBalance());
        });
    }

    private void dealCardToPlayers() {
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }

    public int getPlayerScore(Player player){
        int playerscore = 0;
        for(int i = 0; i < player.getHand().getCardList().size(); i++){
            playerscore += player.getHand().getCardList().get(i).getRank();
        }
        return playerscore;
    }

    public void selectHitOrStay(Player player){
           if(getPlayerScore(player) < 17){
               player.hitCard();
           }
    }

    public void blackjack(){
        int sum = 0;
        playerMap.forEach((name, player)-> {

        });
    }
}
