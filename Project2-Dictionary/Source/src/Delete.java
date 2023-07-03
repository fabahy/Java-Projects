import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Delete {
    private JButton btnLookup;
    private JButton btnDelete;
    private JPanel bodyPanel;
    private JPanel container;
    private JPanel selectPanel;
    private JButton btnInsert;
    private JLabel txtMean;
    private JPanel meanPanel;
    private JPanel wordPanel;
    private JTextArea edtMean;
    private JTextField edtWord;
    private JLabel txtWord;
    private JComboBox selected;
    private ImageIcon icon = new ImageIcon(".\\resources\\search.png");

    public Delete() {
        JFrame frame = new JFrame("Delete");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(550, 200);
        frame.setSize(new Dimension(650,400));
        frame.setResizable(false);

        container = new JPanel();
        BoxLayout box = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(box);


        String[] languages = {"English - Vietnamese", "Vietnamese - English"};
        selected = new JComboBox(languages);
        selected.setSelectedIndex(App.keyTrans);
        selected.setPreferredSize(new Dimension(200, 30));
        selected.setFont(App.font);

        selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectPanel.setBorder(new EmptyBorder(10,10,10,10));
        selectPanel.add(selected);


        txtWord = new JLabel("Enter word: ");
        txtWord.setFont(App.font);
        edtWord = new JTextField();
        edtWord.setFont(App.font);
        edtWord.setPreferredSize(new Dimension(200, 30));
        btnLookup = new JButton(icon);
        btnLookup.setPreferredSize(new Dimension(30, 30));
        btnLookup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBtnLookup();
            }
        });
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
                    handleBtnLookup();
                }
            }
        });

        wordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wordPanel.setBorder(new EmptyBorder(10,10,10,10));
        wordPanel.add(txtWord);
        wordPanel.add(edtWord);
        wordPanel.add(btnLookup);

        bodyPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(bodyPanel, BoxLayout.Y_AXIS);
        bodyPanel.setLayout(boxLayout);
        bodyPanel.add(selectPanel);
        bodyPanel.add(wordPanel);

        txtMean = new JLabel("Meaning");
        txtMean.setBorder(new EmptyBorder(0,0,10,0));
        txtMean.setFont(App.font);
        edtMean = new JTextArea();
        edtMean.setFont(App.font);
        edtMean.setBorder(new EmptyBorder(10,10,10,10));
        edtMean.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(edtMean, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        btnDelete = new JButton("Delete");
        btnDelete.setFont(App.font);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getWord = edtWord.getText().trim();
                if (getWord.equals("")) JOptionPane.showMessageDialog(null, "Please enter word");
                else if (!Words.listVieEng.containsKey(getWord) && !Words.listEngVie.containsKey(getWord))
                    JOptionPane.showMessageDialog(null, "Word not found");
                else {
                    if (selected.getSelectedIndex() == 0) {
                        Words.listEngVie.remove(getWord);
                    } else if (selected.getSelectedIndex() == 1) {
                        Words.listVieEng.remove(getWord);
                    }
                    resetForm();
                    JOptionPane.showMessageDialog(null, "Deleted word successfully");
                }
            }
        });

        meanPanel = new JPanel(new BorderLayout());
        meanPanel.setBorder(new EmptyBorder(10,10,10,10));
        meanPanel.add(txtMean, BorderLayout.NORTH);
        meanPanel.add(scrollPane, BorderLayout.CENTER);

        container.add(bodyPanel);
        container.add(meanPanel);
        container.add(btnDelete);
        frame.add(container);
        frame.setVisible(true);
    }

    private void handleBtnLookup() {
        String getWord = edtWord.getText().trim();
        if (getWord.equals("")) JOptionPane.showMessageDialog(null, "Word not found");
        else if (!Words.listVieEng.containsKey(getWord) && !Words.listEngVie.containsKey(getWord))
            JOptionPane.showMessageDialog(null, "Word not found");
        else {
            // Eng - Vie
            if (selected.getSelectedIndex() == 0) {
                String meaning = Words.listEngVie.get(getWord);
                if (meaning != null)
                    edtMean.setText(meaning);
                else JOptionPane.showMessageDialog(null, "Word not found");
            }
            // Vie - Eng
            else if (selected.getSelectedIndex() == 1) {
                String meaning = Words.listVieEng.get(getWord);
                if (meaning != null)
                    edtMean.setText(meaning);
                else JOptionPane.showMessageDialog(null, "Word not found");
                edtMean.setText(meaning);
            }
        }
    }

    public void resetForm() {
        selected.setSelectedIndex(App.keyTrans);
        edtWord.setText("");
        edtMean.setText("");
    }
}
