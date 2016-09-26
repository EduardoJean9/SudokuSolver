import java.util.Scanner;


public class SudukoAI {
	
	public static boolean isempty(int[][]puzzle, int x, int y){
		//Simple checks the cell in the puzzle to see if its empty
		//or if there is already a number placed in there.
		if(puzzle[x][y] == 0){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean crosscheck(int[][] puzzle, int x, int y, int value){
		//Checks that the value is a legal move in the horizontal plane
		for(int i = 0; i < 9; i++){
			if(puzzle[i][y] == value){
				return false;
			}
		}
		//Checks that the value is a legal move in the vertical plane
		for(int i =0; i<9; i++){
			if(puzzle[x][i] == value){
				return false;
			}
		}
		return true;
	}
	
	public static boolean squarecheck(int[][] puzzle, int x, int y, int value){
		//These value are the top left corner of the square in which x,y value is in
		//Dividing by and integer makes the decimals round down which makes this possible. 
		int xstart = 3*(x/ 3);
		int ystart = 3*(y/3);
		//These values point at the bottom right hand of the square in which the x,y point
		int xend = xstart + 2;
		int yend = ystart + 2;
		for(int i = xstart; i<= xend; i++){
			for(int j = ystart; j<= yend; j++){
				if(puzzle[i][j] == value){
					return false;
				}
			}
		}
		return true;
	}
	//Uses recursion
	public static boolean solvepuzzle(int[][]puzzle, int x, int y){
		//The statement that ends the recursion loop.
		if(x == 9){
			x=0;
			if(++y ==9){
				return true;
			}
		}
		//Makes sure that the given space is empty
		if(isempty(puzzle,x,y) == true){
			//loops through the 9 possible numbers.
			for(int i = 1; i<=9; i++){
				if(crosscheck(puzzle,x,y,i) == true && squarecheck(puzzle,x,y,i)==true){
					//if the its found that a number is a legal move it places it and the function
					//calls itself with it iterating to the next possible spot.
					puzzle[x][y] = i;
					if(solvepuzzle(puzzle,x+1,y)){
						return true;
					}
				}
			}
		}
		//if the current spot is not empty, it recursively calls itself onto the next spot.
		else{
			return solvepuzzle(puzzle,x+1,y);
		}
		//Backtracking statement. If none of the numbers worked, it makes the number go back to
		//0 and returns false since the problem cant be solved. 
		puzzle[x][y] = 0;
		return false;
	}
	//Prints the 2d array given.
	public static void printpuzzle(int[][] puzzle){
		for(int i = 0; i< puzzle.length; i++){
			for(int j = 0; j < puzzle[i].length; j++){
				System.out.print(puzzle[i][j] + " | ");
			}
			System.out.println();
		}
	}

	
	public static void main(String[] args) {
		//Hard code puzzles into program.
		//puzzle 255 from given web site.
		int [][] puzzle1 = {
							{1,3,0,0,0,6,0,0,0},
							{0,7,4,0,0,2,5,8,0},
							{0,0,0,0,5,0,3,0,0},
							{0,8,0,0,1,0,0,0,0},
							{0,0,0,0,6,0,0,2,9},
							{0,0,0,0,0,0,4,3,0},
							{0,0,0,0,3,0,0,5,0},
							{9,0,3,0,0,0,7,0,4},
							{0,0,0,0,7,5,8,0,0},
							};	
		
		int [][] puzzle2 = {
				{0,8,0,0,0,9,0,0,0},
				{0,0,4,0,0,0,8,0,0},
				{0,0,5,0,2,0,0,1,0},
				{0,0,9,0,0,0,0,0,4},
				{4,0,3,0,6,0,0,0,0},
				{1,0,0,2,0,0,5,0,0},
				{7,0,0,0,0,8,0,0,0},
				{0,0,0,5,0,0,2,0,0},
				{0,0,0,0,9,0,4,8,0},
				};	
		
		int [][] puzzle3 = {
				{8,0,6,0,0,0,0,0,0},
				{0,0,0,6,0,0,0,0,0},
				{0,0,0,9,0,3,2,0,0},
				{9,3,0,2,0,0,0,1,0},
				{0,0,0,0,0,0,0,2,6},
				{0,0,0,3,4,0,5,0,0},
				{1,0,0,0,8,0,0,0,0},
				{3,0,0,0,0,7,0,0,4},
				{0,0,4,0,0,0,0,0,0},
				};	
		
		
		System.out.println("Sudoku Puzzle Solver(0 means empty cell)");
		System.out.println();
		
		System.out.println("Easy Puzzle: 255");
		printpuzzle(puzzle1);
		System.out.println();
		System.out.println("Solution for puzzle:");
		solvepuzzle(puzzle1,0,0);
		printpuzzle(puzzle1);
		System.out.println();
		
		System.out.println("Medium Puzzle: 241");
		printpuzzle(puzzle2);
		System.out.println();
		System.out.println("Solution for puzzle:");
		solvepuzzle(puzzle2,0,0);
		printpuzzle(puzzle2);
		System.out.println();
		
		System.out.println("Hard Puzzle: 259");
		printpuzzle(puzzle3);
		System.out.println();
		System.out.println("Solution for puzzle:");
		solvepuzzle(puzzle3,0,0);
		printpuzzle(puzzle3);
	}
}
