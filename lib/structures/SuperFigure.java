package structures;

public class SuperFigure {

	Figure[] figures;
	String id;

	public SuperFigure(Figure[] figures, String id) {
		this.figures = figures;
		this.id = id;
	}

	public Figure[] figures() {
		return this.figures;
	}

	public String id() {
		return this.id;
	}

	public void setFigures(Figure[] figures) {
		this.figures = figures;
	}

	public void setId(String id) {
		this.id = id;
	}
}
