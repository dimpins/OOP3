package Front;

import Front.ParrentAbstract.AbstractFront;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class AuthenticationWindow extends AbstractFront {
    private JPanel panel;
    private JLabel textAuthentication;
    private JButton loginButton;
    private JTextField login;
    private JPasswordField password;
    private JLabel textLogin;
    private JLabel textPassword;

    public AuthenticationWindow() {
        super.setFrame(panel);
    }

    @Override
    public void setOtherElements() {
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
        setBoundsCenter(loginButton, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW + 150, 130, 30);
        setBoundsCenter(textLogin, DEFAULT_WIDTH_WINDOW - 250,
                DEFAULT_HEIGHT_WINDOW - 50, 130, 30);
        setBoundsCenter(textPassword, DEFAULT_WIDTH_WINDOW - 270,
                DEFAULT_HEIGHT_WINDOW + 50, 130, 30);
    }

}
