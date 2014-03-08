/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.gui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import pidev.tunipharma.classes.Pharmacie;
import pidev.tunipharma.dao.PharmaciesDAO;
import pidev.tunipharma.dao.VotesDAO;
import pidev.tunipharma.utils.GUIUtil;

/**
 *
 * @author elron
 */
public class InterfaceInfoPharmacie extends javax.swing.JFrame {

    /**
     * Creates new form InterfaceInfoPharmacie
     *
     * @param id ID de la pharmacie afficher
     */
    private Pharmacie phaInfo;

    public InterfaceInfoPharmacie(int id) {
        initComponents();
        String titre = "";
        Pharmacie p = PharmaciesDAO.getInstance().readById(id);
        phaInfo = p;
        if (p != null) {
            System.out.println("Pharmacie not Null");
            titre = "Information Pharmacie - " + p.getNom_pha();
        } else {
            GUIUtil.showMsgBox("Pharmacie Invalid !!");
            this.dispose();
        }
        this.setTitle(titre);
        setLocationRelativeTo(null);
        
        Etoile1.setVisible(false);
        Etoile2.setVisible(false);
        Etoile3.setVisible(false);
        Etoile4.setVisible(false);
        Etoile5.setVisible(false);
        
        afficherVote(phaInfo.getId_pha());
    }

    private void afficherVote(int id) {

        try {
            
            int nb_etoile = VotesDAO.getInstance().readByIdPha(id);
            switch (nb_etoile) {
                case 1:
                    Etoile1.setVisible(true);
                case 2:
                    Etoile1.setVisible(true);
                    Etoile2.setVisible(true);
                case 3:
                    Etoile1.setVisible(true);
                    Etoile2.setVisible(true);
                    Etoile3.setVisible(true);
                case 4:
                    Etoile1.setVisible(true);
                    Etoile2.setVisible(true);
                    Etoile3.setVisible(true);
                    Etoile4.setVisible(true);
                    break;
                case 5:

                    Etoile1.setVisible(true);
                    Etoile2.setVisible(true);
                    Etoile3.setVisible(true);
                    Etoile4.setVisible(true);
                    Etoile5.setVisible(true);
                    break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfaceInfoPharmacie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void fillInfoPha() {
        txtMesInfosNom.setText(phaInfo.getNom_pha());
        txtMesInfosResponsable.setText(String.valueOf(phaInfo.getId_resp()));
        txtMesInfosEmail.setText(phaInfo.getEmail_pha());
        txtMesInfosVille.setText(String.valueOf(phaInfo.getVille_pha()));
        txtInfosGouvernorat.setText(String.valueOf(phaInfo.getGouv_pha()));
        txtInfosTel.setText(String.valueOf(phaInfo.getTel_pha()));
        txtInfosFax.setText(String.valueOf(phaInfo.getFax_pha()));
        txtInfosType.setText(phaInfo.getTypePhaNom());
        txtMesInfosAddresse.setText(phaInfo.getAddresse_pha());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInfoPha = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtMesInfosNom = new javax.swing.JTextField();
        txtMesInfosResponsable = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtMesInfosEmail = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtMesInfosVille = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtInfosGouvernorat = new javax.swing.JPasswordField();
        jLabel34 = new javax.swing.JLabel();
        txtInfosTel = new javax.swing.JPasswordField();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtMesInfosAddresse = new javax.swing.JTextArea();
        btModCpt = new javax.swing.JButton();
        btAnnuler = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        txtInfosFax = new javax.swing.JPasswordField();
        txtInfosType = new javax.swing.JPasswordField();
        jLabel36 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        PanelVote = new javax.swing.JPanel();
        Etoile1 = new javax.swing.JButton();
        Etoile2 = new javax.swing.JButton();
        Etoile3 = new javax.swing.JButton();
        Etoile4 = new javax.swing.JButton();
        Etoile5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        jLabel1.setText("Informations Pharmacie");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Nom :");

        txtMesInfosNom.setName("Nom");
        txtMesInfosNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesInfosNomActionPerformed(evt);
            }
        });

        txtMesInfosResponsable.setName("Prénom");
        txtMesInfosResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesInfosResponsableActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Responsable :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Adresse Email :");

        txtMesInfosEmail.setName("Email");
        txtMesInfosEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesInfosEmailActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Ville : ");

        txtMesInfosVille.setName("Num Tél");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Gouvenorat :");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Tel : ");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Adresse :");

        txtMesInfosAddresse.setColumns(20);
        txtMesInfosAddresse.setRows(5);
        txtMesInfosAddresse.setName("Adresse");
        jScrollPane10.setViewportView(txtMesInfosAddresse);

        btModCpt.setText("Modifier Pharmacie");
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

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Fax : ");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Type : ");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Note : ");

        Etoile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pidev/tunipharma/gui/etoile2.jpg"))); // NOI18N
        Etoile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Etoile1ActionPerformed(evt);
            }
        });

        Etoile2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pidev/tunipharma/gui/etoile2.jpg"))); // NOI18N
        Etoile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Etoile2ActionPerformed(evt);
            }
        });

        Etoile3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pidev/tunipharma/gui/etoile2.jpg"))); // NOI18N
        Etoile3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Etoile3ActionPerformed(evt);
            }
        });

        Etoile4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pidev/tunipharma/gui/etoile2.jpg"))); // NOI18N

        Etoile5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pidev/tunipharma/gui/etoile2.jpg"))); // NOI18N

        javax.swing.GroupLayout PanelVoteLayout = new javax.swing.GroupLayout(PanelVote);
        PanelVote.setLayout(PanelVoteLayout);
        PanelVoteLayout.setHorizontalGroup(
            PanelVoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVoteLayout.createSequentialGroup()
                .addComponent(Etoile1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Etoile2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Etoile3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Etoile4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Etoile5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        PanelVoteLayout.setVerticalGroup(
            PanelVoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVoteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelVoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Etoile2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(Etoile1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Etoile3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Etoile4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Etoile5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelInfoPhaLayout = new javax.swing.GroupLayout(panelInfoPha);
        panelInfoPha.setLayout(panelInfoPhaLayout);
        panelInfoPhaLayout.setHorizontalGroup(
            panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPhaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMesInfosEmail)
                        .addComponent(txtMesInfosVille)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                        .addComponent(txtInfosGouvernorat)
                        .addComponent(txtInfosTel)
                        .addComponent(txtInfosFax)
                        .addComponent(txtInfosType)
                        .addComponent(txtMesInfosNom)
                        .addComponent(txtMesInfosResponsable, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(PanelVote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoPhaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btModCpt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelInfoPhaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoPhaLayout.setVerticalGroup(
            panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPhaLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelVote, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoPhaLayout.createSequentialGroup()
                        .addComponent(txtMesInfosNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMesInfosResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMesInfosEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMesInfosVille, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)))
                    .addGroup(panelInfoPhaLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtInfosGouvernorat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtInfosTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtInfosFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtInfosType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(30, 30, 30)
                .addGroup(panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btModCpt)
                    .addComponent(btAnnuler))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfoPha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(panelInfoPha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulerActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btAnnulerActionPerformed

    private void btModCptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModCptActionPerformed
        // TODO add your handling code here:
        if (((JButton) evt.getSource()).getText().equals("Modifier Compte")) {
            GUIUtil.enAllTextField(panelInfoPha);
            btModCpt.setText("Enregistrer Modification");
        } else {
            if (GUIUtil.checkForm(panelInfoPha)) {
                if (GUIUtil.showConfBox("Voulez-vous enregistrer les modification ?")) {
                    GUIUtil.enAllTextField(panelInfoPha);
                    String pass = String.valueOf(txtInfosGouvernorat.getPassword(), 0, txtInfosGouvernorat.getPassword().length);
                    //                    Compte cpt = new Compte(phaInfo.getId_cpt(), txtMesInfosNom.getText(),
                    //                        txtMesInfosPrenom.getText(),
                    //                        txtMesInfosAddresse.getText(),
                    //                        txtMesInfosEmail.getText(),
                    //                        pass,
                    //                        Integer.parseInt(txtMesInfosTel.getText()),
                    //                        phaInfo.getType_cpt(),
                    //                        phaInfo.isEtat_cpt());
                    //                    ComptesDAO.getInstance().update(cpt);
                    //GUIUtils.showMsgBox(cpt.toString());
                    btModCpt.setText("Modifier Compte");
                }
            }
        }
    }//GEN-LAST:event_btModCptActionPerformed

    private void txtMesInfosEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesInfosEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesInfosEmailActionPerformed

    private void txtMesInfosResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesInfosResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesInfosResponsableActionPerformed

    private void txtMesInfosNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesInfosNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesInfosNomActionPerformed

    private void Etoile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Etoile1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Etoile1ActionPerformed

    private void Etoile2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Etoile2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Etoile2ActionPerformed

    private void Etoile3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Etoile3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Etoile3ActionPerformed

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
            java.util.logging.Logger.getLogger(InterfaceInfoPharmacie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceInfoPharmacie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceInfoPharmacie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceInfoPharmacie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceInfoPharmacie(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Etoile1;
    private javax.swing.JButton Etoile2;
    private javax.swing.JButton Etoile3;
    private javax.swing.JButton Etoile4;
    private javax.swing.JButton Etoile5;
    private javax.swing.JPanel PanelVote;
    private javax.swing.JButton btAnnuler;
    private javax.swing.JButton btModCpt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JPanel panelInfoPha;
    private javax.swing.JPasswordField txtInfosFax;
    private javax.swing.JPasswordField txtInfosGouvernorat;
    private javax.swing.JPasswordField txtInfosTel;
    private javax.swing.JPasswordField txtInfosType;
    private javax.swing.JTextArea txtMesInfosAddresse;
    private javax.swing.JTextField txtMesInfosEmail;
    private javax.swing.JTextField txtMesInfosNom;
    private javax.swing.JTextField txtMesInfosResponsable;
    private javax.swing.JTextField txtMesInfosVille;
    // End of variables declaration//GEN-END:variables

    
}
