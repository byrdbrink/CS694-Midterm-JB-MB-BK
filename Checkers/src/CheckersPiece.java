public class CheckersPiece {
   private int horizPos;
   private int vertPos;
   private boolean isKing;
   private boolean isRed;
   
   public CheckersPiece(int hPos, int vPos, boolean king, boolean isRed) {
      horizPos = hPos;
      vertPos = vPos;
      isKing = king;
      this.isRed = isRed;
   }

   public int getHorizPos() {
      return horizPos;
   }

   public void setHorizPos(int hPos) {
      horizPos = hPos;
   }

   public int getVertPos() {
      return vertPos;
   }

   public void setVertPos(int vPos) {
      vertPos = vPos;
   };
   
   public boolean isKing() {
      return isKing;
   }

   public void setIsKing(boolean king) {
      isKing = king;
   }

   public boolean getIsRed() {
      return this.isRed;
   }
}
