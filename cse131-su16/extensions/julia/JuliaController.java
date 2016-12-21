package julia;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sedgewick.StdDraw;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JSlider;
import javax.swing.JTextArea;

import java.awt.Label;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class JuliaController extends JFrame {

	private JPanel contentPane;
	private Point start = new Point(0,0);
	private final Julia julia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JuliaController frame = new JuliaController();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JuliaController() {
		setTitle("Julia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		julia = new Julia(800, 800, 50);
		julia.draw();
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		final JPanel btnPanel = new JPanel();
		btnPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(btnPanel);
		btnPanel.setLayout(null);

		final JSlider IPPSlider = new JSlider();
		IPPSlider.setMajorTickSpacing(50);
		IPPSlider.setPaintLabels(true);
		IPPSlider.setMinorTickSpacing(50);
		IPPSlider.setSnapToTicks(true);
		IPPSlider.setPaintTicks(true);
		IPPSlider.setMaximum(300);
		IPPSlider.setMinimum(50);
		IPPSlider.setBounds(63, 143, 175, 46);
		btnPanel.add(IPPSlider);

		IPPSlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				julia.setIPP(IPPSlider.getValue());
				julia.draw();
			}
		});
		
		final JSlider numSamples = new JSlider();
		numSamples.setMajorTickSpacing(600);
		numSamples.setMaximum(2000);
		numSamples.setMinimum(200);
		numSamples.setValue(800);
		numSamples.setSnapToTicks(true);
		numSamples.setPaintTicks(true);
		numSamples.setPaintLabels(true);
		numSamples.setBounds(77, 221, 137, 43);
		btnPanel.add(numSamples);
		numSamples.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				julia.setNumSamples(numSamples.getValue(), numSamples.getValue());
				julia.draw();
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				julia.reset();
				julia.draw();
				IPPSlider.setValue(50);
				numSamples.setValue(800);
			}
		});
		btnReset.setBounds(105, 13, 89, 23);
		btnPanel.add(btnReset);

		JButton btnZoomIn = new JButton("Zoom In");
		btnZoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				julia.zoomIn();
				julia.draw();
			}
		});
		btnZoomIn.setBounds(63, 49, 89, 23);
		btnPanel.add(btnZoomIn);

		JButton btnZoomOut = new JButton("Zoom Out");
		btnZoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				julia.zoomOut();
				julia.draw();
			}
		});
		btnZoomOut.setBounds(149, 49, 89, 23);
		btnPanel.add(btnZoomOut);

		JButton btnMouse = new JButton("Mouse");
		btnMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				// Need to move this out of the way of the button handler, or we get
				//   deadlock with Sedgewick's mouse-polling approach.
				//
				new Thread(
						new Runnable() {
							public void run() {
								Color was = btnPanel.getBackground();
								btnPanel.setBackground(Color.RED);
								System.out.println("Click to select the upper left coordinate");
								while (!StdDraw.mousePressed())
									;
								System.out.println("Got upper left-hand corner, now click to select the lower right hand corner");
								btnPanel.setBackground(Color.GREEN);
								Complex ul = new Complex(StdDraw.mouseX(), StdDraw.mouseY());
								StdDraw.setPenColor(Color.GREEN);
								StdDraw.setPenColor(Color.GREEN);
								StdDraw.line(ul.getReal(), ul.getImaginary(), ul.getReal(), -2);
								StdDraw.line(ul.getReal(), ul.getImaginary(), 2, ul.getImaginary());
								StdDraw.show(200);
								while (!StdDraw.mousePressed())
									;
								System.out.println("Got lower right-hand corner");
								btnPanel.setBackground(was);
								Complex lr = new Complex(StdDraw.mouseX(), StdDraw.mouseY());
								StdDraw.line(lr.getReal(), lr.getImaginary(), lr.getReal(), 2);
								StdDraw.line(lr.getReal(), lr.getImaginary(), -2, lr.getImaginary());
								//
								// correct so area zoomed is square
								//
								//Complex diff = lr.minus(ul);
								//double sqlen = Math.max(diff.getReal(), -diff.getImaginary());
								//diff = new Complex(sqlen, -sqlen);
								//lr = ul.plus(diff);

								julia.setCoordinates(new Complex(ul.getReal(),lr.getImaginary()), new Complex(lr.getReal(),ul.getImaginary()));
								julia.draw();
							}
						}
						).start();
			}
		});
		btnMouse.setBounds(105, 85, 89, 24);
		btnPanel.add(btnMouse);

	

		Label label = new Label("IPP");
		label.setBounds(136, 120, 25, 24);
		btnPanel.add(label);

		JButton btnTestSetCoords = new JButton("Test 1");
		btnTestSetCoords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				julia.setCoordinates(new Complex(-.35, -.2), new Complex(-.1, .1));
				julia.setIPP(50);
				julia.draw();
			}
		});
		btnTestSetCoords.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTestSetCoords.setBounds(2, 294, 97, 25);
		btnPanel.add(btnTestSetCoords);

		JButton btnZoomTest = new JButton("Test 2");
		btnZoomTest.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnZoomTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				julia.setCoordinates(new Complex(-.31, -.19), new Complex(-.11, .04));
				julia.setIPP(50);
				julia.zoomOut();
				julia.zoomOut();
				julia.zoomOut();
				julia.zoomIn();
				julia.zoomIn();
				julia.zoomIn();
				julia.zoomIn();
				julia.zoomIn();
				julia.draw();
			}
		});
		btnZoomTest.setBounds(100, 294, 97, 25);
		btnPanel.add(btnZoomTest);

		JButton btnBumpTest = new JButton("Test 3");
		btnBumpTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				julia.setCoordinates(new Complex(-.29, -.22), new Complex(-.09, .01));
				julia.setIPP(250);
				julia.draw();
			}
		});
		btnBumpTest.setBounds(197, 294, 97, 25);
		btnPanel.add(btnBumpTest);

		JButton coolDesign1 = new JButton("Cool Design 1");
		coolDesign1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		coolDesign1.setBounds(25, 345, 97, 25);
		coolDesign1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				julia.setCoordinates(new Complex(-.006, .21), new Complex(.011, .23));
				julia.setIPP(200);
				julia.draw();
			}
		});
		btnPanel.add(coolDesign1);

		JButton coolDesign2 = new JButton("Cool Design 2");
		coolDesign2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		coolDesign2.setBounds(171, 345, 97, 25);
		coolDesign2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				julia.setCoordinates(new Complex(-.3002, -.3051), new Complex(-.291, -.297));
				julia.setIPP(100);
				julia.draw();
			}
		});
		btnPanel.add(coolDesign2);

		JLabel lblSamples = new JLabel("Samples");
		lblSamples.setBounds(119, 202, 56, 16);
		btnPanel.add(lblSamples);

		

// make num iters a parameter in the constructor



	}
}
