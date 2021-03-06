package edu.iis.powp.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.iis.client.plottermagic.ClientPlotter;
import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.LinePlotterAdapter;
import edu.iis.powp.adapter.PlotterMagicAdapter;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.Context;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.appext.ApplicationWithDrawer;
import edu.iis.powp.command.FigureCommandFactory;
import edu.iis.powp.events.predefine.SelectChangeVisibleOptionListener;
import edu.iis.powp.events.predefine.SelectTestFigureOneOptionListener;
import edu.iis.powp.events.predefine.SelectTestFigureTwoOptionListener;
import edu.kis.powp.drawer.panel.DefaultDrawerFrame;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.LineFactory;


public class TestPlotSoftPatterns
{
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
    /**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param context Application context.
	 */
	private static void setupPresetTests(Context context) {
	    SelectTestFigureOneOptionListener selectTestFigureOneOptionListener = new SelectTestFigureOneOptionListener();
	    SelectTestFigureTwoOptionListener selectTestFigureTwoOptionListener = new SelectTestFigureTwoOptionListener();
		
		context.addTest("Figure Joe 1", selectTestFigureOneOptionListener);
		context.addTest("Figure Joe 2", selectTestFigureTwoOptionListener);
		context.addTest("Rectangle", (a) -> FigureCommandFactory.getRectangle(-100, -50, 200, 100).execute(Application.getComponent(DriverManager.class).getCurrentPlotter()));
		context.addTest("Equilateral triangle", (a) -> FigureCommandFactory.getEquilateralTriangle(0, -66, 100).execute(Application.getComponent(DriverManager.class).getCurrentPlotter()));
		context.addTest("Circle", (a) -> FigureCommandFactory.getCircle(0,  0,  100).execute(Application.getComponent(DriverManager.class).getCurrentPlotter()));
	}

	/**
	 * Setup driver manager, and set default IPlotter for application.
	 * 
	 * @param context Application context.
	 */
	private static void setupDrivers(Context context) {
		IPlotter clientPlotter = new ClientPlotter();
		context.addDriver("Client Plotter", clientPlotter);
		Application.getComponent(DriverManager.class).setCurrentPlotter(clientPlotter);
		
		IPlotter plotter = new PlotterMagicAdapter(Application.getComponent(DrawPanelController.class));
		context.addDriver("Buggy Simulator", plotter);
		
		IPlotter dottedPlotter = new LinePlotterAdapter(Application.getComponent(DrawPanelController.class), LineFactory.getDottedLine());
		context.addDriver("Buggy Simulator with dotted line", dottedPlotter);
		
		IPlotter specialPlotter = new LinePlotterAdapter(Application.getComponent(DrawPanelController.class), LineFactory.getSpecialLine());
		context.addDriver("Buggy Simulator with special line", specialPlotter);

		context.updateDriverInfo();
	}

	/**
	 * Auxiliary routines to enable using Buggy Simulator.
	 * 
	 * @param context Application context.
	 */
	@SuppressWarnings("unused")
	private static void setupDefaultDrawerVisibilityManagement(Context context) {
		DefaultDrawerFrame defaultDrawerWindow = DefaultDrawerFrame.getDefaultDrawerFrame();
        context.addComponentMenuElementWithCheckBox(DrawPanelController.class, "Default Drawer Visibility", 
        		new SelectChangeVisibleOptionListener(defaultDrawerWindow), true);
        defaultDrawerWindow.setVisible(true);
	}
	
	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param context Application context.
	 */
	private static void setupLogger(Context context) {
		Application.addComponent(Logger.class);
		context.addComponentMenu(Logger.class, "Logger", 0);
		context.addComponentMenuElement(Logger.class, "Clear log", (ActionEvent e) -> context.flushLoggerOutput());
		context.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> LOGGER.setLevel(Level.FINE));
		context.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> LOGGER.setLevel(Level.INFO));
		context.addComponentMenuElement(Logger.class, "Warning level", (ActionEvent e) -> LOGGER.setLevel(Level.WARNING));
		context.addComponentMenuElement(Logger.class, "Severe level", (ActionEvent e) -> LOGGER.setLevel(Level.SEVERE));
		context.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> LOGGER.setLevel(Level.OFF));
	}
		
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ApplicationWithDrawer.configureApplication();
                Context context = Application.getComponent(Context.class);
                
            	setupDrivers(context);
            	setupPresetTests(context);
            	setupLogger(context);
            }

        });
    }

}
