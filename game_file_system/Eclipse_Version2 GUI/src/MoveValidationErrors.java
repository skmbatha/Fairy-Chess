public class MoveValidationErrors {
	
	public static String response="";

    /**
     * To be called in case of 5.2
     *
     * @param line The line number of the illegal move
     */
    public static void illegalMove(int line) {
    	//response="ERROR: Illegal move on line ";
       // System.exit(11);
    }

    /**
     * To be called in case of 5.3
     *
     * @param line The line number of the illegal capture
     */
    public static void illegalCapture(int line) {
    	//response="ERROR: Illegal capture on line ";
        //System.exit(12);
    }

    /**
     * To be called in case of 5.4
     *
     * @param line The line number of the illegal castling move
     */
    public static void illegalCastlingMove(int line) {
    	response="ERROR: Illegal castling move on line ";
        //System.exit(13);
    }

    /**
     * To be called in case of 5.5
     *
     * @param line The line number of the illegal promotion
     */
    public static void illegalPromotion(int line) {
    	response="ERROR: Illegal promotion on line ";
       // System.exit(14);
    }

    /**
     * To be called in case of 5.6
     *
     * @param line The line number of the move with the illegal check suffix
     */
    public static void illegalCheck(int line) {
    	response="ERROR: Illegal check suffix on line ";
        //System.exit(15);
    }

    /**
     * To be called in case of 5.7
     *
     * @param line The line number of the move with the illegal checkmate suffix
     */
    public static void illegalCheckmate(int line) {
    	response="ERROR: Illegal checkmate suffix on line ";
     //   System.exit(16);
    }
}
