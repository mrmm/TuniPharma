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
import pidev.tunipharma.utils.GUIUtils;

public class TableButton extends JPanel {

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
        add(new JScrollPane(makeTable(model, 1)));
        setBorder(BorderFactory.createTitledBorder("Multiple Buttons in a Table Cell"));
        setPreferredSize(new Dimension(320, 240));
    }

    public static void fixWidth(final JTable table, final int columnIndex, final int width) {
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        column.setMinWidth(width);
        column.setMaxWidth(width);
        column.setPreferredWidth(width);
    }

    public static JTable makeTable(DefaultTableModel tableModel, int btColumn) {

        final JTable table = new JTable(tableModel);
        table.setRowHeight(50);
        table.setAutoCreateRowSorter(true);
        TableColumn column = table.getColumnModel().getColumn(btColumn);
        column.setCellRenderer(new ButtonsRenderer());
        column.setCellEditor(new ButtonsEditor(table));
        return table;
    }

    public static ButtonsRenderer getBtRenderer() {
        return new ButtonsRenderer();
    }

    public static ButtonsEditor getBtEditor(JTable t) {
        return new ButtonsEditor(t);
    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//
//    public static void createAndShowGUI() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            ex.printStackTrace();
//        }
//        JFrame frame = new JFrame("MultipleButtonsInTableCell");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.getContentPane().add(new TableButton());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}

class ButtonsPanel extends JPanel {

    public final List<JButton> buttons = Arrays.asList(new JButton(new ImageIcon("img/edit.png")), new JButton(new ImageIcon("img/delete.png")));

    public ButtonsPanel() {
        super();
        setOpaque(true);
        for (JButton b : buttons) {
            b.setFocusable(false);
            b.setRolloverEnabled(false);
            add(b);
        }
    }
}

class ButtonsRenderer extends ButtonsPanel implements TableCellRenderer {

    public ButtonsRenderer() {
        super();
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

    public String[] getTableInfos(JTable table, int row, boolean disp) {

        String[] res = new String[2];
        res[0] = "";
        Object id = table.getModel().getValueAt(row, 0);
        GUIUtils.showMsgBox("Table " + table.getName());
        switch (table.getName()) {
            case "tableModCpt":
                if (disp) {
                    new InterfaceInfoCompte((int) id).setVisible(true);
                }
                res[0] += "le compte de " + table.getModel().getValueAt(row, 1) + " " + table.getModel().getValueAt(row, 2);
                break;
            case "tableNouvInscriCpt":
                if (disp) {
                    new InterfaceInfoCompte((int) id).setVisible(true);
                }
                res[0] += "le compte de " + table.getModel().getValueAt(row, 1) + " " + table.getModel().getValueAt(row, 2);
                break;
            case "tableModPha":
                if (disp) {
                    new InterfaceInfoPharmacie((int) id).setVisible(true);
                }
                res[0] += "la pharmacie " + table.getModel().getValueAt(row, 1);
                break;
        }
        res[1] = String.valueOf(id);
        return res;
    }

    public ButtonsEditor(final JTable table) {
        super();
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
        //<----

        buttons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //JOptionPane.showMessageDialog(table, "Afficher/Modifier");
                int row = table.convertRowIndexToModel(table.getEditingRow());
                Object id = table.getModel().getValueAt(row, 0);
                getTableInfos(table, row, true);
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
                if (GUIUtils.showConfBox("Voullez-vous supprimer " + getTableInfos(table, row, false)[0] + " ?")) {

                }
            }
        });

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
