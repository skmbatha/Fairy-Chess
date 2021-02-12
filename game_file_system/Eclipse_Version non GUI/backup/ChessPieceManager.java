
import java.io.File;
import java.util.Scanner;

public class ChessPieceManager {

	Piece pArray[] = new Piece[11];
	Status statLine = new Status();
	private int counter = 0;
	int x = 10;
	int y = 10;
	String boardArr[][] = new String[x][y];
	Piece obj = new Piece();
	String pieceAllocation;
	String boradConfiguration;
	String statusLine;
	int countP = 0;
	char al[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };
	int num[] = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

	
	
	//==============================================BOARD VALIDATION===================================================
	// 3.2.1
	// PawnbAllocations///////////////////////////////////////////////////////////////
/*	public int checkPawnAllocation() {
		int pawnAmt = 0;
		int line = 0;

		// count number of pawn
		if (!(pieceAllocation == (null))) {// 
			for (int i = 0; i < counter; i++) {
				if (pArray[i] == null) {
					line = countP + 1; // countP: number of lines starting with
										// %
					break;
				}

				if (pArray[i].getRank(pArray[i].getName()).equals("pawn")) {
					pawnAmt = pawnAmt + pArray[i].getAmount();
					if (pawnAmt > 10) {
						line = i + 2;
						break;
					}
				} // get line number if pawns exceed 10
				if (pawnAmt == 0) {
					line = i + 2;// retrn line of diviser bar
				}
			}
		}
		// if no issues return 0 else return line of issue
		return line;
	}*/

	// 3.2.2
	// OfficerAllocations///////////////////////////////////////////////////////////
	public int checkOfficerAllocation() { // check king and rook are included
		int officerAmt = 0;
		int line = 0;
		int king = 0;
		int rooks = 0;
		int rooksLine = 0;
		int i = 0;
		// count number of rooks and kings
		if (!(pieceAllocation == (null))) {

			for (i = 0; i < counter; i++) {/// orrrr i<counter-1
				if (pArray[i] == null) {
					line = countP + 1;
					break;
				}
				if (pArray[i].getName().equals("k")) {
					king = king + pArray[i].getAmount();
					if (king > 1) {
						line = i + 1;
						break;
					}
				}
				if (pArray[i].getName().equals("r")) {
					rooks = rooks + pArray[i].getAmount();
					rooksLine = i + 1;
				}
				if (pArray[i].getRank(pArray[i].getName()).equals("officer")) {
					officerAmt = officerAmt + pArray[i].getAmount();
					if (officerAmt > 10) {
						line = i + 1;
						break;
					}
				}
			}
			if (king == 0 || officerAmt == 0) {
				line = (counter) + 2;
				// System.out.println(line );
			} else if (rooks < 2 || rooks > 9) {
				if (rooks == 0) {
					line = i + 2;
				} else {
					line = rooksLine;
				}
			}
		} else {
			line = 2;
		}

		return line;
	}

	// Board Configuration USE PIECE OBJ
	// 3.3.1 Illegal
	// Pieces////////////////////////////////////////////////////////////////////////
	char notAlph;
	int notNumn;
	public boolean illegalPiece() {
		int line = 0;
		int alph = 0;
		boolean check = true; // 
		for(int i=0;i<x;i++){
			if(!check){break;}
			for(int j=0;j<y;j++){
				for(int z=0;z<counter;z++){
					if(!(boardArr[i][j].equalsIgnoreCase("p") || boardArr[i][j].equalsIgnoreCase("d") || boardArr[i][j].equalsIgnoreCase("k") || boardArr[i][j].equalsIgnoreCase("r") || 
					   boardArr[i][j].equalsIgnoreCase("q") || boardArr[i][j].equalsIgnoreCase("n") || boardArr[i][j].equalsIgnoreCase("b") || boardArr[i][j].equalsIgnoreCase("f") || 
					   boardArr[i][j].equalsIgnoreCase("e") || boardArr[i][j].equalsIgnoreCase("a"))){
						check=false;alph=j;
						break;
					}
				}
				}				
			}
		notAlph = al[alph];//6
		notNumn = num[line];//0
		//System.out.println(line);
		return check;
	}

	public char getboardAlph() {
		return notAlph;//g
	}

	public int getboardNum() {
		return notNumn;//10
	}

	// 3.3.2 Exceeding Pawn
	// Allocation////////////////////////////////////////////////////////////////////////
	int bpwns = 0;
	int bdrunks = 0;
	int wpwns = 0;
	int wdrunks = 0;
	char exceedingpawnAlph;
	int exceedingpawnNum;

	public boolean exceedingPawnAllocation() {
		boolean check = true;
		int line = 0;
		int alph = 0;
		int pawnFinal = 0;
		int drunkFinal = 0;
		for (int z = 0; z < counter; z++) {
			if (pArray[z].getName().equals("p")) {
				pawnFinal = pawnFinal + pArray[z].getAmount();// amount of allocated pawns
			}
			if (pArray[z].getName().equals("p")) {
				drunkFinal = drunkFinal + pArray[z].getAmount();// amount of allocated drunks
			}
		}

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				if (!check) {
					break;
				}
				// System.out.println(i);
				// System.out.println(j);
				// System.out.println(boardArr[0][0]);

				if (boardArr[i][j].equals("p")) {// amount of pawns on board
					bpwns++;
				}
				if (boardArr[i][j].equals("d")) {
					bdrunks++;
				}
				if (boardArr[i][j].equals("P")) {
					wpwns++;
				}
				if (boardArr[i][j].equals("D")) {
					wdrunks++;
				}

				if (bpwns > pawnFinal || wpwns > pawnFinal) {
					check = false;
					line = i;
					alph = j;
					break;
				}
				if (bdrunks > drunkFinal || wdrunks > drunkFinal) {
					check = false;
					line = i;
					alph = j;
					break;
				}

			}
		}
		 System.out.println(bpwns);
		exceedingpawnAlph = al[alph];
		exceedingpawnNum = num[line];
		return check;
	}

	public char exceedingpawngetboardAlph() {
		return exceedingpawnAlph;
	}

	public int exceedingpawngetboardNum() {
		return exceedingpawnNum;
	}

	// 3.3.3 Exceeding Officer
	// Allocation////////////////////////////////////////////////////////////////////
	char exceedingofficerAlph;
	int exceedingofficerNum;

	public boolean exceedingOfficerAllocation() {
		int paPfficers = 0;
		int bOfficers = 0;
		int wOfficers = 0;
		int line = 0;
		int alph = 0;
		boolean check = true;
		for (int z = 0; z < counter; z++) {
			// number of officers for each in piece Allocation
			if (pArray[z].getRank(pArray[z].getName()).equals("officer")) {
				paPfficers = paPfficers + pArray[z].getAmount();// amount of
																// pieceallocated
																// officers
				// System.out.println(pArray[z].getAmount());
			}

		} // System.out.println("PALLOC "+paPfficers);

		// n=i+10-(p+d)
		int bmax = paPfficers + 10 - (bpwns + bdrunks);
		int wmax = paPfficers + 10 - (wpwns + wdrunks);
		// for(int i =0;i<boardArr.length;i++){if(check){break;}}
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {

				if (!check) {
					System.out.println(i);
					System.out.println(j);
					break;
				}
				if (boardArr[i][j].equals("k") || boardArr[i][j].equals("r") || // get
																				// number
																				// of
																				// blacksmall
																				// officers
																				// on
																				// board
						boardArr[i][j].equals("q") || boardArr[i][j].equals("n") || boardArr[i][j].equals("b")
						|| boardArr[i][j].equals("f") || boardArr[i][j].equals("e") || boardArr[i][j].equals("a")
						|| boardArr[i][j].equals("w")) {// amount of black
														// officers on board
					bOfficers++;

				}
				if (boardArr[i][j].equals("K") || boardArr[i][j].equals("R") || // get
																				// number
																				// of
																				// whitebig
																				// officers
																				// on
																				// board
						boardArr[i][j].equals("Q") || boardArr[i][j].equals("N") || boardArr[i][j].equals("B")
						|| boardArr[i][j].equals("F") || boardArr[i][j].equals("E") || boardArr[i][j].equals("A")
						|| boardArr[i][j].equals("W")) {
					wOfficers++;
				}

				if (bOfficers > bmax) {
					check = false;
					line = i;
					alph = j;// System.out.println();
				}
				if (wOfficers > wmax) {
					check = false;
					line = i;
					alph = j;
				}

			}

		}
		System.out.println(bOfficers);
		System.out.println(wOfficers);

		// System.out.println(bmax);

		exceedingofficerAlph = al[alph];// doesnt print even when replaced wth
										// num
		exceedingofficerNum = num[line];// ""
		return check;
	}

	public char exceedingofficergetboardAlph() {
		return exceedingofficerAlph;
	}

	public int exceedingofficergetboardNum() {
		return exceedingofficerNum;
	}

	// 3.3.4 illegal Board
	// Dimension/////////////////////////////////////////////////////////////////
	public boolean illegalBoardDimension() {
		boolean boardLegal;
		if (boardArr.length == 10 && boardArr[0].length == 10) {
			boardLegal = true;
		} else {
			boardLegal = false;
		}
		return boardLegal;
	}

	// 3.3.5 Illegal Pawn
	// Position/////////////////////////////////////////////////
	char illegalpawnAlph;
	int illegalpawnNum;

	public boolean illegalPawnPosition() {
		boolean check = true;
		int line = 0;
		int alph = 0;

		for (int i = 0; i < x; i++) {
			if (boardArr[0][i].equals("p") || boardArr[0][i].equals("d")) {// check
																			// board
																			// row
																			// one
																			// for
																			// small/black
																			// pawns/drunk
				line = 1;
				alph = i;
				check = false;
				break;
			}
			if (!(boardArr[9][i].equals("p") || boardArr[9][i].equals("d"))) {// check
																				// board
																				// row
																				// ten
																				// for
																				// big/white
																				// pawn/drunk
				line = 9;
				alph = i;
				check = false;
				break;
			}
		}

		illegalpawnAlph = al[alph];
		illegalpawnNum = num[line];
		return check;
	}

	public char PawnPositiongetboardAlph() {
		return illegalpawnAlph;
	}

	public int PawnPositiongetboardNum() {
		return illegalpawnNum;
	}

	// 3.3.6 Illegal Pawn
	// Position/////////////////////////////////////////////////
	char illegalElephantAlph;
	int illegalElephantNum;

	public boolean illegalElephantPosition() {
		int line = 0;
		int alph = 0;
		boolean check = true;
		for (int i = 0; i < 10; i++) {/// check top half of board for bigwhite
										/// elephant
			for (int j = 0; j < 5; j++) {
				if (boardArr[i][j].equals("E")) {
					check = false;
					line = i;
					alph = j;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (boardArr[i][j].equals("e")) {/// check top half of board for
													/// smallblack elephant
					check = false;
					line = i;
					alph = j;
					break;
				}
			}
		}

		return check;
	}

	public char elephantPositionAlph() {
		return illegalElephantAlph;
	}

	public int elephantPositionNum() {
		return illegalpawnNum;
	}
	

	////// Status line/////////////////////////////////
	public int illegalNextPlayer() {
		int line = 0;
		if (!(statLine.getNextPlayer().equalsIgnoreCase("W") || statLine.getNextPlayer().equalsIgnoreCase("B"))) {
			line = (counter) + boardArr[0].length + 2 + countP;
		}
		return line;
	}

	// 3.4.2 public int illegalCastlingOpportunities() {
	//
	// }
	//
	public int illegalHalfMoveClock() {
		int line = 0;
		if (!(statLine.getHafMove() >= 0 && statLine.getHafMove() <= 50)) {
			line = (counter) + boardArr[0].length + 2 + countP;
		}
		return line;

	}
     //3.4.4
	public int illegalMoveCounter() {
		int line = 0;
		if (!(statLine.getMoveCounter() >= 0) || !(statLine.getMoveCounter() <= 99)) {
			line = (counter) + boardArr[0].length + 2 + countP;
		}
		return line;
	}

}
