public class MovementRules
{

public static boolean ruleChoose(String pieceType,int[] current,int[] next)
{
	MovementRules MRlocal=new MovementRules();
    boolean result=false;
	switch((pieceType.toLowerCase()).charAt(0))
	{
	case 'n':result=MRlocal.kniteRule(current,next);
	         if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0) FairyChess.halfMoveClock++;
		break;
		
	case 'b':result=MRlocal.bishopRule(current,next);
			if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0)  FairyChess.halfMoveClock++;
		break;
		
	case 'q':result=MRlocal.queenRule(current,next);
			if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0)  FairyChess.halfMoveClock++;
		break;
		
	case 'p':result=MRlocal.pawnRule(current,next);    
		break;
		
	case 'd':result=MRlocal.drunkenPawnRule(current,next);
		break;
		
	case 'k':result=MRlocal.kingRule(current,next);
	         if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0)  FairyChess.halfMoveClock++;
		break;
		
	case 'r':result=MRlocal.rookRule(current,next);
			if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0)  FairyChess.halfMoveClock++;
			
		break;
		
	case 'f':result=MRlocal.flyingDragonRule(current,next);
			if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0)  FairyChess.halfMoveClock++;
		break;
		
	case 'e':result=MRlocal.elephantRule(current,next);
			if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0)  FairyChess.halfMoveClock++;
		break;
		
	case 'a':result=MRlocal.amazonRule(current,next);
			if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0)  FairyChess.halfMoveClock++;
		break;
		
	case 'w':result=MRlocal.princessRule(current,next);
			if(DecodeAndDoMove.notationType.compareTo("cp")!=0 && result) if(Status.busyCheck==0)  FairyChess.halfMoveClock++;
		break;
		
	default:
		//if(Status.busyCheck==0);
		//System.out.println("defaulted");
	}
return result;
}

public boolean kniteRule(int[] current,int[] next)

{
	int value=Math.abs(current[0]-next[0])+Math.abs(current[1]-next[1]);
	if(value!=3)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You can only move 1 block horizontally and 2 vertically or visa versa,\nyour move was incorrect!");
		return false;
	}
	
	if(Math.abs(current[0]-next[0])>2)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You cannot jump more than two steps vertically!");
		return false;		
	}
	
	if(Math.abs(current[1]-next[1])>2)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You cannot jump more than two steps horizontally!");
		return false;		
	}
	
	return true;
}

public boolean bishopRule(int[] current,int[] next)
{
	int deltaX=next[1]-current[1];
	int deltaY=next[0]-current[0];
	
	if(FairyChess.Board[next[0]][next[1]]!='.')//not really supposed to be here
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:There is your friendly piece where you placed the bishop!");
		return false;		
	}
	
	
	if(Math.abs(deltaX)!=Math.abs(deltaY))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You can only move diagonally,you move was incorrect!");
		return false;		
		
	}
	
	int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
	int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
	
	for(int i=1;i<Math.abs(deltaX);i++)
	{
		if(FairyChess.Board[next[0]-i*normY][next[1]-i*normX]!='.')
		{
			if(Status.busyCheck==0)
			if(Status.printExtrainfo)
			System.out.print("Error:You cannot skip any piece in your diagonal move!!");
			return false;			
		}
	}
		
	return true;
}

public boolean queenRule(int[] current,int[] next)
{
	int deltaX=next[1]-current[1];
	int deltaY=next[0]-current[0];
	
	
	if(Math.abs(deltaX)>0 && Math.abs(deltaY)==0)//Allow only if movement was made in the horizontally only
	{
		int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
		for(int i=1;i<Math.abs(deltaX);i++)
		{
			if(FairyChess.Board[next[0]][next[1]-i*normX]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your horizontal move!!");
				return false;			
			}
		}
		return true;	
	}

	if(Math.abs(deltaY)>0 && Math.abs(deltaX)==0)//Allow only if movement was made in the vertically only 
	{
		int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
		for(int i=1;i<Math.abs(deltaY);i++)
		{
			if(FairyChess.Board[next[0]-i*normY][next[1]]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your vertical move!!");
				return false;			
			}
		}
		return true;
	}
	
	if(Math.abs(deltaX)!=Math.abs(deltaY))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You can only move diagonally in a staight line,your move was incorrect!");
		return false;		
		
	}
	
	int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
	int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
	
	for(int i=1;i<Math.abs(deltaX);i++)
	{
		if(FairyChess.Board[next[0]-i*normY][next[1]-i*normX]!='.')
		{
			if(Status.busyCheck==0)
			if(Status.printExtrainfo)
			System.out.print("Error:You cannot skip any piece in your diagonal move!!");
			return false;			
		}
	}
	return true;
}

public boolean pawnRule(int[] current,int[] next)
{
	//int[] tempPos=FairyChess.searchPos(current);
	boolean cond=(FairyChess.piece[current[0]][current[1]]).getfirstTime();
	
	if(Status.busyCheck==1 && Math.abs(next[0]-current[0])==1 && Math.abs(next[1]-current[1])==1)
	{
		return true;
	}
	
	if(((next[0]-current[0]==-FairyChess.currentPlayer) || (next[0]-current[0]==-FairyChess.currentPlayer)) && Status.busyCheck==1)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move backwards!");
		return false;
	}
	
	if((next[0]-current[0]==FairyChess.currentPlayer) || (next[0]-current[0]==FairyChess.currentPlayer))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move backwards!");
		return false;
	}
	
	if(Math.abs(next[0]-current[0])>1 && Math.abs(next[1]-current[1])>1 && (DecodeAndDoMove.notationType.compareTo("mv")==0 || Status.busyCheck==1))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot capture like that:Cannot move more than two blocks diagonally when captureing!!");
		return false;		
	}
	
	
	if(Math.abs(next[0]-current[0])>1 && !cond && (DecodeAndDoMove.notationType.compareTo("mv")==0 || Status.busyCheck==1))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move more than one block foward!");
		return false;
	}

	
	if(Math.abs(next[0]-current[0])>2 && cond && (DecodeAndDoMove.notationType.compareTo("mv")==0 || Status.busyCheck==1))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move more than two blocks foward!");
		return false;
	}
	
	if(Math.abs(next[0]-current[0])==2 && cond && (DecodeAndDoMove.notationType.compareTo("mv")==0 || Status.busyCheck==1))
	{
		if(Status.busyCheck==0)
		{
		
			if((FairyChess.currentPlayer==-1 && FairyChess.Board[current[0]+1][current[1]]!='.') || (FairyChess.currentPlayer==1 && FairyChess.Board[current[0]-1][current[1]]!='.'))//boudry check isnot implemented...BE CAREFUL!!!
			{
				if(Status.printExtrainfo)
					System.out.print("Error:Cannot jump over other blocks!/n");
				return false;
			}
		}
	}
	
	
	if(FairyChess.Board[next[0]][next[1]]!='.' && Status.busyCheck!=1 && DecodeAndDoMove.notationType.compareTo("pr")!=0)
	{
		if(DecodeAndDoMove.notationType.compareTo("cp")!=0)
		if(Status.busyCheck==0){
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move over any other piece!");
		return false;}
	}
	
	if(current[1]!=next[1] && current[0]!=next[0] && DecodeAndDoMove.notationType.compareTo("cp")!=0 && DecodeAndDoMove.notationType.compareTo("pr")!=0)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move diagonally!");
		return false;			
	}
	
	if(current[1]!=next[1] && DecodeAndDoMove.notationType.compareTo("cp")!=0 && DecodeAndDoMove.notationType.compareTo("pr")!=0)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move side ways!");
		return false;			
	}
	
	FairyChess.piece[current[0]][current[1]].setfirstTime(false);
	return true;
}

public boolean drunkenPawnRule(int[] current,int[] next)
{
	if((current[0]==0 && FairyChess.currentPlayer==1 || current[0]==9 && FairyChess.currentPlayer==-1) && DecodeAndDoMove.notationType.compareTo("pr")==0)
	{
		//am assuming when you are here you can go to any position from here and also become any officer provided their existence !=0
	}
	
	else
	{
		
	if(Status.busyCheck==1 || DecodeAndDoMove.notationType.compareTo("cp")==0)
	{
		if(Math.abs(next[1]-current[1])==1 && Math.abs(next[0]-current[0])==0)
			return false;
	}
		
	if(Status.busyCheck==1 && Math.abs(next[0]-current[0])==1 && Math.abs(next[1]-current[1])==1)
	{
		return true;
	}
	
	if(((next[0]-current[0]==-FairyChess.currentPlayer) || (next[0]-current[0]==-FairyChess.currentPlayer)) && Status.busyCheck==1)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move backwards!");
		return false;
	}
		
	if((next[0]-current[0]==FairyChess.currentPlayer) || (next[0]-current[0]==FairyChess.currentPlayer))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move backwards!");
		return false;
	}
	
	
	
	if(Math.abs(next[0]-current[0])>1 && (DecodeAndDoMove.notationType.compareTo("mv")==0 || Status.busyCheck==1))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move more than one block foward!");
		return false;
	}
	
	
	if(Math.abs(next[1]-current[1])>1 && (DecodeAndDoMove.notationType.compareTo("mv")==0 || Status.busyCheck==1))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move more than one block side ways!");
		return false;
	}
	
	if(FairyChess.Board[next[0]][next[1]]!='.' && Status.busyCheck!=1)
	{
		if(DecodeAndDoMove.notationType.compareTo("cp")!=0)
		if(Status.busyCheck==0){
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move over any other piece!");
		return false;}
	}
	
	if(current[1]!=next[1] && current[0]!=next[0] && (DecodeAndDoMove.notationType.compareTo("mv")==0 || Status.busyCheck==1))
	{
		if(Status.busyCheck==0){
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move diagonally!");
		return false;}
	}
	
	}
	
	return true;
}

public boolean kingRule(int[] current,int[] next)
{
	
	int deltaX=next[1]-current[1];
	int deltaY=next[0]-current[0];
	
	if(Math.abs(deltaX)>1 || Math.abs(deltaY)>1)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:Cannot move the KING  more than one square in any direction!");
		return false;		
	}
	
	//Placing the king in check

	return true;
}

public boolean rookRule(int[] current,int[] next)
{
	int deltaX=next[1]-current[1];
	int deltaY=next[0]-current[0];
	
	
	if(Math.abs(deltaX)>0 && Math.abs(deltaY)==0)//Allow only if movement was made in the horizontally only
	{
		int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
		for(int i=1;i<Math.abs(deltaX);i++)
		{
			if(FairyChess.Board[next[0]][next[1]-i*normX]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your horizontal move!!");
				return false;			
			}
		}
		
		return true;
		
	}

	if(Math.abs(deltaY)>0 && Math.abs(deltaX)==0)//Allow only if movement was made in the vertically only 
	{
		int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
		for(int i=1;i<Math.abs(deltaY);i++)
		{
			if(FairyChess.Board[next[0]-i*normY][next[1]]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your vertical move!!");
				return false;			
			}
		}
		
		return true;
	}
	
	if(Math.abs(deltaY)>0 && Math.abs(deltaX)>0)//Allow only if movement was made in the vertically only 
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You cannot Move diagonaly,only move vertically or horizontally!");
		return false;			

	}

	return true;
}

public boolean flyingDragonRule(int[] current,int[] next)
{
	int deltaX=next[1]-current[1];
	int deltaY=next[0]-current[0];
	
	
	if(Math.abs(deltaX)!=Math.abs(deltaY))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You can only move diagonally,you move was incorrect!");
		return false;		
		
	}
	
	int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
	int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
	
	for(int i=1;i<Math.abs(deltaX);i++)
	{
		if(FairyChess.Board[next[0]-i*normY][next[1]-i*normX]!='.')
		{
			if(Status.busyCheck==0)
			if(Status.printExtrainfo)
			System.out.print("Error:You cannot skip any piece in your diagonal move!!");
			return false;			
		}
	}
	
	if(Math.abs(deltaX)>2 || Math.abs(deltaY)>2)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You can only move 2 blocks per move(limited)!!");
		return false;		
	}
		
	return true;
}

public boolean elephantRule(int[] current,int[] next)
{
	int deltaX=next[1]-current[1];
	int deltaY=next[0]-current[0];
	
	if(next[0]<5 && FairyChess.currentPlayer==1 || next[0]>5 && FairyChess.currentPlayer==-11)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("You cannot move accross the half board border!!!");
		return false;		
	}
	
	
	if(Math.abs(deltaX)>0 && Math.abs(deltaY)==0)//Allow only if movement was made in the horizontally only
	{
		int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
		for(int i=1;i<Math.abs(deltaX);i++)
		{
			if(FairyChess.Board[next[0]][next[1]-i*normX]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your horizontal move!!");
				return false;			
			}
		}
		return true;	
	}

	if(Math.abs(deltaY)>0 && Math.abs(deltaX)==0)//Allow only if movement was made in the vertically only 
	{
		int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
		for(int i=1;i<Math.abs(deltaY);i++)
		{
			if(FairyChess.Board[next[0]-i*normY][next[1]]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your vertical move!!");
				return false;			
			}
		}
		return true;
	}
	
	if(Math.abs(deltaY)>0 && Math.abs(deltaX)>0)//Allow only if movement was made in the vertically only 
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You cannot Move diagonaly,only move vertically or horizontally!");
		return false;			

		}
	return true;
}

public boolean amazonRule(int[] current,int[] next)
{
	int value=Math.abs(current[0]-next[0])+Math.abs(current[1]-next[1]);
	if(value==3)
	{
		if(Math.abs(current[0]-next[0])>2)
		{
			if(Status.busyCheck==0)
			if(Status.printExtrainfo)
			System.out.print("Error:You cannot jump more than two steps vertically!");
			return false;		
		}
		
		if(Math.abs(current[1]-next[1])>2)
		{
			if(Status.busyCheck==0)
			if(Status.printExtrainfo)
			System.out.print("Error:You cannot jump more than two steps horizontally!");
			return false;		
		}
		return true;
	}
		
	
	int deltaX=next[1]-current[1];
	int deltaY=next[0]-current[0];
	
	
	if(Math.abs(deltaX)>0 && Math.abs(deltaY)==0)//Allow only if movement was made in the horizontally only
	{
		int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
		for(int i=1;i<Math.abs(deltaX);i++)
		{
			if(FairyChess.Board[next[0]][next[1]-i*normX]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your horizontal move!!");
				return false;			
			}
		}
		return true;	
	}

	if(Math.abs(deltaY)>0 && Math.abs(deltaX)==0)//Allow only if movement was made in the vertically only 
	{
		int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
		for(int i=1;i<Math.abs(deltaY);i++)
		{
			if(FairyChess.Board[next[0]-i*normY][next[1]]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your vertical move!!");
				return false;			
			}
		}
		return true;
	}
	
	if(Math.abs(deltaX)!=Math.abs(deltaY))
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You can also move diagonally in a staight line,your move was incorrect!");
		return false;		
		
	}
	
	int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
	int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
	
	for(int i=1;i<Math.abs(deltaX);i++)
	{
		if(FairyChess.Board[next[0]-i*normY][next[1]-i*normX]!='.')
		{
			if(Status.busyCheck==0)
			if(Status.printExtrainfo)
			System.out.print("Error:You cannot skip any piece in your diagonal move!!");
			return false;			
		}
	}
	return true;
}

public boolean princessRule(int[] current,int[] next)
{
	int deltaX=next[1]-current[1];
	int deltaY=next[0]-current[0];
	
	
	if(Math.abs(deltaX)==Math.abs(deltaY))
	{
		int normX=(next[1]-current[1])/(Math.abs(next[1]-current[1]));
		int normY=(next[0]-current[0])/(Math.abs(next[0]-current[0]));
		
		for(int i=1;i<Math.abs(deltaX);i++)
		{
			if(FairyChess.Board[next[0]-i*normY][next[1]-i*normX]!='.')
			{
				if(Status.busyCheck==0)
				if(Status.printExtrainfo)
				System.out.print("Error:You cannot skip any piece in your diagonal move!!");
				return false;			
			}
		}		
		return true;
	}
	
	int value=Math.abs(current[0]-next[0])+Math.abs(current[1]-next[1]);
	if(value!=3)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You can only move 1 block horizontally and 2 vertically or visa versa,\nyour move was incorrect!");
		return false;
	}
	
	if(Math.abs(current[0]-next[0])>2)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You cannot jump more than two steps vertically!");
		return false;		
	}
	
	if(Math.abs(current[1]-next[1])>2)
	{
		if(Status.busyCheck==0)
		if(Status.printExtrainfo)
		System.out.print("Error:You cannot jump more than two steps horizontally!");
		return false;		
	}
	
	
	return true;
}

}