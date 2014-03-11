/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.gui;

import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import pidev.tunipharma.classes.BoiteMessages;
import pidev.tunipharma.classes.Compte;
import pidev.tunipharma.classes.Demande;
import pidev.tunipharma.classes.Evenement;
import pidev.tunipharma.classes.Gouvernorat;
import pidev.tunipharma.classes.Message;
import pidev.tunipharma.classes.Pharmacie;
import pidev.tunipharma.classes.Service;
import pidev.tunipharma.classes.TypeService;
import pidev.tunipharma.classes.Ville;
import pidev.tunipharma.dao.BoitesMessagesDAO;
import pidev.tunipharma.dao.DemandesDAO;
import pidev.tunipharma.dao.EvenementsDAO;
import pidev.tunipharma.dao.GouvernoratsDAO;
import pidev.tunipharma.dao.MessagesDAO;
import pidev.tunipharma.dao.PharmaciesDAO;
import pidev.tunipharma.dao.ServicesDAO;
import pidev.tunipharma.dao.VillesDAO;
import pidev.tunipharma.utils.GUIUtil;
import pidev.tunipharma.utils.Session;
import static pidev.tunipharma.gui.TableButton.makeTable;

/**
 *
 * @author elron
 */
public class InterfaceUtilisateur extends javax.swing.JFrame {

    /**
     * Creates new form PharmacienAjouterService
     */
    private Compte connUser = Session.getCptConn();
    private Pharmacie maPharmacie;

    public InterfaceUtilisateur() {

        connUser = Session.getCptConn();
        //connUser = new Compte(3, "Mourad", "Maatoug", "El Fahs", "mourad@gmail.com", "112233", 12345678, Compte.COMPTE_PHARMACIEN, true);
        //connUser = ComptesDAO.getInstance().readById(1);
        if (connUser == null) {
            GUIUtil.showMsgBox(this, "Veuillez-connecter !");
            this.dispose();
        } else {

            initComponents();
            if (connUser.getType_cpt() == Compte.COMPTE_CLIENT) {
                System.out.println("Compte COMPTE_CLIENT");
                this.setTitle("Mon Compte [Client]");
                tabPaneMonCompte.remove(tabPaneMonCompte.getComponents().length - 1);
            } else if (connUser.getType_cpt() == Compte.COMPTE_PHARMACIEN) {
                System.out.println("Compte COMPTE_PHARMACIEN");
                this.setTitle("Mon Compte [Pharmacien]");
                maPharmacie = PharmaciesDAO.getInstance().readByIdResp(connUser.getId_cpt());
                System.out.println("Ma Pharmacie : " + maPharmacie);
            }
            setLocationRelativeTo(null);
            System.out.println("Mon Compte : " + connUser);
            System.out.println("Ma Pharmacie : " + maPharmacie);
            if (maPharmacie == null) {
                btMaPhaConfirmer.setName("Ajout");
                btMaPhaConfirmer.setText("Ajouter ma pharmacie");
            } else {
                fillMaPhaInformation();
                btMaPhaConfirmer.setName("Affichage");
                btMaPhaConfirmer.setText("Modifier les informations");
                GUIUtil.disAllTextField(panelInfoMaPha);
                fillTableMesSrv();
            }
            GUIUtil.villeGouvListener(comboBoxMaPhaGouv, comboBoxMaPhaVille, false);
            GUIUtil.fillGouvsCB(comboBoxMaPhaGouv, false);
            // Remplissage de tableau des messages
            fillTableMesMsg();
            //Remplissage des champs avec les information de compte
            fillMesInformation();

            // controlle de saisi - Ajout evenement
            GUIUtil.onChangeEmpty(txtMaPhaAjoutEventNom, btMaPhaAjoutEventConfirmer);
            GUIUtil.onChangeEmpty(txtMaPhaAjoutEventDesc, btMaPhaAjoutEventConfirmer);

            // controlle de saisi - Ajout service
            GUIUtil.onChangeEmpty(txtMaPhaAjoutSrvDesc, buttonMaPhaAjoutSrvConfirmer);
            GUIUtil.onChangeEmpty(txtMaPhaAjoutSrvNom, buttonMaPhaAjoutSrvConfirmer);

            //desactiver les champs de text des mes information
            GUIUtil.disAllTextField(panelInfoPerso);

            GUIUtil.fillTypeSrvCB(comboBoxMaPhaAjoutSrvType);
        }

    }

    private void fillTableMesMsg() {
        BoiteMessages bm = BoitesMessagesDAO.getInstance().readByIdCpt(connUser.getId_cpt());
        List<Message> l = MessagesDAO.getInstance().readAllByIdUser(bm.getId_bt());
        GUIUtil.rempTableMesMsg(tableMesMsg, l);
        tableMesMsg.getColumnModel().getColumn(0).setPreferredWidth(2);
    }

    private void fillTableMesSrv() {
        List<Service> l = ServicesDAO.getInstance().readAllByIdPha(maPharmacie.getId_pha());
        GUIUtil.rempTableSrv(tableMaPhaMesSrv, l);
    }

    private void fillTableMesEvent() {
        List<Evenement> l = EvenementsDAO.getInstance().readAllByIdPha(maPharmacie.getId_pha());
        GUIUtil.rempTableEvent(tableMaPhaMesEvent, l);
    }

    private void fillMesInformation() {
        txtMesInfosNom.setText(connUser.getNom_cpt());
        txtMesInfosPrenom.setText(connUser.getPrenom_cpt());
        txtMesInfosEmail.setText(connUser.getEmail_cpt());
        txtMesInfosTel.setText(connUser.getTel_cpt() + "");
        txtMeInfosAddresse.setText(connUser.getAddresse_cpt());
    }

    private void fillMaPhaInformation() {
        txtMaPhaNom.setText(maPharmacie.getNom_pha());
        txtMaPhaTel.setText(maPharmacie.getTel_pha() + "");
        txtMaPhaFax.setText(maPharmacie.getFax_pha() + "");
        txtMaPhaAddresse.setText(connUser.getAddresse_cpt());
        txtMaPhaLatitude.setText(maPharmacie.getLat_gm_pha());
        txtMaPhaLongitude.setText(maPharmacie.getLong_gm_pha());
        txtMaPhaEmail.setText(maPharmacie.getEmail_pha());
        Gouvernorat gouvPha = GouvernoratsDAO.getInstance().readById(maPharmacie.getGouv_pha());
        comboBoxMaPhaGouv.setSelectedItem(gouvPha);
        Ville villePha = VillesDAO.getInstance().readById(maPharmacie.getVille_pha());
        comboBoxMaPhaGouv.setSelectedItem(villePha);
        comboBoxMaPhaType.setSelectedIndex(maPharmacie.getType_pha() - 1);
        GUIUtil.addAllDateFromDB(tableMaPhaJourDeGarde, maPharmacie.getId_pha());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPaneMonCompte = new javax.swing.JTabbedPane();
        jPanel787 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        panelInfoPerso = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
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
        txtMeInfosNvMDP = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtMeInfosAddresse = new javax.swing.JTextArea();
        btMesInfoConfirmer = new javax.swing.JButton();
        btMesInfoAnnuler = new javax.swing.JButton();
        txtMeInfosNvRMDP = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        panelMsg = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMesMsg = new javax.swing.JTable();
        panelMaPha = new javax.swing.JPanel();
        jTabMyPhaInfo = new javax.swing.JTabbedPane();
        panelInfoPha = new javax.swing.JPanel();
        panelInfoMaPha = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        txtMaPhaNom = new javax.swing.JTextField();
        dateGarde = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableMaPhaJourDeGarde = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txtMaPhaEmail = new javax.swing.JTextField();
        txtMaPhaTel = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtMaPhaFax = new javax.swing.JTextField();
        comboBoxMaPhaGouv = new javax.swing.JComboBox();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        comboBoxMaPhaVille = new javax.swing.JComboBox();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtMaPhaAddresse = new javax.swing.JTextArea();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtMaPhaLongitude = new javax.swing.JTextField();
        txtMaPhaLatitude = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        comboBoxMaPhaType = new javax.swing.JComboBox();
        btMaPhaConfirmer = new javax.swing.JButton();
        btMaPhaAnnuler = new javax.swing.JButton();
        btAjoutJourGarde = new javax.swing.JButton();
        panelEvents = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        panelMesEvents = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableMaPhaMesEvent = new javax.swing.JTable();
        panelAjoutEvent = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btMaPhaAjoutEventAnnuler = new javax.swing.JButton();
        btMaPhaAjoutEventConfirmer = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtMaPhaAjoutEventNom = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtMaPhaAjoutEventDesc = new javax.swing.JTextArea();
        jLabel44 = new javax.swing.JLabel();
        spMaPhaAjoutEventHeure = new javax.swing.JSpinner();
        spMaPhaAjoutEventMin = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        dateChooserAjoutEvent = new com.toedter.calendar.JDateChooser();
        tabbedPaneServices = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableMaPhaMesSrv = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        panelAjoutService = new javax.swing.JPanel();
        txtMaPhaAjoutSrvNom = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtMaPhaAjoutSrvDesc = new javax.swing.JTextArea();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        comboBoxMaPhaAjoutSrvType = new javax.swing.JComboBox();
        buttonMaPhaAjoutSrvConfirmer = new javax.swing.JButton();
        buttonMaPhaAjoutSrvAnnuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interface Pharmacien");
        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        tabPaneMonCompte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabPaneMonCompte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPaneMonCompteMouseClicked(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mes informations", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        panelInfoPerso.setName(""); // NOI18N

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

        txtMesInfosNom.setName("txtnom"); // NOI18N
        txtMesInfosNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesInfosNomActionPerformed(evt);
            }
        });

        txtMesInfosPrenom.setName("txtprenom"); // NOI18N

        txtMesInfosEmail.setName("txtemail"); // NOI18N

        txtMesInfosTel.setName("txttel"); // NOI18N

        txtMeInfosNvMDP.setName("txtpwd2"); // NOI18N

        txtMeInfosAddresse.setColumns(20);
        txtMeInfosAddresse.setRows(5);
        txtMeInfosAddresse.setName("txtadresse"); // NOI18N
        jScrollPane10.setViewportView(txtMeInfosAddresse);

        btMesInfoConfirmer.setText("Modifier mes information");
        btMesInfoConfirmer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMesInfoConfirmerActionPerformed(evt);
            }
        });

        btMesInfoAnnuler.setText("Annuler");
        btMesInfoAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMesInfoAnnulerActionPerformed(evt);
            }
        });

        txtMeInfosNvRMDP.setName("txtpwd1"); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Ressaisir mot de passe :");

        javax.swing.GroupLayout panelInfoPersoLayout = new javax.swing.GroupLayout(panelInfoPerso);
        panelInfoPerso.setLayout(panelInfoPersoLayout);
        panelInfoPersoLayout.setHorizontalGroup(
            panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPersoLayout.createSequentialGroup()
                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInfoPersoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoPersoLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoPersoLayout.createSequentialGroup()
                                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)))
                        .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMeInfosNvRMDP)
                                .addComponent(txtMeInfosNvMDP, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelInfoPersoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoPersoLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelInfoPersoLayout.createSequentialGroup()
                                .addGap(0, 94, Short.MAX_VALUE)
                                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoPersoLayout.createSequentialGroup()
                                        .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoPersoLayout.createSequentialGroup()
                                        .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)))
                                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMesInfosNom, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(txtMesInfosPrenom)
                                    .addComponent(txtMesInfosEmail)
                                    .addComponent(txtMesInfosTel))))))
                .addContainerGap(546, Short.MAX_VALUE))
            .addGroup(panelInfoPersoLayout.createSequentialGroup()
                .addGap(338, 338, 338)
                .addComponent(btMesInfoConfirmer)
                .addGap(18, 18, 18)
                .addComponent(btMesInfoAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoPersoLayout.setVerticalGroup(
            panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPersoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(22, 22, 22)
                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoPersoLayout.createSequentialGroup()
                        .addComponent(txtMesInfosNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMesInfosPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMesInfosEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMesInfosTel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)))
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMeInfosNvMDP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMeInfosNvRMDP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87)
                .addGroup(panelInfoPersoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMesInfoConfirmer)
                    .addComponent(btMesInfoAnnuler))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfoPerso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfoPerso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel787Layout = new javax.swing.GroupLayout(jPanel787);
        jPanel787.setLayout(jPanel787Layout);
        jPanel787Layout.setHorizontalGroup(
            jPanel787Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel787Layout.setVerticalGroup(
            jPanel787Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPaneMonCompte.addTab("Informations personnelles", jPanel787);

        tableMesMsg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "De", "À", "Date", "Objet", "Message", "Options"
            }
        ));
        tableMesMsg.setToolTipText("");
        tableMesMsg = makeTable(pidev.tunipharma.utils.GUIUtil.getModel(new Object[][]{{"","","","","","",""}}, new String [] {
            "ID", "De", "À", "Date", "Objet", "Message", "Options"
        }),6,TableButton.AFFFICHER_REPONDRE_SUPPRIMER);
        tableMesMsg.setName("tableMesMsg");
        jScrollPane2.setViewportView(tableMesMsg);

        javax.swing.GroupLayout panelMsgLayout = new javax.swing.GroupLayout(panelMsg);
        panelMsg.setLayout(panelMsgLayout);
        panelMsgLayout.setHorizontalGroup(
            panelMsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMsgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMsgLayout.setVerticalGroup(
            panelMsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMsgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPaneMonCompte.addTab("Mes messages", panelMsg);

        panelMaPha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jTabMyPhaInfo.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabMyPhaInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabMyPhaInfoMouseClicked(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Nom :");

        txtMaPhaNom.setName("Nom");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Jours de garde");

        tableMaPhaJourDeGarde.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jour", "Options"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMaPhaJourDeGarde = makeTable(pidev.tunipharma.utils.GUIUtil.getModel(new Object[][]{}, new String [] {
            "Jour", "Options"
        }),1,TableButton.SUPPRIMER);
        tableMaPhaJourDeGarde.setName("tableMaPhaJourDeGarde");
        jScrollPane3.setViewportView(tableMaPhaJourDeGarde);

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setText("Email :");
        jLabel46.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel46.setMinimumSize(new java.awt.Dimension(38, 17));

        txtMaPhaEmail.setName("Email");

        txtMaPhaTel.setName("Num Tél");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("Num Tél :");
        jLabel37.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel37.setMinimumSize(new java.awt.Dimension(38, 17));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Num Fax :");
        jLabel39.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel39.setMinimumSize(new java.awt.Dimension(38, 17));

        txtMaPhaFax.setName("Num Fax");

        comboBoxMaPhaGouv.setName("Gouvernorat");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("Gouvernorat : ");
        jLabel50.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel50.setMinimumSize(new java.awt.Dimension(38, 17));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setText("Ville :");
        jLabel51.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel51.setMinimumSize(new java.awt.Dimension(38, 17));

        comboBoxMaPhaVille.setName("Ville");

        txtMaPhaAddresse.setColumns(20);
        txtMaPhaAddresse.setRows(5);
        txtMaPhaAddresse.setName("Adresse");
        jScrollPane12.setViewportView(txtMaPhaAddresse);

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel52.setText("Adresse :");
        jLabel52.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel52.setMinimumSize(new java.awt.Dimension(38, 17));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("Longitude : ");
        jLabel53.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel53.setMinimumSize(new java.awt.Dimension(38, 17));

        txtMaPhaLongitude.setName("Longitude Google Map");

        txtMaPhaLatitude.setName("Latitude Google Map");

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText("Latitude :");
        jLabel54.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel54.setMinimumSize(new java.awt.Dimension(38, 17));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setText("Type : ");
        jLabel55.setMaximumSize(new java.awt.Dimension(38, 17));
        jLabel55.setMinimumSize(new java.awt.Dimension(38, 17));

        comboBoxMaPhaType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jour", "Nuit" }));
        comboBoxMaPhaType.setSelectedIndex(-1);
        comboBoxMaPhaType.setName("Type de pharmacie");

        btMaPhaConfirmer.setText("Enregistrer les modification");
        btMaPhaConfirmer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMaPhaConfirmerActionPerformed(evt);
            }
        });

        btMaPhaAnnuler.setText("Annuler");
        btMaPhaAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMaPhaAnnulerActionPerformed(evt);
            }
        });

        btAjoutJourGarde.setText("Ajouter garde");
        btAjoutJourGarde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAjoutJourGardeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInfoMaPhaLayout = new javax.swing.GroupLayout(panelInfoMaPha);
        panelInfoMaPha.setLayout(panelInfoMaPhaLayout);
        panelInfoMaPhaLayout.setHorizontalGroup(
            panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoMaPhaLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtMaPhaLatitude)
                    .addComponent(txtMaPhaLongitude, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaPhaNom, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaPhaEmail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaPhaTel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaPhaFax, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxMaPhaGouv, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxMaPhaVille, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxMaPhaType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoMaPhaLayout.createSequentialGroup()
                        .addComponent(dateGarde, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAjoutJourGarde))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(panelInfoMaPhaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btMaPhaConfirmer, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btMaPhaAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoMaPhaLayout.setVerticalGroup(
            panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoMaPhaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoMaPhaLayout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateGarde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btAjoutJourGarde))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInfoMaPhaLayout.createSequentialGroup()
                        .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaPhaNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaPhaEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaPhaTel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaPhaFax, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxMaPhaGouv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxMaPhaVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoMaPhaLayout.createSequentialGroup()
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaPhaLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaPhaLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboBoxMaPhaType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(panelInfoMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMaPhaAnnuler)
                    .addComponent(btMaPhaConfirmer))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelInfoPhaLayout = new javax.swing.GroupLayout(panelInfoPha);
        panelInfoPha.setLayout(panelInfoPhaLayout);
        panelInfoPhaLayout.setHorizontalGroup(
            panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfoMaPha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelInfoPhaLayout.setVerticalGroup(
            panelInfoPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfoMaPha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabMyPhaInfo.addTab("Informations pharmacie", panelInfoPha);

        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Evenement", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        tableMaPhaMesEvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom Evenement", "Description Evenement", "Date Evenement", "Options"
            }
        ));
        tableMaPhaMesEvent.setToolTipText("");
        tableMaPhaMesEvent = makeTable(pidev.tunipharma.utils.GUIUtil.getModel(new Object[][]{}, new String [] {
            "ID", "Nom Evenement", "Description Evenement", "Date Evenement", "Options"
        }),4,TableButton.SUPPRIMER);
        tableMaPhaMesEvent.setName("tableMaPhaMesEvent");
        jScrollPane4.setViewportView(tableMaPhaMesEvent);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelMesEventsLayout = new javax.swing.GroupLayout(panelMesEvents);
        panelMesEvents.setLayout(panelMesEventsLayout);
        panelMesEventsLayout.setHorizontalGroup(
            panelMesEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMesEventsLayout.setVerticalGroup(
            panelMesEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Mes Evenements", panelMesEvents);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ajout Evenement", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        btMaPhaAjoutEventAnnuler.setText("Annuler");

        btMaPhaAjoutEventConfirmer.setText("Confirmer ajout ");
        btMaPhaAjoutEventConfirmer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMaPhaAjoutEventConfirmerActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Nom evenement : ");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Description evenement : ");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Date evenement : ");

        txtMaPhaAjoutEventNom.setName("txtnom"); // NOI18N
        txtMaPhaAjoutEventNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhaAjoutEventNomActionPerformed(evt);
            }
        });

        txtMaPhaAjoutEventDesc.setColumns(20);
        txtMaPhaAjoutEventDesc.setRows(5);
        txtMaPhaAjoutEventDesc.setName("txtadresse"); // NOI18N
        jScrollPane11.setViewportView(txtMaPhaAjoutEventDesc);

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setText("Heure evenement : ");

        spMaPhaAjoutEventHeure.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(8), null, Integer.valueOf(23), Integer.valueOf(1)));
        spMaPhaAjoutEventHeure.setMinimumSize(new java.awt.Dimension(50, 28));
        spMaPhaAjoutEventHeure.setPreferredSize(new java.awt.Dimension(50, 28));

        spMaPhaAjoutEventMin.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), null, Integer.valueOf(59), Integer.valueOf(1)));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(":");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(spMaPhaAjoutEventHeure, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spMaPhaAjoutEventMin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane11)
                            .addComponent(txtMaPhaAjoutEventNom)
                            .addComponent(dateChooserAjoutEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(btMaPhaAjoutEventConfirmer, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btMaPhaAjoutEventAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(357, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhaAjoutEventNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserAjoutEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spMaPhaAjoutEventMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(spMaPhaAjoutEventHeure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMaPhaAjoutEventConfirmer)
                    .addComponent(btMaPhaAjoutEventAnnuler))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout panelAjoutEventLayout = new javax.swing.GroupLayout(panelAjoutEvent);
        panelAjoutEvent.setLayout(panelAjoutEventLayout);
        panelAjoutEventLayout.setHorizontalGroup(
            panelAjoutEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelAjoutEventLayout.setVerticalGroup(
            panelAjoutEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Ajouter evenement", panelAjoutEvent);

        javax.swing.GroupLayout panelEventsLayout = new javax.swing.GroupLayout(panelEvents);
        panelEvents.setLayout(panelEventsLayout);
        panelEventsLayout.setHorizontalGroup(
            panelEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        panelEventsLayout.setVerticalGroup(
            panelEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabMyPhaInfo.addTab("Evenements", panelEvents);

        tableMaPhaMesSrv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Type", "Nom", "Descriptions", "Options"
            }
        ));
        tableMaPhaMesSrv.setToolTipText("");
        tableMaPhaMesSrv = makeTable(pidev.tunipharma.utils.GUIUtil.getModel(new Object[][]{}, new String [] {
            "ID", "Type", "Nom", "Descriptions", "Options"
        }),4,TableButton.SUPPRIMER); tableMaPhaMesSrv.setName("tableMaPhaMesSrv");
        jScrollPane5.setViewportView(tableMaPhaMesSrv);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneServices.addTab("Mes Services", jPanel16);

        panelAjoutService.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ajouter Service", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        txtMaPhaAjoutSrvNom.setName("txtnom"); // NOI18N
        txtMaPhaAjoutSrvNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhaAjoutSrvNomActionPerformed(evt);
            }
        });

        txtMaPhaAjoutSrvDesc.setColumns(20);
        txtMaPhaAjoutSrvDesc.setRows(5);
        txtMaPhaAjoutSrvDesc.setName("txtadresse"); // NOI18N
        jScrollPane14.setViewportView(txtMaPhaAjoutSrvDesc);

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setText("Description service : ");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setText("Nom service : ");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("Type service : ");

        buttonMaPhaAjoutSrvConfirmer.setText("Confirmer ajout service");
        buttonMaPhaAjoutSrvConfirmer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaPhaAjoutSrvConfirmerActionPerformed(evt);
            }
        });

        buttonMaPhaAjoutSrvAnnuler.setText("Annuler");

        javax.swing.GroupLayout panelAjoutServiceLayout = new javax.swing.GroupLayout(panelAjoutService);
        panelAjoutService.setLayout(panelAjoutServiceLayout);
        panelAjoutServiceLayout.setHorizontalGroup(
            panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAjoutServiceLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelAjoutServiceLayout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(panelAjoutServiceLayout.createSequentialGroup()
                        .addGroup(panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)))
                .addGroup(panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(comboBoxMaPhaAjoutSrvType, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaPhaAjoutSrvNom)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(436, 436, 436))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAjoutServiceLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonMaPhaAjoutSrvConfirmer)
                .addGap(18, 18, 18)
                .addComponent(buttonMaPhaAjoutSrvAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAjoutServiceLayout.setVerticalGroup(
            panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAjoutServiceLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhaAjoutSrvNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAjoutServiceLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(comboBoxMaPhaAjoutSrvType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addGroup(panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(panelAjoutServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMaPhaAjoutSrvConfirmer)
                    .addComponent(buttonMaPhaAjoutSrvAnnuler))
                .addContainerGap(221, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAjoutService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(panelAjoutService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabbedPaneServices.addTab("Ajouter Service", jPanel5);

        jTabMyPhaInfo.addTab("Services", tabbedPaneServices);

        javax.swing.GroupLayout panelMaPhaLayout = new javax.swing.GroupLayout(panelMaPha);
        panelMaPha.setLayout(panelMaPhaLayout);
        panelMaPhaLayout.setHorizontalGroup(
            panelMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMaPhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabMyPhaInfo)
                .addContainerGap())
        );
        panelMaPhaLayout.setVerticalGroup(
            panelMaPhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMaPhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabMyPhaInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPaneMonCompte.addTab("Ma pharmacie", panelMaPha);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPaneMonCompte)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPaneMonCompte)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMesInfosNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesInfosNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesInfosNomActionPerformed

    private void txtMaPhaAjoutEventNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhaAjoutEventNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhaAjoutEventNomActionPerformed

    private void txtMaPhaAjoutSrvNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhaAjoutSrvNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhaAjoutSrvNomActionPerformed

    private void btMesInfoConfirmerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMesInfoConfirmerActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btMesInfoConfirmerActionPerformed

    // <editor-fold defaultstate="collapsed" desc="Traitement d'Ajout Evenement">
    private void btMaPhaAjoutEventConfirmerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMaPhaAjoutEventConfirmerActionPerformed
        // TODO add your handling code here:
        if (GUIUtil.checkForm(panelAjoutEvent) && maPharmacie != null) {
            System.out.println("Mon Compte event : " + connUser);

            JDateChooser jc = dateChooserAjoutEvent;
            java.sql.Date date = new java.sql.Date(new Date(jc.getDate().getYear(), jc.getDate().getMonth(), jc.getDate().getDate(), (int) spMaPhaAjoutEventHeure.getValue(), (int) spMaPhaAjoutEventMin.getValue()).getTime());
            Evenement event = new Evenement(-1, maPharmacie.getId_pha(), date, txtMaPhaAjoutEventNom.getText(), txtMaPhaAjoutEventDesc.getText(), false);
            EvenementsDAO.getInstance().create(event);
            GUIUtil.showMsgBox(this, event.toString());
            Demande d = new Demande(-1, Demande.DEMANDE_EVENEMENT, date, connUser.getId_cpt(), event.getId_event());
            System.out.println("Demande : " + d);
            DemandesDAO.getInstance().create(d);

            GUIUtil.resetForm(panelAjoutEvent);
        }
    }//GEN-LAST:event_btMaPhaAjoutEventConfirmerActionPerformed
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Traitement d'Ajout Service">
    private void buttonMaPhaAjoutSrvConfirmerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaPhaAjoutSrvConfirmerActionPerformed
        // TODO add your handling code here:
        if (GUIUtil.checkForm(panelAjoutService) && maPharmacie != null) {
            TypeService typeSrv = ((TypeService) comboBoxMaPhaAjoutSrvType.getSelectedItem());
            Service srv = new Service(-1, maPharmacie.getId_pha(), typeSrv.getId_type_srv(), txtMaPhaAjoutSrvDesc.getText(), txtMaPhaAjoutSrvDesc.getText());
            ServicesDAO.getInstance().create(srv);
            GUIUtil.resetForm(panelAjoutEvent);
        }
    }//GEN-LAST:event_buttonMaPhaAjoutSrvConfirmerActionPerformed

    //</editor-fold>
    //Reinitialiser les information au de l'annulation des modification
    private void btMesInfoAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMesInfoAnnulerActionPerformed
        // TODO add your handling code here:
        fillMesInformation();
    }//GEN-LAST:event_btMesInfoAnnulerActionPerformed

    private void btAjoutJourGardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAjoutJourGardeActionPerformed
        //        GUIUtil.showMsgBox(evt.getSource().getClass().getName());
        try {
            Date d = dateGarde.getDate();
            GUIUtil.addRowCallTable(tableMaPhaJourDeGarde, d);
            ((JTextField) dateGarde.getDateEditor().getUiComponent()).setText("");
        } catch (Exception e) {
            System.out.println("JDateChooser Exception");
        }
    }//GEN-LAST:event_btAjoutJourGardeActionPerformed

    private void btMaPhaAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMaPhaAnnulerActionPerformed
        // TODO add your handling code here:
        if (GUIUtil.showConfBox("Voulez-vous annuler les modification ?")) {
            // Renitialisation de champs des jour de garde
            ((JTextField) dateGarde.getDateEditor().getUiComponent()).setText("");
            if (maPharmacie != null) {
                fillMaPhaInformation();
            }
        }
    }//GEN-LAST:event_btMaPhaAnnulerActionPerformed

    private void btMaPhaConfirmerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMaPhaConfirmerActionPerformed
        // TODO add your handling code here:
        if (btMaPhaConfirmer.getName().equals("Affichage")) {
            btMaPhaConfirmer.setText("Enregistrer les modification");
            btMaPhaConfirmer.setName("Modification");
            GUIUtil.enAllTextField(panelInfoMaPha);
        } else {
            if (GUIUtil.checkForm(panelInfoMaPha)) {
                //Ajout du compte dans la base de donnée
                System.out.println("[Log] Gouvernorat et ville choisi : " + ((Gouvernorat) comboBoxMaPhaGouv.getSelectedItem()).getNom_gouv() + " - " + ((Ville) comboBoxMaPhaVille.getSelectedItem()));
                Pharmacie pha = new Pharmacie((maPharmacie != null ? maPharmacie.getId_pha() : -1),
                        connUser.getId_cpt(),
                        txtMaPhaNom.getText(),
                        txtMaPhaAddresse.getText(),
                        Integer.parseInt(txtMaPhaTel.getText()),
                        Integer.parseInt(txtMaPhaFax.getText()),
                        txtMaPhaLatitude.getText(),
                        txtMaPhaLongitude.getText(),
                        txtMaPhaEmail.getText(),
                        comboBoxMaPhaType.getSelectedIndex() + 1,
                        ((Ville) comboBoxMaPhaVille.getSelectedItem()).getId_ville(),
                        ((Gouvernorat) comboBoxMaPhaGouv.getSelectedItem()).getId_gouv()
                );

                //Ajout dans la base de donnée de la nouvelle pharamacie
                if (maPharmacie != null) {
                    PharmaciesDAO.getInstance().update(pha);
                } else {
                    PharmaciesDAO.getInstance().create(pha);
                    maPharmacie = pha;
                    System.out.println("Pharmacie " + pha + " Créé !!");
                }
                //Ajout de jours de garde dans la base de données
                GUIUtil.addAllDateToDB(tableMaPhaJourDeGarde, pha.getId_pha());
                ((JTextField) dateGarde.getDateEditor().getUiComponent()).setText("");
                GUIUtil.disAllTextField(panelInfoMaPha);
                btMaPhaConfirmer.setText("Modifier les informations");
            }

        }
    }//GEN-LAST:event_btMaPhaConfirmerActionPerformed

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        // TODO add your handling code here:
        fillTableMesSrv();
        fillTableMesEvent();
    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void jTabMyPhaInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabMyPhaInfoMouseClicked
        // TODO add your handling code here:
        fillTableMesSrv();
        fillTableMesEvent();
    }//GEN-LAST:event_jTabMyPhaInfoMouseClicked

    private void tabPaneMonCompteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPaneMonCompteMouseClicked
        // TODO add your handling code here:
        fillTableMesMsg();
    }//GEN-LAST:event_tabPaneMonCompteMouseClicked

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
            java.util.logging.Logger.getLogger(InterfaceUtilisateur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceUtilisateur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceUtilisateur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceUtilisateur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceUtilisateur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAjoutJourGarde;
    private javax.swing.JButton btMaPhaAjoutEventAnnuler;
    private javax.swing.JButton btMaPhaAjoutEventConfirmer;
    private javax.swing.JButton btMaPhaAnnuler;
    private javax.swing.JButton btMaPhaConfirmer;
    private javax.swing.JButton btMesInfoAnnuler;
    private javax.swing.JButton btMesInfoConfirmer;
    private javax.swing.JButton buttonMaPhaAjoutSrvAnnuler;
    private javax.swing.JButton buttonMaPhaAjoutSrvConfirmer;
    private javax.swing.JComboBox comboBoxMaPhaAjoutSrvType;
    private javax.swing.JComboBox comboBoxMaPhaGouv;
    private javax.swing.JComboBox comboBoxMaPhaType;
    private javax.swing.JComboBox comboBoxMaPhaVille;
    private com.toedter.calendar.JDateChooser dateChooserAjoutEvent;
    private com.toedter.calendar.JDateChooser dateGarde;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel787;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabMyPhaInfo;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JPanel panelAjoutEvent;
    private javax.swing.JPanel panelAjoutService;
    private javax.swing.JPanel panelEvents;
    private javax.swing.JPanel panelInfoMaPha;
    private javax.swing.JPanel panelInfoPerso;
    private javax.swing.JPanel panelInfoPha;
    private javax.swing.JPanel panelMaPha;
    private javax.swing.JPanel panelMesEvents;
    private javax.swing.JPanel panelMsg;
    private javax.swing.JSpinner spMaPhaAjoutEventHeure;
    private javax.swing.JSpinner spMaPhaAjoutEventMin;
    private javax.swing.JTabbedPane tabPaneMonCompte;
    private javax.swing.JTabbedPane tabbedPaneServices;
    private javax.swing.JTable tableMaPhaJourDeGarde;
    private javax.swing.JTable tableMaPhaMesEvent;
    private javax.swing.JTable tableMaPhaMesSrv;
    private javax.swing.JTable tableMesMsg;
    private javax.swing.JTextArea txtMaPhaAddresse;
    private javax.swing.JTextArea txtMaPhaAjoutEventDesc;
    private javax.swing.JTextField txtMaPhaAjoutEventNom;
    private javax.swing.JTextArea txtMaPhaAjoutSrvDesc;
    private javax.swing.JTextField txtMaPhaAjoutSrvNom;
    private javax.swing.JTextField txtMaPhaEmail;
    private javax.swing.JTextField txtMaPhaFax;
    private javax.swing.JTextField txtMaPhaLatitude;
    private javax.swing.JTextField txtMaPhaLongitude;
    private javax.swing.JTextField txtMaPhaNom;
    private javax.swing.JTextField txtMaPhaTel;
    private javax.swing.JTextArea txtMeInfosAddresse;
    private javax.swing.JTextField txtMeInfosNvMDP;
    private javax.swing.JTextField txtMeInfosNvRMDP;
    private javax.swing.JTextField txtMesInfosEmail;
    private javax.swing.JTextField txtMesInfosNom;
    private javax.swing.JTextField txtMesInfosPrenom;
    private javax.swing.JTextField txtMesInfosTel;
    // End of variables declaration//GEN-END:variables
}
