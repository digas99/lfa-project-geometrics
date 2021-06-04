package Structures;

public class Color {
    
	String hexCode;
	RGB rgb;

	public Color(String hexCode) {
		this.hexCode = hexCode;
	}

	public Color(RGB rgb) {
		this.rgb = rgb;
	}

	public String colorHexCode() {
		return hexCode;
	}

	public RGB colorRGB() {
		return rgb;
	}

	public void setColor(String hexCode) {
		this.hexCode = hexCode;
	}

	public void setColor(RGB rgb) {
		this.rgb = rgb;
	}
}
