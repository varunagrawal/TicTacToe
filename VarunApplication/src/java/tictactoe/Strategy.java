package tictactoe;

/**
 *
 * @author eule
 */

public class Strategy {
    
    void use( int aiPlayer, int opponent ){
        
        int strategy = (int)(Math.random() * 5);
        //System.out.println(strategy);
        
        switch( strategy ){
            case 0:
                LA2_DC( aiPlayer, opponent );
                break;
            case 1:
                LA2( aiPlayer, opponent );
                break;
            case 2:
                LA1( aiPlayer, opponent );
                break;
            case 3:
                LA0_M( aiPlayer, opponent );
                break;
            case 4:
                LA0( aiPlayer, opponent );
                break;
        }
        
    }
    
    void LA2_DC( int aiPlayer, int opponent ){
        int p_move;
        
        if( (p_move = Moves.definitiveOffenseDefense(aiPlayer)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
			
            }else if( (p_move = Moves.definitiveOffenseDefense(opponent)) >= 0 ){                 
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
                        
            }else if( (p_move = Moves.diagonalCorrection(aiPlayer, opponent)) >= 0 && Game.CELLSFILLED == 3 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
            }else if( (p_move = Moves.tentativeOffenseDefense(aiPlayer)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
            }else if( (p_move = Moves.tentativeOffenseDefense(opponent)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
            }else if( (p_move = Moves.motion(aiPlayer)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
            }else{
		p_move = Moves.prioritizedSelection();
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
            }
    }
    
    void LA2( int aiPlayer, int opponent ){
        int p_move;
        
        if( (p_move = Moves.definitiveOffenseDefense(aiPlayer)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
			
            }else if( (p_move = Moves.definitiveOffenseDefense(opponent)) >= 0 ){                 
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
                        
            }else if( (p_move = Moves.tentativeOffenseDefense(aiPlayer)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
            }else if( (p_move = Moves.tentativeOffenseDefense(opponent)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
            }else if( (p_move = Moves.motion(aiPlayer)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
            }else{
		p_move = Moves.prioritizedSelection();
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
            }
    }
    
    void LA1( int aiPlayer, int opponent ){
        int p_move;
        
        if( (p_move = Moves.definitiveOffenseDefense(aiPlayer)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
			
            }else if( (p_move = Moves.definitiveOffenseDefense(opponent)) >= 0 ){                 
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
                        
            }else if( (p_move = Moves.motion(aiPlayer)) >= 0 ){
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
            }else{
		p_move = Moves.prioritizedSelection();
		Game.BOARD[p_move/3][p_move%3] = aiPlayer;
            }
    }
    
    void LA0_M( int aiPlayer, int opponent ){
        int p_move;
        
        if( (p_move = Moves.motion(aiPlayer)) >= 0 ){
            Game.BOARD[p_move/3][p_move%3] = aiPlayer;
		
        }else{
            p_move = Moves.prioritizedSelection();
            Game.BOARD[p_move/3][p_move%3] = aiPlayer;
        }
    }
    
    void LA0( int aiPlayer, int opponent ){
        int p_move;
      
	p_move = Moves.prioritizedSelection();
	Game.BOARD[p_move/3][p_move%3] = aiPlayer;
    }
    
}

