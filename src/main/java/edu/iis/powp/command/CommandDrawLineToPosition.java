package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

public class CommandDrawLineToPosition implements PlotterCommand {
	
	private int x;
	private int y;

	public CommandDrawLineToPosition(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute(IPlotter plotter) {
		plotter.drawTo(x, y);
	}

}
