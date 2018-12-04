package com.company;

import com.company.GUI.SwingFrame;

public class Main {

    public static void main(String[] args)
    {
        SwingFrame mainFrame = new SwingFrame();
        Model model = new Model();

        Controller controller = new Controller(mainFrame, model);
    }
}
