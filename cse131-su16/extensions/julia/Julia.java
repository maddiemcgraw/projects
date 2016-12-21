package julia;

import java.awt.Color;
import java.awt.Image;

import sedgewick.StdDraw;

public class Julia {



	/**
	 * 
	 * @param numRealSamples
	 * @param numImaginarySamples
	 * @param IPP
	 */
	public Julia(int numRealSamples, int numImaginarySamples, int IPP) {
		// FIXME
	}


	/**
	 * Refresh the display by computing point by point the color value as described in the lab.
	 * It may be helpful to call StdDraw.show(0) before and after you are done, so that the display need not be
	 * update after each point is computed.
	 */
	public void draw() {
		// FIXME
	}

	
	/**
	 * Reset the display so that the supplied complex coordinates frame the lower left and upper right of what is seen.
	 * @param ll new lower-left coordinate for the displayed area
	 * @param ur new upper-right coordinate for the displayed area.
	 */
	public void setCoordinates(Complex ll, Complex ur) {
		//FIXME
	}
	
	/**
	 * Establish the coordinates of the display to go from -2 - 2i at the lower-lefthand corner to 2 + 2i at the upper-right,
	 * and set the maximum number of iterations to 50.
	 */
	public void reset() {
		//FIXME
	}
	
	/**
	 * Zoom in to the area framed by 1/4 of the current display.
	 */
	public void zoomIn() {
		//FIXME
	}

	/**
	 *  Inverse of {@link zoomIn() zoomIn}:  as if the currently viewed display becomes framed by 1/4 of the new display all around.
	 */
	public void zoomOut() {
		//FIXME
	}

	/**
	 * Sets the IPP
	 * @param IPP
	 */
	public void setIPP(int IPP) {
		//FIXME
	}
	
	/**
	 * sets the number of samples
	 * @param numRealSamples
	 * @param numImaginarySamples
	 */
	public void setNumSamples(int numRealSamples, int numImaginarySamples){
		//FIXME
	}


}



