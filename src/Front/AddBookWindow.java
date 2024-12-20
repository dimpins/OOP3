package Front;

import Front.ParrentAbstract.AbstractFront;
import Server.LocalServer;

import javax.swing.*;

public class AddBookWindow extends AbstractFront {       //окно добавления книги
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
        setActionCloseButton(closeButton);

        addButton.addActionListener(e -> {        //действие кнопки добавления
           String author = authorField.getText();
           String bookName = nameBookField.getText();

           if(LocalServer.isBook(bookName)){             //если такая книга уже есть в бд
               JOptionPane.showMessageDialog(null, "Такая книга уже есть в базе данных!");
           } else {               //если новая
               LocalServer.addBook(author, bookName);
               JOptionPane.showMessageDialog(null, "Книга успешно добавлена!");
               authorField.setText("");
               nameBookField.setText("");
           }

        });
    }

}
