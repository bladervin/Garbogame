package garbogame;

import garbogame.GarboGrid;
import garbogame.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GarboController {
    GarboGrid grid1= new GarboGrid();
    

    HashMap<String, Integer>  mapRank=  new HashMap<String, Integer>();
    
    //to print the grid
    public void gridPrint(){

    grid1.createGrid();
    }	
        
    //to create the grid
    public void DisplayGrid(){
        grid1.createGrid();
        }	
	
    //to update the grid
    public void updateGrid(int i,int j,String value){
        grid1.updateGrid( i,j,value);
        }	
	
	
    //to get if  card is pressent in the grid at that position
    public boolean isPresent(int row,int column){
        String value= grid1.getvalue( row,column);
        if(value.equals("-"))
            {
                return false;
                }
        else{
            return true;
            }
        }	
	
    //to get if subsequent card is pressent in the grid 
    public boolean isSubsequent(int row,int column){
        return grid1.checkPresent( row,column);
    }	
	
    //method to control the gameplay for deal 1
    public void GarboPlayChanceDeal1(String[]  player1CardsDeal1, String[]  player2CardsDeal1, int turns,Scanner inputs, ArrayList<Player> players,boolean lastdeal){
            //map of the ranks to calculate points later in this method
            mapRank.put("S", 1);
	    mapRank.put("D", 4);
	    mapRank.put("C", 3);
	    mapRank.put("H", 2);
	    mapRank.put("K", 6);
	    mapRank.put("Q", 12);
	    mapRank.put("J", 5);
	    mapRank.put("10", 10);
	    mapRank.put("9", 9);
	    mapRank.put("8", 8);
	    mapRank.put("7", 7);
	    mapRank.put("A", 11);
             
	    int row=0;
	    int column=0;
            // for 4 turns
            while((turns/2)<4){
                boolean flag=true;
                int user;
                // user 1 is identified by even turns and user 2 by odd turns vcards of the users gets porint on his chance
                if (turns%2==0){
                    System.out.println("Player 1 Chance-Please find below the cards of Player 1");
                    System.out.println(Arrays.toString(player1CardsDeal1));
                    user=1;
                    }
                else{
                    System.out.println("Player 2 Chance-Please find below the cards of Player 2");
                    System.out.println(Arrays.toString(player2CardsDeal1));
                    user=2;
                    }
                
                //to get card value and position of card from the user
                System.out.println("Please input card in thr format 7 of H if you want to input 7 of hearts from the cards there in array");
                String userCards= inputs.nextLine();
                String location= "";
                String[] locationAttributes = new String[4];
                int count=0;
                while(count==0){
                    try{
                        System.out.println("Please enter the position you want to add the card for example if you want it to add at 1 row and first colum enter 1,1");
                        location= inputs.nextLine();
                        locationAttributes = location.split(",");
                        row = Integer.parseInt(locationAttributes[0]);
                        column = Integer.parseInt(locationAttributes[1]);
                        count=1;
                        }
                    catch (NumberFormatException nFE){
                        System.out.println("Entered Location is invalid");
                        }
                    }
                                                
                //give error if entered card is not in players card array
                if(((user==1) && !check(player1CardsDeal1,userCards))&&flag){
                    System.out.println("Entered card is not in the Player1 array Pleas enter from the below array"); 
                    turns --;
                    flag=false;
                    }
                
                if(((user==2) && !check(player2CardsDeal1,userCards))&&flag){
                    System.out.println("Entered card is not in the Player2 array Pleas enter from the below array"); 
                    turns --;
                    flag=false;
                    }
                //if user enter position more thn the array
                if(((row<1||row>4) ||(column<1||column>4))&&flag){
                    System.out.println("Entered Position is invalid  Please enter different position of row btween 1 to 4 and column between 1 to 4"); 
                    turns --;
                    flag=false;
                    }
                
                if(((turns/2)<8&&isPresent(row,column))&&flag&&!lastdeal){
                    System.out.println("Entered Position is already filled Please enter different position of row btween 1 to 4 and column between 1 to 4"); 
                    turns --;
                    flag=false;
                    }
                
                //error if no subsequent card is present in the array
                if(((turns/2)<8 && !isSubsequent(row,column)&&turns>0)&&flag){
                    System.out.println("There is no Subsequent card Present Side by Side to the entered Location"
                    + " Please enter different position of row btween 1 to 4 and column between 1 to 4 with subsequent card"); 
                    turns --;
                    flag=false;
                    }
                
                //if all the condition pass 
                if (flag){
                    // updation of entered card in grid
                    updateGrid(row,column,userCards);
                    String[] userCardsSplit = userCards.split(" ");
                    int points=0;
                    
                    //calculation of points if any of the user and removal of enterered lement from the user array
                    if (user==1){
                        int valueIndex =getindex(player1CardsDeal1,userCards);
       			player1CardsDeal1=removeTheElement(player1CardsDeal1,valueIndex);
       			points=calculatepoints(userCards,row,column);
       			(players.get(1).points)=(players.get(1).points)+points;
       			System.out.println("With this move Player2 earns "+points +" points");
                        }
                    else
                        {int valueIndex = getindex(player2CardsDeal1,userCards);
       			player2CardsDeal1=removeTheElement(player2CardsDeal1,valueIndex);
       			points=calculatepoints(userCards,row,column);
       			(players.get(0).points)=(players.get(0).points)+points;
       			System.out.println("With this move Player1 earns "+points +" points");
                        }
                    }
                turns++;
                }
	}
	
	 //method to control the gameplay for deal 2 and 3 are the features are similiar to GarboPlayChanceDeal1 except it returns athe remaining card in users array left after deal2
	public  HashMap<String, String[]>  GarboPlayChanceDeal2(String[]  player1CardsDeal2, String[]  player2CardsDeal2, int turns,Scanner inputs, ArrayList<Player> players,boolean lastdeal)
	{
		HashMap<String,String[]> map=new HashMap<String,String[]>();
		  int lastvalue=0;
		if (lastdeal)
		{lastvalue=8;}
		else
		{lastvalue=4;}
		while((turns/2)<lastvalue)
	        {
		   boolean flag=true;
       	int user;
       	if (turns%2==0)
       {
       	 System.out.println("Player 1 Chance-Please find below the cards of Player 1");
       	 System.out.println(Arrays.toString(player1CardsDeal2));
       	 user=1;
       
       
       }
       	else 
       	{
       		System.out.println("Player 2 Chance-Please find below the cards of Player 2");
       		System.out.println(Arrays.toString(player2CardsDeal2));
       		user=2;
       	}
       	 System.out.println("Please input card in thr format 7 of H if you want to input 7 of hearts from the cards there in array");
       	 String userCards= inputs.nextLine();
       	 System.out.println("Please enter the position you want to add the card for example if you want it to add at 1 row and first colum enter 1,1");
       	 String location= inputs.nextLine();
       	 if(((user==1) && !check(player1CardsDeal2,userCards))&&flag)
       	 {
       		 System.out.println("Entered card is not in the Player1 array Pleas enter from the below array"); 
       		 turns --;
       		 flag=false;
       	 }
       	 
       	 if(((user==2) && !check(player2CardsDeal2,userCards))&&flag)
       	 {
       		 System.out.println("Entered card is not in the Player2 array Pleas enter from the below array"); 
       		 turns --;
       		 flag=false;
       	 }
       	 
       	 String[] locationAttributes = location.split(",");
       	 
       	 int row = Integer.parseInt(locationAttributes[0]);
       	 int column = Integer.parseInt(locationAttributes[1]);

       	 if(((turns/2)<8&&isPresent(row,column))&&flag&&!lastdeal)
       	 {
       		 System.out.println("Entered Position is already filled Please enter different position of row btween 1 to 4 and column between 1 to 4"); 
       		 turns --;
       		 flag=false;
       	 }
       	if(((row<1&&row>4) ||(column<1&&column>4))&&flag)
      	 {
      		 System.out.println("Entered Position is invalid  Please enter different position of row btween 1 to 4 and column between 1 to 4"); 

      		 turns --;
      		 flag=false;
      	 }
       	 
       	if(((turns/2)<8 && !isSubsequent(row,column)&&turns>0)&&flag)
       	{
       		
     		 System.out.println("There is no Subsequent card Present Side by Side to the entered Location Please enter different position of row btween 1 to 4 and column between 1 to 4 with subsequent card"); 

      		 turns --;
      		 flag=false;
       	}
       	
       	 if (flag)
       	 {   
       		updateGrid(row,column,userCards);
       		String[] userCardsSplit = userCards.split(" ");
       		int points=0;
       		
       		
       		if (user==1)
       		{
       			int valueIndex =getindex(player1CardsDeal2,userCards);
       			player1CardsDeal2=removeTheElement(player1CardsDeal2,valueIndex);
       			points=calculatepoints(userCards,row,column);
       			(players.get(1).points)=(players.get(1).points)+points;
       			System.out.println("With this move Player2 earns "+points +" points");
       		
       		}
       		else
       		{
       			int valueIndex =getindex(player2CardsDeal2,userCards);
       			player2CardsDeal2=removeTheElement(player2CardsDeal2,valueIndex);
       			points=calculatepoints(userCards,row,column);
       			(players.get(0).points)=(players.get(0).points)+points;
       			System.out.println("With this move Player1 earns "+points +" points");
       		}
       	 }
       
        turns++;
       
	}
		  map.put("player1CardsDeal2", player1CardsDeal2);
		  map.put("player2CardsDeal2", player2CardsDeal2);
		  return map;
	}

	 private static boolean check(String[] arr, String toCheckValue) 
	    { 
	        boolean test = false; 
	        for (String element : arr) { 
	            if (element.equals(toCheckValue)) { 
	                test = true; 
	                break; 
	            } 
	        }
	        return test;
	    } 
	// to remove element from an array
public static String[] removeTheElement(String[] arr,int index)
{ 
if (arr == null|| index < 0|| index >= arr.length) { 
return arr; 
} 
String[] anotherArray = new String[arr.length - 1]; 

for (int i = 0, k = 0; i < arr.length; i++) 
{ 
     if (i == index) { 
      continue; 
} 
anotherArray[k++] = arr[i]; 
} 
return anotherArray; 
} 
	
//to get the index of element in array
public static int getindex(String[] arr,String value)
{ 

int index = -1;
for (int i=0;i<arr.length;i++) {
    if (arr[i].equals(value)) {
        index = i;
        break;
    }
}
return index;
}
// to calculate the poins of the opponent after users move 
private  int calculatepoints(String value, int rows,int column){ 
    String[] arraySplit= value.split(" ");
    int total=0;
    int totalValue = 0; 
    int totalSuite = 0; 
    int rowsValue=0;
    int columnValue=0;
    int diagonalValue=0;
    int rankValue=mapRank.get(arraySplit[0]);
    int rankSuite=mapRank.get(arraySplit[2]);
    rowsValue=grid1.traverserowsandCountValue(arraySplit[0],rows);
    columnValue=grid1.traverseColumnsandCountValue(arraySplit[0],column);
    diagonalValue=grid1.traverseDiagonalandCountValue(arraySplit[0],rows,column);
    totalValue=rankValue*(rowsValue+columnValue+diagonalValue);
    totalSuite=rankSuite*grid1.traverseDiagonalAdjacentandCountSuits(arraySplit[2],rows,column);
    total=totalSuite+totalValue;
    return total;
} 
}