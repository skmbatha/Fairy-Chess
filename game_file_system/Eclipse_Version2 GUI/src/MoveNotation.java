public class MoveNotation
{
public static char promotion='.';
public static String activity="null";

//========================================================MOVEMENT============================================================== done
public boolean movement(String current,String next)
{
int pos[]=Piece.positionDecode(current);
int pos2[]=Piece.positionDecode(next);

//Check for legality of Move!
boolean moveLegality=false;


//Check for movement illegalizm
//1.Source is not occupied by our color
boolean cond1=Character.isLowerCase(FairyChess.Board[pos[0]][pos[1]]) && FairyChess.currentPlayer==1;
boolean cond2=Character.isUpperCase(FairyChess.Board[pos[0]][pos[1]]) && FairyChess.currentPlayer==-1;
//2.Destination occupied by friendly piece
boolean cond3=Character.isLowerCase(FairyChess.Board[pos2[0]][pos2[1]]) && FairyChess.currentPlayer==-1;
boolean cond4=Character.isUpperCase(FairyChess.Board[pos2[0]][pos2[1]]) && FairyChess.currentPlayer==1;
//3.done in movement
//4.if current player is in check and the next move doesnt't remove him from check
boolean cond5=FairyChess.currentPlayer==-1 && isCheck(false,"null","null") && isCheck(false,current,next);
boolean cond6=FairyChess.currentPlayer==1 && isCheck(false,"null","null") && isCheck(false,current,next);
//5.
boolean cond7=FairyChess.currentPlayer==-1 && Status.bCheckMate==1;
boolean cond8=FairyChess.currentPlayer==1 && Status.wCheckMate==1;
//6.
char a=FairyChess.Board[pos[0]][pos[1]];
boolean cond9=(FairyChess.halfMoveClock==50 && a!='p') || (FairyChess.halfMoveClock==50 && a!='d');	
//7.
boolean cond10=isCheck(false,current,next) || isCheckMate(false,current,next);

//resolve conclusion
boolean result=(cond1|cond2|cond3|cond4|cond5|cond6|cond7|cond8|cond9|cond10);
//8.
moveLegality=MovementRules.ruleChoose(Character.toString(FairyChess.Board[pos[0]][pos[1]]),pos,pos2);//apply pieceRules*/
//Implement
if(activity.compareTo("null")==0)
if((isCheckMate(true,current,next) || isCheck(true,current,next)) && DecodeAndDoMove.CCQ.compareTo("null")==0)
	return false;


if(activity.compareTo("busy")==0)
result=false;

if(activity.compareTo("null")==0)
if(result==false && moveLegality==true)
{
int[] curr=FairyChess.searchPos(current);
FairyChess.piece[curr[0]][curr[1]].setPos(next);

if(FairyChess.currentPlayer==-1)
FairyChess.moveCounter++;//increment if move has occured

FairyChess.currentPlayer*=-1;//toggle
return true;
}
else{
	return false;
}
else
	return moveLegality;
}
//========================================================CAPTURE=============================================================== done
public boolean capture(String current,String next,String command)
{
boolean cond1=false,cond2=false,cond3=false,cond4=false,cond5=false,cond6=false,cond7=false,cond8=false,cond9=false;

int pos[]=Piece.positionDecode(current);
int pos2[]=Piece.positionDecode(next);

//1.
//1.Source is not occupied by our color
cond1=Character.isLowerCase(FairyChess.Board[pos[0]][pos[1]]) && FairyChess.currentPlayer==1;
cond2=Character.isUpperCase(FairyChess.Board[pos[0]][pos[1]]) && FairyChess.currentPlayer==-1;
//2.
//2.Destination occupied by friendly piece
cond3=(Character.isLowerCase(FairyChess.Board[pos2[0]][pos2[1]])) && FairyChess.currentPlayer==-1;
cond4=(Character.isUpperCase(FairyChess.Board[pos2[0]][pos2[1]])) && FairyChess.currentPlayer==1;
//3.MovementRules
//4.
cond5=isCheck(false,"null","null") && isCheck(false,current,next);
//5.Fixed:check mate check function
cond6=FairyChess.currentPlayer==-1 &&  Status.bCheckMate==1;
cond8=FairyChess.currentPlayer==1 &&  Status.wCheckMate==1;
//6.
cond7=(isCheck(false,current,next)) || (isCheckMate(false,current,next));
//7.
cond9=FairyChess.Board[pos2[0]][pos2[1]]=='.';//check if the capture destination position is occupied or not!

boolean result=(cond9|cond8|cond7|cond6|cond5|cond4|cond3|cond2|cond1);
boolean ans=MovementRules.ruleChoose(Character.toString(FairyChess.Board[pos[0]][pos[1]]),pos,pos2);//apply pieceRules

//System.out.println(" "+cond1+" "+cond2+" "+cond3+" "+cond4+" "+cond5+" "+cond6+" "+cond7+" "+cond8+"\n");
//8.
if(activity.compareTo("null")==0)
if((isCheckMate(true,current,next) || isCheck(true,current,next)) && DecodeAndDoMove.CCQ.compareTo("null")==0)
	return false;


if(activity.compareTo("busy")==0)
result=false;

if(activity.compareTo("null")==0)
if(result==false && ans==true)
{
FairyChess.setPieceCapture(current,next);
if(FairyChess.currentPlayer==-1)
FairyChess.moveCounter++;//increment if move has occured
FairyChess.currentPlayer*=-1;//toggle
return true;
}else
{
	return false;
}
else
	return ans;

}

//====================================================== =PROMOTION============================================================= done
public boolean promotion(String current,String next,String command)
{
int pos[]=Piece.positionDecode(current);
int pos2[]=Piece.positionDecode(next);

String processType="mv";

char[] chrTemp=command.toCharArray();
if(chrTemp[current.length()]=='-')
processType="mv";
else if(chrTemp[current.length()]=='x')
	processType="cp";

promotion=chrTemp[command.length()-1];//promotion variable
//============================================START=================================

//1.
boolean cond1=(FairyChess.currentPlayer==-1 && pos2[0]!=9) || (FairyChess.currentPlayer==1 && pos2[0]!=0);

//2.  =========================================
boolean cond2=false;
switch(promotion)
{
case 'n':cond2=(FairyChess.nInit==0);
break;
case 'b':cond2=(FairyChess.bInit==0);
break;
case 'q':cond2=(FairyChess.qInit==0);
break;
case 'k':cond2=(FairyChess.kInit==0);
break;
case 'r':cond2=(FairyChess.rInit==0);
break;
case 'f':cond2=(FairyChess.fInit==0);
break;
case 'a':cond2=(FairyChess.aInit==0);
break;
case 'w':cond2=(FairyChess.wInit==0);
break;
}

//3.
boolean cond3=promotion=='e';

//5. & 6.
boolean cond4=isCheck(false,current,next) || isCheckMate(false,current,next);
//8.
boolean cond5=((isCheckMate(true,current,next) || isCheck(true,current,next))) && DecodeAndDoMove.CCQ.compareTo("null")==0;

//    =========================================
MoveNotation obj=new MoveNotation();

boolean result=(cond1|cond2|cond3|cond4|cond5);
//System.out.println(cond1+" "+cond2+" "+cond3+" "+cond4+" "+cond5);

boolean move=false;

if(processType.compareTo("mv")==0){
	DecodeAndDoMove.notationType="mv";
	activity="busy";
	move=obj.movement(current,next);
}

if(processType.compareTo("cp")==0){
	DecodeAndDoMove.notationType="cp";
	activity="busy";
	move=obj.capture(current,next,command);	
}

activity="null";

//System.out.println(result+"   "+move);
if(result==false && move==true)
{
	//int[] curr=FairyChess.searchPos(next);
	//FairyChess.piece[curr[0]][curr[1]].setName(Character.toString(promotion));	
	
	int[] curr=FairyChess.searchPos(current);
	FairyChess.piece[curr[0]][curr[1]].setPos(next);
	FairyChess.piece[curr[0]][curr[1]].setName(Character.toString(promotion));	
	
	if(FairyChess.currentPlayer==-1)
	FairyChess.moveCounter++;//increment if move has occured
	FairyChess.currentPlayer*=-1;//toggle
	return true;
}
else
return false;
}
//========================================================CASTLING============================================================== done
public boolean castling(String KingOrQueen,String current,String next,String command)
{
	//variable declaration---
	char[] chStrArr=FairyChess.castlingOp.toCharArray();
	
	//3.
	if(FairyChess.halfMoveClock==50)
		return false;
	
	//5. & 6.
	boolean cond4=isCheck(false,"castle",command) | isCheckMate(false,"castle",command);
	//System.out.println("Cstle:"+ isCheck(false,"castle",command));
	//8.
	if((isCheckMate(true,"castle",command) || isCheck(true,"castle",command)) && DecodeAndDoMove.CCQ.compareTo("null")==0)
		return false;
	
	if(cond4)
		return false;
	
	
	//1.2.
	//=================================================Game play for this========================================================
	//player White;Queen
	if(KingOrQueen.compareTo("queen")==0){
	if(Status.wPlayerKmoved==0 && FairyChess.currentPlayer==1 && Status.wPlayerR1moved==0)
	{
	   if(FairyChess.Board[9][1]=='.' && FairyChess.Board[9][2]=='.' && FairyChess.Board[9][3]=='.' && FairyChess.Board[9][4]=='.')
	   {
		 if(chStrArr[2]!='+') return false;//error
		 FairyChess.setPieceCapture("f1","b1");
		 FairyChess.setPieceCapture("a1","c1"); 
		 
			if(FairyChess.currentPlayer==-1)
			FairyChess.moveCounter++;//increment if move has occured
			FairyChess.currentPlayer*=-1;//toggle
	     FairyChess.halfMoveClock++;
		 return true;
	   }
	}
	
	//player Black;Queen

	if(Status.bPlayerKmoved==0 && FairyChess.currentPlayer==-1 && Status.bPlayerR1moved==0)
	{
	   if(FairyChess.Board[0][1]=='.' && FairyChess.Board[0][2]=='.' && FairyChess.Board[0][3]=='.' && FairyChess.Board[0][4]=='.')
	   {
		 if(chStrArr[0]!='+') return false;//error
		 FairyChess.setPieceCapture("f10","b10");
		 FairyChess.setPieceCapture("a10","c10"); 
		 
			if(FairyChess.currentPlayer==-1)
			FairyChess.moveCounter++;//increment if move has occured
			FairyChess.currentPlayer*=-1;//toggle
		 FairyChess.halfMoveClock++;
		 return true;
	   }
	}}
	
	//player White;king
	if(KingOrQueen.compareTo("king")==0){
	if(Status.wPlayerKmoved==0 && FairyChess.currentPlayer==1 && Status.wPlayerR2moved==0)
	{
	   if(FairyChess.Board[9][6]=='.' && FairyChess.Board[9][7]=='.' && FairyChess.Board[9][8]=='.')
	   {
		 if(chStrArr[3]!='+') return false;//error
		 FairyChess.setPieceCapture("f1","i1");
		 FairyChess.setPieceCapture("j1","h1"); 
		 
			if(FairyChess.currentPlayer==-1)
			FairyChess.moveCounter++;//increment if move has occured
			FairyChess.currentPlayer*=-1;//toggle
		 FairyChess.halfMoveClock++;
		 return true;
	   }
	}
	
	//player Black;king

	if(Status.bPlayerKmoved==0 && FairyChess.currentPlayer==-1 && Status.bPlayerR2moved==0)
	{
	   if(FairyChess.Board[0][6]=='.' && FairyChess.Board[0][7]=='.' && FairyChess.Board[0][8]=='.')
	   {
		 if(chStrArr[1]!='+') return false;//error
		 FairyChess.setPieceCapture("f10","i10");
		 FairyChess.setPieceCapture("j10","h10"); 
		 
			if(FairyChess.currentPlayer==-1)
			FairyChess.moveCounter++;//increment if move has occured
			FairyChess.currentPlayer*=-1;//toggle 
			
		 FairyChess.halfMoveClock++;
		 return true;
	   }
	}}	
	//=======================================================================================================================
	return false;
}
//========================================================CHECKFunc=============================================================
public static boolean isCheck(boolean Invert,String start,String end)//destination address
{
	//============================================INITIALIZATION=================================
	
	//import data and create temp data storage
	Status.busyCheck=1;
	char[][] TempBoard=new char[10][10];
	for(int i=0;i<10;i++)
		for(int j=0;j<10;j++)
			TempBoard[i][j]=FairyChess.Board[i][j];
	
	//castle kind of shuffle
	if(start.compareTo("castle")==0)
	{
		tempCastle(end,TempBoard);
	}
	else if((DecodeAndDoMove.notationType.compareTo("pr")==0)){
		//initilialize stuff
		int[] ST=Piece.positionDecode(start);
		int[] ED=Piece.positionDecode(end);
		
		// move piece increment
		TempBoard[ED[0]][ED[1]]=promotion;
		TempBoard[ST[0]][ST[1]]='.'; 
	}
	else if(start.compareTo("null")!=0){	
	//initilialize stuff
	int[] ST=Piece.positionDecode(start);
	int[] ED=Piece.positionDecode(end);
	
	// move piece increment
	TempBoard[ED[0]][ED[1]]=TempBoard[ST[0]][ST[1]];
	TempBoard[ST[0]][ST[1]]='.';   
	}
	
    //................................................
    if(Invert)
    FairyChess.currentPlayer*=-1;  
    //................................................
	
    //the king's position
    boolean result=false;
	int pos[]=new int[2];
	
	if(FairyChess.currentPlayer==-1)
	pos=getKingPosition(TempBoard,'k');
	else if (FairyChess.currentPlayer==1)
	pos=getKingPosition(TempBoard,'K');
	//................................................
	
	
	int test[]=new int[2];
	
	for(int j=0;j<10;j++){
		for(int k=0;k<10;k++)
		{
			test[0]=j;
			test[1]=k;
			
			if(test[0]==pos[0] && test[1]==pos[1])
				continue;
			
			if(TempBoard[j][k]=='.')
				continue;
			
			if(Character.isUpperCase(TempBoard[j][k]) && FairyChess.currentPlayer==1)
				continue;
			
			if(Character.isLowerCase(TempBoard[j][k]) && FairyChess.currentPlayer==-1)
				continue;
			
			result=MovementRules.ruleChoose(Character.toString(TempBoard[j][k]),test,pos);

			if(result)
			{
				//if(Status.printExtrainfo)
			    //System.out.println("{"+j+"}{"+k+"}");
			    //................................................
			    if(Invert==true)
			    FairyChess.currentPlayer*=-1;
			    //................................................
			    Status.busyCheck=0;
			return true;
			}
		}}
    //................................................
    if(Invert==true)
    FairyChess.currentPlayer*=-1;
    //................................................
	Status.busyCheck=0;
	return false;
}
public static boolean isCheck(boolean Invert,int[] ST,int[] ED,char[][] TempBoard)//destination address
{
	//============================================INITIALIZATION=================================
	
	//import data and create temp data storage
	Status.busyCheck=1;

    boolean result=false;
	// move piece increment
	TempBoard[ED[0]][ED[1]]=TempBoard[ST[0]][ST[1]];
	TempBoard[ST[0]][ST[1]]='.';   
    //................................................
    if(Invert)
    FairyChess.currentPlayer*=-1;  
    //................................................

	//................................................
	
	
	int test[]=new int[2];
	
	for(int j=0;j<10;j++)
		for(int k=0;k<10;k++)
		{
			test[0]=j;
			test[1]=k;
			
			if(test[0]==ED[0] && test[1]==ED[1])
				continue;
			
			if(TempBoard[j][k]=='.')
				continue;
			
			if(Character.isUpperCase(TempBoard[j][k]) && FairyChess.currentPlayer==1)
				continue;
			
			if(Character.isLowerCase(TempBoard[j][k]) && FairyChess.currentPlayer==-1)
				continue;
			

			result=MovementRules.ruleChoose(Character.toString(TempBoard[j][k]),test,ED);

			if(result)
			{
			//if(Status.printExtrainfo)
			//System.out.println("{"+j+"}{"+k+"}");
			
		    //................................................
		    if(Invert==true)
		    FairyChess.currentPlayer*=-1;
		    //................................................
		    Status.busyCheck=0;
			return true;
			}
		}
    //................................................
    if(Invert==true)
    FairyChess.currentPlayer*=-1;
    //................................................
	Status.busyCheck=0;
	return false;
}

public static boolean isCheckMate(boolean Invert,String start,String end)
{
    //................................................
    boolean result=false,c1=false,c2=false,c3=false,c4=false,c5=false,c6=false,c7=false,c8=false;
	int pos[]=new int[2];
	int temp[]=new int[2];
	
	char[][] TempBoard=new char[10][10];
	for(int i=0;i<10;i++)
		for(int j=0;j<10;j++)
			TempBoard[i][j]=FairyChess.Board[i][j];
	
	//castle kind of shuffle
	if(start.compareTo("castle")==0)
	{
		tempCastle(end,TempBoard);
	}
	else if((DecodeAndDoMove.notationType.compareTo("pr")==0)){
		//initilialize stuff
		int[] ST=Piece.positionDecode(start);
		int[] ED=Piece.positionDecode(end);
		
		// move piece increment
		TempBoard[ED[0]][ED[1]]=promotion;
		TempBoard[ST[0]][ST[1]]='.'; 
	}
	else if(start.compareTo("null")!=0){
	//initilialize stuff
	int[] ST=Piece.positionDecode(start);
	int[] ED=Piece.positionDecode(end);
	
	// move piece increment
	TempBoard[ED[0]][ED[1]]=TempBoard[ST[0]][ST[1]];
	TempBoard[ST[0]][ST[1]]='.';   }
	
	
	if(Invert==false){
		if(FairyChess.currentPlayer==-1)
			pos=getKingPosition(TempBoard,'k');
			else if (FairyChess.currentPlayer==1)
				pos=getKingPosition(TempBoard,'K');}
	else
	{
		if(FairyChess.currentPlayer==-1)
			pos=getKingPosition(TempBoard,'K');
			else if (FairyChess.currentPlayer==1)
			pos=getKingPosition(TempBoard,'k');	
	}
	//................................................
	int status=0;
	int status2=0;
	
	//................................................
	for(int i=1;i<9;i++)
	{
	switch(i)
	{
	case 1:temp[0]=pos[0]-1;
		   temp[1]=pos[1]-1;
		   
	   if((temp[0]<=9 && temp[0]>=0) && (temp[1]<=9 && temp[1]>=0))
	   {
		   if(TempBoard[temp[0]][temp[1]]=='.')
		   {status++;
		   result=isCheck(Invert,pos,temp,TempBoard);
		   if(result==true){status2++;
			   //if(Status.printExtrainfo)
			   //System.out.println("top left");
			   c1=result;}
		   }
	   }
		break;
		//================================================================
		
	case 2:temp[0]=pos[0];
	   	   temp[1]=pos[1]-1;
	   
		   if((temp[0]<=9 && temp[0]>=0) && (temp[1]<=9 && temp[1]>=0))
		   {
			   if(TempBoard[temp[0]][temp[1]]=='.')
			   {status++;
			   result=isCheck(Invert,pos,temp,TempBoard);
			   if(result==true){status2++;
				   //if(Status.printExtrainfo)
				   //System.out.println("left");
				   c2=result;}
			   }
		   }
		break;
		//================================================================
		
	case 3:temp[0]=pos[0]+1;
	       temp[1]=pos[1]-1;
	   
		   if((temp[0]<=9 && temp[0]>=0) && (temp[1]<=9 && temp[1]>=0))
		   {
			   if(TempBoard[temp[0]][temp[1]]=='.')
			   {status++;
				   result=isCheck(Invert,pos,temp,TempBoard);
				   if(result==true){status2++;
				   //if(Status.printExtrainfo)
				   //System.out.println("bottom left");
					   c3=result;}
				   }
		   }
		break;
		//================================================================
		
	case 4:temp[0]=pos[0]-1;
	       temp[1]=pos[1];
	   
		   if((temp[0]<=9 && temp[0]>=0) && (temp[1]<=9 && temp[1]>=0))
		   {
			   if(TempBoard[temp[0]][temp[1]]=='.')
			   {status++;
				   result=isCheck(Invert,pos,temp,TempBoard);
				   if(result==true){status2++;
				   //if(Status.printExtrainfo)
				   //System.out.println("top middle");
					   c4=result;}
				   }
		   }
		break;
		//================================================================
		
	case 5:temp[0]=pos[0]+1;
	       temp[1]=pos[1]+1;
	   
		   if((temp[0]<=9 && temp[0]>=0) && (temp[1]<=9 && temp[1]>=0))
		   {
			   if(TempBoard[temp[0]][temp[1]]=='.')
			   {status++;
				   result=isCheck(Invert,pos,temp,TempBoard);
				   if(result==true){status2++;
				   //if(Status.printExtrainfo)
				   //System.out.println("bottom right");
					   c5=result;}
				   }
		   }
		break;
		//================================================================
		
	case 6:temp[0]=pos[0]+1;
	       temp[1]=pos[1];
	   
		   if((temp[0]<=9 && temp[0]>=0) && (temp[1]<=9 && temp[1]>=0))
		   {
			   if(TempBoard[temp[0]][temp[1]]=='.')
			   {status++;
				   result=isCheck(Invert,pos,temp,TempBoard);
				   if(result==true){status2++;
				   //if(Status.printExtrainfo)
				   //System.out.println("bottom middle");
					   c6=result;}
				   }
		   }
		break;
		//================================================================
		
	case 7:temp[0]=pos[0]-1;
	       temp[1]=pos[1]+1;
	   
		   if((temp[0]<=9 && temp[0]>=0) && (temp[1]<=9 && temp[1]>=0))
		   {
			   if(TempBoard[temp[0]][temp[1]]=='.')
			   {status++;
				   result=isCheck(Invert,pos,temp,TempBoard);
				   if(result==true){status2++;
				   //if(Status.printExtrainfo)
				   //System.out.println("top right");
					   c7=result;}
				   }
		   }
		break;
		//================================================================
		
	case 8:temp[0]=pos[0];
	       temp[1]=pos[1]+1;
	   
		   if((temp[0]<=9 && temp[0]>=0) && (temp[1]<=9 && temp[1]>=0))
		   {
			   if(TempBoard[temp[0]][temp[1]]=='.')
			   {status++;
				   result=isCheck(Invert,pos,temp,TempBoard);
				   if(result==true){status2++;
				   //if(Status.printExtrainfo)
				   //System.out.println("center middle");
					   c8=result;}
				   }
		   }
		//================================================================
		
	}
	}
	
	//final

	if(status==0 && status2==0)
      return false;		
	
	if(status==status2)
    return true;
	else
	return false;
}

//HELPER FUNCTIONS
public static void tempCastle(String command,char[][] TempBoard)
{
	//player White;Queen
	if(command.compareTo("0-0-0")==0){
	if(FairyChess.currentPlayer==1)
	{
		int[] p1=Piece.positionDecode("f1");
		int[] p2=Piece.positionDecode("c1");
		int[] p3=Piece.positionDecode("a1");
		int[] p4=Piece.positionDecode("d1");
		
		TempBoard[p2[0]][p2[1]]=TempBoard[p1[0]][p1[1]];
		TempBoard[p1[0]][p1[1]]='.';
		TempBoard[p4[0]][p4[1]]=TempBoard[p3[0]][p3[1]];
		TempBoard[p3[0]][p3[1]]='.';
		 
		 return ;
	}
	
	//player Black;Queen

	if(FairyChess.currentPlayer==-1)
	{
		int[] p1=Piece.positionDecode("f10");
		int[] p2=Piece.positionDecode("c10");
		int[] p3=Piece.positionDecode("a10");
		int[] p4=Piece.positionDecode("d10");
		
		TempBoard[p2[0]][p2[1]]=TempBoard[p1[0]][p1[1]];
		TempBoard[p1[0]][p1[1]]='.';
		TempBoard[p4[0]][p4[1]]=TempBoard[p3[0]][p3[1]];
		TempBoard[p3[0]][p3[1]]='.';

		 return;
	   
	}}
	
	//player White;king
	if(command.compareTo("0-0")==0){
	if(FairyChess.currentPlayer==1)
	{
		int[] p1=Piece.positionDecode("f1");
		int[] p2=Piece.positionDecode("i1");
		int[] p3=Piece.positionDecode("j1");
		int[] p4=Piece.positionDecode("h1");
		
		TempBoard[p2[0]][p2[1]]=TempBoard[p1[0]][p1[1]];
		TempBoard[p1[0]][p1[1]]='.';
		TempBoard[p4[0]][p4[1]]=TempBoard[p3[0]][p3[1]];
		TempBoard[p3[0]][p3[1]]='.';

		 return;
	   
	}
	
	//player Black;king

	if(FairyChess.currentPlayer==-1)
	{
		int[] p1=Piece.positionDecode("f10");
		int[] p2=Piece.positionDecode("i10");
		int[] p3=Piece.positionDecode("j10");
		int[] p4=Piece.positionDecode("h10");
		
		TempBoard[p2[0]][p2[1]]=TempBoard[p1[0]][p1[1]];
		TempBoard[p1[0]][p1[1]]='.';
		TempBoard[p4[0]][p4[1]]=TempBoard[p3[0]][p3[1]];
		TempBoard[p3[0]][p3[1]]='.';
	    
		 return;
	   
	}}	
}

public static int[] getKingPosition(char[][] board,char name)
{
	for(int i=0;i<10;i++)
	{
		for(int j=0;j<10;j++)
		{
			if(board[i][j]==name)
			{
				int[] pos=new int[2];
				pos[0]=i;
				pos[1]=j;
				return pos;
			}
		}
	}
	return null;
}
}


