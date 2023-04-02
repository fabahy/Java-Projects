package pbhuy.studentmanagement;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HuyBao
 */
public class App extends javax.swing.JFrame {

    private final StudentManagement StudentManager;

    /**
     * Creates new form GUIStudentManagement
     */
    public App() {
        initComponents();
        this.StudentManager = new StudentManagement();
    }

    public void clear() {
        this.edtID.setText("");
        this.edtName.setText("");
        this.edtGPA.setText("");
        this.lblImage.setIcon(null);
        this.edtImage.setText("");
        this.edtAddress.setText("");
        this.edtNote.setText("");
    }

    public boolean validationForm() {
        boolean isChecked = false;
        if (this.edtID.getText().equals("")) {
            isChecked = false;
            JOptionPane.showMessageDialog(null, "ID Required");
            this.edtID.requestFocus();
        } else if (this.edtName.getText().equals("")) {
            isChecked = false;
            JOptionPane.showMessageDialog(null, "Name Required");
            this.edtName.requestFocus();
        } else if (this.edtGPA.getText().equals("")) {
            isChecked = false;
            JOptionPane.showMessageDialog(null, "GPA Required");
            this.edtGPA.requestFocus();
        } else if (this.edtImage.getText().equals("")) {
            isChecked = false;
            JOptionPane.showMessageDialog(null, "Image Required");
            this.edtImage.requestFocus();
        } else if (this.edtAddress.getText().equals("")) {
            isChecked = false;
            JOptionPane.showMessageDialog(null, "Address Required");
            this.edtAddress.requestFocus();
        } else {
            isChecked = true;
        }
        return isChecked;
    }

    public void insert() {
        Student std = new Student();
        std.setID(this.edtID.getText());
        std.setName(this.edtName.getText());
        std.setGPA(Float.parseFloat(this.edtGPA.getText()));
        std.setImg(this.edtImage.getText());
        std.setAddress(this.edtAddress.getText());
        std.setNote(this.edtNote.getText());
        this.StudentManager.addStudent(std);
    }

    public void showTable() {
        ArrayList<Student> listStd = this.StudentManager.getListStd();
        DefaultTableModel dtm = (DefaultTableModel) this.tblStudent.getModel();
        dtm.setRowCount(0);
        for (Student std : listStd) {
            dtm.addRow(new Object[]{std.getID(), std.getName(), std.getGPA(), std.getImg(), std.getAddress(), std.getNote()});
        }
    }

    public void delete() {
        int index = this.tblStudent.getSelectedRow();
        if (index == -1) {
            return;
        }
        this.StudentManager.removeStudent(index);
    }

    public void update() {
        int index = this.tblStudent.getSelectedRow();
        if (index == -1) {
            return;
        }
        Student std = new Student();
        std.setID(this.edtID.getText());
        std.setName(this.edtName.getText());
        std.setGPA(Float.parseFloat(this.edtGPA.getText()));
        std.setImg(this.edtImage.getText());
        std.setAddress(this.edtAddress.getText());
        std.setNote(this.edtNote.getText());
        this.StudentManager.changeStudent(index, std);
    }

    public void importData() throws FileNotFoundException, IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("File", "txt", "csv", "dat");
        fileChooser.setFileFilter(fnef);
        int option = fileChooser.showDialog(this, "Import");

        if (option == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "File not found");
        } else if (option == JFileChooser.APPROVE_OPTION) {
            if (!this.StudentManager.getListStd().isEmpty()) {
                this.StudentManager.removeAllStudents();
            }
            File f = fileChooser.getSelectedFile();
            String filePath = f.getAbsolutePath();
            int index = f.getName().lastIndexOf('.');
            String extension = null;
            if (index > 0) {
                extension = f.getName().substring(index + 1);
            }

            if ("txt".equals(extension) || "csv".equals(extension)) {
                try {
                    File file = new File(filePath);
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    Object[] tableLines = br.lines().toArray();
                    for (Object tableLine : tableLines) {
                        String line = tableLine.toString().trim();
                        String[] dataRow = line.split(",");
                        Student std = new Student();
                        std.setID(dataRow[0]);
                        std.setName(dataRow[1]);
                        std.setGPA(Float.parseFloat(dataRow[2]));
                        std.setImg(dataRow[3]);
                        std.setAddress(dataRow[4]);
                        std.setNote(dataRow[5]);
                        this.StudentManager.addStudent(std);
                    }
                } catch (HeadlessException | NumberFormatException e) {
                }
            } else {
                FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois;
                ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    Student std = (Student) ois.readObject();
                    this.StudentManager.addStudent(std);
                }
            }
            this.showTable();
            JOptionPane.showMessageDialog(null, "Imported data successfully");    
        }

    }

    public void exportData() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Document", "txt", "csv", "dat");
        fileChooser.setFileFilter(fnef);
        int option = fileChooser.showDialog(this, "Export");
        if (option == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "File not found");
        } else if (option == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String filepath = f.getAbsolutePath();
            int index = f.getName().lastIndexOf('.');
            String extension = null;
            if (index > 0) {
                extension = f.getName().substring(index + 1);
            }
            if ("txt".equals(extension) || "csv".equals(extension)) {
                try {
                    FileWriter fw = new FileWriter(filepath);
                    BufferedWriter bw = new BufferedWriter(fw);
                    ArrayList<Student> list = this.StudentManager.getListStd();
                    for (Student s : list) {
                        bw.write(s.toString());
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                try {
                    FileOutputStream fos = new FileOutputStream(filepath);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    ArrayList<Student> list = this.StudentManager.getListStd();
                    for (Student s : list) {
                        oos.writeObject(s);
                    }
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            JOptionPane.showMessageDialog(null, "Exported data successfully");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        edtID = new javax.swing.JTextField();
        edtName = new javax.swing.JTextField();
        txtGPA = new javax.swing.JLabel();
        edtGPA = new javax.swing.JTextField();
        edtImage = new javax.swing.JTextField();
        txtAddress = new javax.swing.JLabel();
        edtAddress = new javax.swing.JTextField();
        txtNote = new javax.swing.JLabel();
        edtNote = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnSortGPA = new javax.swing.JToggleButton();
        btnSortID = new javax.swing.JToggleButton();
        btnImage = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        txtGPA1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Management");

        txtID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtID.setText("ID");

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtName.setText("Name");

        edtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtIDActionPerformed(evt);
            }
        });

        edtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNameActionPerformed(evt);
            }
        });

        txtGPA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGPA.setText("GPA");

        edtGPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtGPAActionPerformed(evt);
            }
        });

        edtImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtImageActionPerformed(evt);
            }
        });

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAddress.setText("Address");

        edtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtAddressActionPerformed(evt);
            }
        });

        txtNote.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNote.setText("Note");
        txtNote.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        edtNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNoteActionPerformed(evt);
            }
        });

        btnInsert.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnImport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExport.setText("Export");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        tblStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "GPA", "Image", "Address", "Note"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudent.setMinimumSize(new java.awt.Dimension(90, 60));
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStudent);

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 140, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Student Management");

        btnSortGPA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSortGPA.setText("Sort by GPA");
        btnSortGPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortGPAActionPerformed(evt);
            }
        });

        btnSortID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSortID.setText("Sort by ID");
        btnSortID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortIDActionPerformed(evt);
            }
        });

        btnImage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnImage.setText("Upload");
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });

        txtGPA1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGPA1.setText("Image");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(267, 267, 267))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtGPA1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(edtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnImage)
                                .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtGPA, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(edtID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                .addComponent(lblImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(edtName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtGPA))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAddress)
                            .addComponent(txtNote))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(edtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnExport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnInsert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnImport, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnDelete)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnUpdate)
                                                .addGap(6, 6, 6)
                                                .addComponent(btnClear))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnSortID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnSortGPA, javax.swing.GroupLayout.Alignment.TRAILING))))
                                    .addComponent(edtNote))))))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGPA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImage)
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGPA1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInsert)
                            .addComponent(btnDelete)
                            .addComponent(btnUpdate)
                            .addComponent(btnClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSortGPA)
                            .addComponent(btnImport))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSortID)
                            .addComponent(btnExport)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Image", "png", "jpg", "gif");
        fileChooser.setFileFilter(fnef);
        int showDialog = fileChooser.showOpenDialog(null);
        if (showDialog == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String filepath = f.getAbsolutePath();
            ImageIcon imgIcon = new ImageIcon(filepath);
            Image img = imgIcon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            this.lblImage.setIcon(new ImageIcon(img));
            this.edtImage.setText(f.getName());
        }
    }//GEN-LAST:event_btnImageActionPerformed

    private void btnSortIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortIDActionPerformed
        if (btnSortID.isSelected()) {
            this.StudentManager.sortAscByID();
        } else {
            this.StudentManager.sortDescByID();
        }
        this.showTable();
    }//GEN-LAST:event_btnSortIDActionPerformed

    private void btnSortGPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortGPAActionPerformed
        if (btnSortGPA.isSelected()) {
            this.StudentManager.sortAscByGPA();
        } else {
            this.StudentManager.sortDescByGPA();
        }
        this.showTable();
    }//GEN-LAST:event_btnSortGPAActionPerformed

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) this.tblStudent.getModel();
        int index = this.tblStudent.getSelectedRow();
        if (index == -1) {
            return;
        }
        String fID = dtm.getValueAt(index, 0).toString();
        String fName = dtm.getValueAt(index, 1).toString();
        String fGPA = dtm.getValueAt(index, 2).toString();
        String fImage = dtm.getValueAt(index, 3).toString();
        String fAddress = dtm.getValueAt(index, 4).toString();
        String fNote = dtm.getValueAt(index, 5).toString();
        this.edtID.setText(fID);
        this.edtName.setText(fName);
        this.edtGPA.setText(fGPA);
        this.edtImage.setText(fImage);
        this.edtAddress.setText(fAddress);
        this.edtNote.setText(fNote);
    }//GEN-LAST:event_tblStudentMouseClicked

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        this.exportData();
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        try {
            this.importData();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.clear();
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.update();
        this.showTable();
        this.clear();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.delete();
        this.showTable();
        this.clear();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        if (this.validationForm()) {
            this.insert();
            this.showTable();
            this.clear();
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void edtNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNoteActionPerformed

    private void edtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtAddressActionPerformed

    private void edtGPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtGPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtGPAActionPerformed

    private void edtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNameActionPerformed

    private void edtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtIDActionPerformed

    private void edtImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtImageActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImage;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnInsert;
    private javax.swing.JToggleButton btnSortGPA;
    private javax.swing.JToggleButton btnSortID;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField edtAddress;
    private javax.swing.JTextField edtGPA;
    private javax.swing.JTextField edtID;
    private javax.swing.JTextField edtImage;
    private javax.swing.JTextField edtName;
    private javax.swing.JTextField edtNote;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tblStudent;
    private javax.swing.JLabel txtAddress;
    private javax.swing.JLabel txtGPA;
    private javax.swing.JLabel txtGPA1;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtNote;
    // End of variables declaration//GEN-END:variables
}
