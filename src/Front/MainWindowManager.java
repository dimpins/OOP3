package Front;

import Front.ParrentAbstract.AbstractFront;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MainWindowManager extends AbstractFront {

    private JPanel panel;
    private JTable tableData;
    private JComboBox comboBoxSort;
    private JScrollPane scrollData;
    private JButton buttonSearch;
    private JTextField fieldSearch;
    private JButton addBookButton;
    private JButton listAccounts;
    private JButton exitButton;

    public MainWindowManager() {
        super.setFrame(panel, tableData);
    }

    @Override
    protected void setOtherElements() {
        super.setTableDataCenter(tableData, scrollData);
        setBoundsCenter(comboBoxSort, DEFAULT_WIDTH_WINDOW/2  - 200,
                DEFAULT_HEIGHT_WINDOW/2 - 250, 300, 40);
        setBoundsCenter(fieldSearch, DEFAULT_WIDTH_WINDOW - 200,
                DEFAULT_HEIGHT_WINDOW/2 - 250, 250, 40);

        fieldSearch.setDocument(new PlainDocument(){
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength() + str.length() <= 45){
                    super.insertString(offs, str, a);
                }
            }
        });

        setBoundsCenter(buttonSearch, DEFAULT_WIDTH_WINDOW + 350,
                DEFAULT_HEIGHT_WINDOW/2 - 250, 200, 40);
        setBoundsCenter(addBookButton, DEFAULT_WIDTH_WINDOW + 850,
                DEFAULT_HEIGHT_WINDOW/2 - 300, 200, 40);
        setBoundsCenter(listAccounts, DEFAULT_WIDTH_WINDOW + 850,
                DEFAULT_HEIGHT_WINDOW/2 - 200, 250, 40);
        setBoundsCenter(exitButton, DEFAULT_WIDTH_WINDOW + 900,
                (int) (DEFAULT_HEIGHT_WINDOW*1.5) + 200, 200, 40);

    }
}
