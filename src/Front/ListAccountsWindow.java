package Front;

import Front.ParrentAbstract.AbstractFront;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

public class ListAccountsWindow extends AbstractFront {
    private JPanel panel;
    private JScrollPane scrollData;
    private JTable tableData;
    private JButton closeButton;

    public ListAccountsWindow() {
            setFrame(panel);
            DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Менеджер", "Аккаунты"}, 0) {

                @Override
                public Class getColumnClass(int columnIndex) {
                    return columnIndex == 0 ? Boolean.class : String.class;
                }

            };

            tableData.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
            tableData.setModel(tableModel);

            for (int i = 0; i < 100000; i++) {
                tableModel.addRow(new Object[]{true, "Book" + i});
            }
    }

    @Override
    protected void setOtherElements() {
        super.setTableDataCenter(tableData, scrollData);
        setBoundsCenter(closeButton, DEFAULT_WIDTH_WINDOW + 900,
                (int) (DEFAULT_HEIGHT_WINDOW*1.5) + 200, 200, 40);
    }
}
