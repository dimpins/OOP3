package Front.ParrentAbstract;

import Front.AuthenticationWindow;
import Front.MainWindowManager;
import Server.LocalServer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public abstract class AbstractFront extends JFrame {         //абстрактный класс родитель для всех классов окон

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();        //размеры экрана пользователя
    public static final int DEFAULT_WIDTH_WINDOW = 1280;                    //стандартные размеры окна
    public static final int DEFAULT_HEIGHT_WINDOW = 720;

    public static class AuthorAndNameBook{              //класс для обработки сортировки книг и авторов
        private String author;
        private String nameBook;

        public AuthorAndNameBook(String author, String nameBook){         //конструктор
            this.author = author;
            this.nameBook = nameBook;
        }

        public String getAuthor(){
            return author;
        }

        public String getNameBook(){
            return nameBook;
        }

        public Object[] getValues(){
            return new Object[]{author, nameBook};
        }   //геттер для занесения в модель таблицы
    }

    protected ArrayList<AuthorAndNameBook> getListAuthorsAndNameBook(DefaultTableModel model){    //возвращает список книг и авторов в модели таблицы
        ArrayList<AuthorAndNameBook> listValues = new ArrayList<>();
        for(int i = 0; i<model.getRowCount(); i++){
            listValues.add(new AuthorAndNameBook(model.getValueAt(i, 0).toString(),
                    model.getValueAt(i, 1).toString()));
        }
        return listValues;
    }

    protected void addNewValuesTable(JTable table, DefaultTableModel model, ArrayList<AuthorAndNameBook> listValues){   //добавляем новые значения в таблицу
        model.setRowCount(0);     //очищаем
        for(AuthorAndNameBook authorAndNameBook : listValues){
            model.addRow(authorAndNameBook.getValues());  //добавляем
        }
        table.setModel(model);   //ставим новую модель у таблицы
    }

    protected void setActionComboBoxSort(JComboBox comboBoxSort, JTable tableData, DefaultTableModel tableModel){   //действие для выбора сортировки
        comboBoxSort.addActionListener(_ ->{
            String selected = (String) comboBoxSort.getSelectedItem();

            if(selected.equals("А-Я: авторы")){               //если по алфавиты авторов
                ArrayList<AuthorAndNameBook> listValues = getListAuthorsAndNameBook(tableModel);  //заполняем список книг и авторов
                if(!listValues.isEmpty()) {
                    listValues.sort(Comparator.comparing(e -> e.getAuthor().toLowerCase()));  //сортируем список по нижнему регистру, по авторам
                }
                addNewValuesTable(tableData, tableModel, listValues);   //добавляем новые значения в таблицу
            } else if(selected.equals("Я-А: авторы")){
                ArrayList<AuthorAndNameBook> listValues = getListAuthorsAndNameBook(tableModel);
                if(!listValues.isEmpty()) {
                    listValues.sort((e1, e2) -> e2.getAuthor().toLowerCase().compareTo(e1.getAuthor().toLowerCase()));
                }
                addNewValuesTable(tableData, tableModel, listValues);
            } else if(selected.equals("А-Я: названия")){
                ArrayList<AuthorAndNameBook> listValues = getListAuthorsAndNameBook(tableModel);
                if(!listValues.isEmpty()) {
                    listValues.sort(Comparator.comparing(e -> e.getNameBook().toLowerCase()));
                }
                addNewValuesTable(tableData, tableModel, listValues);
            } else if(selected.equals("Я-А: названия")){
                ArrayList<AuthorAndNameBook> listValues = getListAuthorsAndNameBook(tableModel);
                if(!listValues.isEmpty()) {
                    listValues.sort((e1, e2) -> e2.getNameBook().toLowerCase().compareTo(e1.getNameBook().toLowerCase()));
                }
                addNewValuesTable(tableData, tableModel, listValues);
            }
        });
    }

    protected void setActionSearchButton(JButton buttonSearch,JTextField fieldSearch ,JTable tableData, DefaultTableModel tableModel){  //действие для кнопки поиска
        buttonSearch.addActionListener(_ ->{
            String text = fieldSearch.getText();          //тест в поле поиска
            if(text.isEmpty()){       //если пустой, выводим все данные из бд
                DefaultTableModel newTableModel = LocalServer.addAllBooksToTableModel();
                tableData.setModel(newTableModel);
                setNewTableModel(tableModel, newTableModel);
            } else {  //иначе
                ArrayList<AuthorAndNameBook> listValues = getListAuthorsAndNameBook(LocalServer.addAllBooksToTableModel())  //берём все значения из бд
                        .stream().filter(e -> e.getAuthor().toLowerCase().startsWith(text.toLowerCase())   //фильтруем, чтобы текст из поля ввода был в начале либо у автора, либо у книги
                                || e.getNameBook().toLowerCase().startsWith(text.toLowerCase()))                             // по нижнему регистру
                        .collect(Collectors.toCollection(ArrayList::new));                                                 //преобразуем стрим в arraylist
                addNewValuesTable(tableData, tableModel, listValues);                         //заносим новые значения
            }
        });
    }

    protected void setNewTableModel(DefaultTableModel oldModel, DefaultTableModel newModel){          //метод для обновления модели таблицы
        oldModel = newModel;
    }

    protected void setActionExitButton(JButton button){           //действие для кнопки выхода из аккаунта
        button.addActionListener(_ ->{
           AbstractFront.this.dispose();                        //закрываем текущее окно
            AuthenticationWindow authenticationWindow = new AuthenticationWindow();  //открываем окно аутентификации
        });
    }

    protected void setActionCloseButton(JButton button){          //действие для закрытия окна действия (добавления/удаления книги и списка пользователей)
        button.addActionListener(_ ->{
            AbstractFront.this.dispose();                     //закрываем текущее окно
            MainWindowManager mainWindowManager = new MainWindowManager();   //открываем окно менеджера
        });
    }

    protected void setFrame(JPanel panel) {                      //устанавливаем окно по умолчанию
        setBoundsCenter(this,screenSize.width, screenSize.height,        //ставим окно по центру
                DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW);
        setTitle("Program");                                   //заголовок окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         //чтобы закрыть можно было
        setResizable(false);                                    //запрещаем изменять размеры
        setLayout(null);                                        //убираем стандартный менеджер компоновки, чтобы самостоятельно располагать элементы внутри окна
        setBackground(Color.WHITE);                          //белый цвет фона
        this.setOtherElements();                             //вызываем метод установки остальных элементов (переопределяется в классах наследниках)
        panel.setLayout(null);                               //убираем стандартный менеджер компоновки у панели окна
        add(panel);                                          //добавляем панель в окно
        setVisible(true);                                      //делаем видимым
        setBoundsCenter(panel, DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW,    //устанавливаем панель по центру
                DEFAULT_WIDTH_WINDOW, DEFAULT_HEIGHT_WINDOW);
    }

    protected void setFrame(JPanel panel, JTable tableData, DefaultTableModel tableModel) {       //устанавливаем окно, где есть таблица
        setFrame(panel);
        tableData.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableData.setModel(tableModel);
    }

    protected void setTableDataCenter(JTable tableData, JScrollPane scrollData) {       //устанавливаем таблицу в стандартное положение
        setBoundsCenter(scrollData, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW, 1200, 500);
        setBoundsCenter(tableData, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW, 1200, 500);
    }

    protected void setElementsAuthenticationWindow(JLabel textAuthentication,                     //устанавливаем элементы окна аутентификации
                                                   JTextField login, JTextComponent password,     //метод нужен т.к. окна аутентификации, регистрации похожи
                                                   JLabel textLogin, JLabel textPassword){
        setBoundsCenter(textAuthentication, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW - 200, 200, 22);
        setBoundsCenter(login, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW - 50, 240, 30);

        login.setDocument(new PlainDocument(){
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {       //ограничиваем ввод логина в 20 символов
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

    protected void setElementsBookWindow(JLabel textAddBook, JTextField authorField,                     //метод для установки окон добавления/удаления книг
                                         JTextField nameBookField, JLabel textAuthor,                    //использует метод установки элоементов окна аутинтификации, но немного левее сдвигает текст т.к. он длиннее у этих окнах
                                         JLabel textNameBook, JButton addButton, JButton closeButton){
        setElementsAuthenticationWindow(textAddBook, authorField, nameBookField, textAuthor, textNameBook);
        setBoundsCenter(textNameBook, DEFAULT_WIDTH_WINDOW - 310,
                DEFAULT_HEIGHT_WINDOW + 50, 130, 30);
        setBoundsCenter(addButton, DEFAULT_WIDTH_WINDOW,
                DEFAULT_HEIGHT_WINDOW + 150, 180, 30);
        setBoundsCenter(closeButton, DEFAULT_WIDTH_WINDOW + 900,
                (int) (DEFAULT_HEIGHT_WINDOW * 1.5) + 200, 200, 40);
    }

    protected void setBoundsCenter(Container container, int x, int y, int width, int height) {               //устанавливаем элемент в центре заданных координат
        container.setBounds(x/2 - width/2,
                y/2 - height/2, width, height );
    }

    protected abstract void setOtherElements();      //абстрактный метод для установки всех элементов окна (для классов наследников)

}
