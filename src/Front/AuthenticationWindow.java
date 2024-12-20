package Front;

import Front.ParrentAbstract.AbstractFront;
import Server.LocalServer;

import javax.swing.*;


public class AuthenticationWindow extends AbstractFront {       //окно авторизации
    private JPanel panel;
    private JLabel textAuthentication;
    private JButton loginButton;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JLabel textLogin;
    private JLabel textPassword;
    private JButton regButton;

    public AuthenticationWindow() {
        super.setFrame(panel);
    }

    @Override
    public void setOtherElements() {
        super.setElementsAuthenticationWindow(textAuthentication,
                loginField, passwordField, textLogin, textPassword);
        setBoundsCenter(loginButton, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW + 150, 130, 30);
        loginButton.addActionListener(_ ->{       //действие кнопки войти
            String login = loginField.getText();
            String password = passwordField.getText();
            if(!LocalServer.isLogin(login)){         //если такого логина нет
                JOptionPane.showMessageDialog(null, "Такого логина нет!\n Зарегистрируйтесь!");
            } else if(!LocalServer.isPassword(login, password)){   //если неверный пароль
                JOptionPane.showMessageDialog(null, "Неверный пароль!");
            } else if (LocalServer.isManager(login)){      //проверка менеджера
                AuthenticationWindow.this.dispose();
                MainWindowManager manager = new MainWindowManager();
            } else {
                AuthenticationWindow.this.dispose();
                MainWindowClient client = new MainWindowClient();
            }
        });

        setBoundsCenter(regButton, DEFAULT_WIDTH_WINDOW + 900,
                DEFAULT_HEIGHT_WINDOW / 2 - 300, 200, 40);
        regButton.addActionListener(_ -> {
            AuthenticationWindow.this.dispose();
            RegistrationWindow registrationWindow = new RegistrationWindow();
        });
    }

}
