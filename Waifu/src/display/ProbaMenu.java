package display;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProbaMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProbaMenu() {
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		add(mntmNewMenuItem);
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);

	}

}
