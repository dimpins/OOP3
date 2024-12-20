package Front;

import Front.ParrentAbstract.AbstractFront;
import Server.LocalServer;

import javax.swing.*;

public class DelBookWindow extends AbstractFront {         //окно удаления книги
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
        setActionCloseButton(closeButton);

        dellButton.addActionListener(e -> {         //действие удаления книги
            String author = authorField.getText();
            String bookName = nameBookField.getText();

            if(!LocalServer.isBook(bookName)){            //если такой книги нет
                JOptionPane.showMessageDialog(null, "Такой книги нет в базе данных!");
            } else {                   //если есть
                if(!LocalServer.isAuthor(bookName, author)){ //если не тот автор
                    JOptionPane.showMessageDialog(null, "Автора с такой книгой не нашлось!");
                } else {                 //если тот
                    LocalServer.dellBook(author, bookName);
                    JOptionPane.showMessageDialog(null, "Книга успешно удалена!");
                    authorField.setText("");
                    nameBookField.setText("");
                }
            }
        });
    }

}
