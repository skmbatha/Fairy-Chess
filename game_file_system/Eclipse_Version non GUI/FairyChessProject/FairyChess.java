import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FairyChess {
	
	//Global variables
	//Reading from the board
	public static int nInit=0;
	public static int bInit=0;
	public static int qInit=0;
	public static int pInit=0;
	public static int dInit=0;
	public static int kInit=0;
	public static int rInit=0;
	public static int fInit=0;
	public static int eInit=0;
	public static int aInit=0;
	public static int wInit=0;
	public static int noOfPieces;
	public static int lineCntr=0;
	
	//status variables
	public static int nextPlayer=0;
	public static int currentPlayer=1; //1=w;-1=b
	public static String castlingOp="";
	public static int halfMoveClock=0;
	public static int moveCounter=0;

	public static Piece[][] piece=new Piece[10][10];
	public static int pieceCNTR=0;
			
	//board array
	public static char[][] Board=new char[10][10];//game played here
	
	public static void Initialize(String directory) throws FileNotFoundException
	{
		File file=new File(directory);
		Scanner boardFile=new Scanner(file);
		
		MoveNotation.activity="null";
		
		//read the lines
		String line;
		char line2[];
		
		while(boardFile.hasNext())
		{
		line=boardFile.nextLine();
		line2=line.toCharArray();
		
		if(line2[0]=='%')//if comment
			continue;
		
		if(line.compareTo("-----")==0)//if map zone,read map
		{
			for(int i=0;i<10;i++)
			{
			line=boardFile.nextLine();
			line2=line.toCharArray();
			
			if(line2[0]=='%')
			{
			i-=1;
             continue;			
			}
			
			  for(int j=0;j<10;j++)
			   {

				  
				  Board[i][j]=line2[2*j];
				//  if(line2[2*j]!='.')
				//  {
					  //Set Piece name
					  String name;
					  name=Character.toString(line2[2*j]);
					  
					  int position[]=new int[2];
					  position[0]=i;	  
					  position[1]=j;
					  
				      //set rank
				      String rank;
				      if(name.compareTo("p")==0 || name.compareTo("d")==0)
				    	  rank="Pawn";
				      else
				    	  rank="officer";
				      
                      //instantiate objects
				      piece[i][j]=new Piece(name,rank,position);
					  
					  //increment necessary stuff
					  pieceCNTR++;
				 // }
			   }
			}
			
		line=boardFile.nextLine();//discard the "boundary"
		continue;
		}

		//check the declared variables
		if(line.length()==3)
		switch(line2[0])
		{
		case 'n':nInit=Character.getNumericValue(line2[2]);
			break;
		case 'b':bInit=Character.getNumericValue(line2[2]);
			break;
		case 'q':qInit=Character.getNumericValue(line2[2]);
			break;
		case 'p':pInit=Character.getNumericValue(line2[2]);
			break;
		case 'd':dInit=Character.getNumericValue(line2[2]);
			break;
		case 'k':kInit=Character.getNumericValue(line2[2]);
			break;
		case 'r':rInit=Character.getNumericValue(line2[2]);
			break;
		case 'f':fInit=Character.getNumericValue(line2[2]);
			break;
		case 'e':eInit=Character.getNumericValue(line2[2]);
			break;
		case 'a':aInit=Character.getNumericValue(line2[2]);
			break;
		case 'w':wInit=Character.getNumericValue(line2[2]);
			break;
		default://System.out.println("defaulted");
		}
		
		
		//read status line
		if(line2.length>=10)
		if(line2[6]==':' && line2[6]==':')
		{
			String[] subLines=line.split(":");
			//========================
			if(subLines[0].compareTo("w")==0)
				{nextPlayer=-1;
			    currentPlayer=1;}
			else{
				nextPlayer=1;
				currentPlayer=-1;}
			//========================
			castlingOp=subLines[1];
			halfMoveClock=Integer.parseInt(subLines[2]);
			moveCounter=Integer.parseInt(subLines[3]);
		}
		}
		
		boardFile.close();//close file
		
		Status.op[0]='+';
		Status.op[1]='+';
		Status.op[2]='+';
		Status.op[3]='+';
		
		noOfPieces=nInit+bInit+qInit+pInit+dInit+kInit+rInit+fInit+eInit+aInit+wInit;
		
		return;
	}
	
//====================================PRINT====================================	
	public static void printBoard(char[][] board)//from array information
	{
		int imax=board.length;
		int jmax=board[0].length;
		
		//print header
		/*System.out.println("n: "+nInit);
		System.out.println("b: "+bInit);
		System.out.println("q: "+qInit);
		System.out.println("p: "+pInit);
		System.out.println("d: "+dInit);
		System.out.println("k: "+kInit);
		System.out.println("r: "+rInit);
		System.out.println();*/
		
		//print map
		int imax0=10;
	
		
		for(int i=0;i<imax;i++)
		{	

			for(int j=0;j<jmax;j++)
			{
				if(j==(jmax-1))
					System.out.print(board[i][j]);
				else
					System.out.print(board[i][j]+" ");
			}	
			System.out.print("\n");
			imax0-=1;
		}nextPlayer=currentPlayer*-1;
		
		/*//print status
		//System.out.println();
		
		//player
		
		//if(nextPlayer==1)
		//	System.out.println("nextPlayer: "+'w');
		//else
		//	System.out.println("nextPlayer: "+'b');	
			
	//	System.out.println("castingOp: "+castlingOp);
	//	System.out.println("halfMoveClock: "+halfMoveClock);
	//	System.out.println("moveCounter: "+moveCounter);*/
	}

///=======================PRINT BOARD FROM PICE OBJ INFORMATION==================
public static void printBoard()//from array information
{

int x=0,y=0;

for(int i=0;i<10;i++)
{
	 for(int j=0;j<10;j++)
	 {	
		 Board[i][j]='.';
	 }
}

for(int i=0;i<10;i++)
{
	 for(int j=0;j<10;j++)
	 {	
		 if((piece[i][j].getName()).compareTo(".")!=0)
		 {
			 x=piece[i][j].getPosition()[0];
			 y=piece[i][j].getPosition()[1];
			 Board[x][y]=piece[i][j].getName().charAt(0);
		 }
		 
	 }
}

printBoard(Board);

//System.out.println("====================================================================================");
}

//============================SEARCH FOR AN OBJ AT POSITION=======================
public static int[] searchPos(int[] posTemp)
{
int[] result=new int[2];

for(int i=0;i<10;i++)
{
	for(int k=0;k<10;k++)
	{
		if(piece[i][k].getPosition()[0]==posTemp[0] && piece[i][k].getPosition()[1]==posTemp[1])
		{
			result[0]=i;
			result[1]=k;
			
		 return result;
		}
	}
}
return null;
}


public static int[] searchPos(String location)
{
int[] posTemp=Piece.positionDecode(location);
int[] result=new int[2];

for(int i=0;i<10;i++)
{
	for(int k=0;k<10;k++)
	{
		if(piece[i][k].getPosition()[0]==posTemp[0] && piece[i][k].getPosition()[1]==posTemp[1])
		{
			result[0]=i;
			result[1]=k;
			
		 return result;
		}
	}
}
return null;
}

public static Piece setPieceCapture(String location,String location2)
{
int[] posTemp=Piece.positionDecode(location);
int[] finale=Piece.positionDecode(location2);

for(int i=0;i<10;i++)
{
	for(int k=0;k<10;k++)
	{
		if(piece[i][k].getPosition()[0]==finale[0] && piece[i][k].getPosition()[1]==finale[1])
		{
		piece[i][k].setName(".");
		piece[i][k].setPos(0, 10);
		}
	}
}

for(int i=0;i<10;i++)
{
	for(int k=0;k<10;k++)
	{
		if(piece[i][k].getPosition()[0]==posTemp[0] && piece[i][k].getPosition()[1]==posTemp[1])
		{
		piece[i][k].setPos(location2);
		}
	}
}


return null;
}

//==============================SEARCH FOR AN OBJ AT NAME==========================
public static int[] searchName(String name)
{

for(int i=0;i<10;i++)
{
	for(int j=0;j<10;j++)
	{
	   if(piece[i][j].getName().compareTo(name)==0)
	   {
		   int[] pos=new int[2];
		   pos[0]=i;
		   pos[1]=j;
		   return pos;
	   }
	}
}
return null;//just in case nothing is received
}

public static void mainResult(String args0) throws FileNotFoundException
{
	File file=new File(args0);
	Scanner boardFile=new Scanner(file);
	
	//read the lines
	String line;
	char line2[];
	
	while(boardFile.hasNext())
	{
	line=boardFile.nextLine();
	line2=line.toCharArray();
	
	if(line.compareTo("-----")!=0)
		System.out.println(line);
	
	if(line.compareTo("-----")==0)//if map zone,read map
	{
		System.out.println(line);
		printBoard();
		System.out.println(line);
		
		if(currentPlayer==1)
			System.out.print("w:");
	    else
			System.out.print("b:");	
			
		System.out.print(castlingOp+":");
		System.out.print(halfMoveClock+":");
		System.out.print(moveCounter);
		break;
	}
	}
}

public static  void refreshBoardArray()
{
	
int x=0,y=0;

for(int i=0;i<10;i++)
{
	 for(int j=0;j<10;j++)
	 {	
		 Board[i][j]='.';
	 }
}

for(int i=0;i<10;i++)
{
	 for(int j=0;j<10;j++)
	 {	
		 if((piece[i][j].getName()).compareTo(".")!=0)
		 {
			 x=piece[i][j].getPosition()[0];
			 y=piece[i][j].getPosition()[1];
			 Board[x][y]=piece[i][j].getName().charAt(0);
		 }
		 
	 }
}
}
	

//=========================================MAIN=======================================
	
public static void main(String[] args) {
	
//=========================================BOARD VALIDATE====================================
//...
//========================================GAME PLAY====================================

try{
Initialize(args[0]);
//Initialize the instruction text doc!
DecodeAndDoMove obj=new DecodeAndDoMove();//initialize the game engine
File file=new File(args[1]);//instantiate file
Scanner filereader=new Scanner(file);//instantiate stream reader
String input=" ";
char a=' ';

while(filereader.hasNextLine()){//test

input=filereader.nextLine();
lineCntr++;//increment line counter
a=input.toCharArray()[0];
if(a=='%')
	continue;

obj.commandDecoder(input);//read instruction,decode,interpret,implement and throw an error in case of one
Status.updateCheck();//update check variables
Status.updateCheckMate();//update check mate variables

refreshBoardArray();//refresh the local Chess Array boardrefreshBoardArray();//refresh the local Chess Array board
Status.updateCastlingOp();//update castling status
}
}
catch(FileNotFoundException e)
{System.out.print("File not found");}
//========================================PRINT RESULTS====================================
try{
mainResult(args[0]);
}catch(FileNotFoundException e)
{}

}}
//========================================END====================================






/*
while(true)
{
//update castling
Status.updateCastlingOp();


//print
Status.updateCheck();
Status.updateCheckMate();
printBoard();

//check if in check
//System.out.println("Check??:"+Status.updateCheck());

//update checkMate
//System.out.println("CheckMate??:"+Status.updateCheckMate()+"\n\n");
//System.out.println("-------------------------------------------------------------------------------");
//read in
//obj.commandDecoder();
}*/

//=====================================================================================