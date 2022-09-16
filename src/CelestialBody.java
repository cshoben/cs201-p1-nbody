

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 * @author Chelsea Shoben
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	/**
	 *
	 * @return
	 */
	public double getX() {
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double dist;
		double rSqr;
		
		rSqr = (Math.pow( (this.myXPos - b.myXPos) , 2) + Math.pow( (this.myYPos - b.myYPos), 2));
		dist = Math.sqrt(rSqr);
		return dist;
	}

	public double calcForceExertedBy(CelestialBody b) {
		double force;
		double g = 6.67*1e-11;

		double dist = calcDistance(b);

		force = g * (this.myMass*b.myMass) / Math.pow(dist, 2);

		return force;
	}

	public double calcForceExertedByX(CelestialBody b) {
		double forceX;
		forceX = calcForceExertedBy(b) * ((b.myXPos - this.myXPos)) / calcDistance(b);

		return forceX;
	}
	public double calcForceExertedByY(CelestialBody b) {
		double forceY;
		forceY = calcForceExertedBy(b) * ((b.myYPos - this.myYPos)) / calcDistance(b);

		return forceY;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies) {
			if (! b.equals(this)){
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies) {
			if (! b.equals(this)){
				sum += calcForceExertedByY(b);
			}
			
		}
		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		double accX = xforce / this.myMass;
		double accY = yforce / this.myMass;;

		double nvx = this.myXVel + (deltaT * accX);
		double nvy = this.myYVel + (deltaT * accY);

		double nx = this.myXPos + (deltaT * nvx);
		double ny = this.myYPos + (deltaT * nvy);

		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;

	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
