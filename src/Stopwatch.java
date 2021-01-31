import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Stopwatch extends JFrame implements ActionListener {

	ImageIcon icon = new ImageIcon("stopwatch.png");
	JButton startButton = new JButton("Start");
	JButton resetButton = new JButton("Reset");
	JLabel timeLabel = new JLabel();
	int elapsedTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			elapsedTime = elapsedTime + 1000;
			hours = (elapsedTime/3600000);
			minutes = (elapsedTime/60000) % 60; // to prevent to go over 60 minutes
			seconds = (elapsedTime/1000) % 60;
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			hours_string = String.format("%02d", hours);
			timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		}
	});
	
	Stopwatch() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(410,500);
		this.setTitle("Stopwatch");
		this.setIconImage(icon.getImage());
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		timeLabel.setBounds(50,80,300,200);
		timeLabel.setFont(new Font("Stencil",Font.PLAIN,50));
		timeLabel.setBorder(BorderFactory.createEtchedBorder());
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		startButton.setBounds(100,350,100,50);
		startButton.setFont(new Font("Stencil",Font.PLAIN,20));
		startButton.setFocusable(false);
		startButton.setBackground(Color.lightGray);
		startButton.setForeground(Color.blue);
		startButton.addActionListener(this);
		
		resetButton.setBounds(200,350,100,50);
		resetButton.setFont(new Font("Stencil",Font.PLAIN,20));
		resetButton.setFocusable(false);
		resetButton.setBackground(Color.lightGray);
		resetButton.addActionListener(this);
		
		this.add(timeLabel);
		this.add(startButton);
		this.add(resetButton);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startButton) {
			if(started==false) {
				started=true;
				startButton.setText("Stop");
				startButton.setForeground(Color.red);
				start();
			} else {
				started=false;
				startButton.setText("Start");
				startButton.setForeground(Color.blue);
				stop();
			}
		}
		if(e.getSource()==resetButton) {
			started=false;
			startButton.setText("Start");
			startButton.setForeground(Color.blue);
			reset();
		}
		
	}
	
	void start() {
		timer.start();
	}
	
	void stop() {
		timer.stop();
	}

	void reset() {
		timer.stop();
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
	}

}
