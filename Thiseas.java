import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Thiseas {
	//x are rows 
	//y are columns
	//We're going need a couple of boolean variables to stop or continue our loops whenever we want
	static boolean flag1 = false;
	static boolean flag2 = false;
	//We need those to find our bound of the maze
	static int Xmax; 
	static int Ymax; 
	static String[][] maze; 
	//Here is our entrance in the maze
	static int entranceRow; 
	static int entranceColumn; 
	//to know the current location in the array
	static int currentX;
	static int currentY;
	static String exit;
	//Here we store the coordinates of every step we take towards to the exit of the maze
	static StringStackImpl<String> stack1 = new StringStackImpl<>(); //the number of the row 
	static StringStackImpl<String> stack2 = new StringStackImpl<>();//and number of column
	
	public static void main(String[] args) {
		
		//method to load the txt file. Parse to the method the argrument
		loadFile(args[0]);				
		//Check if the entrance exists in the maze and it is the rightfull place
		if (maze[entranceRow][entranceColumn].equals("E")==false) {
			flag1 = true;
			exit = ".";
			System.out.println("The entrance is wrongly stated!!");
		}	
		//We start from the entrance the text says 
		currentX = entranceRow; 
		currentY = entranceColumn;
		//So we store the coordinates in our stacks
		stack1.push(Integer.toString(entranceRow));
		stack2.push(Integer.toString(entranceColumn));
		while (flag1 == false) {
			//We check every possible way we can move forward to
			CheckDown(currentX, currentY, flag2);
			CheckUp(currentX, currentY, flag2);
			CheckLeft(currentX, currentY, flag2);
			CheckRight(currentX, currentY, flag2);
			//check if we found the exit
			if (flag2 == true) {
				if (currentX == 0 || currentX == Xmax || currentY == 0 || currentY == Ymax) {
					exit = Integer.toString(currentX) + " " + Integer.toString(currentY);
					System.out.println("The exit is: " + exit);
					flag2 = false;
				}
			} else {
				//remove form the stacks
				stack1.pop();
				stack2.pop();
				
			}
			//if the both stacks are empty the flag1 will stop the while loop
			//that checks if there any exit in the maze
			flag2 = false;
			if (stack1.isEmpty() && stack2.isEmpty()) {
				flag1 = true;
			}
		}
		//since the flag1 stop the while loop the exit is null that means no exit was found.
		if (exit == null) {
			System.out.println("The maze has no exit!! ");
		}
	}
	
	/*Checking all possible directions
	*Up, down, left and right
	*We check first of all if we are out of the bounds of the maze
	*if we're not then we can move forward 
	*If it is 1 we can't if it is 0 we can
	*In case it is 0 we store the coordinates in our stacks
	*Row in stack1 and column in stack2
	*/
	
	private static void CheckDown(int x, int y, boolean flag) {
		if (flag == false) {
			currentX = x+1;
			if (currentX<0 || y<0 || currentX>Xmax || y>Ymax) {
				currentX = currentX-1;
				flag2 = false;
			} else {
				//We cannot move if there is 1 or E (the entrance) or "ok" which is explained below
				if (maze[currentX][y].equals("1") == true || maze[currentX][y].equals("ok") == true || maze[currentX][y].equals("E") == true) {
					currentX = currentX-1;
					flag2 = false;
				}
				
				if (maze[currentX][y].equals("0") == true) {
					stack1.push(Integer.toString(currentX));
					stack2.push(Integer.toString(y));
					/*We change the 0 to "ok" so we know we went there 
					*so we won't go again in case it's deadend or
					*we have found 1 exit and we are looking for more
					*/
					maze[currentX][y] = "ok";
					flag2 = true;
				}
			}	
		} 
	}
	
	//The same applies for the rest of the 3 methods
	private static void CheckUp(int x, int y, boolean flag) {
		if (flag == false) {
			currentX = x-1;
			if (currentX<0 || y<0 || currentX>Xmax || y>Ymax) {
				currentX = currentX+1;
				flag2 = false;
			} else {
				if (maze[currentX][y].equals("1") == true || maze[currentX][y].equals("ok") == true || maze[currentX][y].equals("E") == true) {
					currentX = currentX+1;
					flag2 = false;
				}
				if (maze[currentX][y].equals("0") == true) {
					stack1.push(Integer.toString(currentX));
					stack2.push(Integer.toString(y));
					maze[currentX][y] = "ok";
					flag2 = true;
				}
			}	
		} 
	}
	
	private static void CheckRight(int x, int y, boolean flag) {
		if (flag == false) {
			currentY = y+1;
			if (x<0 || currentY<0 || x>Xmax || currentY>Ymax) {
				currentY = currentY-1;
				flag2 = false;
			} else {
				if (maze[x][currentY].equals("1") == true || maze[x][currentY].equals("ok") == true || maze[x][currentY].equals("E") == true) {
					currentY = currentY-1;
					flag2 = false;
				}
				if (maze[x][currentY].equals("0") == true) {
					stack1.push(Integer.toString(x));
					stack2.push(Integer.toString(currentY));
					maze[x][currentY] = "ok";
					flag2 = true;
				}
			}	
		} 
	}
	
	private static void CheckLeft(int x, int y, boolean flag) {
		if (flag == false) {
			currentY = y-1;
			if (x<0 || currentY<0 || x>Xmax || currentY>Ymax) {
				currentY = currentY+1;
				flag2 = false;
			} else {
				if (maze[x][currentY].equals("1") == true || maze[x][currentY].equals("ok") == true || maze[x][currentY].equals("E") == true ) {
					currentY = currentY+1;
					flag2 = false;
				}
				if (maze[x][currentY].equals("0") == true) {
					stack1.push(Integer.toString(x));
					stack2.push(Integer.toString(currentY));
					maze[x][currentY] = "ok";
					flag2 = true;
				}
			}	
		} 
	}
	
	//method to load the txt file from the command line
	private static void loadFile(String arg) {
		
		String line; 
		String[] listSize; //array to store the size of the maze 
		String[] listEntrance; //array to store the coordinates of the entrance
		String[] listLine; //array to store every row of the maze without the tokens
	
		try {
			//read the file as an argument
			BufferedReader reader = new BufferedReader(new FileReader(arg));
			line = reader.readLine();
			//reads lines until line is null
			while (line != null) {
				//spliting method for the parsing the data of the txt
				listSize = line.split(" ");
				//store the dimensions of the maze
				Xmax = Integer.parseInt(listSize[0])-1;
				Ymax = Integer.parseInt(listSize[1])-1;
				//and here initialize the maze as an array of Strings that has two dimensions
				maze = new String[Xmax + 1][Ymax + 1];
				line = reader.readLine();
				listEntrance = line.split(" ");
				//then from the second line store the coordinates of the entrance
				entranceRow = Integer.parseInt(listEntrance[0]);
				entranceColumn = Integer.parseInt(listEntrance[1]);
				//with two for loops store the positions of the elements of the maze in the two dimensions array
				for (int k=0; k<=Xmax; k++){
					line = reader.readLine();
					for (int m=0; m<=Ymax; m++) {
						if (line != null) {
							listLine = line.split(" ");
							maze[k][m] = listLine[m];
						} else {
							break;
						}
					}
				}
				line = reader.readLine();
				if(line == null){
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Error!!!");
			
		}
	}
	
	

}