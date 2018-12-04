package com.company;

import com.company.AbstractTree.AbstractTree;
import com.company.AbstractTree.Node;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class UITreeModel implements TreeModel
{
    private AbstractTree abstractTree;
    protected EventListenerList listenerList = new EventListenerList();

    public UITreeModel(AbstractTree abstractTree)
    {
        this.abstractTree = abstractTree;
    }

    @Override
    public Object getRoot() {
        return abstractTree.getRoot();
    }

    @Override
    public Object getChild(Object parent, int index) {
        return abstractTree.getChild((Node)parent,index);
    }

    @Override
    public int getChildCount(Object parent) {
        return abstractTree.getChildsCount((Node)parent);
    }

    @Override
    public boolean isLeaf(Object node) {
        return abstractTree.getChildsCount((Node)node) == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if(parent == null || child == null)
        {  return -1;}
        else
        {
            return ((Node)parent).getChilds().indexOf((Node)child);
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }

}
