package Front;

import Front.ParrentAbstract.AbstractFront;
import Server.LocalServer;

import javax.swing.*;

public class RegistrationWindow extends AbstractFront {    //окно для регистрации
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
        regButton.addActionListener(e -> {        //кнопка регистрации
            if (login.getText().length() < 6) {     //если поле логина меньше 6
                JOptionPane.showMessageDialog(null, "Логин должен содержать не менее 6 элементов!");
            } else if (password.getText().length() < 8) {       //если поле пароля меньше 8
                JOptionPane.showMessageDialog(null, "Пароль должен содержаться не менее 8 элементов!");
            } else if(LocalServer.isLogin(login.getText())) {          //если такой логин уже есть в бд
                JOptionPane.showMessageDialog(null, "Такой логин уже занят!");
            } else {                         //если всё норм, добавляем и открываем окно авторизации
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
