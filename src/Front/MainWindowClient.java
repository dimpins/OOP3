package Front;

import Front.ParrentAbstract.AbstractFront;
import Server.LocalServer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainWindowClient extends AbstractFront {     //окно основного приложения для клиента


    private JPanel panel;
    private JTable tableData;
    private DefaultTableModel tableModel = LocalServer.addAllBooksToTableModel();
    private JComboBox comboBoxSort;
    private JScrollPane scrollData;
    private JButton buttonSearch;
    private JTextField fieldSearch;
    private JButton exitButton;

    public MainWindowClient() {
        super.setFrame(panel, tableData, tableModel);
    }


    @Override
    protected void setOtherElements() {
        super.setTableDataCenter(tableData, scrollData);
        setBoundsCenter(comboBoxSort, DEFAULT_WIDTH_WINDOW / 2,
                DEFAULT_HEIGHT_WINDOW / 2 - 250, 300, 40);
        setActionComboBoxSort(comboBoxSort, tableData, tableModel);

        setBoundsCenter(fieldSearch, DEFAULT_WIDTH_WINDOW + 300,
                DEFAULT_HEIGHT_WINDOW / 2 - 250, 250, 40);

        fieldSearch.setDocument(new PlainDocument() {
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {   //ограничиваем ввод для поиска на 45 символов
                if (getLength() + str.length() <= 45) {
                    super.insertString(offs, str, a);
                }
            }
        });

        setBoundsCenter(buttonSearch, DEFAULT_WIDTH_WINDOW + 900,
                DEFAULT_HEIGHT_WINDOW / 2 - 250, 200, 40);
        setActionSearchButton(buttonSearch, fieldSearch, tableData, tableModel);

        setBoundsCenter(exitButton, DEFAULT_WIDTH_WINDOW + 900,
                (int) (DEFAULT_HEIGHT_WINDOW * 1.5) + 200, 200, 40);
        setActionExitButton(exitButton);
    }

}
