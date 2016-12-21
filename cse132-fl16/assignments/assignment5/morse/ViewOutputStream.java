package assignment5.morse;
import java.io.*;
import javax.swing.JFrame;
import studio4.PrintStreamPanel;
public class ViewOutputStream extends FilterOutputStream{
	    final private PrintStreamPanel psp;
	    final private PrintStream ps;
	    public ViewOutputStream(OutputStream out) {
	        super(out);
	        JFrame f = new JFrame("ViewInputStream");
			f.setBounds(100,100,225,300);
	        psp = new PrintStreamPanel();
			f.getContentPane().add(psp);
			f.setVisible(true);
	        ps = psp.getPrintStream(); 
	    }
	    @Override
	    public void write(int i) throws IOException {
	    	ps.println(Integer.toHexString(i));
	    	super.write(i);
	}
}
