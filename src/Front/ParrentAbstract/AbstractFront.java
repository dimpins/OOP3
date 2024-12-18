package Front.ParrentAbstract;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractFront extends JFrame {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int DEFAULT_WIDTH_WINDOW = 1280;
    public static final int DEFAULT_HEIGHT_WINDOW = 720;


    protected void setFrame(JPanel panel) {
        setBoundsCenter(this,screenSize.width, screenSize.height,
                DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW);
        setTitle("Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setBackground(Color.WHITE);
        this.setOtherElements();
        panel.setLayout(null);
        add(panel);
        setVisible(true);
        setBoundsCenter(panel, DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW,
                DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW);
    }

    protected void setBoundsCenter(Container container, int x, int y, int width, int height) {
        container.setBounds(x/2 - width/2,
                y/2 - height/2, width, height );
    }

    protected abstract void setOtherElements();

}
