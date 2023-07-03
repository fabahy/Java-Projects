import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.util.*;

public class Favourites {
    private JTextArea edtFav;
    private JPanel sortPanel;
    private JButton btnSortAsc;
    private JButton btnSortDesc;

    public Favourites() {
        JFrame frame = new JFrame("Favourites");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(550, 200);
        frame.setSize(new Dimension(650,400));
        BorderLayout borderLayout = new BorderLayout();
        frame.setLayout(borderLayout);
        frame.setResizable(false);


        sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnSortAsc = new JButton("A - Z");
        btnSortAsc.setPreferredSize(new Dimension(80, 30));
        btnSortAsc.setFont(App.font);

        btnSortDesc = new JButton("Z - A");
        btnSortDesc.setPreferredSize(new Dimension(80, 30));
        btnSortDesc.setFont(App.font);

        edtFav = new JTextArea();
        edtFav.setFont(App.font);
        edtFav.setBorder(new EmptyBorder(10,10,10,10));
        JScrollPane scrollPane = new JScrollPane(edtFav, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        showListFav(Words.listFav);

        addEventListenerBtnSortAsc();
        addEventListenerBtnSortDesc();

        sortPanel.add(btnSortAsc);
        sortPanel.add(btnSortDesc);
        frame.add(sortPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showListFav(ArrayList<String> arr) {
        StringBuilder result = new StringBuilder();
        result.append("------------------------------------\n");
        for (String s : arr) {
            result.append(s).append("\n");
        }
        result.append("------------------------------------\n");
        edtFav.setText(result.toString());
    }

    private void addEventListenerBtnSortAsc() {
        btnSortAsc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale locale = new Locale("vi", "VN");
                Collator collator = Collator.getInstance(locale);
                ArrayList<String> newList = new ArrayList<>(Words.listFav);
                Collections.sort(newList, collator);
                showListFav(newList);
            }
        });
    }
    private void addEventListenerBtnSortDesc() {
        btnSortDesc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale locale = new Locale("vi", "VN");
                Collator collator = Collator.getInstance(locale);
                ArrayList<String> newList = new ArrayList<>(Words.listFav);
                Comparator<Object> comparator = Collections.reverseOrder(collator);
                Collections.sort(newList, comparator);
                showListFav(newList);
            }
        });
    }
}
