
public class MainEngine {
	CPU MainEngineCPU;
	Engine Right1;
	Engine Right2;
	Engine Left1;
	Engine Left2;
	Engine DownRight1;
	Engine DownRight2;
	Engine DownLeft1;
	Engine DownLeft2;
	spaceCraft mSpace;

	
	
	enum Status {
		Flying,
		Landing,
		BeforeLanding
	}
	double current_rotation;
	double balance_to = 0;
	double height;
	double rotation_before_landing = 58.3;
	Status mStatus;
	
	boolean start = true;
	public MainEngine(spaceCraft m) {
		init();
		MainEngineCPU = new CPU(1000,"SpaceCraft");
		mSpace = m;
	}
	public void init() {
		Right1 = new Engine(this, 1);
		Right2 = new Engine(this,0.75);
		Left1 = new Engine(this,1);
		Left2 = new Engine(this,0.75);
		DownRight1 = new Engine(this,-1);
		DownRight2 = new Engine(this,-0.75);
		DownLeft1 = new Engine(this,-1);
		DownLeft2 = new Engine(this,-0.75);
		mStatus = Status.BeforeLanding;
	}
	// Entering to final landing status
	public void startFinalLanding() {
		mStatus = mStatus.Landing;
	}
	// start stabilize status
	public void startStabalize() {
		start = true;
	}
	public void Stabalize(int deltaTime) {
		if(start) {
			height = mSpace.alt;
			current_rotation = mSpace.ang;
			if(mStatus == mStatus.BeforeLanding) {	
				if(current_rotation > rotation_before_landing+5) {	 
					DownRight1.Stabilize();
					DownLeft1.Stabilize();
				}
				else if(current_rotation > rotation_before_landing+3) {	 
					DownRight1.Stabilize();
					DownLeft2.Stabilize();
				}
				else if(current_rotation > rotation_before_landing+1) {	 
					DownRight2.Stabilize();
				}	
				else if(current_rotation < rotation_before_landing+5) {	 
					Right1.Stabilize();
					Left1.Stabilize();
				}
				else if(current_rotation < rotation_before_landing+3) {	 
					Right1.Stabilize();
					Left2.Stabilize();
				}
				else if(current_rotation < rotation_before_landing+1) {	 
					Right2.Stabilize();
				}	
			}
			else if(mStatus == mStatus.Landing) {	// three small engines stabilize 
					DownRight1.Stabilize();
					Right1.Stabilize();
					DownLeft1.Stabilize();
			}
		}
	}
	  

}
