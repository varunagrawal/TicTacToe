/*
Tic Tac Toe Program.
Uses techniques discussed in a paper.
Input is 'x y' where 1 <= input <= 3

Author: Varun Agrawal
*/


#include "Tic_Tac_Toe.hpp"


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
		
		
		vector<int> coord = prompt();
		o_move = coord[0]*3 + coord[1];	
				
		board[o_move/3][o_move%3] = opponent;
		
		cellsFilled++;
		
		
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
		
		
	}
	
	display();
	
	printf("It's a Draw!\n");		
	return 0;
}
