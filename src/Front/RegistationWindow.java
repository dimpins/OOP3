package Front;

import Front.ParrentAbstract.AbstractFront;

import javax.swing.*;

public class RegistationWindow extends AbstractFront {
    private JPanel panel;
    private JLabel textRegistration;
    private JButton regButton;
    private JTextField login;
    private JPasswordField password;
    private JLabel textLogin;
    private JLabel textPassword;

    public RegistationWindow() {
        setFrame(panel);
    }

    @Override
    protected void setOtherElements() {
        super.setElementsAuthenticationWindow(textRegistration, login, password, textLogin, textPassword);
        setBoundsCenter(regButton, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW + 150, 230, 30);
    }
}
