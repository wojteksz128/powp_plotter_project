package edu.iis.powp.command;

public class CommandDrawLineToPosition implements PlotterCommand {
	
	private int x;
	private int y;

	public CommandDrawLineToPosition(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
