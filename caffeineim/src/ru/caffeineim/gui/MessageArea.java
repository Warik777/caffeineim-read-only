package ru.caffeineim.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import ru.caffeineim.BuddyInfo;

/**
 * @date 15.01.2009
 * @author Andrew R/B
 */
public class MessageArea extends JScrollPane {

    private short type = 0;
    private String uin = "";
    /**Необходимо будет потом заменить JTextArea на самописный компонент*/
    private JTextArea messageArea = new JTextArea();
    private SimpleDateFormat df = new SimpleDateFormat("hh.mm.ss");

    public MessageArea(String uin, short type) {
        this.getViewport().add(messageArea);
        this.uin = uin;
        this.type = type;
    }

    /**
     * Отображает сообщение
     * @param message
     */
    public void insertMessage(String message) {
        if (type == MessagePanel.INCOMING_PANE) {
            messageArea.append(BuddyInfo.getNickByUin(uin) + "(" + df.format(new Date()) + "):\r\n");
        } else {
            messageArea.append(BuddyInfo.getMyNick() + "(" + df.format(new Date()) + "):\r\n");
        }
        messageArea.append(message + "\r\n");
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }
}
