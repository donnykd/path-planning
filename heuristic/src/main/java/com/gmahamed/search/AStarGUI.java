package com.gmahamed.search;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A graphical user interface for visualizing the A* pathfinding algorithm.
 * 
 * @author Khalid Mahamed
 */
public class AStarGUI extends JFrame {

    private JPanel gridPanel; // Grid panel
    private int spaceWidth; // Width of the connectivity space
    private int spaceHeight; // Height of the connectivity space
    private State startState; // The start state
    private State goalState; // The goal state
    private List<Node> blockedNodes; // List of blocked nodes
    private List<State> itemList; // List of items

    /**
     * Constructs a new AStarGUI with the specified dimensions, start and goal states,
     * list of blocked nodes, and list of items.
     *
     * @param spaceWidth The width of the connectivity space.
     * @param spaceHeight The Height of the connectivity space.
     * @param startState The start state.
     * @param goalState The goal state.
     * @param blockedNodes List of blocked nodes.
     * @param itemList List of items.
    */
    public AStarGUI(int spaceWidth, int spaceHeight, State startState, State goalState, List<Node> blockedNodes, List<State> itemList) {
        this.spaceWidth = spaceWidth;
        this.spaceHeight = spaceHeight;
        this.startState = startState;
        this.goalState = goalState;
        this.blockedNodes = blockedNodes;
        this.itemList = itemList;

        setTitle("AStar Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600); 

        gridPanel = new JPanel(new GridLayout(spaceHeight, spaceWidth));
        add(gridPanel, BorderLayout.CENTER);

        initializeGrid();
    }

    private void initializeGrid() {
        // Set the grid space
        for (int i = 0; i < spaceHeight; i++) {
            for (int j = 0; j < spaceWidth; j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(cell);

                cell.setBackground(Color.WHITE);

                // Add "Start" to the start grid
                if(i == startState.getNode().i && j == startState.getNode().j){
                    JLabel label = new JLabel("Start");
                    label.setForeground(Color.BLACK);
                    label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 24));
                    cell.add(label);
                }
                // Add "Goal" to the start grid
                if(i == goalState.getNode().i && j == goalState.getNode().j){
                    JLabel label = new JLabel("Goal");
                    label.setForeground(Color.BLACK);
                    label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 24));
                    cell.add(label);
                }

                // Highlighting all the blockedNodes black
                for (Node blockedNode : blockedNodes) {
                    if(i == blockedNode.i && j == blockedNode.j){
                        cell.setBackground(Color.BLACK);
                    }        
                }

                // Add "Key" to the position of the keys
                for(Item item : Item.values()){
                    for (State landmark : itemList){
                        // Prevents an error that occurs when accessing the itemNode
                        if(landmark.set_entity != null || landmark.getItemNode(item) == null)
                            break;
                        if(i == landmark.getItemNode(item).i && j == landmark.getItemNode(item).j){
                            JLabel label = new JLabel("Key");
                            label.setForeground(Color.BLACK);
                            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 24));
                            cell.add(label);
                        }
                    }
                }
            }
        }
        setVisible(true);
    }

    /**
     * Updates the grid visualization by highlighting the given state.
     *
     * @param state The current state in the A* algorithm.
     */
    public void updateGrid(State state) {
        try {
            Thread.sleep(100); // Delay in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < spaceHeight; i++) {
            for (int j = 0; j < spaceWidth; j++) {
                JPanel cell = (JPanel) gridPanel.getComponent(i * spaceWidth + j);
                State cellState = new State(Entity.EXPLORER, new Node(i, j));
                
                for (Node blockedNode : blockedNodes) {
                    if(i == blockedNode.i && j == blockedNode.j){
                        cell.setBackground(Color.BLACK);
                    }        
                }
                if (state.equals(cellState)) {
                    cell.setBackground(Color.ORANGE);
                } 
            }
        }
    }

    /**
     * Reconstructs the grid visualization to highlight the path taken by the A* algorithm.
     *
     * @param state The state when goal was found.
     */
    public void reconstructGrid(State state){
        try {
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < spaceHeight; i++) {
            for (int j = 0; j < spaceWidth; j++) {
                JPanel cell = (JPanel) gridPanel.getComponent(i * spaceWidth + j);

                // Highlights the path blue
                if(i == state.getNode().i && j == state.getNode().j || (i == startState.getNode().i && j == startState.getNode().j)){
                    cell.setBackground(Color.BLUE);
                }
            }
        }
    }

}