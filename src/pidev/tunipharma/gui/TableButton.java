package pidev.tunipharma.gui;
////-*- mode:java; encoding:utf-8 -*-
//// vim:set fileencoding=utf-8:
////http://terai.xrea.jp/Swing/MultipleButtonsInTableCell.html
////

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import pidev.tunipharma.classes.Compte;
import pidev.tunipharma.classes.Demande;
import pidev.tunipharma.classes.Evenement;
import pidev.tunipharma.dao.ComptesDAO;
import pidev.tunipharma.dao.DemandesDAO;
import pidev.tunipharma.dao.EvenementsDAO;
import pidev.tunipharma.dao.PharmaciesDAO;
import pidev.tunipharma.utils.GUIUtil;

public class TableButton extends JPanel {

    public final static int AFFFICHER_SUPPRIMER = 1;
    public final static int AFFFICHER_ACCEPTER_REFUSER = 2;
    public final static int AFFFICHER_REPONDRE_SUPPRIMER = 3;

    public TableButton() {
        super(new BorderLayout());
        String empty = "aa";
        String[] columnNames = {"String", "Button"};
        Object[][] data = {
            {"AAA", empty}, {"CCC", empty}, {"BBB", empty}, {"ZZZ", empty}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        add(new JScrollPane(makeTable(model, 1, 1)));
        setBorder(BorderFactory.createTitledBorder("Multiple Buttons in a Table Cell"));
        setPreferredSize(new Dimension(320, 240));
    }

    public static void fixWidth(final JTable table, final int columnIndex, final int width) {
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        column.setMinWidth(width);
        column.setMaxWidth(width);
        column.setPreferredWidth(width);
    }

    public static JTable makeTable(DefaultTableModel tableModel, int btColumn, int type) {

        final JTable table = new JTable(tableModel);
        table.setRowHeight(50);
        table.setAutoCreateRowSorter(true);
        TableColumn column = table.getColumnModel().getColumn(btColumn);
        column.setCellRenderer(new ButtonsRenderer(type));
        column.setCellEditor(new ButtonsEditor(table, type));
        return table;
    }

    /**
     * @param type 1 Pour modifier et supprimer , 2 pour afficher, accepter et
     * refuser
     * @return
     */
    public static ButtonsRenderer getBtRenderer(int type) {
        return new ButtonsRenderer(type);
    }

    /**
     * @param type 1 Pour modifier et supprimer , 2 pour afficher, accepter et
     * refuser
     * @param t table pour creer les bouttons
     * @return
     */
    public static ButtonsEditor getBtEditor(JTable t, int type) {
        return new ButtonsEditor(t, type);
    }
}

class ButtonsPanel extends JPanel {

    public List<JButton> buttons;
    public int type;

    public ButtonsPanel(int type) {
        super();
        this.type = type;
        setOpaque(true);
        buttons = Arrays.asList(new JButton(new ImageIcon("img/edit.png")));
        switch (type) {
            case TableButton.AFFFICHER_SUPPRIMER:
                buttons = Arrays.asList(new JButton(new ImageIcon("img/edit.png")), new JButton(new ImageIcon("img/delete.png")));
                break;
            case TableButton.AFFFICHER_ACCEPTER_REFUSER:
                buttons = Arrays.asList(new JButton(new ImageIcon("img/edit.png")), new JButton(new ImageIcon("img/accept.png")), new JButton(new ImageIcon("img/deny.png")));
                break;
            case TableButton.AFFFICHER_REPONDRE_SUPPRIMER:
                buttons = Arrays.asList(new JButton(new ImageIcon("img/edit.png")), new JButton(new ImageIcon("img/accept.png")), new JButton(new ImageIcon("img/deny.png")));
                break;
        }

        for (JButton b : buttons) {
            b.setFocusable(false);
            b.setRolloverEnabled(false);
            add(b);
        }

    }
}

class ButtonsRenderer extends ButtonsPanel implements TableCellRenderer {

    public ButtonsRenderer(int type) {
        super(type);
        setName("Table.cellRenderer");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        return this;
    }
}

class ButtonsEditor extends ButtonsPanel implements TableCellEditor {

    transient protected ChangeEvent changeEvent = null;

    /**
     * @param table Le JTable à utiliser
     * @param row Le ligne selectionné dans le tableau
     * @param disp true pour afficher les fenêtre pour ne pas afficher
     * @return String[3] case 0 : Message a afficher case 1 : l'ID de l'objet
     * (Compte,Pharmacie) case 2 : 1 pour un compte, 2 pour pharmacie et 3 pour
     * demande
     */
    public String[] getTableInfosMod(JTable table, int row, boolean disp) {

        String[] res = new String[3];
        res[0] = "";
        Object id = table.getModel().getValueAt(row, 0);
        //GUIUtils.showMsgBox("Table " + table.getName());
        switch (table.getName()) {
            case "tableModCpt":
                if (disp) {
                    new InterfaceInfoCompte((int) id).setVisible(true);
                }
                res[0] += "supprimer le compte de " + table.getModel().getValueAt(row, 1) + " " + table.getModel().getValueAt(row, 2);
                res[2] = "" + 1;
                break;
            case "tableNouvInscriCpt":
                res[0] += "le compte de " + table.getModel().getValueAt(row, 1) + " " + table.getModel().getValueAt(row, 2);
                res[2] = "" + 1;
                break;
            case "tableDemandesPha":
                res[0] += "l'evenement de la pharmacie " + table.getModel().getValueAt(row, 1) + " le " + table.getModel().getValueAt(row, 2);
                res[2] = "" + 3;
                break;
            case "tableModPha":
                if (disp) {
                    new InterfaceInfoPharmacie((int) id).setVisible(true);
                }
                res[0] += "supprimer la pharmacie " + table.getModel().getValueAt(row, 1);
                res[2] = "" + 2;
                break;
            case "tableMesMsg":
                if (disp) {
                    new InterfaceInfoPharmacie((int) id).setVisible(true);
                }
                res[0] += "supprimer ce message";
                res[2] = "" + 2;
                break;
        }
        res[1] = String.valueOf(id);
        return res;
    }

    public ButtonsEditor(final JTable table, int type) {
        super(type);
        //---->
        //DEBUG: view button click -> control key down + edit button(same cell) press -> remain selection color
        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonModel m = ((JButton) e.getSource()).getModel();
                if (m.isPressed() && table.isRowSelected(table.getEditingRow()) && e.isControlDown()) {
                    setBackground(table.getBackground());
                }
            }
        };
        buttons.get(0).addMouseListener(ml);
        buttons.get(1).addMouseListener(ml);

        //Au cas de la modification 
        final Object[] daoInstances = new Object[]{ComptesDAO.getInstance(), PharmaciesDAO.getInstance(), EvenementsDAO.getInstance()};
        if (this.type == TableButton.AFFFICHER_SUPPRIMER) {
            buttons.get(0).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //JOptionPane.showMessageDialog(table, "Afficher/Modifier");
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    Object id = table.getModel().getValueAt(row, 0);
                    getTableInfosMod(table, row, true);
                    fireEditingStopped();
                }
            });
            buttons.get(1).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //JOptionPane.showMessageDialog(table, "Suppri");
                    //Object o = table.getModel().getValueAt(table.getSelectedRow(), 0);
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    //int n = getRowToObject(table.getName());
                    fireEditingStopped();

                    String[] res = getTableInfosMod(table, row, false);

                    if (GUIUtil.showConfBox("Voullez-vous " + res[0] + " ?")) {
                        if (daoInstances[Integer.parseInt(res[2]) - 1] instanceof ComptesDAO) {
                            ((ComptesDAO) daoInstances[Integer.parseInt(res[2]) - 1]).delete(Integer.parseInt(res[1]));
                            //GUIUtils.showMsgBox("CompteDAO delete : "+Integer.parseInt(res[1]));
                        } else if (daoInstances[Integer.parseInt(res[2]) - 1] instanceof PharmaciesDAO) {
                            ((PharmaciesDAO) daoInstances[Integer.parseInt(res[2]) - 1]).delete(Integer.parseInt(res[1]));
                            //GUIUtils.showMsgBox("PharmaciesDAO dalete : "+Integer.parseInt(res[1]) );
                        } else {
                            GUIUtil.showMsgBox("Ni Pharmacie, Ni Compte !! ");
                        }

                    }
                }
            });

        } else if (this.type == TableButton.AFFFICHER_ACCEPTER_REFUSER) {
            //Au cas de la vaidation de demande 
            buttons.get(2).addMouseListener(ml);

            //Boutton de Refu !!
            buttons.get(2).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //GUIUtils.showMsgBox("Refu !!");
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    Object id = table.getModel().getValueAt(row, 0);
                    getTableInfosMod(table, row, true);
                    String[] res = getTableInfosMod(table, row, false);
                    if (GUIUtil.showConfBox(table, "Voullez-vous refuser " + res[0] + " ?")) {
                        Demande d = DemandesDAO.getInstance().readById(Integer.parseInt(id.toString()));
                        DemandesDAO.getInstance().delete(d);
                        if (daoInstances[Integer.parseInt(res[2]) - 1] instanceof ComptesDAO) {
                            ((ComptesDAO) daoInstances[Integer.parseInt(res[2]) - 1]).delete(d.getId_concerne_dmd());
                            //GUIUtils.showMsgBox("CompteDAO delete : "+Integer.parseInt(res[1]));
                        } else if (daoInstances[Integer.parseInt(res[2]) - 1] instanceof EvenementsDAO) {
                            ((EvenementsDAO) daoInstances[Integer.parseInt(res[2]) - 1]).delete(d.getId_concerne_dmd());
                            //GUIUtils.showMsgBox("PharmaciesDAO dalete : "+Integer.parseInt(res[1]) );
                        } else {
                            GUIUtil.showMsgBox(table, "Ni Pharmacie, Ni Compte !! ");
                        }

                    }
                    fireEditingStopped();
                }
            });

            //Boutton de l'Affichage !!
            buttons.get(0).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //GUIUtils.showMsgBox("Affichage !!");
                    //JOptionPane.showMessageDialog(table, "Afficher/Modifier");
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    Object id = table.getModel().getValueAt(row, 0);
                    String[] res = getTableInfosMod(table, row, false);
                    String msg = "";
                    //reccuperation des information de la demmande
                    Demande d = DemandesDAO.getInstance().readById(Integer.parseInt(id.toString()));
                    if (daoInstances[Integer.parseInt(res[2]) - 1] instanceof ComptesDAO) {
                        Compte cpt = ((ComptesDAO) daoInstances[Integer.parseInt(res[2]) - 1]).readById(d.getId_concerne_dmd());
                        msg += "Nom et Prénom : " + cpt.getNom_cpt() + " " + cpt.getPrenom_cpt() + "\n";
                        msg += "Email : " + cpt.getEmail_cpt() + "\n";
                        msg += "Num Tél : " + cpt.getTel_cpt() + "\n";
                        msg += "Date de l'inscription : " + d.getDate_dmd().toString();
                        GUIUtil.showMsgBox(table, msg);
                        //GUIUtils.showMsgBox("CompteDAO delete : "+Integer.parseInt(res[1]));
                    } else if (daoInstances[Integer.parseInt(res[2]) - 1] instanceof PharmaciesDAO) {
                        ((EvenementsDAO) daoInstances[Integer.parseInt(res[2]) - 1]).changeEtat(d.getId_concerne_dmd(), true);
                        //GUIUtils.showMsgBox("PharmaciesDAO dalete : "+Integer.parseInt(res[1]) );
                        Evenement event = ((EvenementsDAO) daoInstances[Integer.parseInt(res[2]) - 1]).readById(d.getId_concerne_dmd());
                        msg += "Nom de l'evenement : " + event.getNom_event() + "\n";
                        msg += "Description de l'evenement : " + event.getDesc_event() + "\n";
                        msg += "Date de l'evenement : " + event.getDate_event().toString() + "\n";
                        msg += "Date de la demande : " + d.getDate_dmd().toString();
                        GUIUtil.showMsgBox(table, msg);
                    } else {
                        GUIUtil.showMsgBox(table, "Ni Pharmacie, Ni Compte !! ");
                    }

                    fireEditingStopped();
                }
            });

            //Boutton de l'Acceptation !!
            buttons.get(1).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //GUIUtils.showMsgBox("Acceptation !!");
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    Object id = table.getModel().getValueAt(row, 0);
                    getTableInfosMod(table, row, true);
                    String[] res = getTableInfosMod(table, row, false);
                    if (GUIUtil.showConfBox(table, "Voullez-vous accepter " + res[0] + " ?")) {
                        Demande d = DemandesDAO.getInstance().readById(Integer.parseInt(id.toString()));
                        DemandesDAO.getInstance().delete(d);
                        if (daoInstances[Integer.parseInt(res[2]) - 1] instanceof ComptesDAO) {
                            ((ComptesDAO) daoInstances[Integer.parseInt(res[2]) - 1]).changeEtat(d.getId_concerne_dmd(), true);
                            //GUIUtils.showMsgBox("CompteDAO delete : "+Integer.parseInt(res[1]));
                        } else if (daoInstances[Integer.parseInt(res[2]) - 1] instanceof PharmaciesDAO) {
                            ((EvenementsDAO) daoInstances[Integer.parseInt(res[2]) - 1]).changeEtat(d.getId_concerne_dmd(), true);
                            //GUIUtils.showMsgBox("PharmaciesDAO dalete : "+Integer.parseInt(res[1]) );
                        } else {
                            GUIUtil.showMsgBox("Ni Pharmacie, Ni Compte !! ");
                        }

                    }
                    fireEditingStopped();
                }
            });
        } else {

        }
        //<----

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.setBackground(table.getSelectionBackground());
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    //Copid from AbstractCellEditor
    //protected EventListenerList listenerList = new EventListenerList();
    //transient protected ChangeEvent changeEvent = null;
    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    @Override
    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
    }

    public CellEditorListener[] getCellEditorListeners() {
        return listenerList.getListeners(CellEditorListener.class);
    }

    protected void fireEditingStopped() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listeners[i + 1]).editingStopped(changeEvent);
            }
        }
    }

    protected void fireEditingCanceled() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listeners[i + 1]).editingCanceled(changeEvent);
            }
        }
    }
}
