package Front;

import Front.ParrentAbstract.AbstractFront;

import javax.swing.*;

public class DelBookWindow extends AbstractFront {
    private JPanel panel;
    private JLabel textDellBook;
    private JButton dellButton;
    private JTextField authorField;
    private JTextField nameBookField;
    private JLabel textAuthor;
    private JLabel textNameBook;
    private JButton closeButton;

    public DelBookWindow() {
        setFrame(panel);
    }

    @Override
    protected void setOtherElements() {
        super.setElementsBookWindow(textDellBook, authorField, nameBookField, textAuthor,
                textNameBook, dellButton, closeButton);
    }

}
