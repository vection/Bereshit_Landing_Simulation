import java.awt.Graphics;
import java.util.Random;

public class spaceCraft {
	
	public static final double WEIGHT_EMP = 165; // kg
	public static final double WEIGHT_FULE = 420; // kg
	public static final double WEIGHT_FULL = WEIGHT_EMP + WEIGHT_FULE; // kg
// https://davidson.weizmann.ac.il/online/askexpert/%D7%90%D7%99%D7%9A-%D7%9E%D7%98%D7%99%D7%A1%D7%99%D7%9D-%D7%97%D7%9C%D7%9C%D7%99%D7%AA-%D7%9C%D7%99%D7%A8%D7%97
	public static final double MAIN_ENG_F = 430; // N
	public static final double SECOND_ENG_F = 25; // N
	public static final double MAIN_BURN = 0.15; //liter per sec, 12 liter per m'
	public static final double SECOND_BURN = 0.009; //liter per sec 0.6 liter per m'
	public static final double ALL_BURN = MAIN_BURN + 8*SECOND_BURN;
	public static double accMax(double weight) {
		return acc(weight, true,8);
	}
	public static double acc(double weight, boolean main, int seconds) {
		double t = 0;
		if(main) {t += MAIN_ENG_F;}
		t += seconds*SECOND_ENG_F;
		double ans = t/weight;
		return ans;
	}
	
	double gravity = 1.62;
	private CPU cpu;
	boolean debug = true;
	boolean landed = false;
	double vs = 24.8; // Vertical speed
	double hs = 932; // Horizontal speed
	double dist = 181*1000; // Distance to land
	double ang = 58.3; // zero is vertical (as in landing) Orientation
	double alt = 13478; // 2:25:40 (as in the simulation) // https://www.youtube.com/watch?v=JJ0VfRL9AMs Height in alttitude 13748 32947
	double time = 0; // Delta time
	double dt = 1; // sec
	double acc=0; // Acceleration rate (m/s^2)
	double distance_moved=0;
	double fuel = 121; // Fuel
	double weight = WEIGHT_EMP + fuel;
	double NN = 0.7; // rate[0,1] 
	
	MainEngine mMain = new MainEngine(this);
	public spaceCraft() {
		intalize();
	}
	public void intalize() {
		cpu = new CPU(500,"SpaceCraft");
	}
	
	
	void update(int deltaTime) {
		if(alt < 2000) mMain.startFinalLanding();
		if(!landed) {
			double ang_rad = Math.toRadians(ang);
			double h_acc = Math.sin(ang_rad)*acc;
			double v_acc = Math.cos(ang_rad)*acc;
			double vacc = Moon.getAcc(hs);	
			time+=dt;
			double as = (hs/dist)/Math.toRadians(Math.PI*2); // Thinking it's Angular speed per second.
			ang += as;
			double dw = dt*ALL_BURN*NN;
			if(fuel>0) {
				fuel -= dw;
				weight = WEIGHT_EMP + fuel;
				acc = NN* accMax(weight);
			}
			else { // ran out of fuel
				acc=0;
			}

			v_acc -= vacc; // moon affects the vertical acceleration 
			if(hs>0) {hs -= h_acc*dt;} // updating hs 
			dist -= hs*dt; // updating distance
			vs -= v_acc*dt;
			if(vs < 0) { vs = 1; }
			alt -= dt*vs;  // updating alltitude
			distance_moved += hs*dt;
			if(debug) {
				if(time % 10 == 0 || time > 560) 
				  System.out.println("Time: "+time+" Height: "+alt +" VS: "+ vs + " HS: "+hs +" Weight: "+weight +" Acceleration: " +acc +"Rotation: "+ang+" Distance: "+(distance_moved/1000)+" km");
			}
		}
		
		
	}

	
	void startLanding(int deltaTime) {
		if(alt <= 1) {
			landed = true;
		}
		if(!landed) {
			// over 2 km above the ground
			if(alt>2000) {	// maintain a vertical speed of [20-25] m/s
				if(vs >25) {NN+=0.003*dt;} // more power for braking
				if(vs <20) {NN-=0.003*dt;} // less power for braking
			}
			// lower than 2 km - horizontal speed should be close to zero
			else {
				if(ang>3) { mMain.startStabalize(); }
				if(ang > 20) {
					NN = 0.63;
				}
				else {
					NN = 0.55;
				}
				if(hs<2) {hs=0;}
				if(alt<125) { // very close to the ground!
					if(vs<5) {NN=0.7;} 
					else { NN = 1; }// if it is slow enough - go easy on the brakes 
				}
			}
		}
	}
 	

}
