package garbogame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GarboGrid {
    String GridArr[][] = new String[4][4];
        	
    //creation of the grid of 4X4 to enter cards
    public void createGrid(){
        System.out.println("Below is the current Grid");
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                GridArr[i][j]="-";
                System.out.print(GridArr[i][j]+"\t");
                }
            System.out.println();
            }
        }
            
    //updation of grid at that location and with that card value
    public void updateGrid(int i, int j, String value){
            
            GridArr[i-1][j-1]=value;
            for(int k = 0; k < 4; k++){
                for (int l = 0; l < 4; l++){
                    System.out.print(GridArr[k][l]+"\t");
                    }
	          
	         System.out.println();
	     }
	}
	
	public String getvalue(int row ,int column)
	{
		return GridArr[row-1][column-1];
	}
	//to check if following card is present 
	public boolean checkPresent(int row ,int column)
	{
	boolean flag =true;
	String value;
	String value2;
		if (row==1)
		{
			value=GridArr[row][column-1];
			
				flag=checkvalue(value);
				if (flag)
				{
				return true;	
				}
			
			}
		
		else if(row==4)
		{
			value=GridArr[row-2][column-1];
			flag=checkvalue(value);
			if (flag)
			{
			return true;	
			}
		}
	     	else
	      	{
		        value=GridArr[row][column-1];
				value2=GridArr[row-2][column-1];
				flag=checkvalue(value,value2);
				
				if (flag)
				{
				return true;	
				}
	     		
	     	}
		
		if (column==1)
		{
			value=GridArr[row-1][column];
			
				flag=checkvalue(value);
				if (flag)
				{
				return true;	
				}
			}
		else if(column==4)
		{
			value=GridArr[row-1][column-2];
			flag=checkvalue(value);
			if (flag)
			{
			return true;	
			}
		}
	     	else
	      	{
			
				value=GridArr[row-1][column];
				value2=GridArr[row-1][column-2];
				flag=checkvalue(value,value2);
				
				if (flag)
				{
				return true;	
				}
	     		
	     	}
		return false;
		
		
	}
	
	//to check the value of position entered
	public boolean checkvalue(String value)
	{
		if (value.equals("-"))
		{
			return false;
		} 	
		else
		{
			return true;	
		}
	}
	
	public boolean checkvalue(String value,String value2)
	{
		if (value.equals("-")&&value2.equals("-"))
		{
			return false;
		} 	
		else
		{
			return true;	
		}
	}
	//Count the no of times the value repeated in rows
	public int traverserowsandCountValue(String value,int row)
	{
		int count=0;
	
         for (int j = 0; j < 4; j++)
         {
        	  if(GridArr[row-1][j].contains(value))
			  {
			   count=count+1;
			  }			  
        
     }
		return count-1;
	}
	//to count the number of times the value repeated in columns
	public int traverseColumnsandCountValue(String value,int column)
	{
		int count=0;
		for (int i = 0; i < 4; i++)
     {     
        	  if(GridArr[i][column-1].contains(value))
			  {
			   count=count+1;
			  }			  
         
     }
		return count-1;
	}
	//to count the number of times the value repeated in diagonals
	public int traverseDiagonalandCountValue(String value,int row,int column)
	{
		int count=0;
		int rowCount = row-1;
	    int columnCount = column-1;
	    int rowCount1 = row-1;
	    int columnCount1 = column-1;

	    
	   while (rowCount>0&&columnCount>0)
	   {	  
		  rowCount--;
		  columnCount--;
		  if(GridArr[rowCount][columnCount].contains(value))
		  {
		   count=count+1;
		  }	
	   }
	 
		 
	   while (rowCount1<=3&&columnCount1<=3)
	   {	  

		  if(GridArr[rowCount1][columnCount1].contains(value))
		  {
		   count=count+1;
		  }		
		  rowCount1++;
		  columnCount1++;
	   }
		return count-1;
	}
	
	//to count the number of times the suits repeated in adjacent diagonal
	public int traverseDiagonalAdjacentandCountSuits(String value,int row,int column)
	{
		int count=0;
		
		if (row==4)
		{
			
			if (column==4)
			{
				  if(GridArr[2][2].contains(value))
				  {
				   count=count+1;
				  }		
			}
			else if (column==1)
			{
				 if(GridArr[2][1].contains(value))
				  {
				   count=count+1;
				  }		
			}
			else
			{
				
				if(GridArr[2][column-2].contains(value))
				  {
				   count=count+1;
				  }	
				
				if(GridArr[2][column].contains(value))
				  {
				   count=count+1;
				  }	
				
			}
			
		}
		else if(row==1)
		{
			if (column==4)
			{
				if(GridArr[1][2].contains(value))
				  {
				   count=count+1;
				  }		
			}
			else if (column==1)
			{
				if(GridArr[1][1].contains(value))
				  {
				   count=count+1;
				  }		
			}
			else
			{
				if(GridArr[1][column-2].contains(value))
				  {
				   count=count+1;
				  }	
				
				if(GridArr[1][column].contains(value))
				  {
				   count=count+1;
				  }	
			}	
		}
		else
		{
			if (column==4)
			{
				if(GridArr[row-2][2].contains(value))
				  {
				   count=count+1;
				  }	
				
				if(GridArr[row][2].contains(value))
				  {
				   count=count+1;
				  }	
			}
			else if (column==1)
			{
				if(GridArr[row-2][1].contains(value))
				  {
				   count=count+1;
				  }	
				
				if(GridArr[row][1].contains(value))
				  {
				   count=count+1;
				  }		
			}
			else
			{

				if(GridArr[row-2][column-2].contains(value))
				  {
				   count=count+1;
				  }	
				
				if(GridArr[row][column-2].contains(value))
				  {
				   count=count+1;
				  }		
				

				if(GridArr[row-2][column].contains(value))
				  {
				   count=count+1;
				  }	
				
				if(GridArr[row][column].contains(value))
				  {
				   count=count+1;
				  }		
			}
		}
		
		return count;
	}
	
}
