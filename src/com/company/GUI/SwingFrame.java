package com.company.GUI;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class SwingFrame extends JFrame
{
    private JTree tree;
    private JPanel panel;
    private JTextField nameField;
    private ContextMenu contextMenu;
    //////////////////////////////////////
    HashMap<TreePath,Boolean> treeCollapseState = new HashMap<>();

    public SwingFrame()
    {
        init();

        contextMenu = new ContextMenu();


    }

    private void init()
    {
        setContentPane(panel);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void saveTreeCollapseState()
    {

        for(int i = 0; i < tree.getRowCount(); i++)
        {
            TreePath tp = tree.getPathForRow(i);
            boolean ic = tree.isCollapsed(tree.getPathForRow(i));
            treeCollapseState.put(tp,ic);
        }
    }

    public void loadTreeCollapseState()
    {
        Set<TreePath> treePaths = treeCollapseState.keySet();
        for(TreePath tp : treePaths)
        {
            if(treeCollapseState.get(tp))
                tree.collapsePath(tp);
        }
    }

    public JTree getTree() {
        return tree;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    private void createUIComponents() {
        tree = new JTree();
        tree.setCellRenderer(new DefaultTreeCellRenderer(){
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);
                setIcon(null);
                return this;
            }});
    }

    public JTextField getNameField() {
        return nameField;
    }
}
