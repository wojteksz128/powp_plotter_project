package edu.iis.powp.command;

public class CommandSetPosition implements PlotterCommand {
	
	private int x;
	private int y;

	public CommandSetPosition(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
