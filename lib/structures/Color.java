package structures;

public class Color {
    
	String hexCode;
	RGB rgb;

	public Color(String hexCode) {
		this.hexCode = hexCode.charAt(0) == '#' ? hexCode : "#"+hexCode;
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
		this.hexCode = hexCode.charAt(0) == '#' ? hexCode : "#"+hexCode;
	}

	public void setColor(RGB rgb) {
		this.rgb = rgb;
	}

	public static Color stringToColor(String color) {
		color = color.replaceAll(" ", "");
		String[] split = color.split(",");
		if (split.length > 0) {
			return new Color(new RGB(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
		}
		color = color.replaceAll("#", "");
		return new Color(color);
	}

	@Override public String toString() {
		if (this.hexCode != null)
			return this.hexCode;
		else
			return this.rgb.toString();
	}
}
