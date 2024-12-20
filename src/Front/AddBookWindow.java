package Front;

import Front.ParrentAbstract.AbstractFront;

import javax.swing.*;

public class AddBookWindow extends AbstractFront {
    private JPanel panel;
    private JLabel textAddBook;
    private JButton addButton;
    private JTextField authorField;
    private JTextField nameBookField;
    private JLabel textAuthor;
    private JLabel textNameBook;
    private JButton closeButton;

    public AddBookWindow() {
        setFrame(panel);
    }

    @Override
    protected void setOtherElements() {
        super.setElementsBookWindow(textAddBook, authorField, nameBookField, textAuthor,
                textNameBook, addButton, closeButton);
    }

}
