package edu.iis.powp.command;

import java.util.List;

import edu.iis.client.plottermagic.IPlotter;

public class ComplexCommand implements PlotterCommand {
	
	private List<PlotterCommand> listOfCommands;

	public ComplexCommand(List<PlotterCommand> listOfCommands) {
		super();
		this.listOfCommands = listOfCommands;
	}

	@Override
	public void execute(IPlotter plotter) {
		listOfCommands.forEach(command -> command.execute(plotter));
	}
}
