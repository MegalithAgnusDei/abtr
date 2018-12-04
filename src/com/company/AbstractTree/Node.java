package com.company.AbstractTree;

import java.util.ArrayList;

public class Node <T>
{
    private String name = "";
    private int lastNamingIdx = 0;
    private Node parent = null;
    private ArrayList<Node> childs = null;
    ////////////////////////////////////////
    T data;

    public Node(Node parent, T data)
    {
        this(parent == null ? "Root" : "Node" + parent.getLastNamingIdx(),parent,data);
        if(parent != null) parent.setLastNamingIdx(parent.getLastNamingIdx() + 1);
    }

    public Node(String name, Node parent, T data)
    {
        this.name = name;
        this.parent = parent;
        if(parent != null)
            parent.addChild(this);
        childs = new ArrayList<Node>();
        this.data = data;
    }

    public void addChild(Node n)
    {
        childs.add(n);
    }

    public ArrayList<Node> getChilds()
    {
        return childs;
    }

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getLastNamingIdx() {
        return lastNamingIdx;
    }

    public void setLastNamingIdx(int lastNamingIdx) {
        this.lastNamingIdx = lastNamingIdx;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public T getData()
    {
        return data;
    }
}
