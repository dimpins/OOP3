package Front.ParrentAbstract;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.*;

public abstract class AbstractFront extends JFrame {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int DEFAULT_WIDTH_WINDOW = 1280;
    public static final int DEFAULT_HEIGHT_WINDOW = 720;


    protected void setFrame(JPanel panel) {
        setBoundsCenter(this,screenSize.width, screenSize.height,
                DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW);
        setTitle("Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setBackground(Color.WHITE);
        this.setOtherElements();
        panel.setLayout(null);
        add(panel);
        setVisible(true);
        setBoundsCenter(panel, DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW,
                DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW);
    }

    protected void setFrame(JPanel panel, JTable tableData) {
        setFrame(panel);
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Авторы", "Книги"}, 0);
        tableData.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableData.setModel(tableModel);
        for(int i = 0; i<100000; i++){
            tableModel.addRow(new Object[]{i+1, "Book" + i});
        }
    }

    protected void setTableDataCenter(JTable tableData, JScrollPane scrollData) {
        setBoundsCenter(scrollData, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW, 1200, 500);
        setBoundsCenter(tableData, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW, 1200, 500);
    }

    protected void setElementsAuthenticationWindow(JLabel textAuthentication,
                                                   JTextField login, JTextComponent password,
                                                   JLabel textLogin, JLabel textPassword){
        setBoundsCenter(textAuthentication, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW - 200, 200, 22);
        setBoundsCenter(login, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW - 50, 240, 30);

        login.setDocument(new PlainDocument(){
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength() + str.length() <= 20){
                    super.insertString(offs, str, a);
                }
            }
        });

        setBoundsCenter(password, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW + 50, 240, 30);
        setBoundsCenter(textLogin, DEFAULT_WIDTH_WINDOW - 250,
                DEFAULT_HEIGHT_WINDOW - 50, 130, 30);
        setBoundsCenter(textPassword, DEFAULT_WIDTH_WINDOW - 270,
                DEFAULT_HEIGHT_WINDOW + 50, 130, 30);
    }

    protected void setElementsBookWindow(JLabel textAddBook, JTextField authorField,
                                         JTextField nameBookField, JLabel textAuthor,
                                         JLabel textNameBook, JButton addButton, JButton closeButton){
        setElementsAuthenticationWindow(textAddBook, authorField, nameBookField, textAuthor, textNameBook);
        setBoundsCenter(textNameBook, DEFAULT_WIDTH_WINDOW - 310,
                DEFAULT_HEIGHT_WINDOW + 50, 130, 30);
        setBoundsCenter(addButton, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW + 150, 180, 30);
        setBoundsCenter(closeButton, DEFAULT_WIDTH_WINDOW + 900,
                (int) (DEFAULT_HEIGHT_WINDOW * 1.5) + 200, 200, 40);
    }

    protected void setBoundsCenter(Container container, int x, int y, int width, int height) {
        container.setBounds(x/2 - width/2,
                y/2 - height/2, width, height );
    }

    protected abstract void setOtherElements();

}
