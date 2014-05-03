import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new JFrame("Rectangle");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new TestWorld());
				frame.pack();
				frame.setVisible(true);
			}
			
		});

	}

}
