/**
 * Created by Valentin on 02/06/2017.
 */

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

public class CreateButton {
		JButton name;
		
		public CreateButton(String symbol, ActionListener listener, JPanel pane) {
			name = new JButton(symbol);
			name.addActionListener(listener);
			name.setActionCommand(symbol);
			pane.add(name);
		}
	}
