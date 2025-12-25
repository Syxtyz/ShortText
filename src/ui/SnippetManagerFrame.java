package ui;

import model.Snippet;
import repository.SnippetRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SnippetManagerFrame extends JFrame {

    private final SnippetRepository repo = new SnippetRepository();
    private final DefaultTableModel tableModel;

    private final JTable table;
    private final JTextField triggerField = new JTextField();
    private final JTextArea contentArea = new JTextArea(5, 20);
    private final JTextField descField = new JTextField();

    public SnippetManagerFrame() {
        setTitle("ShortText – Snippets");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{"ID", "Trigger", "Content", "Description"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.removeColumn(table.getColumnModel().getColumn(0));

        JScrollPane tableScroll = new JScrollPane(table);

        JPanel form = buildForm();

        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                tableScroll,
                form
        );
        split.setDividerLocation(450);

        add(split);
        refreshTable();
        hookSelection();
    }

    private JPanel buildForm() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel fields = new JPanel(new GridLayout(0, 1, 5, 5));
        fields.add(new JLabel("Trigger"));
        fields.add(triggerField);
        fields.add(new JLabel("Content"));
        fields.add(new JScrollPane(contentArea));
        fields.add(new JLabel("Description"));
        fields.add(descField);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");

        add.addActionListener(e -> {
            repo.insert(triggerField.getText(),
                        contentArea.getText(),
                        descField.getText());
            clearForm();
            refreshTable();
        });

        update.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                repo.update(id,
                        triggerField.getText(),
                        contentArea.getText(),
                        descField.getText());
                refreshTable();
            }
        });

        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                repo.delete(id);
                clearForm();
                refreshTable();
            }
        });

        buttons.add(add);
        buttons.add(update);
        buttons.add(delete);

        panel.add(fields, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    private void hookSelection() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                int row = table.getSelectedRow();
                triggerField.setText((String) tableModel.getValueAt(row, 1));
                contentArea.setText((String) tableModel.getValueAt(row, 2));
                descField.setText((String) tableModel.getValueAt(row, 3));
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Snippet s : repo.findAll()) {
            tableModel.addRow(new Object[]{
                    s.getId(),
                    s.getTrigger(),
                    s.getContent(),
                    s.getDescription()
            });
        }
    }

    private void clearForm() {
        triggerField.setText("");
        contentArea.setText("");
        descField.setText("");
    }
}