package Front;

import Front.ParrentAbstract.AbstractFront;
import Server.LocalServer;

import javax.swing.*;

public class RegistrationWindow extends AbstractFront {
    private JPanel panel;
    private JLabel textRegistration;
    private JButton regButton;
    private JTextField login;
    private JPasswordField password;
    private JLabel textLogin;
    private JLabel textPassword;
    private JButton authenticationButton;

    public RegistrationWindow() {
        setFrame(panel);
    }

    @Override
    protected void setOtherElements() {
        super.setElementsAuthenticationWindow(textRegistration, login, password, textLogin, textPassword);
        setBoundsCenter(regButton, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW + 150, 230, 30);
        regButton.addActionListener(e -> {
            if (login.getText().isEmpty() || login.getText().length() < 6) {
                JOptionPane.showMessageDialog(null, "Логин должен содержать не менее 6 элементов!");
            } else if (password.getText().isEmpty() || password.getText().length() < 8) {
                JOptionPane.showMessageDialog(null, "Пароль должен содержаться не менее 8 элементов!");
            } else if(LocalServer.isLogin(login.getText())) {
                JOptionPane.showMessageDialog(null, "Такой логин уже занят!");
            } else {
                LocalServer.addAccount(login.getText(), password.getText());
                JOptionPane.showMessageDialog(null, "Успешная регистрация, " + login.getText() + "!");
                RegistrationWindow.this.dispose();
                AuthenticationWindow authenticationWindow = new AuthenticationWindow();
            }
        });

        setBoundsCenter(authenticationButton, DEFAULT_WIDTH_WINDOW + 900,
                DEFAULT_HEIGHT_WINDOW / 2 - 300, 200, 40);
        authenticationButton.addActionListener(_ -> {
            RegistrationWindow.this.dispose();
            AuthenticationWindow authenticationWindow = new AuthenticationWindow();
        });
    }

}
