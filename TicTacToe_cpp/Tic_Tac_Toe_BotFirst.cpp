/*
Tic Tac Toe Playing Bot
Techniques based on a paper.
Input is 'x y' where 1 <= input <= 3

Here the bot plays first.

Author: Varun Agrawal
*/

#include "Tic_Tac_Toe.hpp"

int won(int symbol){
	int win = 0;
	
	for(int i=0;i<8;i++){
		win = 0;
		
		for(int j=0;j<3;j++){
			int x = trackCells[i][j];
			
			if(board[x/3][x%3] == symbol){
				win += 1;
			}
			
		}
		
		if(win == 3){
			printf("%d\n", i);
			return true;
		}
	}
	
	return false;
}


int main()
{
	int player, opponent;
	
	player = 2;
	opponent = 1;
	
	int cellsFilled = 0;
	int o_move, p_move;
	
	display();
	
	while(cellsFilled < 9){
		
		if( won(player) ){
			printf("Sorry! You lose...\n\n");
			return 0;
		}else if( won(opponent) ){
			printf("You Win!!\n\n");
			return 0;
		}
		
		
		if( (p_move = definitiveOffenseDefense(player)) >= 0 ){
			board[p_move/3][p_move%3] = player;
			
		}else if( (p_move = definitiveOffenseDefense(opponent)) >= 0 ){
			board[p_move/3][p_move%3] = player;
		}else if( cellsFilled == 3 && (p_move = diagonalCorrection(player, opponent)) >= 0 ){
			board[p_move/3][p_move%3] = player;
		
		}else if( (p_move = tentativeOffenseDefense(player)) >= 0 ){
			board[p_move/3][p_move%3] = player;
		
		}else if( (p_move = tentativeOffenseDefense(opponent)) >= 0 ){
			board[p_move/3][p_move%3] = player;
		
		}else if( (p_move = motion(player)) >= 0 ){
			board[p_move/3][p_move%3] = player;
		
		}else{
			p_move = prioritizedSelection();
			board[p_move/3][p_move%3] = player;
		}		
		
		cellsFilled++;

		display();
		printf("%d\n", cellsFilled);		
		
		if(cellsFilled >= 9)
			break;
		
		vector<int> coord = prompt();
		o_move = coord[0]*3 + coord[1];	
				
		board[o_move/3][o_move%3] = opponent;
		
		cellsFilled++;
		
		
	}
	
	display();
	
	printf("It's a Draw!\n");		
	return 0;
}
