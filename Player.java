package garbogame;


public class Player {
	String username;
        //method to get username of player
	public String getUsername() {
		return username;
	}
        //method to set username of player
	public void setUsername(String username) {
		this.username = username;
	}

	int playerID;
	int points;
 //method to get points of player
	public int getPoints() {
		return points;
	}
 //method to set  points of player
	public void setPoints(int points) {
		this.points = points;
	}
 //constructor to set the values of the class variables and initialize the class object
	public Player(String username, int playerID,int points) {
		this.username = username;
		this.playerID = playerID;
		this.points = points;
	}
	
	public Player() {
		
	}
	

}