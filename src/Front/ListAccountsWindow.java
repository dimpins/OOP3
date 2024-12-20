package Front;

import Front.ParrentAbstract.AbstractFront;
import Server.LocalServer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListAccountsWindow extends AbstractFront {    //класс для списка пользователей и изменения прав доступа
    private JPanel panel;
    private JScrollPane scrollData;
    private JTable tableData;
    private DefaultTableModel model = LocalServer.addListOfAccountsToTableModel();       //задаём модели таблице все строки
    private JButton closeButton;
    private JLabel textListAccounts;

    public ListAccountsWindow() {
        setFrame(panel);
    }

    @Override
    protected void setOtherElements() {
        super.setTableDataCenter(tableData, scrollData);
        tableData.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableData.setModel(model);
        tableData.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));  //делаем так, чтобы внутри таблицы, можно было изменять
        tableData.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){         //значения в первом столбце (права доступа)
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(Boolean.TRUE.equals(value));
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                return checkBox;
            }
        });
        tableData.setEnabled(true);

        setBoundsCenter(closeButton, DEFAULT_WIDTH_WINDOW + 900,
                (int) (DEFAULT_HEIGHT_WINDOW * 1.5) + 200, 200, 40);
        setActionCloseButton(closeButton);

        setBoundsCenter(textListAccounts, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW/2 - 250, 400, 40);
    }

}
