package com.gmahamed.search;

import javax.swing.*;
import java.awt.*;

public class AStarGUI extends JFrame {

    private JPanel gridPanel;
    private int spaceWidth;
    private int spaceHeight;

    public AStarGUI(int spaceWidth, int spaceHeight) {
        this.spaceWidth = spaceWidth;
        this.spaceHeight = spaceHeight;

        setTitle("AStar Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600); 

        gridPanel = new JPanel(new GridLayout(spaceHeight, spaceWidth));
        add(gridPanel, BorderLayout.CENTER);

        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < spaceHeight; i++) {
            for (int j = 0; j < spaceWidth; j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(cell);
            }
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new AStarGUI(5, 5);
    }
}