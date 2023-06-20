import javax.swing.JFrame;

public class Main {

	JFrame frame;
	GamePanel panel;
	public static final int HEIGHT = 800;
	public static final int LENGTH = 500;
	
	public Main() {
		frame = new JFrame();
		panel = new GamePanel();
	}
	
	void setup() {
		frame.add(panel);
		frame.setSize(HEIGHT, LENGTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);
	}
	public static void main(String[] args) {
		Main game = new Main();
		game.setup();
	}
}
