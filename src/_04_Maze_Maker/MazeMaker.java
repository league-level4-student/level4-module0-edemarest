package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(5, 5);
		
		//4. select a random cell to start
		int rand = new Random().nextInt(5);
		int rand2 = new Random().nextInt(5);
		
		
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[rand][rand2]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell c) {
		//A. mark cell as visited
		c.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> n = getUnvisitedNeighbors(c);
		//C. if has unvisited neighbors,
		if(!(n.isEmpty())) {
			//C1. select one at random.
			int rand = new Random().nextInt(n.size());
			
			Cell neighbor = n.get(rand);
			//C2. push it to the stack
			uncheckedCells.push(neighbor);
			//C3. remove the wall between the two cells
			if(neighbor.getX()>c.getX()) {
				c.setEastWall(false);
				neighbor.setWestWall(false);
			}
			else if(neighbor.getX()<c.getX()) {
				c.setWestWall(false);
				neighbor.setEastWall(false);
			}
			else if(neighbor.getY()>c.getY()) {
				c.setNorthWall(false);
				neighbor.setSouthWall(false);
			}
			else if(neighbor.getY()<c.getY()) {
				c.setSouthWall(false);
				neighbor.setNorthWall(false);
			}
			//C4. make the new cell the current cell and mark it as visited
			c = neighbor;
			c.setBeenVisited(true);;
			//C5. call the selectNextPath method with the current cell
			System.out.println("Unvisited neighbors: "+c.getX()+" "+c.getY());
			selectNextPath(c);
		}
		else {
		//D. if all neighbors are visited

		if(!(uncheckedCells.isEmpty())) {
			c = uncheckedCells.pop();
			System.out.println("Neighbors: "+c.getX()+" "+c.getY());
			selectNextPath(c);
		}
		}
			//D1. if the stack is not empty
			
				// D1a. pop a cell from the stack
		
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		int num = 0;
		int y = c.getY();
		int x = c.getX();
		ArrayList<Cell> n = new ArrayList<Cell>();
		if (y > 0) {
			if (!(maze.getCell(x, y-1).hasBeenVisited())) {
				n.add(maze.getCell(x, y-1));
			}
		}
		if (y < maze.cells[0].length - 1) {
			if (!(maze.getCell(x, y+1).hasBeenVisited())) {
				n.add(maze.getCell(x, y+1));
			}
		}
		if (x > 0) {
			if (!(maze.getCell(x-1,y).hasBeenVisited())) {
				n.add(maze.getCell(x-1,y));
			}
		}
		if (x < maze.cells.length-1) {
			if (!(maze.getCell(x+1, y).hasBeenVisited())) {
				n.add(maze.getCell(x+1, y));
			}
		}
		
			
			
		
		return n;
	}
}
