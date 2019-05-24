import javax.swing.JFrame;

public class Main{
	private static JFrame frame;
	
	float distance_to_land = 30000;
	float speed_per_second = 1700;
	double gravity = 1.62;
	Painter painter;
	spaceCraft mCraft;
	
	public static void main(String[] args) {
		spaceCraft mCraft = new spaceCraft();
		CPU painterCPU = new CPU(60,"painter"); // 60 FPS painter
		painterCPU.addFunction(frame::repaint);
		painterCPU.play();
		
		CPU updatesCPU = new CPU(10,"updates");
		updatesCPU.addFunction(mCraft::update);
		updatesCPU.addFunction(mCraft::startLanding);
		updatesCPU.addFunction(mCraft.mMain::Stabalize);
		updatesCPU.play();
		
		
	}
}
