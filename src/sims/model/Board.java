package sims.model;

public class Board {
	private String productID;
	private String boardID;
	private String targetBoard;

	public Board() {
		super();
	}
	
	public Board(String productID, String boardID, String targetBoard) {
		super();
		this.productID = productID;
		this.boardID = boardID;
		this.targetBoard = targetBoard;	
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getBoardID() {
		return boardID;
	}

	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}

	public String getTargetBoard() {
		return targetBoard;
	}

	public void setTargetBoard(String targetBoard) {
		this.targetBoard = targetBoard;
	}
	
}


