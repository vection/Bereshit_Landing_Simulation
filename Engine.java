
public class Engine {
	
	double power_to_rotation; // Rotation 90 
	double power = 1; // power per sec 
	double main_engine_rotation;
	MainEngine mEngine;
	
	public Engine(MainEngine m, double powerto) {
		this.power_to_rotation = powerto;
		mEngine = m;
	}
	
	
	public void Stabilize() {
		if(mEngine.mSpace.ang > 0) {
			mEngine.mSpace.ang += power*power_to_rotation;
		}
	}
	
	

}
