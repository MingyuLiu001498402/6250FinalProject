package edu.neu.coe.info6205.life.base;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
    static Random random = new Random();
    static int g0X = 34;
    static int g0Y = 20;
    static int startX = 34;
    static int startY = 20;
    static int curX;
    static int curY;

//    public  static JPanel panel = new JPanel();

    private static int[][] map = new int[41][69];
    static int MAP_WIDTH = 68;
    static int MAP_HEIGHT = 40;
    List<JLabel> labels = new ArrayList<>();

    private static GUI gui = new GUI();
    public static GUI getGUI(){
        return gui;
    }

    public void toBlank()
    {
        for(int i = 0; i < MAP_HEIGHT; i++)
        {
            for(int j = 0; j < MAP_WIDTH; j++)
            {
                JLabel label = new JLabel();
                Color color = Color.white;
                label.setBackground(color);
            }
        }
    }

    private GUI() {
        setSize(1400, 850);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
//        setVisible(true);
        getContentPane().setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(MAP_HEIGHT, MAP_WIDTH));
        panel.setBounds(10, 10, MAP_WIDTH * 20, MAP_HEIGHT * 20);
        getContentPane().add(panel);
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                JLabel label = new JLabel();
                Color color = null;
                if (map[i][j] == 0) {
                    color = Color.white;
                }
                label.setBackground(color);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(label);
                labels.add(label);
            }
        }
    }
}