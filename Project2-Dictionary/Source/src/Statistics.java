import com.toedter.calendar.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Statistics {
    private JPanel mainPanel;
    private JScrollPane scroll;
    private JPanel timePanel;
    private JLabel txtDayBegin;
    private JLabel txtDayEnd;
    private JButton btnCalculate;
    private JTextArea edtHistory;
    private JDateChooser dayBegin;
    private JDateChooser dayEnd;
    public Statistics() {
        JFrame frame = new JFrame("Statistics");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(550, 200);
        frame.setSize(new Dimension(650,400));
        BorderLayout borderLayout = new BorderLayout();
        frame.setLayout(borderLayout);
        frame.setResizable(false);
        frame.setVisible(true);
        String dateFormat = "dd/MM/yyyy";

        timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtDayBegin = new JLabel("Begin day:");
        txtDayBegin.setFont(App.font);
        dayBegin = new JDateChooser();
        dayBegin.setPreferredSize(new Dimension(120, 30));
        dayBegin.setFont(App.font);
        dayBegin.setDateFormatString(dateFormat);



        txtDayEnd = new JLabel("End day: ");
        txtDayEnd.setFont(App.font);
        dayEnd = new JDateChooser();
        dayEnd.setPreferredSize(new Dimension(120, 30));
        dayEnd.setFont(App.font);
        dayEnd.setDateFormatString(dateFormat);
        btnCalculate = new JButton("Show");
        btnCalculate.setPreferredSize(new Dimension(120, 30));
        btnCalculate.setFont(App.font);


        edtHistory = new JTextArea();
        edtHistory.setBorder(new EmptyBorder(10,10,10,10));
        edtHistory.setEditable(false);
        edtHistory.setFont(App.font);

        scroll = new JScrollPane(edtHistory, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addEventListenerBtnCalculate();


        timePanel.add(txtDayBegin);
        timePanel.add(dayBegin);
        timePanel.add(txtDayEnd);
        timePanel.add(dayEnd);
        timePanel.add(btnCalculate);
        frame.add(timePanel, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);
    }

    private void addEventListenerBtnCalculate() {
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                Date date = dayBegin.getDate();
                cal.setTime(date);
                int dayBeg = cal.get(Calendar.DAY_OF_MONTH);
                int monthBeg = cal.get(Calendar.MONTH) + 1;
                int yearBeg = cal.get(Calendar.YEAR);

                date = dayEnd.getDate();
                cal.setTime(date);
                int dayEnd = cal.get(Calendar.DAY_OF_MONTH);
                int monthEnd = cal.get(Calendar.MONTH) + 1;
                int yearEnd = cal.get(Calendar.YEAR);

                edtHistory.setText("");
                if (!isLogicDate(dayBeg, monthBeg, yearBeg, dayEnd, monthEnd, yearEnd)) edtHistory.setText("Invalid date");
                else {
                    edtHistory.append("-----------------------------------------------------\n");
                    ArrayList<String> newList = new ArrayList<String>();
                    for (int i = 0; i < Words.historyList.size(); i++) {
                        if (isLogicDate(dayBeg, monthBeg, yearBeg, Words.historyList.get(i).getDay(), Words.historyList.get(i).getMonth(), Words.historyList.get(i).getYear())
                                && isLogicDate(Words.historyList.get(i).getDay(), Words.historyList.get(i).getMonth(), Words.historyList.get(i).getYear(), dayEnd, monthEnd, yearEnd)) {
                            newList.add(Words.historyList.get(i).getKey());
                        }
                    }
                    while (newList.size() != 0) {
                        int count = 1;
                        String temp;
                        temp = newList.get(0);
                        for (int i = 1; i < newList.size(); i++) {
                            if (newList.get(i).equals(temp)) {
                                count++;
                                newList.remove(i);
                                i--;
                            }
                        }
                        newList.remove(0);
                        edtHistory.append(temp + ": " + count + " (times)" + "\n");
                    }
                    edtHistory.append("-----------------------------------------------------\n");
                }
            }
        });
    }

    public boolean isLogicDate(int dayBegin, int monthBegin, int yearBegin, int dayEnd, int monthEnd, int yearEnd){
        if(yearEnd > yearBegin)
            return true;
        if(yearBegin == yearEnd){
            if(monthEnd > monthBegin)
                return true;
            if(monthBegin == monthEnd){
                if(dayEnd > dayBegin)
                    return true;
            }
        }
        return false;
    }
}
