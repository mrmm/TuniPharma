/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.utils;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import pidev.tunipharma.classes.Compte;
import pidev.tunipharma.classes.Gouvernorat;
import pidev.tunipharma.classes.Ville;
import pidev.tunipharma.dao.GouvernoratsDAO;
import pidev.tunipharma.dao.VillesDAO;

/**
 *
 * @author elron
 */
public class GUIUtils {

    // <editor-fold defaultstate="collapsed" desc="Traitement - Gouvernorat & Villes ">

    // Remplissage des Villes dans ComboBox, selon ID Gouvernorat selectionné
    public static void fillVillesCB(JComboBox cb, int id, boolean all) {
        try {
            cb.removeAllItems();
            if (all) {
                cb.addItem(new Ville(-1, -1, "Tous"));
            }
            List<Ville> lv = (id > 0 ? VillesDAO.getInstance().readAllByIdGouv(id) : VillesDAO.getInstance().readAll());
            for (Ville v : lv) {
                cb.addItem(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Remplissage des Gouvernorats dans ComboBox
    public static void fillGouvsCB(JComboBox cb, boolean all) {
        try {
            if (all) {
                cb.addItem(new Gouvernorat(-1, "Tous"));
            }

            for (Gouvernorat g : GouvernoratsDAO.getInstance().readAll()) {
                cb.addItem(g);
            }
            //cb.setSelectedIndex(1);
        } catch (SQLException ex) {
            Logger.getLogger(GUIUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Ajout Listener sur ComboBox Gouvernorat pour Changer liste des Villes
    public static void villeGouvListener(JComboBox cbGouv, final JComboBox cbVille, final boolean all) {
        cbGouv.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    GUIUtils.fillVillesCB(cbVille, ((Gouvernorat) e.getItem()).getId_gouv(), all);
                }
            }
        });
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Traitement - Verification des champs ">
    // Verification des champs dans un Container (Panel, TabbedPane, ...) 
    // S'ils sont bien remplis
    public static boolean checkForm(Container c) {
        Component[] comp = c.getComponents();
        if (comp.length == 1 && comp[0] instanceof Container) {
            checkForm((Container) comp[0]);
        }
        boolean test = true;
        int i = 0;
        while (test && i < comp.length) {
            System.out.println(comp[i].getClass().getName());
            if (comp[i] instanceof JTextField) {
                JTextField tf = (JTextField) comp[i];
                if (tf.getText().isEmpty()) {
                    test = false;
                    //Verification de format des email 
                    String m = "";
                    if (tf.getName().toUpperCase().contains("EMAIL") && !isEmail(tf)) {
                        m += "  n'est pas Email invalide";
                        test = false;
                    }
                    if (tf.getName().toUpperCase().contains("NUM") && !isNumeric(tf.getText()) && tf.getText().length() < 8) {
                        m += " n'est pas numéro valide ";
                        test = false;
                    }
                    showMsgBox("Le champs " + tf.getName() + " est invalide " + m + " !!");
                }
            } else if (comp[i] instanceof JComboBox) {
                JComboBox cb = (JComboBox) comp[i];
                if (cb.getSelectedIndex() == -1) {
                    test = false;
                    showMsgBox("Le " + cb.getName() + " n'est pas encore choisi !!");
                }
            } else if (comp[i] instanceof JScrollPane) {
                JScrollPane js = (JScrollPane) comp[i];
                Component[] jsComp = js.getViewport().getComponents();
                JTextArea ta = (JTextArea) jsComp[0];
                if (ta.getText().isEmpty()) {
                    test = false;
                    showMsgBox("Le champs " + ta.getName() + " est vide !!");
                }
            } else if (comp[i] instanceof JTextArea) {
                JTextArea ta = (JTextArea) comp[i];
                if (ta.getText().isEmpty()) {
                    test = false;
                    showMsgBox("Le champs " + ta.getName() + " est vide !!");
                }
            } else if (comp[i] instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) comp[i];
                if (!cb.isSelected()) {
                    test = false;
                    showMsgBox("Le champs " + cb.getName() + " n'est pas coché !!");
                }
            }
            i++;
        }
        return test;
    }

    public static void resetForm(Container c) {
        Component[] comp = c.getComponents();
        if (comp.length == 1 && comp[0] instanceof Container) {
            resetForm((Container) comp[0]);
        }
        int i = 0;
        while (i < comp.length) {
            if (comp[i] instanceof JTextField) {
                // Reset des champs texte - Text Field
                JTextField tf = (JTextField) comp[i];
                tf.setText("");
                c.add(tf);
            } else if (comp[i] instanceof JComboBox) {
                // Reset des Listes deroullantes - Combo Box
                JComboBox cb = (JComboBox) comp[i];
                cb.setSelectedIndex(-1);
                c.add(cb);
            } else if (comp[i] instanceof JScrollPane) {
                // Reset des zone de texte dans ScrollPane - Text Area
                JScrollPane js = (JScrollPane) comp[i];
                Component[] jsComp = js.getViewport().getComponents();
                JTextArea ta = (JTextArea) jsComp[0];
                ta.setText("");
                JViewport jvp = new JViewport();
                jvp.setView(ta);
                js.setViewport(jvp);
                c.add(js);
            } else if (comp[i] instanceof JCheckBox) {
                // Reset des case a cocher - Check Box
                JCheckBox cb = (JCheckBox) comp[i];
                cb.setSelected(false);
                c.add(cb);
            } else if (comp[i] instanceof JTextArea) {
                // Reset des Zone de texte - Text Area
                JTextArea cb = (JTextArea) comp[i];
                cb.setText("");
                c.add(cb);
            } else if (comp[i] instanceof JButton) {
                // Reset des Zone de texte - Text Area
                JButton cb = (JButton) comp[i];
                cb.setEnabled(true);
                c.add(cb);
            } else {
                c.add(comp[i]);
            }
            i++;
        }
    }

    public static void setErrOkField(JComponent c, boolean b) {
        if (!b) {
            c.setForeground(new Color(139, 0, 0));
            c.setBackground(new Color(255, 148, 148));

        } else {
            c.setForeground(new Color(0, 100, 0));
            c.setBackground(new Color(144, 238, 144));
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Ajout de Listener - onChange">
    public static void onChangeEmpty(final JTextComponent c, final JButton b) {
        c.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (c.getText().isEmpty()) {
                    setErrOkField(c, false);
                    b.setEnabled(false);
                } else {
                    setErrOkField(c, true);
                    b.setEnabled(true);
                }
            }
        });

    }

    public static void onChangeEmail(final JTextComponent c, final JButton b) {
        c.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (c.getText().isEmpty() || !isEmail((JTextField) c)) {
                    setErrOkField(c, false);
                    b.setEnabled(false);
                } else {
                    setErrOkField(c, true);
                    b.setEnabled(true);
                }
            }
        });
    }

    public static void onChangeNumber(final JTextComponent c, final JButton b) {
        c.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (c.getText().isEmpty() || !isNumeric(c.getText().toString()) || c.getText().length() < 8) {
                    setErrOkField(c, false);
                    b.setEnabled(false);
                } else {
                    setErrOkField(c, true);
                    b.setEnabled(true);
                }
            }
        });
    }

    public static void onChangeMDP(final JTextComponent p1, final JTextComponent p2, final JButton b) {
        CaretListener cl = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (p1.getText().isEmpty() || !p1.getText().equals(p2.getText())) {
                    setErrOkField(p1, false);
                    setErrOkField(p2, false);
                    b.setEnabled(false);
                } else {
                    setErrOkField(p1, true);
                    setErrOkField(p2, true);
                    b.setEnabled(true);
                }
            }
        };
        p1.addCaretListener(cl);
        p2.addCaretListener(cl);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Message Box et Alert">
    // Methode pour afficher les alerts
    public static void showMsgBox(String txt) {
        JOptionPane.showMessageDialog(null, txt);
    }

    // Methode pour afficher message de confirmation
    public static boolean showConfBox(String txt) {
        boolean test = false;
        int confRes = JOptionPane.showConfirmDialog(null, txt);
        if (confRes == JOptionPane.YES_NO_OPTION) {
            test = true;
        }
        return test;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Verification format des champs">
    public static boolean isEmail(JTextField tf) {
        boolean isValidEmail = false;
        String validExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern compare = Pattern.compile(validExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compare.matcher(tf.getText().toString());
        if (matcher.matches()) {
            isValidEmail = true;
        }
        return isValidEmail;
    }

    public static boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Manipulation des JTables">
    public static void rempTableCompte(JTable t, List<Compte> l) {
        remAllRows(t);
        System.out.println("Taille de liste " + l.size());
        Iterator<Compte> it = l.iterator();
        DefaultTableModel model = (DefaultTableModel) t.getModel();
        Compte c;
        while (it.hasNext()) {
            c = it.next();
            model.addRow(new Object[]{c.getNom_cpt(), c.getPrenom_cpt(), c.getTypeCptNom(), ""});
        }
    }

    public static void rempTableNouvInscri(JTable t, List<Compte> l) {
        remAllRows(t);
        System.out.println("Taille de liste " + l.size());
        Iterator<Compte> it = l.iterator();
        DefaultTableModel model = (DefaultTableModel) t.getModel();
        Compte c;
        while (it.hasNext()) {
            c = it.next();
            model.addRow(new Object[]{c.getNom_cpt(), c.getPrenom_cpt(), c.getTypeCptNom(), ""});
        }
    }

    public static void remAllRows(JTable t) {
        DefaultTableModel dm = (DefaultTableModel) t.getModel();
        int colCount = dm.getColumnCount();
        String[] tab = new String[colCount];
        for (int i = 0; i < colCount; i++) {
            tab[i] = dm.getColumnName(i);
        }
        dm = new DefaultTableModel(tab, 0);
        t.setModel(dm);
    }

    public static void addRow(JTable t, Object[] o) {
        DefaultTableModel dm = (DefaultTableModel) t.getModel();
        dm.addRow(o);
        t.setModel(dm);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="JTable Manipulation">
// </editor-fold>
    
    public static void addCompPanel(JPanel p, Component[] c) {
        if (c[0] instanceof JRootPane) {
            addCompPanel(p, ((JRootPane) c[0]).getComponents());
        }
        for (int i = 0; i < c.length; i++) {
            p.add(c[i]);
        }
    }

    public static void getComponentTree(Component[] c, String m, String d, int j, JDateChooser jd) {
        boolean t = true;
        int i = 0;
        j++;
//        System.out.println("getComponentTree Called with comonent : "+c.length);
        while (i < c.length) {
            if (c[i] instanceof Container) {
                getComponentTree(((Container) c[i]).getComponents(), m, d, j, jd);
                System.out.println("getComponentTree Called with component " + j + " : " + c.getClass().getName());
            } else {
                if (t) {
                    d += "-";
                    t = false;
                }
                m += d + c[i].getClass().getName() + "\n" + m;
            }
            if (c[i] instanceof JDateChooser) {
                System.out.println("JDateChooser Found in component[" + j + "][" + i + "]");
                jd = (JDateChooser) c[i];
                
            }
            if (c[i] instanceof JButton) {
                System.out.println("Button '" + ((JButton) c[i]).getText() + "' found in component[" + j + "][" + i + "]");
            }
            i++;
        }

    }
}
