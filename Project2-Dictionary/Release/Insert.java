import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Insert {
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
    private JComboBox typed;
    private JComboBox selected;

    public Insert() {
        JFrame frame = new JFrame("Insert");
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
        String[] types = {"None/Không có", "Noun/Danh từ", "Verb/Động từ", "Adjective/Tính từ", "Adverb/Trạng từ", "Pronoun/Đại từ", "Preposition/Giới từ", "Conjunction/Liên từ", "Interjection/Thán từ"};
        typed = new JComboBox(types);
        typed.setPreferredSize(new Dimension(200, 30));
        typed.setFont(App.font);

        selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectPanel.setBorder(new EmptyBorder(10,10,10,10));
        selectPanel.add(selected);
        selectPanel.add(typed);


        txtWord = new JLabel("New word: ");
        txtWord.setFont(App.font);
        edtWord = new JTextField();
        edtWord.setFont(App.font);
        edtWord.setPreferredSize(new Dimension(200, 30));

        wordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wordPanel.setBorder(new EmptyBorder(10,10,10,10));
        wordPanel.add(txtWord);
        wordPanel.add(edtWord);

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
        JScrollPane scrollPane = new JScrollPane(edtMean, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        btnInsert = new JButton("Insert");
        btnInsert.setFont(App.font);
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Eng - Vie
                String[] getType = typed.getSelectedItem().toString().split("/");
                String typeEng = normalizeType(getType[0]);
                String typeVie = normalizeType(getType[1]);
                String getWord = edtWord.getText();
                String getMean = edtMean.getText();
                if (getWord.equals("")) JOptionPane.showMessageDialog(null, "Invalid new word");
                else if (Words.listKeyVieEng.contains(getWord) || Words.listKeyEngVie.contains(getWord))
                    JOptionPane.showMessageDialog(null, "This word already exists");
                else if (getMean.equals("")) JOptionPane.showMessageDialog(null, "Invalid new meaning");
                else {
                    // Eng - Vie
                    if (selected.getSelectedIndex() == 0) {
                        StringBuilder newWord = new StringBuilder();
                        String[] keyWord = getWord.split(" ");
                        newWord.append("@").append(getWord).append("\n").append(typeEng).append(getMean);
                        Words.listEngVie.put(keyWord[0], newWord.toString());
                        Words.listKeyEngVie.add(keyWord[0]);
                    }
                    // Vie - Eng
                    else if (selected.getSelectedIndex() == 1) {
                        StringBuilder newWord = new StringBuilder();
                        String[] keyWord = getWord.split(" ");
                        newWord.append("@").append(getWord).append("\n").append(typeEng).append(getMean);
                        Words.listVieEng.put(keyWord[0], newWord.toString());
                        Words.listKeyVieEng.add(keyWord[0]);
                    }
                    resetForm();
                    JOptionPane.showMessageDialog(null, "Added new word successfully");
                }
            }
        });

        meanPanel = new JPanel(new BorderLayout());
        meanPanel.setBorder(new EmptyBorder(10,10,10,10));
        meanPanel.add(txtMean, BorderLayout.NORTH);
        meanPanel.add(scrollPane, BorderLayout.CENTER);

        container.add(bodyPanel);
        container.add(meanPanel);
        container.add(btnInsert);
        frame.add(container);
        frame.setVisible(true);
    }

    public void resetForm() {
        selected.setSelectedIndex(App.keyTrans);
        typed.setSelectedIndex(0);
        edtWord.setText("");
        edtMean.setText("");
    }

    public String normalizeType(String type) {
        StringBuilder result = new StringBuilder("");
        if (!type.equals("None") && !type.equals("Không có")) {
            result.append("* ").append(type).append("\n");
        }
        return result.toString();
    }
}
