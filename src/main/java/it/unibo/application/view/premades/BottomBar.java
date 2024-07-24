package it.unibo.application.view.premades;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import it.unibo.application.controller.Controller;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class BottomBar extends JPanel {
    public BottomBar(Controller controller) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(new JLabel("IDLE"));
    }
}
