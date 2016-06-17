package ecom.model;

import java.io.Serializable;

public class Images implements Serializable {
	private static final long serialVersionUID = -6536573795939603674L;

	private String iconImage;
	private String image1;
	private String image2;
	
	
	public String getIconImage() {
		return iconImage;
	}
	public String getImage1() {
		return image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	
}
