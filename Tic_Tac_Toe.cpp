#include<iostream>
#include<cstdio>
#include<string>
#include<cmath>
#include<vector>
#include<algorithm>
#include<cctype>

using namespace std;

#define SIZE 		3
#define DEFINITIVE 	5
#define MOTION 		7
#define DC		5
#define PLAYER		3
#define OPPONENT	1

/**********Global Variables*********/
int board[SIZE][SIZE] = {0};
int cellPriority[9] = {3, 2, 3, 2, 4, 2, 3, 2, 3};
int trackPriority[8] = {8, 8, 8, 8, 8, 8, 10, 10};
	
int trackCells[8][SIZE] = {
			{0, 1, 2},
			{3, 4, 5},
			{6, 7, 8},
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			{0, 4, 8},
			{2, 4, 6}
			};
/**********************************/						

int diagonalCorrection(int player, int opponent){
	//printf("DC\n");
	int dc = 0;
	int p;
	
	for(int i=6;i<8;i++){
		
		dc = 0;
		
		for(int j=0;j<3;j++){
			int x = trackCells[i][j];
			if(board[x/3][x%3] == player){
				dc += PLAYER;				// 3
				p = x;
			}else if(board[x/3][x%3] == opponent){
				dc += OPPONENT;				// 1
			}
		}
			
		if(dc == DC){
			if(p == 4){
				return 1;
			}else{
				
			if(p == 0 || p == 2)
				return 1;
			else if(p == 6 || p == 8)
				return 7;
				
			}
		}
			
	}
				
	
	return -1;
}	


int tentativeOffenseDefense(int symbol){
	//printf("TOD\n");
	int common, currentTrack[3];
	bool to;
	int track1, track2;
	
	vector<int> vc;
	vc.clear();
	
	for(int i=0;i<8;i++){	
		
		bool intersect = false;
		
		for(int j=0;j<8;j++){
			
			if(i == j) continue;
					
			track1 = track2 = 0;
			
			for(int k=0;k<3;k++){
				for(int l=0;l<3;l++){
					
					int a = trackCells[i][k];
					int b = trackCells[j][l]; 
					if(board[a/3][a%3] == symbol)
						track1 += 1;
					if(board[b/3][b%3] == symbol)
						track2 += 1;
					
					if(a == b){
						intersect = true;
						common = a;
					}
					
				}
			}
			
			if(intersect){
				if(board[common/3][common%3] == symbol){
					continue;
				}else if(track1 == 1 && track2 == 1){
					vc.push_back(common);
				}
	
			}
			
		}
	}
	
	if(vc.empty())
		return -1;
	else{
		int max = -1;
		for(int i=0;i<vc.size();i++){
			if(max < vc[i])
				max = vc[i];
		}
		
		return max;
	}
				
}



int definitiveOffenseDefense(int symbol){
	//printf("DOD\n");
	int d = -1, count;
	
	for(int i=0;i<8;i++){
		count = 0;
		
		for(int j=0;j<3;j++){
			int x = trackCells[i][j];
			
			if(board[x/3][x%3] == symbol){
				count++;	
			}else if(board[x/3][x%3] == 0){
				count += PLAYER;
				d = x;
			}
				
			if(count == DEFINITIVE)
				return d;
		}
	}
	
	return -1;
}


int motion(int symbol){
	//printf("M\n");
	int m = -1, t = -1, mp;
	int tp = 0;
	
	for(int i=0;i<8;i++){
		
		int count = 0;
		
		for(int j=0;j<3;j++){
			int x = trackCells[i][j];
			if(board[x/3][x%3] == symbol)
				count++;
			else if(board[x/3][x%3] == 0)
				count += PLAYER;
		}
		
		if(count == MOTION){
			if(tp < trackPriority[i]){
				tp = trackPriority[i];
				t = i;
			}
		}
	}
	
	if(t < 0)
		return -1;
		
	mp = 0;
	for(int i=0;i<3;i++){
		int cell = trackCells[t][i];

		if(board[cell/3][cell%3] != 0)
			continue;

		if(mp < cellPriority[cell]){
			mp = cellPriority[cell];
			m = cell;
		}
	}
	
	return m;
}


int prioritizedSelection(){
	//printf("P\n");
	int ps = 0;
	int p = 0;
	
	for(int i=0;i<9;i++){
		if(board[i/3][i%3] == 0){
			if(p < cellPriority[i]){
				p = cellPriority[i];
				ps = i;
			}
		}
	}
	
	return ps;
}
	

int prompt(){
	int x;
	
	while(true){
		printf("Please input the cell number to fill: ");
		scanf("%d", &x);
		
		if(board[x/3][x%3] == 0)
			break;
		else if(x > 8 || x < 0)
			printf("Incorrect input.\n");
		else
			printf("Please try again!\n");
	}
	
	return x;
}


void display(){

	system("clear");
	
	printf("\n");
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			if(board[i][j] == 1){
				printf("X ");
			}else if(board[i][j] == 2){
				printf("O ");
			}else{
				printf("_ ");
			}
		}
		printf("\n\n");
	}
	printf("\n");
}


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
	
	while(cellsFilled < 9){
		
		display();
		
		if( won(player) ){
			printf("Sorry! You lose...\n\n");
			return 0;
		}else if( won(opponent) ){
			printf("You Win!!\n\n");
			return 0;
		}
		
		o_move = prompt();
		board[o_move/3][o_move%3] = opponent;
		
		cellsFilled++;
		
		
		if( (p_move = definitiveOffenseDefense(player)) >= 0 ){
			board[p_move/3][p_move%3] = player;
			
		}else if( (p_move = definitiveOffenseDefense(opponent)) >= 0 ){
			board[p_move/3][p_move%3] = player;
		}else if( (p_move = diagonalCorrection(player, opponent)) >= 0 && cellsFilled == 3 ){
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

	}
	
	display();
	
	printf("It's a Draw!\n");		
	return 0;
}
