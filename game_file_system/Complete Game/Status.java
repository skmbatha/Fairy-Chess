
public class Status 
{
  //CASTLING VARIABLES :1:moved
  public static int wPlayerR1moved=0;
  public static int wPlayerR2moved=0;
  public static int wPlayerKmoved=0;
  public static int bPlayerR1moved=0;
  public static int bPlayerR2moved=0;
  public static int bPlayerKmoved=0;
  public static int busyCheck=0;
  public static boolean printExtrainfo=true;
	
  //CHECK VARIBLES 1:TRUE
  public static int wCheck=0;
  public static int bCheck=0;
	
  //CHECKMATE VARIBLES 1:TRUE
  public static int wCheckMate=0;
  public static int bCheckMate=0;
  
  //PLAYED INITIALLY:just to tell whether the pawn can move two steps or not to the  1:NOT YET PLAYED
  public static int firstMoveW=0;
  public static int firstMoveB=0;  
  public static char[] op=new char[4];
  
  //METHODS
  public static void updateCastlingOp()
  {
      op=FairyChess.castlingOp.toCharArray();
	  //updating position
	  if(FairyChess.Board[0][5]!='k')
		  Status.bPlayerKmoved=1;
	  if(FairyChess.Board[9][5]!='K')
		  Status.wPlayerKmoved=1;
	  
	  if(FairyChess.Board[0][0]!='r')
		  bPlayerR1moved=1;
	  if(FairyChess.Board[0][9]!='r')
		  bPlayerR2moved=1;
	  if(FairyChess.Board[9][0]!='R')
		  wPlayerR1moved=1;
	  if(FairyChess.Board[9][9]!='R')
		  wPlayerR2moved=1;
	  
	  //BLACK
	  if(FairyChess.currentPlayer==-1 && bPlayerKmoved==0) 
		  if(bPlayerR1moved==1)
			  op[0]='-';
	  
	      if(bPlayerR2moved==1)
	    	  op[1]='-';
	      
	      if(bPlayerKmoved==1)
	      { 
	    	  op[0]='-';
	    	  op[1]='-';
	      }
	    
		  
	  //WHITE
		  if(FairyChess.currentPlayer==1 && wPlayerKmoved==0) 
			  if(wPlayerR1moved==1)
				  op[2]='-';
		      if(wPlayerR2moved==1)
			      op[3]='-';
		      
		  if(wPlayerKmoved==1)
			 { 
				  op[2]='-';
				  op[3]='-';
			 }
	      
	  //GENERATE STRING
	  FairyChess.castlingOp= new String(op);
  }
  
  public static boolean updateCheck()
  {
	  boolean result=MoveNotation.isCheck(false,"null","null");
	  if(result)
		  if(FairyChess.currentPlayer==-1)
			  bCheck=1;
		  else
			  wCheck=1;
	  else
		  if(FairyChess.currentPlayer==-1)
			  bCheck=0;
		  else
			  wCheck=0;
		  return result;
  }
  
  public static boolean updateCheckMate()
  {
	  boolean value=MoveNotation.isCheckMate(false,"null","null")&updateCheck();
	  if(value)
		  if(FairyChess.currentPlayer==-1)
			  bCheckMate=1;
		  else
			  wCheckMate=1;
	  else
		  if(FairyChess.currentPlayer==-1)
			  bCheckMate=0;
		  else
			  wCheckMate=0;
		  return value;
  }
  
	//System.out.println("halfMoveClock: "+halfMoveClock);
	//System.out.println("moveCounter: "+moveCounter);
  
}


