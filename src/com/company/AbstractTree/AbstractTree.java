package com.company.AbstractTree;

public class AbstractTree
{
    Node root = null;

    public AbstractTree()
    {
        root = new Node(null,null);
    }

    public AbstractTree(Node root)
    {
        this.root = root;
    }

    public void removePath(Node node)
    {
        node.getParent().getChilds().remove(node);
    }

    public void splitPath(Node node)
    {
        for(Object c : node.getChilds()) {
            Node n = (Node) c;
            n.setParent(node.getParent());

            node.getParent().addChild((Node)c);
        }
        removePath(node);
    }


    public void createChildForNode(Node parent, Object data)
    {
        new Node(parent,data);
    }

    public Node getRoot() {
        return root;
    }

    public Node getChild(Node parent, int index)
    {
        return (Node) parent.getChilds().get(index);
    }

    public int getChildsCount(Node parent)
    {
        return parent.getChilds().size();
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
