import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class driver extends JPanel implements ActionListener, KeyListener {
	
	//GOOD Game. Just like how I played with old lady mee-maw
	//PERFECT 10/10 with SOLID variation
	
	int table_width = 800; //width of the screen "table"
	int table_height = 830;//height of the screen "table"
	
	
	//variables for ball
	int b_s = 20;//size
	int b_y = table_height/2 -10; //ball top left corner
	int b_x = table_width/2 -10; //ball top left corner
	int b_vx = 0;
	int b_vy = 0;
	
	//variables for a paddle
	int p_w = b_s/2;
	int p_h = 100;
	int p_x = table_width*6/8;
	int p_y = (table_height-p_h)/2;
	int p_vy = 5;
		
	//variables for computer paddle
	int c_w= b_s/2;
	int c_h= 100;
	int c_x= table_width*2/8;
	int c_y= (table_height-c_h)/2;
	
	
	
	//scoring variables
	int score1=0, score2=0, score3=0; //p1 and p2
	
	
	/*
	 * Paint all the graphics here.
	 */
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		Font font2 = new Font ("Courier New", 1, 50);
		g.setFont(font2);
		
		//Draw the table - background of black
		// use fillRect, what is the dimension of the table?
		// look for variables at the top you need to use
		g.fillRect(0,0, table_width, table_height);
		
		// draw a white line directly in the middle
		g.setColor(Color.white);
		for(int y=0; y<table_height; y=y+50){
			g.fillRect(table_width/2 -1, y , 2, 25);
		}
		
		//where is it drawn and how big? look for variables
		//above that you need to use. DO NOT HARDCODE NUMBERS
		g.fillRect(p_x,p_y,p_w,p_h);
		g.fillRect(c_x,c_y,c_w,c_h);
		//draw the score on the screen		
		Color myNewBlue = new Color (0, 255, 255);
		g.setColor(myNewBlue);
		for(int x1=0,y1=0,x2=800,y2=0; x1<800;x1 +=10,y2 +=7)
		{
			g.drawLine(x1,y1,x2,y2);
		}
		
		for(int x1=0, y1=800, x2=800, y2=800; x1<800; x1 +=10, y2 -=7)
		{
			g.drawLine(x1,y1,x2,y2);
		}
		
		for(int x1=800, y1=0, x2=0, y2=0; x1>0; x1 -=10, y2 +=7)
		{
			g.drawLine(x1,y1,x2,y2);
		}
		
		for(int x1=800, y1=800, x2=0, y2=800; x1>0; x1 -=10, y2 -=7)
		{
			g.drawLine(x1,y1,x2,y2);
		}
		g.setColor(Color.red);
		g.drawLine(720,265,720,535);
		g.drawLine(80,265,80,535);
		
		
		g.setColor(Color.yellow);
		String s1 = Integer.toString(score1);
		String s2 = Integer.toString(score2);
		g.drawString(s1, table_width/4,50);
		while(b_x+b_s >= 720){
			score1= score1 + 1;
			p_x = table_width*6/8;
			p_y = (table_height-p_h)/2;
			c_x= table_width*2/8;
			c_y= (table_height-c_h)/2;
			b_x= table_width/2 -10;
			b_y= table_height/2 -10;
			b_vx=0;
			b_vy=0;
			
		}
		g.drawString(s2, table_width*3/4,50);
		while(b_x+b_s <= 80){
			score2= score2 + 1;
			p_x = table_width*6/8;
			p_y = (table_height-p_h)/2;
			c_x= table_width*2/8;
			c_y= (table_height-c_h)/2;
			b_x= table_width/2 -10;
			b_y= table_height/2 -10;
			b_vx=0;
			b_vy=0;
			
		}
		
		//draw the ball - should be white and a square
		//before drawing ball, we'll randomize a color for it
		int red, green, blue;
		
	
		red = (int)(Math.random()*(256)+0);//[0,255]
		green = (int)(Math.random()*(256)+0);
		blue = (int)(Math.random()*(256)+0);
		Color c = new Color(red,green,blue);
		g.setColor(c);; //set color
				
		g.fillOval(b_x,b_y, b_s,b_s);

	}//end of paint method - put code above for anything dealing with drawing -
	
	/**
	 * Update the positions of the ball and paddle.
	 * Update the scores, counter and time
	 */
	public void update() {
		//update variables for game here
		//Assume this is run 60times per second 
		b_x = b_x + b_vx; 
		b_y = b_y + b_vy;

		
		//add a right side wall
		if(b_x+b_s >= table_width){
			b_vx*=-1; //flip the sign of the velocity of ball in x direction
		}
		
		if(b_y+b_s >= table_height){
			b_vy*=-1; //flip the sign of the velocity of ball in x direction
		}
		
		
		//add the left side wall using the top as your template
		if(b_x+b_s <= 0){
			b_vx*=-1;
		}
		
		if(b_y+b_s <= 0){
			b_vy*=-1;
		}
		
		if(p_y<=0){
			p_y=0;
		}
		if(c_y<=0){
			c_y=0;
		}
		if(p_y+p_h>=table_height){
			p_y=table_height-p_h;
		}
		if(c_y+c_h>=table_height){
			c_y=table_height-c_h;
		}
		if((b_y>=p_y)&&(b_y+b_s<=p_y+p_h)&&(b_x<=p_x+p_w)&&(b_x+b_s>=p_x)){
			b_vx+=2;
			b_vy+=2;
			b_vx*=-1;
			score3++;
			
		}
		if((b_y>=c_y)&&(b_y+b_s<=c_y+c_h)&&(b_x<=c_x+c_w)&&(b_x+b_s>=c_x)){
			b_vx*=-1;
			b_vx+=2;
			b_vy+=2;
			score3++;
			
		}

	for(int i=10, e=7; i<800; i+=10, e+=7){

		int x1=800-i, y1=0, x2=0, y2=e;
 		int j1 = (y2-y1)*(b_x-x1)/(x2-x1) + y1;
 		if(b_y<=j1 && b_x<=x1){
 			b_vx*= -1;
			b_vy*= -1;
			break;
 		}
 		
 		int x3=i, y3=0, x4=800, y4=e;
 		int j2 = (y4-y3)*(b_x-x3)/(x4-x3) + y3;
 		if(b_y<=j2 && (b_x+b_s)>=x3){
 			b_vx*= -1;
			b_vy*= -1;
			break;
 		}
 		
		int x5=i, y5=800, x6=800, y6=800-e;
 		int j3 = (y6-y5)*(b_x-x5)/(x6-x5) + y5;
 		if((b_y+b_s)>=j3 && (b_x+b_s)>=x5){
 			b_vx*= -1;
			b_vy*= -1;
 			break;
 		}

 		
 		int x7=800-i, y7=800, x8=0, y8=800-e;
 		int j4 = (y8-y7)*(b_x-x7)/(x8-x7) + y7;
 		if((b_y+b_s)>=j4 && b_x<=x7){
 			b_vx*= -1;
			b_vy*= -1;
			break;
 		}
	}
	
	}//end of update method - put code above for any updates on variable
	
		
	
	//==================code above ===========================
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}
	
	public static void main(String[] arg) {
		driver d = new driver();
	}
	public driver(){
		
		JFrame f = new JFrame();
		f.setTitle("Pong");
		f.setSize(table_width,table_height);
		f.setBackground(Color.black);
		f.setResizable(false);
		f.addKeyListener(this);
		f.add(this);
		t = new Timer(17,this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	Timer t;


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==38){
			p_y-=50;
		}
		if(arg0.getKeyCode()==40){
			p_y+=50;
		}
		if(arg0.getKeyCode()==87){
			c_y-=50;
		}
		if(arg0.getKeyCode()==83){
			c_y+=50;
		}
		if(arg0.getKeyCode()==32){
			int random= (int)(Math.random()*(6)+5);
			b_vx=random;
			b_vy=random;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		
		
		
		
	}

	
}









