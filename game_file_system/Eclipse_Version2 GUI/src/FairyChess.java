import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
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
	public static int mode=1;//1:manual; 2=auto
	public static boolean input_available=false;
	public static String boardDir="pawn_valid_1.board";
	public static String displayInput="";
	public static boolean done=false;
	
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
		
		
		//BOARD SETUP
		StdDraw.setPenColor(169,145,141);
		StdDraw.filledRectangle(500,300,500,300);
	    
		//INFO SETUP
		StdDraw.setPenColor(76,64,62);
		StdDraw.filledRectangle(800,575,400,20);
		StdDraw.filledRectangle(800,330,400,20);
		StdDraw.filledRectangle(602,265,200,40);
		StdDraw.filledRectangle(905,265,100,40);
		StdDraw.setPenColor(255,255,255);
		StdDraw.textLeft(610, 575, "PIECE ALLOCATIONS");
		
		
		//======================================================
		if(mode==1){
		StdDraw.filledRectangle(700,250,95,20);
		StdDraw.filledRectangle(902,250,92,20);
		StdDraw.textLeft(608, 290, "TEXT(move:SPACE_BAR)");
		StdDraw.text(900, 290, "MOUSE_INPUT");
		}
		
		StdDraw.textLeft(608, 330, "STATUS : ");
		StdDraw.setPenColor(132,14,53);
		StdDraw.filledRectangle(800,113,195,105);
		if(mode==2)
		{
			//STEP IN BUTTON
			StdDraw.setPenColor(162,200,200);
			StdDraw.filledRectangle(900, 266,60,18);
			StdDraw.setPenColor(0,0,0);
			StdDraw.text(900,265, "STEP");
			StdDraw.setPenColor(255,255,255);
			StdDraw.textLeft(610, 185,"PRESS BUTTON TO STEP THROUGH MOVES!");
			StdDraw.filledRectangle(800, 165,370,1);
			StdDraw.filledRectangle(800, 125,370,1);
			StdDraw.textLeft(608, 265, "LINE:  "+lineCntr);
			StdDraw.textLeft(608, 143, "CURRENT LINE:  "+displayInput);
		}
		
		StdDraw.setPenColor(255,255,255);
		if(mode==1)
			StdDraw.textLeft(610, 120, MoveValidationErrors.response);
		else
			StdDraw.textLeft(610, 105, MoveValidationErrors.response);
		//ADDITIONAL RIGHT PANE INFO
			try{
			mainResult(boardDir);
			}catch(FileNotFoundException e)
			{}
		//PRINT BOARD
		StdDraw.picture(300,300,"GUI/board.jpg",600,600);
		
		//FOR TEXT STEP THROUGH
		if(done)
			StdDraw.textLeft(608,60, "DONE...END OF FILE!");
		//print map
		
		//BUTTON EFFECT
		int imax0=10;
	    int x=0;
	    int y=0;
	    
		for(int i=0;i<imax;i++)
		{	

			for(int j=0;j<imax;j++)
			{
			    x=27+(j*58);
			    y=571-(i*58);
				
				switch(board[i][j])
				{
				case 'n':StdDraw.picture(x,y,"GUI/BKnight.png",60,60);
					break;
				case 'b':StdDraw.picture(x,y,"GUI/BBishop.png",60,60);
					break;
				case 'q':StdDraw.picture(x,y,"GUI/BQueen.png",60,60);
					break;
				case 'p':StdDraw.picture(x,y,"GUI/BPawn.png",60,60);
					break;
				case 'd':StdDraw.picture(x,y,"GUI/BDrunkenSoldier.png",56,56);
					break;
				case 'k':StdDraw.picture(x,y,"GUI/BKing.png",60,60);
					break;
				case 'r':StdDraw.picture(x,y,"GUI/BRook.png",60,60);
					break;
				case 'f':StdDraw.picture(x,y,"GUI/",60,60);
					break;
				case 'e':StdDraw.picture(x,y,"GUI/",60,60);
					break;
				case 'a':StdDraw.picture(x,y,"GUI/",60,60);
					break;
				case 'w':StdDraw.picture(x,y,"GUI/",60,60);
					break;
					
			case 'N':StdDraw.picture(x,y,"GUI/WKnight.png",60,60);
				break;
			case 'B':StdDraw.picture(x,y,"GUI/WBishop.png",60,60);
				break;
			case 'Q':StdDraw.picture(x,y,"GUI/WQueen.png",60,60);
				break;
			case 'P':StdDraw.picture(x,y,"GUI/WPawn.png",60,60);
				break;
			case 'D':StdDraw.picture(x,y,"GUI/WDrunkenSoldier.png",56,56);
				break;
			case 'K':StdDraw.picture(x,y,"GUI/WKing.png",60,60);
				break;
			case 'R':StdDraw.picture(x,y,"GUI/WRook.png",60,60);
				break;
			case 'F':StdDraw.picture(x,y,"GUI/",60,60);
				break;
			case 'E':StdDraw.picture(x,y,"GUI/",60,60);
				break;
			case 'A':StdDraw.picture(x,y,"GUI/",60,60);
				break;
			case 'W':StdDraw.picture(x,y,"GUI/",60,60);
				break;
				default://System.out.println("defaulted");
				}
			}	

		}
		
		nextPlayer=currentPlayer*-1;//toggle player
		StdDraw.show();

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
	int x=610,y=542;
	
	while(boardFile.hasNext())
	{
	line=boardFile.nextLine();
	line2=line.toCharArray();
	
	if(line2[0]=='%')
		continue;
		
	if(line.compareTo("-----")!=0)
	{
		StdDraw.textLeft(x, y,line);
		y-=30;
	}
		
	
    if(line.compareTo("-----")==0)//if map zone,read map
	{		
		if(currentPlayer==1){
			StdDraw.textLeft(705, 330, "W :");
			StdDraw.textLeft(860, 330, "PLAYER:white");
		}else{
	    	StdDraw.textLeft(705, 330, "B :");
	    	StdDraw.textLeft(860, 330, "PLAYER:black");}
			
		StdDraw.textLeft(740, 330,castlingOp+" :");
		StdDraw.textLeft(790, 330,halfMoveClock+"  :  ");
		StdDraw.textLeft(820, 330,Integer.toString(moveCounter));

		break;
    }
	}
	
	boardFile.close();
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
	
public static String getMouseBoardPos(int x,int y)
{
	char hor='0',ver='0';
	String ret="";
	
	for(int i=0;i<10;i++)
	{
		if(x>=(i*58) && x<(i*58+58))
		{
			hor=(char)(97+i);
			break;
		}
	}
	
	for(int i=0;i<10;i++)
	{
		if(y>=(24+i*57) && y<(i*57+81))
		{
			if(i!=9)
			{
				ver=(char)(49+i);
				ret=Character.toString(hor)+Character.toString(ver);
				break;
			}
			else
			{
				ret=Character.toString(hor)+"10";
				break;
			}
		}
	}
	
	
	if(hor=='0')
		return "null";
	else
		return ret;
}
//=========================================MAIN=======================================
	
public static void initAnimation()
{
	
//StdDraw.setCanvasSize(1000,1000);///set canvas size
//StdDraw.setXscale(0, 1000);///set x scale
//StdDraw.setYscale(0,1000);///set y scale
	
for(int y=0;y<100;y++)
{
	StdDraw.picture(500,300,"GUI/animation.png",(int)Math.round(1414*Math.sin(((Math.PI*y)/400))),(int)Math.round(848*Math.sin(((Math.PI*y)/400))));
	StdDraw.show();
	//while(!StdDraw.isMousePressed());
}

try{Thread.sleep(1000);}catch(Exception c){}

for(int y=100;y>0;y--)
{
	StdDraw.picture(500,300,"GUI/animation.png",1414*Math.sin(((Math.PI*y)/400)),848*Math.sin(((Math.PI*y)/400)));

	StdDraw.show();
}

}


public static void main(String[] args) {
	
//=========================================BOARD VALIDATE====================================
//...
//========================================GAME PLAY====================================

try{

/*//=========================================INITIALIZE=======================================
File selectedFile2=null;
JFileChooser jfc2 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
jfc2.setCurrentDirectory(new File("."));
FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Chess board files", "board");
jfc2.addChoosableFileFilter(filter2);
jfc2.setDialogTitle("Select the board file");	

if (jfc2.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) 
    selectedFile2 = jfc2.getSelectedFile();
else
	System.exit(4);
//==========================================================================================

Initialize(selectedFile2.getAbsolutePath());
boardDir="pawn_valid_1.board";
*/
Initialize("pawn_valid_1.board");

DecodeAndDoMove obj=new DecodeAndDoMove();//initialize the game engine
String input=" ";
char a=' ';
String inputfile=" ";
String inputStr="";
char in=' ';
StdDraw.enableDoubleBuffering();///Enable double double buffering on the  StdDraw

//======================================MOVE FILE QUERY BOX================================== 
StdDraw.setCanvasSize(400,100);///set canvas size
StdDraw.setXscale(0, 400);///set x scale
StdDraw.setYscale(0,100);///set y scale
StdDraw.setPenColor(0,0,0);
StdDraw.text(200,75, "DO YOU WANT TO IMPORT A MOVE FILE???");	
StdDraw.text(200, 45, "PRESS Y/N.");
StdDraw.show();

while(!StdDraw.hasNextKeyTyped());
char inp=StdDraw.nextKeyTyped();
if(inp=='Y' || inp=='y')
{
	 //go call a dialogue window
	 inputfile="File directory from  dialogue";
	 mode=2;
}
else mode=1;

if(mode==2){
	//window
	File selectedFile;
	JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	jfc.setCurrentDirectory(new File("."));
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Chess move files", "moves");
	jfc.addChoosableFileFilter(filter);
	jfc.setDialogTitle("Select the moves file");
	
	int returnValue = jfc.showOpenDialog(null);
	if (returnValue == JFileChooser.APPROVE_OPTION) 
	    selectedFile = jfc.getSelectedFile();
	else
	{
		selectedFile=new File("");
		System.exit(11);
	}
	
	//==========================================GUI INIT=========================================
	StdDraw.setCanvasSize(1000,600);///set canvas size
	StdDraw.setXscale(0, 1000);///set x scale
	StdDraw.setYscale(0,600);///set y scale
	initAnimation();

	//==========================================AUTO PLAY========================================
	while(StdDraw.isMousePressed());
	
	Scanner filereader=new Scanner(selectedFile );//instantiate stream reader
	do{
		input=filereader.nextLine();
		displayInput=input;
		lineCntr++;//increment line counter
		a=input.toCharArray()[0];
		if(a!='%'){
		obj.commandDecoder(input);//read instruction,decode,interpret,implement and throw an error in case of one
		Status.updateCheck();//update check variables
		Status.updateCheckMate();//update check mate variables

		refreshBoardArray();//refresh the local Chess Array boardrefreshBoardArray();//refresh the local Chess Array board
		Status.updateCastlingOp();//update castling status
		}
		printBoard();
		
		//=======BUTTON EFFECT===========
		while(!StdDraw.isMousePressed());
		StdDraw.setPenColor(255,255,255);
		StdDraw.filledRectangle(900, 266,60,18);
		StdDraw.setPenColor(0,0,0);
		StdDraw.text(900,265, "STEP");
		StdDraw.show();
		while(StdDraw.isMousePressed());
		//================================
	}
	while(filereader.hasNextLine());
    done=true;
	printBoard();
}//test}

//============================================MANUAL PLAY====================================
if(mode==1)
{
	
	//==========================================GUI INIT=========================================
	StdDraw.setCanvasSize(1000,600);///set canvas size
	StdDraw.setXscale(0, 1000);///set x scale
	StdDraw.setYscale(0,600);///set y scale
	initAnimation();

	//==========================================AUTO PLAY========================================
	while(true)
	{
		printBoard();
		//WAIT FOR INPUT FROM EITHER THE MOUSE OR THE KEYPAD(TEXT BOX) ENABLE FLAG(input available)
		while(!StdDraw.isMousePressed() && !StdDraw.hasNextKeyTyped());
		if(StdDraw.isMousePressed())
		{
			while(StdDraw.isMousePressed());
			StdDraw.setPenColor(0,0,0);
			//get position then generate the input strin
			inputStr=getMouseBoardPos((int)StdDraw.mouseX(),(int)StdDraw.mouseY());
			inputStr+="-";
			StdDraw.textLeft(870,250,inputStr);
			StdDraw.show();
			while(!StdDraw.isMousePressed());
			while(StdDraw.isMousePressed());
			inputStr+=getMouseBoardPos((int)StdDraw.mouseX(),(int)StdDraw.mouseY());
			StdDraw.textLeft(870,250,inputStr);
			StdDraw.show();
			Thread.sleep(1000);
		}
	
		if(StdDraw.hasNextKeyTyped())
		{
			StdDraw.setPenColor(0,0,0);
			//====================================RECEIVE TEXT INPUT=================================
			in=StdDraw.nextKeyTyped();
			StdDraw.textLeft(610,250,Character.toString(in));
			StdDraw.show();
			//RECEIVE INPUT DURING GAME PLAY...type input until the space bar is pressed!
			inputStr=Character.toString(in);
			while(!StdDraw.hasNextKeyTyped());
			if(StdDraw.hasNextKeyTyped())
			{
			while(true){
			while(!StdDraw.hasNextKeyTyped());
			
			in=StdDraw.nextKeyTyped();
			//if(in<48 || (in>57 && in <97) || in>106)
				//continue;
			
			inputStr+=Character.toString(in);
			StdDraw.textLeft(610,250,inputStr);
			if(in==' ') break;
			StdDraw.show();
			}}
		}
		
		//====================================PROCESS INPUT DATA FROM ABOVE=================================
		obj.commandDecoder(inputStr.toLowerCase().trim());//read instruction,decode,interpret,implement and throw an error in case of one
		Status.updateCheck();//update check variables
		Status.updateCheckMate();//update check mate variables

		refreshBoardArray();//refresh the local Chess Array boardrefreshBoardArray();//refresh the local Chess Array board
		Status.updateCastlingOp();//update castling status
	}
	
}
Thread.sleep(3000);//pause the program for 3s and the exit 
System.exit(0);
}
catch(Exception e)
{
	MoveValidationErrors.response="File not found";
	System.exit(3);
}
//========================================PRINT RESULTS====================================

}}
//========================================END====================================

