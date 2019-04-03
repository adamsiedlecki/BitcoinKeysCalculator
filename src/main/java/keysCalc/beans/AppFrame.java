package keysCalc.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class AppFrame extends JFrame {


    private JPanel panel; // = new AppPanel();

    @Autowired
    public AppFrame(JPanel panel, Dimension frameDimesion) {
        this.panel = panel;
        add(panel);
        setSize(frameDimesion);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        double x = d.getWidth() / 3;
        double y = d.getHeight() / 4;

        setLocation((int) x, (int) y);
    }


}
