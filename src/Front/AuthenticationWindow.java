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
    private JButton regButton;

    public AuthenticationWindow() {
        super.setFrame(panel);
    }

    @Override
    public void setOtherElements() {
        super.setElementsAuthenticationWindow(textAuthentication,
                login, password, textLogin, textPassword);
        setBoundsCenter(loginButton, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW + 150, 130, 30);
        setBoundsCenter(regButton, DEFAULT_WIDTH_WINDOW + 900,
                DEFAULT_HEIGHT_WINDOW/2 - 300, 200, 40);
    }

}
