
public class Piece {
	private String name, rank;//example pawns.....p1,p2,p3,p4
	int[] pos=new int[2];//current position
    private boolean stTimePawn;


	public Piece(String n,String r) {
		name = n;
		rank = r;
	}
	//New constructor
	public Piece(String name,String rank,int position[]) {
		this.name=name;
		this.rank=rank;//officer/pawn
		this.pos[0]=position[0];
		this.pos[1]=position[1];
		stTimePawn=true;
	}
	
	public boolean getfirstTime()
	{
		return stTimePawn;
	}
	
	public void setfirstTime(boolean Value)
	{
		stTimePawn=Value;
	}
	
	public Piece()
	{}
	
	public static int[] positionDecode(String current)//decode position to array format
	{
		char posArr[]=current.toCharArray();
		int result[]=new int[2];//result stored in here
		
		//[y][ ] value
		if(current.length()==2)
		{
		int tempY=Character.getNumericValue(posArr[1]);
		tempY=10-tempY;
		result[0]=tempY;
		}
		else
		{
			String sub=current.substring(1,3);
			int tempY=Integer.parseInt(sub);
			tempY=10-tempY;
			result[0]=tempY;
		}
		
		//[ ][x] value
		int tempX=(int)posArr[0];
		tempX=tempX-97;
		result[1]=tempX;
		return result;
		
	}

	//position set---------------
    public void setPos(String pos)
    {
    	this.pos=positionDecode(pos);
    }

    public void setName(String name)
    {
    	this.name=name;
    }
    
    
    public void setPos(int x,int y)
    {
    	
    	this.pos[0]=x; 
    	this.pos[1]=y;
    }
    //----------------------------


	public String getName() {
		
		return name;
		}

	public void setRank(String rank) {//give the name in standard naming format
		
		this.rank=rank;
		return;
	}
	
	public String getRank(String pieceName) {
		if (pieceName.equalsIgnoreCase("p") || pieceName.equalsIgnoreCase("d")) {
			rank = "pawn";
		} 
		else if (pieceName.equalsIgnoreCase("k") || pieceName.equalsIgnoreCase("r") || pieceName.equalsIgnoreCase("q")
				|| pieceName.equalsIgnoreCase("n") || pieceName.equalsIgnoreCase("b") || pieceName.equalsIgnoreCase("f")
				|| pieceName.equalsIgnoreCase("e") || pieceName.equalsIgnoreCase("a") || pieceName.equalsIgnoreCase("w")) {
			rank = "officer";
		}
		else{
			rank="non-existent";
		}
		return rank;
	}
	
	public String getRank() {		
		return rank;
	}
	
	public int[] getPosition() {		
		return pos;
	}


	public String toString() {
		return "Piece Name:" + name + "/n" + "Rank:" + rank + "/n";
	}
	
	/*public static void main(String[] args) //test main
	{
		Piece obj=new Piece();	
		System.out.println(obj.positionDecode("g8")[0]+";"+obj.positionDecode("g8")[1]);
		
	}
*/
}
