package structures;

public class Color {
    
	String hexCode;
	RGB rgb;
	String id;

	public Color(String id, String hexCode) {
		this.id = id;
		this.hexCode = hexCode.charAt(0) == '#' ? hexCode : "#"+hexCode;
	}

	public Color(String id, RGB rgb) {
		this.id = id;
		this.rgb = rgb;
	}

	public Color(String hexCode) {
		this.hexCode = hexCode.charAt(0) == '#' ? hexCode : "#"+hexCode;
	}

	public Color(RGB rgb) {
		this.rgb = rgb;
	}

	public Color(Color color) {
		if (color.hexCode() != null) 
			this.hexCode = color.hexCode();
		else
			this.rgb = color.rgb();
		this.id = color.id();
	}

	public Color(String id, Color color) {
		if (color.hexCode() != null)
			this.hexCode = color.hexCode();
		else
			this.rgb = color.rgb();
		this.id = id;
	}

	public String id() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String hexCode() {
		return hexCode;
	}

	public RGB rgb() {
		return rgb;
	}

	public void setColor(String hexCode) {
		this.hexCode = hexCode.charAt(0) == '#' ? hexCode : "#"+hexCode;
	}

	public void setColor(RGB rgb) {
		this.rgb = rgb;
	}

	public static Color parseColor(String color) {
		color = color.replaceAll(" ", "");
		String[] split = color.split(",");
		if (split.length > 1) {
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