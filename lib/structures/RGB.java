package structures;

public class RGB {
    private int p0;
    private int p1;
    private int p2;

    public RGB(int p0, int p1, int p2){
        assert p0 > 0 && p0 <= 255;
        assert p1 > 0 && p1 <= 255;
        assert p2 > 0 && p2 <= 255;
        
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;  
    }

	public int p0() {
		return this.p0;
	}

    public void setP0(int p0) {
        this.p0 = p0;
    }

	public int p1() {
		return this.p1;
	}
    
    public void setP1(int p1){
        this.p1 = p1;
    }

	public int p2() {
		return this.p2;
	}

    public void setP2(int p2) {
		this.p2 = p2;
	}

    @Override public String toString() {
        return this.p0+","+this.p1+","+this.p2;
    }
}
