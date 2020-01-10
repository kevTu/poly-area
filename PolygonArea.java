public class PolygonArea {
	
	private int numOfPoints = 0;	
	private double [] x = new double [numOfPoints];
	private double [] y = new double [numOfPoints];
	
	
	public PolygonArea() {
		
	}
	
	public PolygonArea(int numOfPoints, double [] x, double [] y) {
		this.numOfPoints = numOfPoints;
		this.x = x;
		this.y = y;
	}
	
	private double areaOfPolygon() {
		int numOfTriangles = numOfPoints - 2;
		double [] triangle = new double[numOfTriangles];
		double sum = 0;
		for(int i = 0; i < numOfTriangles; i++) {
			double [] side = {distance(x[0], y[0], x[i+1], y[i+1]), distance(x[i+1], y[i+1], x[i+2], y[i+2]), distance(x[i+2], y[i+2], x[0], y[0])};
			triangle[i] = heronFormula(side[0], side[1], side[2]);
			sum += triangle[i];
		}
		return sum;
	}
	
	private boolean isConvex() {
		if (x.length == 3)
			return true;

		boolean sign = false;

		for (int i = 0; i < x.length - 2; i++) {
			double dx1 = x[i + 2] - x[i + 1];
			double dy1 = y[i + 2] - y[i + 1];
			double dx2 = x[i + 1] - x[i];
			double dy2 = y[i + 1] - y[i];

			double zCrossProduct = (dx1 * dy2) - (dy1 * dx2);
			if (i == 0)
				sign = zCrossProduct > 0;
			else if (sign != (zCrossProduct > 0))
				return false;
		}
		return true;

	}

	private double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	
	private double heronFormula(double a, double b, double c) {
		double s = (a + b + c) / 2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}
	
	public double getPolygonArea() {
		return areaOfPolygon();
	}
	
	public boolean getIsConvex() {
		return isConvex();
	}
}
