package qiwics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;

public class GUI {
	public static void run(final Context c) {
		final GUI gui = new GUI(c);
		gui.shell.open();
				
		while (!gui.shell.isDisposed()) {
			if (gui.isExiting) {
				gui.shell.dispose();
			}
						
			if (!gui.display.readAndDispatch()) {
				gui.display.sleep();
			}
		}
		
		gui.display.dispose();
	}
	
	public GUI(final Context cx) {
		context = cx;
		
	    display = new Display();
	    shell = new Shell(display);
	    shell.setMaximized(true);
	    shell.setLayout(new FillLayout());
	    shell.setText(String.format("Qiwics v%d", Qiwics.version()));
	    shell.addDisposeListener((e) -> {
            exec.shutdown();
        });	    
	}
	
	private final Context context;
	private final Display display;
	private final ExecutorService exec = 
		Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors() + 2);
    private String font = "DejaVu Sans Mono";
    private int fontSize = 10;
	private boolean isExiting;
    private Menu menuBar;
    private final Shell shell;	
}
