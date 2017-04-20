package edu.iis.powp.command;

import java.util.LinkedList;
import java.util.List;

public class FigureCommandFactory {
	
	public static PlotterCommand getRectangle(int startX, int startY, int width, int height) {
		List<PlotterCommand> listOfCommands = new LinkedList<>();
		listOfCommands.add(new CommandSetPosition(startX, startY));
		listOfCommands.add(new CommandDrawLineToPosition(startX + width, startY));
		listOfCommands.add(new CommandDrawLineToPosition(startX + width, startY + height));
		listOfCommands.add(new CommandDrawLineToPosition(startX, startY + height));
		listOfCommands.add(new CommandDrawLineToPosition(startX, startY));
		
		return new ComplexCommand(listOfCommands);
	}
}
