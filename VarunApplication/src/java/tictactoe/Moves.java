package tictactoe;

import java.util.ArrayList;
/**
 *
 * @author eulerion
 */
public class Moves {
    
    static int diagonalCorrection(int player, int opponent){
        int dc;
	int p = 0;
	
	for( int i=6; i<8; i++){
		
		dc = 0;
		
		for( int j=0; j<3; j++){
                    
			int x = Game.TRACKCELLS[i][j];
			
                        if( Game.BOARD[x/3][x%3] == player ){
				dc += Game.PLAYER;				// 3
				p = x;
                                
			}else if( Game.BOARD[x/3][x%3] == opponent ){
				dc += Game.OPPONENT;				// 1
                                
			}
		}
			
		if(dc == 5){                                                    //If DC is applicable
			if(p == 4){
				return 1;
			}else{
				
                            if(p == 0 || p == 2){
                                return 1;
                                
                            }else if(p == 6 || p == 8){
                                return 7;
                            }                              
				
			}
		}
			
	}
				
	
	return -1;
}	


    static int tentativeOffenseDefense(int symbol){
	
	int common = 0;
        int[] currentTrack = new int[3];
	boolean to;
	int track1, track2;
	
	ArrayList<Integer> ac = new ArrayList<Integer>();
	ac.clear();

	for( int i=0; i<8; i++){	
		
		boolean intersect = false;
		
		for( int j=0; j<8; j++){
			
			if(i == j) continue;
					
			track1 = track2 = 0;
			
			for( int k=0; k<3; k++){
                            for( int l=0; l<3; l++){
					
                                int a = Game.TRACKCELLS[i][k];
                                int b = Game.TRACKCELLS[j][l]; 
					
                                if( Game.BOARD[a/3][a%3] == symbol ){
                                    track1 += 1;
                                }						
                                
                                if( Game.BOARD[b/3][b%3] == symbol ){
                                    track2 += 1;					
                                }					
                                    
                                if(a == b){
                                    intersect = true;
                                    common = a;
                                }
                            }
			}
			
			if( intersect == true ){
                            
                            if( Game.BOARD[common/3][common%3] == symbol ){
                                continue;
                            }else if( track1 == 1 && track2 == 1 ){                                    
                                ac.add(common);					
                            }
                            
			}
			
		}
	}
	
	if( ac.isEmpty() ){
            return -1;
        }else{
         
            int max = -1;
            
            for(int i=0;i<ac.size();i++){
            
                if( max < ac.get(i) ){
                    max = ac.get(i);                
                }                   
                
            }
		
            return max;
	}
				
}


    static int definitiveOffenseDefense(int symbol){
	
	int d = -1, count;
	
	for( int i=0; i<8; i++){
		count = 0;
		
		for(int j=0;j<3;j++){
                    int x = Game.TRACKCELLS[i][j];
			
                    if( Game.BOARD[x/3][x%3] == symbol ){
                        count++;
                                
                    }else if( Game.BOARD[x/3][x%3] == 0 ){
                        count += Game.PLAYER;
                        d = x;
                    }
				
                    if( count == 5 ){                                       //If Definitive is applicable
                        return d;
                    }                                        				
		}
	}
	
	return -1;
}


    static int motion(int symbol){
	
	int m = -1, t = -1, mp;
	int tp = 0;
	
	for( int i=0; i<8; i++){
		
            int count = 0;
		
            for( int j=0; j<3; j++){
                    int x = Game.TRACKCELLS[i][j];
                    if( Game.BOARD[x/3][x%3] == symbol ){
                        count++;
                    }else if( Game.BOARD[x/3][x%3] == 0 ){
                        count += Game.PLAYER;
                    }			
		}
		
		if( count == 7 ){                                               //If Motion is applicable
                    if(tp < Game.TRACKPRIORITY[i]){
                        tp = Game.TRACKPRIORITY[i];
                        t = i;
                    }
		}
	}
	
	if(t < 0){
            return -1;
        }
		
	mp = 0;
	for(int i=0;i<3;i++){
		int cell = Game.TRACKCELLS[t][i];

		if( Game.BOARD[cell/3][cell%3] != 0 ){
                    continue;
                }

		if( mp < Game.CELLPRIORITY[cell] ){
			mp = Game.CELLPRIORITY[cell];
			m = cell;
		}
	}
	
	return m;
}

    static int prioritizedSelection(){

	int ps = 0;
	int p = 0;
	
	for(int i=0;i<9;i++){
            if( Game.BOARD[i/3][i%3] == 0){
                if(p < Game.CELLPRIORITY[i]){
                    p = Game.CELLPRIORITY[i];
                    ps = i;
                }
            }
	}
	
	return ps;
    }
    
}
