/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author elron
 */
public class ImageImplement extends JPanel {

    private Image img;

    public ImageImplement(Image img) {
        this.img = img;
        Dimension size = new Dimension(70, 70);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(70,70);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
