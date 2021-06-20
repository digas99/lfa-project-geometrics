package structures;

public class Color {
    
	String hexCode;
	RGB rgb;
	String id;

	public Color(String id, String hexCode) {
		this.id = id;
		this.hexCode = hexCode.charAt(0) == '#' ? hexCode : "#"+hexCode;
		String v0 = this.hexCode.charAt(1) + this.hexCode.charAt(2) +"";
		String v1 = this.hexCode.charAt(3) + this.hexCode.charAt(4) +"";
		String v2 = this.hexCode.charAt(5) + this.hexCode.charAt(6) +"";
		System.out.println("\n\n\n\n\nV0:\n");
		System.out.println(v0);
		System.out.println("\n\n\n\n\n");
		this.rgb = new RGB(Integer.parseInt(v0, 16), Integer.parseInt(v1, 16), Integer.parseInt(v2, 16));
	}

	public Color(String id, RGB rgb) {
		this.id = id;
		this.rgb = rgb;
		this.hexCode = "#"+Integer.toHexString(this.rgb.p0())+Integer.toHexString(this.rgb.p1())+Integer.toHexString(this.rgb.p2());
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
		return String.format("%s (%s)", this.hexCode, this.rgb);
	}
}
