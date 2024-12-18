import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class Front {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int DEFAULT_WIDTH_WINDOW = 1280;
    public static final int DEFAULT_HEIGHT_WINDOW = 720;

    public static JFrame authenticationWindow() {
        JFrame frame = new JFrame();
        setFrame(frame, "Authentication");

        JLabel label = new JLabel("Авторизация");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        setBoundsCenter(label, 700, 200, 130, 22);

        JTextField login = new JTextField();
        login.setFont(new Font("Times New Roman", Font.BOLD, 14));
        login.setForeground(Color.BLACK);
        login.setHorizontalAlignment(SwingConstants.CENTER);
        login.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBoundsCenter(login, 700, 350, 140, 30);

        login.setDocument(new PlainDocument(){
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength() + str.length() <= 20){
                    super.insertString(offs, str, a);
                }
            }
        });


        JPasswordField password = new JPasswordField();
        password.setFont(new Font("Times New Roman", Font.BOLD, 14));
        password.setForeground(Color.BLACK);
        password.setHorizontalAlignment(SwingConstants.CENTER);
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBoundsCenter(password, 700, 450, 140, 30);


        JButton buttonLogin = new JButton("Войти");
        buttonLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        buttonLogin.setForeground(Color.BLACK);
        buttonLogin.setHorizontalAlignment(SwingConstants.CENTER);
        setBoundsCenter(buttonLogin, 700, 550, 130, 30);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setBoundsCenter(panel, DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW,
                700, 400);
        panel.add(label);
        panel.add(login);
        panel.add(password);
        panel.add(buttonLogin);

        frame.add(panel);

        return frame;

    }

    public static void mainWindowClient(JFrame frame){
        //clearFrame(frame);

        frame.setTitle("Program");

        JPanel panel = new JPanel();
        setBoundsCenter(panel, DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW,
                1260, 700);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTable table = new JTable();

        for(int i = 0; i<10000; i++){
            table.getModel().setValueAt(i, i, 0);
            table.getModel().setValueAt("Penis" + i, i, 1);
        }
        setBoundsCenter(table, 1260, 700, 1000, 600);
        JScrollPane scrollPane = new JScrollPane(table);
        setBoundsCenter(scrollPane, 1260, 700, 1000, 600);
        panel.add(scrollPane);
        frame.add(panel);
        frame.repaint();
    }

    public static void clearFrame(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();
    }

    private static void setFrame(JFrame frame, String title){
        frame.setTitle(title);
        frame.setLayout(null);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBoundsCenter(frame, screenSize.width, screenSize.height,
                DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW);
        frame.setResizable(false);
    }

    private static void setBoundsCenter(Container container, int x, int y, int width, int height){
        container.setBounds(x/2 - width/2,
                y/2 - height/2, width, height );
    }
}
