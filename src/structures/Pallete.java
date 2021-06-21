package structures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pallete {
	
	private List<Color> colors;
	private String id;

	public Pallete(String id, List<Color> colors) {
		this.colors = colors;
		this.id = id;
	}

	public Pallete(String id, Color color) {
		this.colors = new ArrayList<>();
		this.colors.add(color);
		this.id = id;
	}

	public Pallete(String id, Color... colors) {
		this.colors = new ArrayList<>();
		for (Color color : colors) {
			this.colors.add(color);
		}
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String id() {
		return this.id;
	} 

	public void setColors(Color color) {
		this.colors = new ArrayList<>();
		this.colors.add(color);
	}

	public void setColors(Color... colors) {
		this.colors = new ArrayList<>();
		for (Color color : colors) {
			this.colors.add(color);
		}
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public List<Color> colors() {
		return this.colors;
	}

	public Color[] colorsArray() {
		Color[] colorsArray = new Color[this.colors.size()];
		this.colors.toArray(colorsArray);
		return colorsArray;
	}

	public void add(Color color) {
		this.colors.add(color);
	}

	public void add(Color... colors) {
		for (Color color : colors) {
			this.colors.add(color);
		}
	}

	public void add(List<Color> colors) {
		this.colors.addAll(colors);
	}

	public void replace(int index, Color color) {
		this.colors.set(index, color);
	}

	public Color first() {
		return this.colors.get(0);
	}

	public Color last() {
		return this.colors.get(this.colors.size()-1);
	}

	public Color get(int index) {
		return this.colors.get(index);
	}

	public Color remove(int index) {
		Color toRemove = this.colors.get(index);
		this.colors.remove(index);
		return toRemove;
	}

	public Color shift() {
		return this.remove(0);
	}

	@Override public String toString() {
		return String.format("%s \"%s\"\nColors: [%d] %s\n", this.getClass().getSimpleName(), this.id(), this.colors.size(), "{"+String.join(", ", this.colors().stream().map(color -> color.id()+": "+color.toString()).collect(Collectors.toList()))+"}");
	}
}
