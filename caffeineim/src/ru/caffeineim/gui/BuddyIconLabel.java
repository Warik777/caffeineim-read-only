/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.caffeineim.gui;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * @date 15.01.2009
 * @author Andrew R/B
 */
public class BuddyIconLabel extends JLabel{

    public BuddyIconLabel(Icon image) {
        super(image);
    }

    public BuddyIconLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    public BuddyIconLabel(String text) {
        super(text);
    }

    public BuddyIconLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public BuddyIconLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }
}
