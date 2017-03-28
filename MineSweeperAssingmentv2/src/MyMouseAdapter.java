import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	public void mousePressed(MouseEvent e) {

		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		myPanel.mouseDownGridX = myPanel.getGridX(x, y);
		myPanel.mouseDownGridY = myPanel.getGridY(x, y);
		myPanel.repaint();
		switch (e.getButton()) {
		case 1:		//Left mouse button
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {

		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame)c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);
		switch (e.getButton()) {
		case 1:		//Left mouse button
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]== Color.RED){
							// do nothing
						}else{
							if (myPanel.minesArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]==Color.BLACK) {
								//paint mines, end game
								for(int gX = 0;gX<9;gX++){
									for(int gY = 0;gY<9;gY++){
										if(myPanel.minesArray[gX][gY]==Color.BLACK){
											myPanel.colorArray[gX][gY]=Color.BLACK;
											myPanel.repaint();
										}else{
											myPanel.colorArray[gX][gY]=Color.LIGHT_GRAY;
											myPanel.repaint();
										}

									}
								}
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
								myPanel.repaint();


							} else {
								//paint square light gray
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]==Color.LIGHT_GRAY){
									//do nothing
								}else{
									if(!(myPanel.minesArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]==Color.BLACK))
										myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]=Color.LIGHT_GRAY;
									for(int mX=myPanel.mouseDownGridX-1;mX<=myPanel.mouseDownGridX+1;mX++){
										for(int mY=myPanel.mouseDownGridY-1;mY<=myPanel.mouseDownGridY+1;mY++){
											if(mX<9&&mX>-1&&mY<9&&mY>-1){
												if(!(myPanel.minesArray[mX][mY]==Color.BLACK)){
													myPanel.colorArray[mX][mY]=Color.LIGHT_GRAY;
												}
											}
										}
									}
								}

							}
							myPanel.repaint();
						}
					}
				}
			}

			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {

						if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == Color.WHITE || myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]==Color.RED) {

							if(!(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == Color.RED) ) {
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED; 
								myPanel.repaint();
							} else 
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == Color.RED) {
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE; 
									myPanel.repaint();
								}
						}
					}
				}
			}
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}