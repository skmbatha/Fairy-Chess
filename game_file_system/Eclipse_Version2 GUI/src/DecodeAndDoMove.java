public class DecodeAndDoMove
{

MoveNotation MN= new MoveNotation();
MovementRules MR=new MovementRules();

public static String notationType=" ";
public static String CCQ="null";
	
//decode instruction
public void commandDecoder(String command)
{
	char cArr[]=command.toCharArray();
	String current=" ";
	String next=" ";
	
	if(command.length()>=5){

			String[] strArr=null;
			
			if(cArr[2]=='-' || cArr[3]=='-')
				strArr=command.split("-");
			else if(cArr[2]=='x' || cArr[3]=='x')
				strArr=command.split("x");
					
			if(strArr!=null){
			current=strArr[0];
			//next
			int cntr=0;
			char[] charVer=strArr[1].toCharArray();
            for(int i=0;i<strArr[1].length();i++)
			{         	
				if((charVer[i]>=48 && charVer[i]<=57) || (charVer[i]>=97 && charVer[i]<=107))
				{ 
				    cntr++;
				}
				else
				break;
			}
            next=strArr[1].substring(0,cntr);	
			}
	}
		
	
	
	//==================================================NAVIGATE TO THE CORRECT HANDLER=================================================
	// Kingside castling
	if  (command.compareTo("0-0")==0)
		{
			notationType="ks";
			if(MN.castling("king",current,next,command)==false)
				MoveValidationErrors.illegalCastlingMove(FairyChess.lineCntr);
		}
	
	// Queenside castling
	else if (command.compareTo("0-0-0")==0)
		{
			notationType="qs"; 
			if(MN.castling("queen",current,next,command)==false)
				MoveValidationErrors.illegalCastlingMove(FairyChess.lineCntr);
		}
	// Move
	else if ((cArr[2]=='-' && cArr[command.length()-1]!='+' && cArr[(command.length()-2)]!='=') || (cArr[3]=='-' && cArr[command.length()-1]!='+' && cArr[(command.length()-2)]!='='))
		{   
			notationType="mv";  
			if(MN.movement(current,next)==false)
				MoveValidationErrors.illegalMove(FairyChess.lineCntr);
		}
	// Capture
	else if ((cArr[2]=='x' && cArr[command.length()-1]!='+' && cArr[(command.length()-2)]!='=') || (cArr[3]=='x' && cArr[command.length()-1]!='+' && cArr[(command.length()-2)]!='='))
		{
			notationType="cp"; 
			if(MN.capture(current,next,command)==false)
				MoveValidationErrors.illegalCapture(FairyChess.lineCntr);

		}
	// Check
	else if ((((cArr[2]=='x')||(cArr[2]=='-')) && cArr[command.length()-1]=='+' && cArr[command.length()-2]!='+' && cArr[(command.length()-2)]!='=') || (((cArr[3]=='x')||(cArr[3]=='-')) && cArr[command.length()-1]=='+' && cArr[command.length()-2]!='+' && cArr[(command.length()-2)]!='='))
		{ 
			
			char[] chrTemp=command.toCharArray();
			CCQ="check";
			
			if(MoveNotation.isCheck(true,current,next))
			{
			if(chrTemp[current.length()]=='-')
			{
				notationType="mv";  
				if(MN.movement(current,next)==false)
					MoveValidationErrors.illegalMove(FairyChess.lineCntr);
			}
			
			else if(chrTemp[current.length()]=='x')
			{
				notationType="cp"; 
				if(MN.capture(current,next,command)==false)
					MoveValidationErrors.illegalCapture(FairyChess.lineCntr);
			}}
			else
			{
				MoveValidationErrors.illegalCheck(FairyChess.lineCntr);
			}		
			CCQ="null";
		}
	// CheckMate
	else if (cArr[command.length()-2]=='+')
		{ 
		    CCQ="checkmate";
			char[] chrTemp=command.toCharArray();
			
			if(MoveNotation.isCheckMate(true,current,next))
			{
			if(chrTemp[current.length()]=='-')
			{
				notationType="mv";  
				if(MN.movement(current,next)==false)
					MoveValidationErrors.illegalMove(FairyChess.lineCntr);
			}
			
			else if(chrTemp[current.length()]=='x')
			{
				notationType="cp"; 
				if(MN.capture(current,next,command)==false)
					MoveValidationErrors.illegalCapture(FairyChess.lineCntr);
			}
			}else
			{
				MoveValidationErrors.illegalCheckmate(FairyChess.lineCntr);
			}
			
			CCQ="null";
		}
	// Promotion
	else if (cArr[(command.length()-2)]=='=')
		{
			notationType="pr"; 			
			if(MN.promotion(current,next,command)==false)
				MoveValidationErrors.illegalPromotion(FairyChess.lineCntr);
		}	
	
	else
	{
		MoveValidationErrors.response="This instruction is unknown!";
	}
	//==================================================================================================================================
}	

	
}