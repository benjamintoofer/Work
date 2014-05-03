import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UnresponsiveUI extends JFrame{

	private boolean stop = false;
	private JTextField tfCount;
	private int count = 1;
	
	public UnresponsiveUI(){
		Container contPanel = this.getContentPane();
		contPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		contPanel.add(new JLabel("Counter"));
		tfCount = new JTextField(count +"",10);
		tfCount.setEditable(false);
		contPanel.add(tfCount);
		
		JButton bttnStart = new JButton("Start Counting");
		contPanel.add(bttnStart);
		bttnStart.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent evt){
				
				stop = false;
			Thread t = new Thread(){
				
			public void run(){
				for (int i = 0; i < 100000; ++i) {
		               if (stop) break;  // check if STOP button has been pushed,
		                                 //  which changes the stop flag to true
		               tfCount.setText(count + "");
		               ++count;
		              
		            	}
					}
				};
				t.start();
			}
		});
		JButton bttnStop = new JButton("Stop");
		contPanel.add(bttnStop);
		bttnStop.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent evt){
				
				stop = true;
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Counter");
		this.setSize(300, 120);
		this.setVisible(true);
	}
public static void main(String[] args){
	 //SwingUtilities.invokeLater(new Runnable() {
       //  public void run() {
            new UnresponsiveUI();  // Let the constructor do the job
        // }
      //});

	}	
}

