import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class App {
    private JPanel main;
    private JPanel header;
    private JPanel body;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JComboBox<String> selectedLanguage;
    private JTextField edtWord;
    private JButton btnTrans;
    private JButton btnFav;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnStatistic;
    private JButton btnViewFav;
    private ImageIcon icon1 = new ImageIcon(".\\resources\\unheart.png");
    private ImageIcon icon2 = new ImageIcon(".\\resources\\heart.png");
    private JTextArea txtTrans;
    private String wordInput;
    private Words WordManager;
    public static int keyTrans = 0; // 0: English, 1: Vietnamese
    public final static Font font = new Font("Arial", Font.BOLD, 14);
    private JList<String> searchList;
    private JPopupMenu menu;
    private DefaultListModel<String> model;
    private JScrollPane scroll;

    public App() {

        WordManager = new Words();
        WordManager.readEngVie();
        WordManager.readVieEng();
        WordManager.readHistoryFromFile();
        WordManager.readListFavouritesFromFile();

        JFrame frame = new JFrame("Dictionary - 20120298");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane();
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                WordManager.saveHistoryToFile();
                WordManager.saveListFavouritesToFile();
                WordManager.saveEngVieToXML();
                WordManager.saveVieEngToXML();
            }
        });
        frame.setLocation(350, 150);
        frame.setPreferredSize(new Dimension(1000, 560));


        // Header
        headerLayout();

        // Top Layout
        topLayout();

        // Center Layout
        centerLayout();

        // Right panel
        rightLayout();


        // Body panel
        body = new JPanel(new BorderLayout());
        body.add(topPanel, BorderLayout.NORTH);
        body.add(centerPanel, BorderLayout.CENTER);
        body.add(rightPanel, BorderLayout.EAST);

        // Main panel
        main = new JPanel(new BorderLayout());
        main.add(header, BorderLayout.NORTH);
        main.add(body, BorderLayout.CENTER);
        frame.add(main);
        frame.pack();
        frame.setVisible(true);
    }

    private void rightLayout() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(10, 0,10,10));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 80)));

        btnViewFav = new JButton("Favourites");
        btnViewFav.setFont(font);
        btnViewFav.setSize(new Dimension(200, 30));
        addEventListenerBtnViewFav();

        btnAdd = new JButton("    Insert    ");
        btnAdd.setFont(font);
        addEventListenerBtnAdd();

        btnStatistic = new JButton(" Statistics ");
        btnStatistic.setFont(font);
        addEventListenerBtnStatistics();

        btnRemove = new JButton("  Remove  ");
        btnRemove.setFont(font);
        addEventListenerBtnRemove();

        btnViewFav.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStatistic.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);


        rightPanel.add(btnAdd);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(btnRemove);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(btnViewFav);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(btnStatistic);
    }

    private void addEventListenerBtnViewFav() {
        btnViewFav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnViewFav) {
                    new Favourites();
                }
            }
        });
    }

    private void addEventListenerBtnRemove() {
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Delete();
            }
        });
    }

    private void addEventListenerBtnAdd() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAdd) {
                    new Insert();
                }
            }
        });
    }

    private void addEventListenerBtnStatistics() {
        btnStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnStatistic) {
                    new Statistics();
                }
            }
        });
    }

    private void headerLayout() {
        header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("Dictionary");
        title.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 42));
        header.add(title);
    }

    private void centerLayout() {
        centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(10,10,10,10));
        centerPanel.setLayout(new BorderLayout(0,0));
        txtTrans = new JTextArea(10, 10);
        txtTrans.setFont(font);
        txtTrans.setBorder(new EmptyBorder(10,10,10,10));
        centerPanel.add(txtTrans, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(txtTrans, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerPanel.add(scrollPane);
    }

    private void topLayout() {
        String[] languages = {"English - Vietnamese", "Vietnamese - English"};
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        selectedLanguage = new JComboBox<>(languages);
        selectedLanguage.setSelectedIndex(0);
        selectedLanguage.setFont(font);
        selectedLanguage.setPreferredSize(new Dimension(200, 30));


        edtWord = new JTextField();
        edtWord.setPreferredSize(new Dimension(350, 30));
        edtWord.setFont(font);

        menu = new JPopupMenu();
        menu.setPreferredSize(new Dimension(350, 200));
        searchList = new JList<String>();
        searchList.setFont(font);

        menu.add(searchList);
        scroll = new JScrollPane(searchList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menu.add(scroll);

        menu.setFocusable(false);
        addKeyEventListenerEdtWord();
        addEventListenerSearchList();

        btnTrans = new JButton("Translate");
        btnTrans.setPreferredSize(new Dimension(150, 30));
        btnTrans.setFont(font);

        btnFav = new JButton(icon1);
        btnFav.setPreferredSize(new Dimension(30,30));

        addEventListenerBtnFav();
        addEventListenerComboBox();
        addEventListenerBtnTrans();

        topPanel.add(selectedLanguage);
        topPanel.add(edtWord);
        topPanel.add(btnTrans);
        topPanel.add(btnFav);
    }

    private void addEventListenerSearchList() {
        searchList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String getItem = (String) searchList.getSelectedValue();
                edtWord.setText(getItem);
                handleBtnTrans();
                menu.setVisible(false);
            }
        });
    }

    private void addKeyEventListenerEdtWord() {
        edtWord.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleBtnTrans();
                    menu.setVisible(false);
                }
                else {
                    String searching = edtWord.getText().trim();
                    model = new DefaultListModel<String>();
                    searchList.clearSelection();
                    if (selectedLanguage.getSelectedIndex() == 0){
                        model.removeAllElements();
                        for (int i = 0; i < Words.listKeyEngVie.size(); i++){
                            if(Words.listKeyEngVie.get(i).startsWith(searching)){
                                model.addElement(Words.listKeyEngVie.get(i));
                            }
                        }
                        searchList.setModel(model);
                    }
                    else if (selectedLanguage.getSelectedIndex() == 1){
                        model.removeAllElements();
                        for(int i = 0; i < Words.listKeyVieEng.size(); i++){
                            if(Words.listKeyVieEng.get(i).startsWith(searching)){
                                model.addElement(Words.listKeyVieEng.get(i));
                            }
                        }
                        searchList.setModel(model);
                    }
                    menu.show(edtWord, 0, edtWord.getHeight());
                }
            }
        });
    }

    private void addEventListenerBtnFav() {
        btnFav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = edtWord.getText();
                if (word.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a word");
                }
                else if (Words.listFav.contains(word)) {
                    Words.listFav.remove(word);
                    btnFav.setIcon(icon1);
                }
                else {
                    if (keyTrans == 0) {
                        if (!Words.listEngVie.containsKey(word)) JOptionPane.showMessageDialog(null, "Word not found");
                        else {
                            Words.listFav.add(word);
                            btnFav.setIcon(icon2);
                        }
                    }
                    else if (keyTrans == 1) {
                        if (!Words.listVieEng.containsKey(word)) JOptionPane.showMessageDialog(null, "Word not found");
                        else {
                            Words.listFav.add(word);
                            btnFav.setIcon(icon2);
                        }
                    }

                }
            }
        });
    }

    private void addEventListenerComboBox() {
        selectedLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedLanguage.getSelectedItem().toString().equals("English - Vietnamese")) keyTrans = 0;
                else if (selectedLanguage.getSelectedItem().toString() == "Vietnamese - English") keyTrans = 1;
            }
        });
    }

    private void addEventListenerBtnTrans() {
        btnTrans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBtnTrans();
            }
        });
    }

    private void handleBtnTrans() {
        LocalDate localDate = LocalDate.now();
        wordInput = edtWord.getText().trim();
        if (!Words.listFav.contains(wordInput)) {
            btnFav.setIcon(icon1);
        }
        else btnFav.setIcon(icon2);
        if (wordInput.equals(""))
            txtTrans.setText("Word not found !!!");
        else if (!Words.listEngVie.containsKey(wordInput) && !Words.listVieEng.containsKey(wordInput)) {
            txtTrans.setText("Word not found !!!");
        }
        else {
            // Eng - Vie
            if (selectedLanguage.getSelectedIndex() == 0) {
                String meaning = WordManager.lookupEngVie(wordInput);
                if (meaning != null) {
                    String[] meanings = meaning.split("\n");
                    StringBuilder result = new StringBuilder(meanings[0]);
                    result.append("\n\n----------------------------------------------------\n\n");
                    for (int i = 1; i < meanings.length - 1; i++) {
                        if (meanings[i + 1].contains("*"))
                            result.append(meanings[i]).append("\n\n");
                        else result.append(meanings[i]).append("\n");
                    }
                    result.append(meanings[meanings.length - 1]);
                    txtTrans.setText(result.toString());
                    History history = new History(wordInput, localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
                    Words.historyList.add(history);
                }
                else txtTrans.setText("Word not found !!!");
            }
            // Vie - Eng
            else if (selectedLanguage.getSelectedIndex() == 1) {
                String meaning = WordManager.lookupVieEng(wordInput);
                if (meaning != null) {
                    String[] meanings = Words.listVieEng.get(wordInput).split("\n");
                    StringBuilder result = new StringBuilder(meanings[0]);
                    result.append("\n\n----------------------------------------------------\n\n");
                    for (int i = 1; i < meanings.length - 1; i++) {
                        if (meanings[i + 1].contains("*"))
                            result.append(meanings[i]).append("\n\n");
                        else result.append(meanings[i]).append("\n");
                    }
                    result.append(meanings[meanings.length - 1]);
                    txtTrans.setText(result.toString());
                    History history = new History(wordInput, localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
                    Words.historyList.add(history);
                } else {
                    txtTrans.setText("Word not found !!!");
                }
            }
        }
    }
    public static void main(String[] args) {
        new App();
    }
}
