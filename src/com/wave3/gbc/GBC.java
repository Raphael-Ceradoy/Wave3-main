package com.wave3.gbc;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GBC extends GridBagConstraints{
	/** 
	 * * Constructs a GBC with a given gridx and gridy position and all other grid * bag constraint values set to the default.
	 */
	public GBC(int gridx, int gridy) {

		this.gridx = gridx;
		this.gridy = gridy;
	}
	public GBC(int gridx, int gridy, int gridwidth, int gridheight)
	{ 	this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	
	public GBC setAnchor(int anchor){ 
		this.anchor = anchor;
		return this;
	}
	
	/** 
	 * * Sets the fill direction.
	 * @param fill the fill direction
	 * @return this object for further modification
	 */
	public GBC setFill(int fill) {

		this.fill = fill;
		return this; 
	}
	

	/* Sets the cell weights.
	 * @param weightx the cell weight in x-direction 
	 * @param weighty the cell weight in y-direction
	 * @return this object for further modification 
	 * */
	public GBC setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}
	
	/**
	 * Sets the insets of this cell.
	 * @param distance the spacing to use in all directions
	 * @return this object for further modification
	 */
	public GBC setInsets(int distance){
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}
	
	public GBC setInsets(int top, int left, int bottom, int right)
	{ 	this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	public GBC setIpad(int ipadx, int ipady){
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}