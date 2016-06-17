package ecom.model;

public class Clothings extends Product {
	private static final long serialVersionUID = 8778677462299133756L;

	// Size Style
	protected int sizeStyle;

	public final int KIDS_YEAR_STYLE  = 1;
	public final int KIDS_MONTH_STYLE = 2;

	// KIDS_YEAR_STYLE
	protected final int SIZE_1_2   = 0;
	protected final int SIZE_2_3   = 1;
	protected final int SIZE_3_4   = 2;
	protected final int SIZE_4_5   = 3;
	protected final int SIZE_5_6   = 4;
	protected final int SIZE_6_7   = 5;
	protected final int SIZE_7_8   = 6;
	protected final int SIZE_8_9   = 7;
	protected final int SIZE_9_10  = 8;
	protected final int SIZE_10_11 = 9;
	protected final int SIZE_11_12 = 10;

	// KIDS_YEAR_STYLE Stock
	protected int stockOfSIZE_1_2;
	protected int stockOfSIZE_2_3;
	protected int stockOfSIZE_3_4;
	protected int stockOfSIZE_4_5;
	protected int stockOfSIZE_5_6;
	protected int stockOfSIZE_6_7;
	protected int stockOfSIZE_7_8;
	protected int stockOfSIZE_8_9;
	protected int stockOfSIZE_9_10;
	protected int stockOfSIZE_10_11;
	protected int stockOfSIZE_11_12;
	
	public Clothings() {
		sizeStyle = 0;
	}

	public int getSizeStyle() {
		return sizeStyle;
	}

	public int getStockOfSIZE_1_2() {
		return stockOfSIZE_1_2;
	}

	public int getStockOfSIZE_2_3() {
		return stockOfSIZE_2_3;
	}

	public int getStockOfSIZE_3_4() {
		return stockOfSIZE_3_4;
	}

	public int getStockOfSIZE_4_5() {
		return stockOfSIZE_4_5;
	}

	public int getStockOfSIZE_5_6() {
		return stockOfSIZE_5_6;
	}

	public int getStockOfSIZE_6_7() {
		return stockOfSIZE_6_7;
	}

	public int getStockOfSIZE_7_8() {
		return stockOfSIZE_7_8;
	}

	public int getStockOfSIZE_8_9() {
		return stockOfSIZE_8_9;
	}

	public int getStockOfSIZE_9_10() {
		return stockOfSIZE_9_10;
	}

	public int getStockOfSIZE_10_11() {
		return stockOfSIZE_10_11;
	}

	public int getStockOfSIZE_11_12() {
		return stockOfSIZE_11_12;
	}

	public void setSizeStyle(int sizeStyle) {
		this.sizeStyle = sizeStyle;
	}

	public void setStockOfSIZE_1_2(int stockOfSIZE_1_2) {
		this.stockOfSIZE_1_2 = stockOfSIZE_1_2;
	}

	public void setStockOfSIZE_2_3(int stockOfSIZE_2_3) {
		this.stockOfSIZE_2_3 = stockOfSIZE_2_3;
	}

	public void setStockOfSIZE_3_4(int stockOfSIZE_3_4) {
		this.stockOfSIZE_3_4 = stockOfSIZE_3_4;
	}

	public void setStockOfSIZE_4_5(int stockOfSIZE_4_5) {
		this.stockOfSIZE_4_5 = stockOfSIZE_4_5;
	}

	public void setStockOfSIZE_5_6(int stockOfSIZE_5_6) {
		this.stockOfSIZE_5_6 = stockOfSIZE_5_6;
	}

	public void setStockOfSIZE_6_7(int stockOfSIZE_6_7) {
		this.stockOfSIZE_6_7 = stockOfSIZE_6_7;
	}

	public void setStockOfSIZE_7_8(int stockOfSIZE_7_8) {
		this.stockOfSIZE_7_8 = stockOfSIZE_7_8;
	}

	public void setStockOfSIZE_8_9(int stockOfSIZE_8_9) {
		this.stockOfSIZE_8_9 = stockOfSIZE_8_9;
	}

	public void setStockOfSIZE_9_10(int stockOfSIZE_9_10) {
		this.stockOfSIZE_9_10 = stockOfSIZE_9_10;
	}

	public void setStockOfSIZE_10_11(int stockOfSIZE_10_11) {
		this.stockOfSIZE_10_11 = stockOfSIZE_10_11;
	}

	public void setStockOfSIZE_11_12(int stockOfSIZE_11_12) {
		this.stockOfSIZE_11_12 = stockOfSIZE_11_12;
	}

}
