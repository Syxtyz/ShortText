package shorttext;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SystemTray {
    public SystemTray(ApplicationGUI frame) {
        if (!java.awt.SystemTray.isSupported()) {
            JOptionPane.showMessageDialog(null, "System Tray not supported on this system.");
            return;
        }
        java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
        Image iconImage = Toolkit.getDefaultToolkit().getImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(iconImage, "ShortText App", createPopupMenu(frame));
        trayIcon.setImageAutoSize(true);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        trayIcon.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
            frame.setState(JFrame.NORMAL);
        }));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
            }
        });
    }

    private PopupMenu createPopupMenu(ApplicationGUI frame) {
        PopupMenu menu = new PopupMenu();
        MenuItem openItem = new MenuItem("Open");
        openItem.addActionListener(e -> {
            frame.setVisible(true);
            frame.setState(JFrame.NORMAL);
        });
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> {
            java.awt.SystemTray.getSystemTray().remove(java.awt.SystemTray.getSystemTray().getTrayIcons()[0]);  // Full package reference
            System.exit(0);
        });

        menu.add(openItem);
        menu.add(exitItem);
        return menu;
    }
}