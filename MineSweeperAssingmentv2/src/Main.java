import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Color Grid");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);
		
		JFrame popUpmsg = new JFrame("Pop Up MSG");
		popUpmsg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUpmsg.setLocation(500, 250);
		popUpmsg.setSize(100, 100);
		//created pop up msg 
		
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		
		JLabel label1 = new JLabel("StartMsg");
		label1.setText("               Start!");
		popUpmsg.add(label1);
		popUpmsg.setVisible(true);
		try{
			Thread.sleep(2000); 
		}
		catch(InterruptedException e){}
		popUpmsg.setVisible(false);
		//added a small delay between visibles to let the user know the game is about to start
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
		while(true){
			if(myPanel.gameHasEnded()){
				label1.setText("       You lose");
				popUpmsg.add(label1);
				popUpmsg.setVisible(true);
				break;
			}
		}
		//while loop to make sure to always check the if statement to know if the game has ended
	}
}