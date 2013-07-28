package tictactoe;

/**
 *
 * @author eule
 */
public class Game {
    static int PLAYER = 3;
    static int OPPONENT = 1;
    static int CELLSFILLED = 0;
    static int SIZE = 3;
    static int[][] BOARD = new int[3][3];	
    static int[] CELLPRIORITY = {3, 2, 3, 2, 4, 2, 3, 2, 3};
    static int[] TRACKPRIORITY = {8, 8, 8, 8, 8, 8, 10, 10};
    static int[][] TRACKCELLS = {
			{0, 1, 2},
			{3, 4, 5},
			{6, 7, 8},
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			{0, 4, 8},
			{2, 4, 6}
			};
    
    
}
