package ru.caffeineim.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * @date 15.01.2009
 * @author Andrew R/B
 */
public class MessagePanel extends JPanel{
    public static final short INCOMING_PANE = 0;
    public static final short OUTGOING_PANE = 1;
    private short type = 0;
    private MessageArea messageArea;


    public MessagePanel(String uin, short type){
         messageArea = new MessageArea(uin, type);
         this.setLayout(new BorderLayout());
         this.add(messageArea, BorderLayout.CENTER);
    }

}
