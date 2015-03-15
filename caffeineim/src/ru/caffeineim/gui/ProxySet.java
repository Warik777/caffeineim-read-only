/** * Copyright 2008 Caffeine-Soft Group * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package ru.caffeineim.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Описание: Класс предназначен для указания данных прокси-сервера
 * @version 0.0.1  10.12.2008
 * @author Andrew Romanoff
 * @author Vladimir Dvinianinov 
 */
public class ProxySet extends JFrame{
    
    private String proxyIP;
    private String proxyPort;
    private String proxyLogin;
    private String proxyPSW;
    private static final String titleStr = "Proxy-settings";
    private static final String borderStr = "Введите параметры прокси-сервера";
    private static final String saveStr = "Сохранить";
    private static final String loginStr = "Авторизация:";
    private static final String passwordStr = "пароль:";
    
    public ProxySet(){
        
        setTitle(titleStr);
        setSize(400, 300);
        
        GridBagPanel c = new GridBagPanel();
        setContentPane(c);

        TitledBorder titleBorder= new TitledBorder(borderStr);
        c.setBorder(titleBorder);
        c.setInsets(8, 4, 8, 4);
        
        JTextField IPfield = new JTextField(proxyIP, 15);
        JTextField portField = new JTextField(proxyPort, 4);
        JTextField loginField = new JTextField(proxyLogin, 15);
        JPasswordField pswField = new JPasswordField(proxyPSW, 10);
        JButton btnSave = new JButton(saveStr);
        btnSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //
            }
        });
        c.setAnchor(GridBagConstraints.EAST);        
        c.place(new JLabel("IP:"),          0, 0, 1, 1);
        c.setAnchor(GridBagConstraints.WEST);
        c.place(IPfield,                    1, 0, 1, 1);
        c.setAnchor(GridBagConstraints.EAST);
        c.place(new JLabel("port:"),        2, 0, 1, 1);
        c.setAnchor(GridBagConstraints.WEST);
        c.place(portField,                  3, 0, 1, 1);
        c.setAnchor(GridBagConstraints.EAST);
        c.place(new JLabel(loginStr), 0, 1, 1, 1);
        c.setAnchor(GridBagConstraints.WEST);
        c.place(loginField,                 1, 1, 1, 1);
        c.setAnchor(GridBagConstraints.EAST);
        c.place(new JLabel(passwordStr),      0, 2, 1, 1);
        c.setAnchor(GridBagConstraints.WEST);
        c.place(pswField,                   1, 2, 1, 1);
        c.setAnchor(GridBagConstraints.WEST);
        c.place(btnSave,                    1, 3, 1, 1);
        setLocation(400, 300);
        pack();
    }
    
    private String getIP() {
        return proxyIP;
    }
    
    public void setIP(String proxyIP) {
        this.proxyIP = proxyIP;
    }
    
    private String getPort() {
        return proxyPort;
    }
    
    public void setPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }
    
    private String getLogin() {
        return proxyLogin;
    }
    
    public void setLogin(String proxyLogin) {
        this.proxyLogin = proxyLogin;
    }
    
    private String getPSW() {
        return proxyPSW;
    }
    
    public void setPSW(String proxyPSW) {
        this.proxyPSW = proxyPSW;
    }

}
