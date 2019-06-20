

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GUI_Frame extends JFrame{

	private DrawingPanel drawingPanel1;//, drawingPanel2;
	
	public GUI_Frame(){
		setSize(700,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel mainJP = new JPanel();

		mainJP.setLayout(new BorderLayout());
		drawingPanel1 = new DrawingPanel();
		JPanel westJP = new JPanel();
		westJP.setLayout(new GridLayout(1,3));
		ShapeSelectionPanel shapeSelectionPanel = new ShapeSelectionPanel();
		westJP.add(shapeSelectionPanel);
		
		SizeSelectionPanel sizeSelectionPanel = new SizeSelectionPanel();
		westJP.add(sizeSelectionPanel);
		
		FontSelectionPanel fontSelectionPanel = new FontSelectionPanel();
		westJP.add(fontSelectionPanel);
		
		ColorChooserPanel colorChooserJP = new ColorChooserPanel();
		
		mainJP.add(colorChooserJP, BorderLayout.NORTH);
		mainJP.add(westJP, BorderLayout.WEST);
		mainJP.add(drawingPanel1, BorderLayout.CENTER);
//		mainJP.add(drawingPanel2);
		add(mainJP);
	}
	
	
	
	@SuppressWarnings("serial")
	private class ShapeSelectionPanel extends JPanel implements ActionListener{
		private JRadioButton [] radBtnArr;
		private ButtonGroup radBtnGroup;
		private final int NUM_SHAPES = DrawingPanel.shapeNames.length;
		
		public ShapeSelectionPanel(){
			radBtnGroup = new ButtonGroup();
			radBtnArr = new JRadioButton[NUM_SHAPES];
			setLayout(new GridLayout(NUM_SHAPES,1));
			for(int i=0; i<radBtnArr.length; i++){
				radBtnArr[i] = new JRadioButton(DrawingPanel.shapeNames[i]);//initialized and put a String of text
				radBtnArr[i].setActionCommand(DrawingPanel.shapeNames[i]);//set the ActionCommand
				radBtnArr[i].addActionListener(this);//make it listen for events to trigger the actionPerformed
				radBtnGroup.add(radBtnArr[i]);//add to the group so only 1 is selected at a time
				add(radBtnArr[i]);//add the radio button to the ShapeSelection JPanel
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String actCmd = e.getActionCommand();
			drawingPanel1.setShape(actCmd);
		}
	}
	
	@SuppressWarnings("serial")
	private class SizeSelectionPanel extends JPanel implements ActionListener{ // make a Shapesizes at drawingpanel for an array of sizes
		private JRadioButton [] BtnArr;
		private ButtonGroup BtnGroup;
		private final int NUM_SIZES = DrawingPanel.shapeSizes.length;
		
		public SizeSelectionPanel(){
			BtnGroup = new ButtonGroup();
			BtnArr = new JRadioButton[NUM_SIZES];
			
			setLayout(new GridLayout(NUM_SIZES,1));
			for(int i=0; i<BtnArr.length; i++){
				BtnArr[i] = new JRadioButton(Integer.toString((DrawingPanel.shapeSizes[i])));//initialized and put a String of text
				BtnArr[i].setActionCommand(Integer.toString(DrawingPanel.shapeSizes[i]));//set the ActionCommand
				BtnArr[i].addActionListener((ActionListener) this);//make it listen for events to trigger the actionPerformed
				BtnGroup.add(BtnArr[i]);//add to the group so only 1 is selected at a time
				add(BtnArr[i]);//add the radio button to the ShapeSelection JPanel
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String actCmd = e.getActionCommand();
			drawingPanel1.setSize(actCmd);
			
		}
	}
	@SuppressWarnings("serial")
	private class FontSelectionPanel extends JPanel implements ActionListener{ //made a Fonts Array so that the user can choose multiple
																			   //Fonts as his/her preference
	
		private JRadioButton [] BtnArr;
		private ButtonGroup BtnGroup;
		private final int num_fonts = DrawingPanel.Fonts.length;
		
		public FontSelectionPanel(){
			BtnGroup = new ButtonGroup();
			BtnArr = new JRadioButton[num_fonts];
			
			setLayout(new GridLayout(num_fonts,1));
			for(int i=0; i<BtnArr.length; i++){
				BtnArr[i] = new JRadioButton(DrawingPanel.Fonts[i]);//initialized and put a String of text
				BtnArr[i].setActionCommand(DrawingPanel.Fonts[i]);//set the ActionCommand
				BtnArr[i].addActionListener((ActionListener) this);//make it listen for events to trigger the actionPerformed
				BtnGroup.add(BtnArr[i]);//add to the group so only 1 is selected at a time
				add(BtnArr[i]);//add the radio button to the ShapeSelection JPanel
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String actCmd = e.getActionCommand();
			drawingPanel1.setFont(actCmd);
						
		}
	}
		
	@SuppressWarnings("serial")
	private class ColorChooserPanel extends JPanel implements ActionListener{
		private JButton jb;
		private JColorChooser colChooser;
		public ColorChooserPanel(){
			colChooser = new JColorChooser();
			jb = new JButton("choose a color");
			jb.addActionListener(this);
			add(jb);
		}
		@Override
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			Color chosenColor = 
					colChooser.showDialog(null, 
							"Choose Color", 
							drawingPanel1.getColor());
			drawingPanel1.setColor(chosenColor);
	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
