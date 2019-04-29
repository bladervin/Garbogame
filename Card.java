package garbogame;

import garbogame.GarboDriver;

public class Card {

//This is the CardSuiteShuffle object for Shuffling the suites
    private GarboDriver.CardSuiteShuffle CardSuite;
    private String CardValue;

    //initialiing constructor of a card
    public Card(GarboDriver.CardSuiteShuffle CardSuite, String value) {
        this.CardSuite = CardSuite;
        this.CardValue = value;
    }

    public Card() {
     
    }
    
    //to get the suit of card
    public GarboDriver.CardSuiteShuffle getSuit() {
        return CardSuite;
    }
     //to set the suit of card
    public void setSuit(GarboDriver.CardSuiteShuffle CardSuite) {
        this.CardSuite = CardSuite;
    }
     //to get the value of card
    public String getValue() {
        return CardValue;
    }
    //to set the value of card
    public void setValue(String value) {
        this.CardValue = value;
    }

}