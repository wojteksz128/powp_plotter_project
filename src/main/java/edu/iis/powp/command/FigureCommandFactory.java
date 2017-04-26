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
	
	public static PlotterCommand getEquilateralTriangle(int startX, int startY, int length) {
		List<PlotterCommand> listOfCommands = new LinkedList<>();
		listOfCommands.add(new CommandSetPosition(startX, startY));
		listOfCommands.add(new CommandDrawLineToPosition(startX + (int)(Math.sin(Math.toRadians(150)) * length), startY - (int)(Math.cos(Math.toRadians(150)) * length)));
		listOfCommands.add(new CommandDrawLineToPosition(startX + (int)(Math.sin(Math.toRadians(210)) * length), startY - (int)(Math.cos(Math.toRadians(210)) * length)));
		listOfCommands.add(new CommandDrawLineToPosition(startX, startY));
		
		return new ComplexCommand(listOfCommands);
	}
	
	public static PlotterCommand getCircle(int startX, int startY, int radius) {
		List<PlotterCommand> listOfCommands = new LinkedList<>();
		
		float angle = 0.0f;
		listOfCommands.add(new CommandSetPosition((int)(startX + Math.sin(angle) * radius), (int)(startY - Math.cos(angle) * radius)));
		
		for (; angle <= 2 * Math.PI; angle += 0.01) {
			listOfCommands.add(new CommandDrawLineToPosition((int)(startX + Math.sin(angle) * radius), (int)(startY - Math.cos(angle) * radius)));
		}
		
		return new ComplexCommand(listOfCommands);
	}
}
