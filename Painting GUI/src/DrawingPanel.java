

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel implements MouseListener,MouseMotionListener,KeyListener{

	
	public static final int EXTRA_SMALL = 10;
	public static final int SMALL = 25;
	public static final int MEDIUM = 50;
	public static final int LARGE = 75;
	public static final int EXTRA_LARGE = 100;
	public static final String NO_SHAPE = "none";
	public static final String CIRCLE = "circle";
	public static final String SQUARE = "square";
	public static final String PENTAGON = "pentagon";
	public static final String TEXT = "text";
	public static final String [] shapeNames = {NO_SHAPE, CIRCLE, SQUARE, PENTAGON, TEXT};
	public static final int [] shapeSizes = {EXTRA_SMALL, SMALL, MEDIUM,LARGE,EXTRA_LARGE};
	public static final String[] Fonts = {"TimesRoman","Helvetica","Courier","Dialog","Zapfdingbats","Monospaced"};

	
	private int xStart=0, yStart=0;
	private int size;
	private Color color;
	private String shape;
	private String Sentence="";
	private String text="";
	private String font;
	
	public DrawingPanel(){
		setBackground(Color.WHITE);
		
		color = Color.BLACK;//default
		size = MEDIUM;
		shape = NO_SHAPE;
		font = Fonts[0];
		addKeyListener(this);
		setFocusable(true);
		addMouseListener(this);//make the JPanel listen for mouse events
		addMouseMotionListener(this);//make the JPanel listen for MORE mouse events

	}
	private void drawShape(int x, int y){
		//Graphics g = getGraphics();
		Graphics2D g = (Graphics2D)getGraphics();
		g.setColor(color);
		int [] Xs = {x-size/3,x-size/3,x, x+size/3,x+size/3};
		int [] Ys = {y+size/3,y-size/3,y-((size/3)*2),y-size/3,y+size/3};
		switch(shape){
		case CIRCLE:
			g.drawOval(x - size/2, y - size/2, size, size);
			break;
		case SQUARE:
			g.fillRect(x - size/2, y - size/2, size, size);
			break;
		case PENTAGON:
			g.fillPolygon(Xs, Ys, 5);
			break;
		case TEXT:
			requestFocus();
			break;
		default:
			shape = NO_SHAPE;
			g.setStroke(new BasicStroke(size/8));
			g.drawLine(x,y,x,y);//Just a dot
		}
		g.dispose();
	}
	private void record(int x, int y){
		xStart = x;
		yStart = y;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		drawShape(x, y);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		record(e.getX(),e.getY()); //store the x,y
		text = ""; //Need this to clear out the previous text.
	}
	@Override
	public void mouseReleased(MouseEvent e) {
//		int xEnd = e.getX();
//		int yEnd = e.getY();
		
//		Graphics g = getGraphics();
		
//		g.setColor(color);
//		g.drawLine(xStart, yStart, xEnd, yEnd);
//		g.dispose();	
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//Nothing in here
		}
	@Override
	public void mouseExited(MouseEvent e) {
		//Nothing in here
		}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int xEnd = e.getX();
		int yEnd = e.getY();
		
		Graphics2D g = (Graphics2D)getGraphics();
		g.setStroke(new BasicStroke(size/10));
		g.setColor(color);
		g.drawLine(xStart, yStart, xEnd, yEnd);
		g.dispose();
		record(xEnd,yEnd); //store the x,y
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	public Color getColor(){
		return color;
	}
	protected void setColor(Color c){
		color = c;
	}
	protected void setShape(String theShape){
		if(theShape.equalsIgnoreCase(CIRCLE)){
			shape = CIRCLE;
		}
		else if(theShape.equalsIgnoreCase(SQUARE)){
			shape = SQUARE;
		}
		else if(theShape.equalsIgnoreCase(PENTAGON)){
			shape = PENTAGON;
		}
		else if(theShape.equalsIgnoreCase(TEXT)){
			shape = TEXT;
		}
		else{
			shape = NO_SHAPE;
			System.out.println("invalid shape was entered "+ theShape);
		}
	}
	protected void setSize(String theSize){
		if( EXTRA_SMALL == Integer.parseInt(theSize)){
			size = EXTRA_SMALL;
		}
		else if(SMALL == Integer.parseInt(theSize)){
			size = SMALL;
		}
		else if(MEDIUM == Integer.parseInt(theSize)){
			size = MEDIUM;
		}
		else if(LARGE == Integer.parseInt(theSize)){
			size = LARGE;
		}
		else if(EXTRA_LARGE== Integer.parseInt(theSize)){
			size = EXTRA_LARGE;
		}
		else{
			size = EXTRA_SMALL	;
			
			System.out.println("invalid size was entered "+ theSize+". Therefore going extra small.");
		}
	
	}
	protected void setFont(String theSize){
		if( theSize == Fonts[0]){
			font = Fonts[0];
		}
		else if( theSize == Fonts[1]){
			font = Fonts[1];
		}
		else if(theSize == Fonts[2]){
			font = Fonts[2];
		}
		else if(theSize == Fonts[3]){
			font = Fonts[3];
		}
		else if(theSize == Fonts[4]){
			font = Fonts[4];
		}
		else if(theSize == Fonts[5]) {
			font = Fonts[5];
		}
		else{
			font = Fonts[0]	; //as default
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) { //only happens
									   //When shape = text
		text = text+e.getKeyChar();	
		Graphics2D g = (Graphics2D)getGraphics();
		g.setColor(color);
		g.setFont((new Font(font, Font.PLAIN, size)));
		g.drawString(text,xStart,yStart);	

	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (text.length()>0) { //prevents from deleting when
							   //text.length() is already emptied
			
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
				Sentence = text.substring(0,text.length()-1);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			text = "";
			yStart = yStart+size;
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {	
		
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			text = Sentence;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			text = "";
		}//without this, the text that has been entered will
		 //start away from the x-axis i have clicked on.
	}
}
