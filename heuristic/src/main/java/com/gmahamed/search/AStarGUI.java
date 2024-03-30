package com.gmahamed.search;

import javax.swing.*;
import java.awt.*;

public class AStarGUI extends JFrame {

    private JPanel gridPanel;
    private int spaceWidth;
    private int spaceHeight;
    private State startState;
    private State goalState;
    private State currentState;

    public AStarGUI(int spaceWidth, int spaceHeight, State startState, State goalState) {
        this.spaceWidth = spaceWidth;
        this.spaceHeight = spaceHeight;
        this.startState = startState;
        this.goalState = goalState;

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

                if(i == startState.getNode().i && j == startState.getNode().j){
                    cell.setBackground(Color.GREEN);
                    currentState = startState;
                }
                if(i == goalState.getNode().i && j == goalState.getNode().j){
                    cell.setBackground(Color.red);
                }
            }
        }
        setVisible(true);
    }

    public static void main(String[] args) {
    }
}