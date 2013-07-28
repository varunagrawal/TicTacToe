package tictactoe;

import java.io.*;
import java.util.*;

/**
 *
 * @author eule
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("This is my TicTacToe game");
        
        TicTacToe game = new TicTacToe();        
        game.play();
        
    }
   
    
    void play(){
        
        int aiPlayer = 2, opponent = 1;
        Game.CELLSFILLED = 0;
	int o_move;
                
	while( Game.CELLSFILLED < 9){
            
            display();
				
            if( won( aiPlayer ) ){
                System.out.println("Sorry! You lose...\n");
                return;
            }else if( won( opponent ) ){
		System.out.println("You Win!!\n");
		return;
            }
		
            o_move = prompt();
            Game.BOARD[o_move/3][o_move%3] = opponent;
            Game.CELLSFILLED += 1;
	
            Strategy s = new Strategy();
            s.use( aiPlayer, opponent);
            Game.CELLSFILLED += 1;

        }
	
        display();
	
        System.out.println("It's a Draw!");		
    
    }
    
    
    int prompt(){
	int x;
	Scanner sc = new Scanner( new BufferedReader(new InputStreamReader( System.in ) ) );
        
	while(true){
		System.out.print("Please input the cell number to fill: ");
		x = sc.nextInt();
		x -= 1;
                
                if(x > 8 || x < 0){
                    System.out.println("Incorrect input.");
                    continue;
                }
                
		if( Game.BOARD[x/3][x%3] == 0 ){
                    break;
                }else{
                    System.out.println("Please try again!");
                }
	}
	
	return x;
}


    void display(){	
	
	System.out.println();
        
	for( int i=0; i<3; i++){
		for( int j=0; j<3; j++){
			if( Game.BOARD[i][j] == 1 ){
				System.out.print("X ");
			}else if( Game.BOARD[i][j] == 2){
				System.out.print("O ");
			}else{
				System.out.print("_ ");
			}
		}
		System.out.println("\n");
	}
        
	System.out.println();
}


    boolean won(int symbol){
    int win;
	
    for(int i=0;i<8;i++){
	win = 0;
		
	for( int j=0; j<3; j++){
            int x = Game.TRACKCELLS[i][j];
			
            if( Game.BOARD[x/3][x%3] == symbol ){
		win += 1;
            }			
        }
		
        if( win == 3 ){
            //System.out.println(i);
            return true;
        }
    }
	
    return false;
}


}
