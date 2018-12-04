package com.company;

import com.company.AbstractTree.AbstractTree;

public class Model
{
    private AbstractTree abstractTree;

    public Model()
    {
        abstractTree = new AbstractTree();
    }

    public AbstractTree getAbstractTree() {
        return abstractTree;
    }
}
