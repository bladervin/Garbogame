package garbogame;

import garbogame.GarboController;
import garbogame.Player;
import garbogame.Card;
import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

// CardSuiteShuffle enum
public class GarboDriver {
    public enum CardSuiteShuffle {
        S, D, C, H
        }
        
    //Creating player arraylist and enter the username of array by the user
    public static ArrayList<Player> player = new ArrayList<>();
    
    public static void main(String[] args) {
        ArrayList<String> username = new ArrayList<>();
    	String username1;
    	String username2;
    	Scanner inputs = new Scanner(System.in);
    	Card CardSuite=new Card();
    	int turns=0;
    	String[] arrayOfRanDomCards=new String[32];
    	
    	System.out.println("\nWelcome to play Garbo.");
    	System.out.println("\n================================================================================");
    	while (true)
		{
    	System.out.println("Player 1 will deal the game first. \nEnter Player 1's user name. ");
		username1 = inputs.nextLine();
		if (username1.trim().isEmpty()) {
                    System.out.println("Invalid Username");
		}
		else
		{
			username.add(username1);
			break;
		}
		}
	
		while (true)
		{
		System.out.println("\nEnter Player 2's user name. ");
		username2 = inputs.nextLine();
		if (username2.trim().isEmpty()) {
			System.out.println("Invalid username");
		}
		else if (username2.equalsIgnoreCase(username.get(0))) {
			System.out.println("Invalid. Player 2's user name is the same as Player 1.");
		}
		else{
		username.add(username2);
		break;}
		}
		player.add(0, new Player(username.get(0),0,0));
		player.add(1, new Player(username.get(1),1,0));
		
		
        String[] CardValue = new String[]{"K", "Q", "J", "10", "9", "8", "7", "A"};

        Card[] deck = new Card[32];
        int i = 0;
        for (CardSuiteShuffle cardSuite: CardSuiteShuffle.values()) {
            for (String value : CardValue) {
                deck[i++] = new Card(cardSuite, value);
            }
        }

        int k=0;
        //craeting array of cards with value and suite
        for (Card card : deck) {
        	arrayOfRanDomCards[k]=card.getValue() + " of " + card.getSuit();
        	k++;
        }
        
        //shuffleing the card
        for (int p=0;p<arrayOfRanDomCards.length;p++)
        {
        	int index=(int) (Math.random()*arrayOfRanDomCards.length);
        	String temp =arrayOfRanDomCards[p];
        	arrayOfRanDomCards[p]=arrayOfRanDomCards[index];
        	arrayOfRanDomCards[index]=temp;
        	
        			
        }
        //creating GarboController object to control the game
        GarboController gc=new GarboController();
        gc.gridPrint();
        //starting level1 deal1 
        System.out.println("Level 1 Deal 1 Starts");
        String[] arrayafter1Deal=new String[23];
        String[] player1CardsDeal1=new String[3];
        String[] player2CardsDeal1=new String[3];	
        //dealing cards for deal 1
        arrayafter1Deal=Arrays.copyOfRange(arrayOfRanDomCards, 8,32);
        player1CardsDeal1=Arrays.copyOfRange(arrayOfRanDomCards, 0,4);
        player2CardsDeal1=Arrays.copyOfRange(arrayOfRanDomCards, 4,8);
      
        //gameplay for deal1
        gc.GarboPlayChanceDeal1(player1CardsDeal1,player2CardsDeal1,turns,inputs,player,false);            	        
        System.out.println("Level 1 Deal 1 Ends");
        System.out.println("Player 1 Points are "+player.get(0).points);
        System.out.println("Player 2 Points are "+player.get(1).points);
        System.out.println("Level 1 Deal 2 Starts");               
        turns=0;
        String[] arrayafter2Deal=new String[7];
        String[] player1CardsDeal2=new String[7];
        String[] player2CardsDeal2=new String[7];	
        //setting cards for deal2
    	arrayafter2Deal=Arrays.copyOfRange(arrayafter1Deal, 16,24);
    	player1CardsDeal2=Arrays.copyOfRange(arrayafter1Deal, 0,8);
    	player2CardsDeal2=Arrays.copyOfRange(arrayafter1Deal, 8,16);
    	 HashMap<String, String[]> mapofremaininguserdata= new  HashMap<String, String[]>();
          //gameplay for deal2 and getting remaing users cards after deal2
    	 mapofremaininguserdata= gc.GarboPlayChanceDeal2(player1CardsDeal2,player2CardsDeal2,turns,inputs,player,false);    	     	
         
        System.out.println("Level 1 Deal 2 Ends");
        System.out.println("Player 1 Points are "+player.get(0).points);
        System.out.println("Player 2 Points are "+player.get(1).points);
        System.out.println("Level 2 Starts");
            
        turns=0;
        
         //setting cards for deal3
         String[] arrayafter3Deal=new String[7];
         String[] player1CardsDeal3pre=new String[3];
         String[] player2CardsDeal3pre=new String[3];	
         String[] player1CardsDeal3post=new String[7];
         String[] player2CardsDeal3post=new String[7];
         player1CardsDeal3pre=Arrays.copyOfRange(arrayafter2Deal, 0,4);
         player2CardsDeal3pre=Arrays.copyOfRange(arrayafter2Deal, 4,8);
         player1CardsDeal3post=mergearray(player1CardsDeal3pre,(String[]) mapofremaininguserdata.get("player1CardsDeal2"));
         player2CardsDeal3post=mergearray(player2CardsDeal3pre,(String[]) mapofremaininguserdata.get("player2CardsDeal2"));
         //gameplay for deal3
         gc.GarboPlayChanceDeal1(player1CardsDeal3post,player2CardsDeal3post,turns,inputs,player,true);
   
         // final result after the game finished and declaring the winner
         System.out.println("Player 1 Points are "+player.get(0).points);
         
         System.out.println("Player 2 Points are "+player.get(1).points);
         
         if (player.get(0).points>player.get(1).points)
         {
        	 System.out.println("Player 1 with username "+player.get(0).username+" wins");
         }
         else if (player.get(0).points<player.get(1).points)
         {
        	 System.out.println("Player 2 with username "+player.get(1).username+" wins");
         }
         else
         {
        	 System.out.println("Its a tie");
         }
    }
     
//function to merge two arrays
    public static String[] mergearray(String arr1[],String arr2[]) {
    	String[] mergedarr=new String[8];
    	System.arraycopy(arr1, 0, mergedarr, 0, 4);
        System.arraycopy(arr2, 0, mergedarr, 4, 4);
        return mergedarr;
     }
}