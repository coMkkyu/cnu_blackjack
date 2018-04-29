package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.NotEnoughBalanceException;
import lombok.Data;

@Data
public class Player {

    private int balance;
    private int currentBet;
    private Hand hand;

    public Player(int seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;
    }

    public void placeBet(int bet) {
        if(balance < bet) {
            throw new NotEnoughBalanceException();
        }
        balance -= bet;
        currentBet = bet;
    }

    //승리하였을경우 현재 배팅한금액의 2배를 보상받는다.
    public void winBet(){
        balance = balance + 2 * currentBet;
        currentBet = 0;
    }

    //비겼을 경우 배팅했던 금액을 돌려 받는다.
    public void drawBet(){
        balance = balance + currentBet;
        currentBet = 0;
    }

    public Card hitCard() {
        return hand.drawCard();
    }
}
