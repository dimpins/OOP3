package Front;

import Front.ParrentAbstract.AbstractFront;
import Server.LocalServer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MainWindowManager extends AbstractFront {        //основное окно для менеджера

    private JPanel panel;
    private JTable tableData;
    private DefaultTableModel tableModel = LocalServer.addAllBooksToTableModel();
    private JComboBox comboBoxSort;
    private JScrollPane scrollData;
    private JButton buttonSearch;
    private JTextField fieldSearch;
    private JButton addBookButton;
    private JButton listAccounts;
    private JButton exitButton;
    private JButton delBook;

    public MainWindowManager() {
        super.setFrame(panel, tableData, tableModel);
    }

    @Override
    protected void setOtherElements() {
        super.setTableDataCenter(tableData, scrollData);
        setBoundsCenter(comboBoxSort, DEFAULT_WIDTH_WINDOW / 2 - 200,
                DEFAULT_HEIGHT_WINDOW / 2 - 250, 300, 40);
        setActionComboBoxSort(comboBoxSort, tableData, tableModel);

        setBoundsCenter(fieldSearch, DEFAULT_WIDTH_WINDOW - 200,
                DEFAULT_HEIGHT_WINDOW / 2 - 300, 250, 40);

        fieldSearch.setDocument(new PlainDocument() {
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {      //ограничиваем ввод до 45 символов
                if (getLength() + str.length() <= 45) {
                    super.insertString(offs, str, a);
                }
            }
        });

        setBoundsCenter(buttonSearch, DEFAULT_WIDTH_WINDOW - 200,
                DEFAULT_HEIGHT_WINDOW / 2 - 200, 200, 40);
        setActionSearchButton(buttonSearch, fieldSearch, tableData, tableModel);

        setBoundsCenter(addBookButton, DEFAULT_WIDTH_WINDOW + 350,
                DEFAULT_HEIGHT_WINDOW / 2 - 300, 200, 40);
        addBookButton.addActionListener(e -> {    //кнопка добавления книги
           MainWindowManager.this.dispose();
           AddBookWindow addBookWindow = new AddBookWindow();
        });

        setBoundsCenter(delBook, DEFAULT_WIDTH_WINDOW + 350,
                DEFAULT_HEIGHT_WINDOW / 2 - 200, 200, 40);
        delBook.addActionListener(e -> {          //кнопка удаления книги
            MainWindowManager.this.dispose();
            DelBookWindow delBookWindow = new DelBookWindow();
        });

        setBoundsCenter(listAccounts, DEFAULT_WIDTH_WINDOW + 850,
                DEFAULT_HEIGHT_WINDOW / 2 - 250, 250, 40);
        listAccounts.addActionListener(e -> {           //кнопка списка пользователей
            MainWindowManager.this.dispose();
            ListAccountsWindow listAccountsWindow = new ListAccountsWindow();
        });

        setBoundsCenter(exitButton, DEFAULT_WIDTH_WINDOW + 900,
                (int) (DEFAULT_HEIGHT_WINDOW * 1.5) + 200, 200, 40);
        setActionExitButton(exitButton);

    }

}
