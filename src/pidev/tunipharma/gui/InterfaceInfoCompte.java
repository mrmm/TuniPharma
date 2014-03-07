/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.gui;

import javax.swing.JButton;
import pidev.tunipharma.classes.Compte;
import pidev.tunipharma.dao.ComptesDAO;
import pidev.tunipharma.utils.GUIUtils;

/**
 *
 * @author elron
 */
public class InterfaceInfoCompte extends javax.swing.JFrame {

    /**
     * Creates new form InterfaceInfoCompte
     */
    private Compte cptInfo;

    public InterfaceInfoCompte(int id) {
        initComponents();
        String titre = "";
        Compte c = null;
        c = ComptesDAO.getInstance().readById((int) id);
        cptInfo = c;
        if (c != null) {
            System.out.println("Compte not Null");
            titre = "Information Compte - " + c.getNom_cpt() + " " + c.getPrenom_cpt();
        } else {
            GUIUtils.showMsgBox("Compte Invalid !!");
            this.dispose();
        }
        this.setTitle(titre);

        txtMesInfosNom.setText(c.getNom_cpt());
        txtMesInfosPrenom.setText(c.getPrenom_cpt());
        txtMesInfosEmail.setText(c.getEmail_cpt());
        txtMesInfosTel.setText(String.valueOf(c.getTel_cpt()));
        txtMesInfosAddresse.setText(c.getAddresse_cpt());
        GUIUtils.disAllTextField(panelInfoCpt);

        GUIUtils.onChangeEmpty(txtMesInfosNom, btModCpt);
        GUIUtils.onChangeEmpty(txtMesInfosPrenom, btModCpt);
        GUIUtils.onChangeNumber(txtInfosNvMDP, btModCpt);
        GUIUtils.onChangeEmail(txtMesInfosEmail, btModCpt);
        GUIUtils.onChangeMDP(txtInfosNvMDP, txtInfosNvRMDP, btModCpt);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInfoCpt = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtMesInfosNom = new javax.swing.JTextField();
        txtMesInfosPrenom = new javax.swing.JTextField();
        txtMesInfosEmail = new javax.swing.JTextField();
        txtMesInfosTel = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtMesInfosAddresse = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        btModCpt = new javax.swing.JButton();
        btAnnuler = new javax.swing.JButton();
        txtInfosNvMDP = new javax.swing.JPasswordField();
        txtInfosNvRMDP = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        jLabel1.setText("Informations Compte");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Nom :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Prenom :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Adresse Email :");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Num Tél :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Nouveau mot de passe :");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Adresse :");

        txtMesInfosNom.setName("Nom");
        txtMesInfosNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesInfosNomActionPerformed(evt);
            }
        });

        txtMesInfosPrenom.setName("Prénom");
        txtMesInfosPrenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesInfosPrenomActionPerformed(evt);
            }
        });

        txtMesInfosEmail.setName("Email");
        txtMesInfosEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesInfosEmailActionPerformed(evt);
            }
        });

        txtMesInfosTel.setName("Num Tél");

        txtMesInfosAddresse.setColumns(20);
        txtMesInfosAddresse.setRows(5);
        txtMesInfosAddresse.setName("Adresse");
        jScrollPane10.setViewportView(txtMesInfosAddresse);

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Ressaisir mot de passe :");

        btModCpt.setText("Modifier Compte");
        btModCpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModCptActionPerformed(evt);
            }
        });

        btAnnuler.setText("Annuler");
        btAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInfoCptLayout = new javax.swing.GroupLayout(panelInfoCpt);
        panelInfoCpt.setLayout(panelInfoCptLayout);
        panelInfoCptLayout.setHorizontalGroup(
            panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoCptLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMesInfosNom)
                    .addComponent(txtMesInfosPrenom)
                    .addComponent(txtMesInfosEmail)
                    .addComponent(txtMesInfosTel)
                    .addComponent(jScrollPane10)
                    .addComponent(txtInfosNvMDP)
                    .addComponent(txtInfosNvRMDP))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoCptLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btModCpt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelInfoCptLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoCptLayout.setVerticalGroup(
            panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoCptLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoCptLayout.createSequentialGroup()
                        .addComponent(txtMesInfosNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMesInfosPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMesInfosEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMesInfosTel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)))
                    .addGroup(panelInfoCptLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtInfosNvMDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtInfosNvRMDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(30, 30, 30)
                .addGroup(panelInfoCptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btModCpt)
                    .addComponent(btAnnuler))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfoCpt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfoCpt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMesInfosNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesInfosNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesInfosNomActionPerformed

    private void txtMesInfosPrenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesInfosPrenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesInfosPrenomActionPerformed

    private void txtMesInfosEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesInfosEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesInfosEmailActionPerformed

    private void btModCptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModCptActionPerformed
        // TODO add your handling code here:
        if (((JButton) evt.getSource()).getText().equals("Modifier Compte")) {
            GUIUtils.enAllTextField(panelInfoCpt);
            GUIUtils.enAllTextField(panelInfoCpt);
            btModCpt.setText("Enregistrer Modification");
        } else {
            if (GUIUtils.checkForm(panelInfoCpt)) {
                if (GUIUtils.showConfBox("Voulez-vous enregistrer les modification ?")) {
                    GUIUtils.disAllTextField(panelInfoCpt);
                    String pass = String.valueOf(txtInfosNvMDP.getPassword(), 0, txtInfosNvMDP.getPassword().length);
                    Compte cpt = new Compte(cptInfo.getId_cpt(), txtMesInfosNom.getText(),
                            txtMesInfosPrenom.getText(),
                            txtMesInfosAddresse.getText(),
                            txtMesInfosEmail.getText(),
                            pass,
                            Integer.parseInt(txtMesInfosTel.getText()),
                            cptInfo.getType_cpt(),
                            cptInfo.isEtat_cpt());
                    ComptesDAO.getInstance().update(cpt);
                    //GUIUtils.showMsgBox(cpt.toString());
                    btModCpt.setText("Modifier Compte");
                }
            }
        }
    }//GEN-LAST:event_btModCptActionPerformed

    private void btAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulerActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btAnnulerActionPerformed

    /**
     * @param args the command line arguments
     * @param c Le compte pour afficher ces informations
     */
    public static void main(String args[], final Object id) {
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
            java.util.logging.Logger.getLogger(InterfaceInfoCompte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceInfoCompte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceInfoCompte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceInfoCompte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Compte c;
                c = ComptesDAO.getInstance().readById((int) id);
                if (c != null) {
                    System.out.println("Compte not Null");
                    //new InterfaceInfoCompte("Information Comptes - " + c.getNom_cpt() + " " + c.getPrenom_cpt(), c).setVisible(true);
                } else {
                    GUIUtils.showMsgBox("Compte Invalid !!");
                }
                GUIUtils.showMsgBox(c.getClass().getName());

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnnuler;
    private javax.swing.JButton btModCpt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JPanel panelInfoCpt;
    private javax.swing.JPasswordField txtInfosNvMDP;
    private javax.swing.JPasswordField txtInfosNvRMDP;
    private javax.swing.JTextArea txtMesInfosAddresse;
    private javax.swing.JTextField txtMesInfosEmail;
    private javax.swing.JTextField txtMesInfosNom;
    private javax.swing.JTextField txtMesInfosPrenom;
    private javax.swing.JTextField txtMesInfosTel;
    // End of variables declaration//GEN-END:variables
}
