package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import locations.*;
import control.*;

/**
 * Class to control the board, storing all objects and their position
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Board {
	
	private Character[] characters;
	private Room[] rooms;
	private Location locations[][]; 
	//For storing locations of players
	private HashMap<Character,Point> playerPositions = new HashMap<Character,Point>();
	private Dice dice;
	
	private Stairwell stairsToStudy;
	private Stairwell stairsToLounge;
	private Stairwell stairsToKitchen;
	private Stairwell stairsToConservatory;
	private Middle middle = new Middle();
	
	private int leftPush, topPush = 0;	//used for locations of items on board
	private Location clickLoc = null;
	private int[] clickCoords = new int[2];	//x and y of clicked location (in terms of squares, not pixels)
	
	/**
	 * Creates an instance of the board
	 * @param characters
	 * @param rooms
	 */
	public Board (Character[] characters, Room[] rooms){
		this.characters = characters;
		this.rooms = rooms;
		this.locations = new Location[24][25];
		this.dice = new Dice(6);
		
		ArrayList<Point> startPoints = new ArrayList<Point>();
		String boardString =  "nnnnnnnnnSnnnnSnnnnnnnnn\n"+
							  "kkkkkUn___bbbb___ncccccc\n"+
							  "kkkkkk__bbbbbbbb__cccccc\n"+
							  "kkkkkk__bbbbbbbb__cccccc\n"+
							  "kkkkkk__bbbbbbbb__cccccc\n"+
							  "kkkkkk_DbbbbbbbbD_DcccLn\n"+
							  "nkkkkk__bbbbbbbb_______S\n"+
							  "____D___bbbbbbbb_______n\n"+
							  "n________D____D___iiiiii\n"+
							  "ddddd____________Diiiiii\n"+
							  "dddddddd__mmmmm___iiiiii\n"+
							  "dddddddd__mmmmm___iiiiii\n"+
							  "ddddddddD_mmmmm___iiiiii\n"+
							  "dddddddd__mmmmm_____D_Dn\n"+
							  "dddddddd__mmmmm___llllln\n"+
							  "dddddddd__mmmmm__lllllll\n"+
							  "n_____D___mmmmm_Dlllllll\n"+
							  "S__________DD____lllllll\n"+
							  "n_____D__hhhhhh___llllln\n"+
							  "Coooooo__hhhhhh________S\n"+
							  "ooooooo__hhhhhhD_D_____n\n"+
							  "ooooooo__hhhhhh__ssssssK\n"+
							  "ooooooo__hhhhhh__sssssss\n"+
							  "ooooooo__hhhhhh__sssssss\n"+
							  "oooooonSnhhhhhhn_nssssss\n";
		
		try {
		
			InputStream input = new ByteArrayInputStream(boardString.getBytes());
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			//Reading string into Square objects
			for (int y = 0; y < locations[0].length; y++){
				String line = reader.readLine();
				for (int x = 0; x < locations.length; x++){
					locations[x][y] = parseLocation(line.charAt(x));
					if (locations[x][y] instanceof StarterSquare){
						startPoints.add(new Point(x,y));
					}
				}
			}
			reader.close();
		} catch (IOException e){
			System.out.println("Could not read Board"); //should never get here
		}
		
		//Enter starting positions
		for (int i = 0; i < characters.length; i++){
			playerPositions.put(characters[i], startPoints.get(i));
		}
		
		addLocations();
	}
	
	/**
	 * @param character
	 * @return Position of character as a Point
	 */
	public Point getPosition(Character character){
		return playerPositions.get(character);
	}
	
	/**
	 * @return Dice used on the board
	 */
	public Dice getDice(){
		return dice;
	}
	
	/**
	 * Gives Location from given character
	 * @param symbol
	 * @return Location corresponding to character
	 */
	public Location parseLocation(char symbol) {
		switch (symbol){
			case 'n' : return null;
			case 'S' : return new StarterSquare();
			case '_' : return new Square(false);
			case 'D' : return new Square(true);
			case 'k' : return rooms[0];	//kitchen
			case 'b' : return rooms[1]; //ball room
			case 'c' : return rooms[2]; //conservatory
			case 'i' : return rooms[3]; //billiard room
			case 'l' : return rooms[4]; //library
			case 's' : return rooms[5]; //study
			case 'h' : return rooms[6]; //hall
			case 'o' : return rooms[7]; //lounge
			case 'd' : return rooms[8]; //dining room
			case 'U' : stairsToStudy = new Stairwell(rooms[5]);
					   return stairsToStudy;
			case 'L' : stairsToLounge = new Stairwell(rooms[7]);
					   return stairsToLounge;
			case 'K' : stairsToKitchen = new Stairwell(rooms[0]);
			   		   return stairsToKitchen;
			case 'C' : stairsToConservatory = new Stairwell(rooms[2]);
			   		   return stairsToConservatory;
			case 'm' : return middle;
		}
		return null; //should never get here
	}
	
	/**
	 * For every location on the board, add the locations above, below,
	 * left and right of it if it can be moved to
	 */
	public void addLocations(){
		for (int i = 0; i < locations.length; i++){
			for (int j = 0; j < locations[i].length; j++){
				if (locations[i][j] != null){
					if (i > 0 )
						locations[i][j].addLocation(locations[i-1][j]);
					if (i < locations.length - 1){
						locations[i][j].addLocation(locations[i+1][j]);
					}
					if (j > 0){
						locations[i][j].addLocation(locations[i][j-1]);
					}
					if (j < locations[i].length - 1){
						locations[i][j].addLocation(locations[i][j+1]);
					}
				}
			}
		}
	}
	
	/**
	 * @param p
	 * @return Location corresponding to coordinates of given Point
	 */
	public Location getSquareFromPoint(Point p){
		return locations[p.x][p.y];
	}

	/**
	 * Sets position of character to given point
	 * @param character
	 * @param point
	 */
	public void setPosition(Character character, Point point) {
		if (point.getX() >= 0 && point.getX() < locations.length 
				&& point.getY() >= 0 && point.getY() < locations[0].length){
			this.playerPositions.put(character, point);
		}
		
	}
	
	/**
	 * @param p
	 * @return Location at given point
	 */
	public Location getLocationAtPoint(Point p){
		if (p.getX() >= 0 && p.getX() < locations.length 
				&& p.getY() >= 0 && p.getY() < locations[0].length) {
			return locations[(int) p.getX()][(int) p.getY()];
		} 
		else {
			return null;
		}
	}
	
	public Character[] getCharacters(){
		return this.characters;
	}
	
	/**
	 * Print out the board to the console
	 */
	public void printBoard(){
		System.out.print("   ");
		for (char x = 'A'; x-'A' < locations.length; x++){
			System.out.print(x);
		}
		System.out.println();
		for (int y = 0; y < locations[0].length; y++){
			System.out.print(y+" ");
			//Extra space if single digit
			if (y < 10){
				System.out.print(" ");
			}
			for (int x = 0; x < locations.length; x++){
				if (locations[x][y] != null){
					boolean characterOnLocation = false;
					for (Character c : playerPositions.keySet()){
						Point p = playerPositions.get(c);
						if (p.getX() == x && p.getY() == y){
							characterOnLocation = true;
							System.out.print(c.toChar());
							break;
						}
					}
					if (!characterOnLocation){
						locations[x][y].print();
					}
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Detects the hovering of the mouse over a character icon and
	 * sets the isHovering parameter in the corresponding character to be true.
	 * @param x
	 * @param y
	 */
	public void mouseHover(int x, int y) {
		for (Character c : playerPositions.keySet()){
			Point p = playerPositions.get(c);
			if (x > p.getX()*20+leftPush && x < p.getX()*20+20+leftPush
				&& y > p.getY()*20+topPush && y < p.getY()*20+20+topPush){
				c.setHovering(true);
			}
			else{
				c.setHovering(false);
			}
		}
	}

	/**
	 * Called from the canvas mouseClicked, records the current x and y position 
	 * and location of the square it is currently in.
	 * @param x
	 * @param y
	 */
	public void boardMouseClicked(int x, int y) {
		for (int yplace = 0; yplace < locations[0].length; yplace++){
			for (int xplace = 0; xplace < locations.length; xplace++){
				if (x > xplace*20+leftPush && x < xplace*20+20+leftPush
					&& y > yplace*20+topPush && y < yplace*20+20+topPush){
					clickLoc = locations[xplace][yplace];
					clickCoords[0] = xplace;
					clickCoords[1] = yplace;
				}
			}
		}
	}
	
	/**
	 * Returns clicked location in utility array.
	 * @return
	 */
	public Object[] getClickedLocation(){
		Object[] result = null;
		Location clicked = clickLoc;
		clickLoc = null;
		if (clicked != null){
			result = new Object[]{clicked,clickCoords};
		}
		return result;
	}
	
	public int getWidth(){
		return locations.length;
	}
	
	public int getHeight(){
		return locations[0].length;
	}

	public void draw(Graphics g, int width, int height) {
		leftPush = (width - (locations.length*20))/2;
		topPush = (height - (locations[0].length*20))/2;
		for (int y = 0; y < locations[0].length; y++){
			for (int x = 0; x < locations.length; x++){
				if (locations[x][y] != null){
					locations[x][y].draw(g, x*20+leftPush, y*20+topPush, this, x, y);
				}
			}
		}
		
		for (Character c : playerPositions.keySet()){
			c.draw(g, (int) playerPositions.get(c).getX()*20+leftPush, 
					(int) playerPositions.get(c).getY()*20+topPush);
		}
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 12));
		g.setColor(Color.WHITE);
		
		g.drawString("Kitchen", 50, 80);
		g.drawString("Ballroom",225, 100);
		g.drawString("Conservatory", 390, 70);
		g.drawString("Dining Room", 50, 260);
		g.drawString("Billard Room", 400, 210);
		g.drawString("Library", 400, 330);
		g.drawString("Lounge", 50, 440);
		g.drawString("Hall", 240, 440);
		g.drawString("Study", 400, 460);
	}
}
