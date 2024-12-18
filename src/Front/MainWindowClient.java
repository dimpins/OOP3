package Front;

import Front.ParrentAbstract.AbstractFront;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class MainWindowClient extends AbstractFront {


    private JPanel panel;
    private JTable tableData;
    private JComboBox comboBoxSort;
    private JScrollPane scrollData;
    private JButton buttonSearch;
    private JTextField fieldSearch;

    public MainWindowClient()  {
        super.setFrame(panel);
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Авторы", "Книги"}, 0);
        tableData.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableData.setModel(tableModel);
        for(int i = 0; i<10; i++){
            tableModel.addRow(new Object[]{i+1, "Book" + i});
        }
    }


    @Override
    protected void setOtherElements() {
        setBoundsCenter(scrollData, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW, 1200, 600);
        setBoundsCenter(tableData, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW, 1200, 600);
        setBoundsCenter(comboBoxSort, DEFAULT_WIDTH_WINDOW/2,
                DEFAULT_HEIGHT_WINDOW/2 - 300, 300, 40);
        setBoundsCenter(fieldSearch, DEFAULT_WIDTH_WINDOW + 300,
                DEFAULT_HEIGHT_WINDOW/2 - 300, 250, 40);

        fieldSearch.setDocument(new PlainDocument(){
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength() + str.length() <= 45){
                    super.insertString(offs, str, a);
                }
            }
        });

        setBoundsCenter(buttonSearch, DEFAULT_WIDTH_WINDOW + 900,
                DEFAULT_HEIGHT_WINDOW/2 - 300, 200, 40);
    }
}
