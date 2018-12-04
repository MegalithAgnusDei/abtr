package com.company.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContextMenu  extends JPopupMenu
{
    JMenuItem[] items;

    public ContextMenu()
    {
        items = new JMenuItem[3];
        items[0] = new JMenuItem("Add");
        items[1] = new JMenuItem("Remove");
        items[2] = new JMenuItem("Split");

        for (JMenuItem i : items)
            add(i);
    }


    public JMenuItem getAddItem() {
        return items[0];
    }

    public JMenuItem getDeleteItem() {
        return items[1];
    }

    public JMenuItem getSplitItem()
    {
        return items[2];
    }
}
