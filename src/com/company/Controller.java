package com.company;

import com.company.AbstractTree.Node;
import com.company.GUI.SwingFrame;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller
{
    private Model model;
    private SwingFrame swingFrame;

    private UITreeModel uiTreeModel;

    public Controller(SwingFrame swingFrame, Model model)
    {
        this.model = model;
        this.swingFrame = swingFrame;

        uiTreeModel = new UITreeModel(model.getAbstractTree());
        swingFrame.getTree().setModel(uiTreeModel);

        initEvents();
    }

    private void initEvents()
    {
        swingFrame.getContextMenu().getAddItem().setAction(new AbstractAction("Add") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Node lastSelected = (Node)swingFrame.getTree().getLastSelectedPathComponent();
                TreePath lastSelectedTreePath = swingFrame.getTree().getSelectionPath();
                model.getAbstractTree().createChildForNode(lastSelected,new Object());

                updateTree();
                swingFrame.getTree().expandPath(lastSelectedTreePath);
            }
        });

        swingFrame.getContextMenu().getDeleteItem().setAction(new AbstractAction("Remove") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Node lastSelected = (Node)swingFrame.getTree().getLastSelectedPathComponent();
                TreePath lastSelectedTreePath = swingFrame.getTree().getSelectionPath();
                model.getAbstractTree().removePath(lastSelected);

                updateTree();
                swingFrame.getTree().expandPath(lastSelectedTreePath);
            }
        });

        swingFrame.getContextMenu().getSplitItem().setAction(new AbstractAction("Split") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Node lastSelected = (Node)swingFrame.getTree().getLastSelectedPathComponent();
                TreePath lastSelectedTreePath = swingFrame.getTree().getSelectionPath();
                model.getAbstractTree().splitPath(lastSelected);

                updateTree();
                swingFrame.getTree().expandPath(lastSelectedTreePath);
            }
        });

        swingFrame.getTree().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = swingFrame.getTree().getClosestRowForLocation(e.getX(), e.getY());
                swingFrame.getTree().setSelectionRow(row);
                if(e.getButton() == 3) {
                    if(swingFrame.getTree().getSelectionPath().getParentPath() == null) {
                        //////  Root node
                        swingFrame.getContextMenu().getAddItem().       setVisible(true);
                        swingFrame.getContextMenu().getDeleteItem().    setVisible(false);
                        swingFrame.getContextMenu().getSplitItem().     setVisible(false);
                        //////
                    }
                    else if(((Node)swingFrame.getTree().getLastSelectedPathComponent()).getChilds().size() == 0){
                        //////  Leaf node
                        swingFrame.getContextMenu().getAddItem().       setVisible(true);
                        swingFrame.getContextMenu().getDeleteItem().    setVisible(true);
                        swingFrame.getContextMenu().getSplitItem().     setVisible(false);
                        //////
                    }
                    else{
                        //////  Regular node
                        swingFrame.getContextMenu().getAddItem().       setVisible(true);
                        swingFrame.getContextMenu().getDeleteItem().    setVisible(true);
                        swingFrame.getContextMenu().getSplitItem().     setVisible(true);
                        //////
                    }
                    swingFrame.getContextMenu().show(e.getComponent(), e.getX(), e.getY());
                }

            }
        });

        swingFrame.getTree().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath selection = e.getNewLeadSelectionPath();
                if(selection != null) {
                    swingFrame.getNameField().setText(((Node) selection.getLastPathComponent()).toString());
                }
                else
                {
                    swingFrame.getNameField().setText(new String());
                }

            }
        });

        swingFrame.getNameField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Node)swingFrame.getTree().getLastSelectedPathComponent()).setName(
                        swingFrame.getNameField().getText()
                );
                updateTree();
            }
        });
    }

    private void updateTree()
    {
        swingFrame.saveTreeCollapseState();
        swingFrame.getTree().setModel(new UITreeModel(model.getAbstractTree()));
        swingFrame.loadTreeCollapseState();
    }
}
