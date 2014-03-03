/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.JTextComponent;
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

        while (comp.length == 1 && comp[0] instanceof Container) {
            comp = ((Container) comp[0]).getComponents();
        }
        boolean test = true;
        int i = 0;
        while (test && i < comp.length) {
            comp[i].getName();
            if (comp[i] instanceof JTextField) {
                JTextField tf = (JTextField) comp[i];
                if (tf.getText().isEmpty()) {
                    test = false;
                    showMsgBox("Le champs " + tf.getName() + " est vide !!");
                }
            }
            if (comp[i] instanceof JComboBox) {
                JComboBox cb = (JComboBox) comp[i];
                if (cb.getSelectedItem() == null) {
                    test = false;
                    showMsgBox("Le " + cb.getName() + " n'est pas encore choisi !!");
                }
            }
            if (comp[i] instanceof JScrollPane) {
                Component[] jsComp = ((JScrollPane) comp[i]).getComponents();
                JTextArea ta = (jsComp[0] instanceof JTextArea ? (JTextArea) jsComp[0] : (JTextArea) comp[i]);
                if (ta.getText().isEmpty()) {
                    test = false;
                    showMsgBox("Le champs " + ta.getName() + " est vide !!");
                }
            }
            if (comp[i] instanceof JCheckBox) {
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

    public static void resetInput(Container c) {

        Component[] comp = c.getComponents();
        if (comp.length == 1 && comp[0] instanceof Container) {
            resetInput((Container) comp[0]);
        } else {
            c.removeAll();
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
                js.setViewportView(ta);
                js.removeAll();
                JViewport jvp = new JViewport();
                new JViewport().setView(ta);
                js.setViewport(jvp);
                c.add(js);
            } else if (comp[i] instanceof JCheckBox) {
                // Reset des case a cocher - Check Box
                JCheckBox cb = (JCheckBox) comp[i];
                cb.setSelected(false);
                comp[i] = cb;
                c.add(cb);
            } else if (comp[i] instanceof JTextArea) {
                // Reset des Zone de texte - Text Area
                JTextArea cb = (JTextArea) comp[i];
                cb.setText("");
                comp[i] = cb;
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

    public static void onChange(final JTextComponent c) {
        c.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (c.getText().isEmpty()) {
                    setErrOkField(c, false);
                } else {
                    setErrOkField(c, true);
                }
            }
        });

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
    public static boolean checkEmail(JTextField tf) {
        boolean isValidEmail = false;
        String validExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern compare = Pattern.compile(validExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compare.matcher(tf.getText().toString());
        if (matcher.matches()) {
            isValidEmail = true;
        }
        return isValidEmail;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Traitement - Gouvernorat & Villes ">
    // </editor-fold>
}
