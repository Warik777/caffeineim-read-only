/** * Copyright 2008 Caffeine-Soft Group * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at *  * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package ru.caffeineim.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Описание: Окно сообщений
 * @version 0.0.1  10.12.2008
 * @author Andrew Romanoff
 * @author Vladimir Dvinianinov
 * @author Renat Nasyrov
 */
public class MessageWindow extends JFrame implements ActionListener{
    
    private static String uin="345678"; //[] array of contacts
    private static int a;               //[] quantity of contacts
    private static int v;               //int v - version of type dialog 1=Minimum 2=Standart 3=Advanced


    public MessageWindow() {
        
        GridBagPanel contentPane = new GridBagPanel();
        setContentPane(contentPane);
        contentPane.setAnchor(GridBagConstraints.WEST);
        contentPane.setInsets(4, 0, 0, 0);
        contentPane.setFill(GridBagConstraints.BOTH);       
        
        GridBagPanel contentPaneUp = new GridBagPanel();
        contentPaneUp.setAnchor(GridBagConstraints.WEST);
        contentPaneUp.setInsets(4, 0, 0, 0);
        contentPaneUp.setFill(GridBagConstraints.BOTH);
        
        GridBagPanel paneInner = new GridBagPanel();
        paneInner.setAnchor(GridBagConstraints.WEST);
        paneInner.setInsets(4, 0, 0, 0);
        
        JTextArea areaText = new JTextArea();
        JScrollPane answerScrollPane = new JScrollPane(areaText); 
        answerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        answerScrollPane.setPreferredSize(new Dimension(350, 150));
        answerScrollPane.setMinimumSize(new Dimension(100, 20));

        GridBagPanel contentPaneGND = new GridBagPanel();
        contentPaneGND.setAnchor(GridBagConstraints.WEST);
        contentPaneGND.setInsets(4, 0, 0, 0);
        
        ImageIcon img = UIHelper.getIcon("Jasmine_32.png");
        JButton btn = new JButton(img);
        paneInner.place(btn,              0, 0, 1, 3);
        paneInner.setFill(GridBagConstraints.BOTH);
        paneInner.place(answerScrollPane, 1, 0, 1, 3);
                
        JTabbedPane tabUINs  = new JTabbedPane();
        tabUINs.addTab(uin, paneInner);
        
        contentPaneUp.place(tabUINs, 0, 0, 5, 4);
        contentPaneUp.setFill(GridBagConstraints.BOTH);
        
        JTextPane paneTextGND = new JTextPane();
        JScrollPane answerScrollPaneGND = new JScrollPane(paneTextGND); 
        answerScrollPaneGND.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        answerScrollPaneGND.setPreferredSize(new Dimension(350, 100));
        answerScrollPaneGND.setMinimumSize(new Dimension(100, 20));

        if (v>1) {
            //paneService for additional buttons
            JPanel paneService = new JPanel();
            //**********************************
            contentPaneGND.add(paneService, new GridBagConstraints(0, 0, 4, 1, 1.1, 1,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        }
        JButton buttonSend = new JButton("Отправить");
        btn = new JButton(UIHelper.getIcon("avp1_32.png"));
        JCheckBox chkBasic = new JCheckBox("Basic");
        //chkBasic = 1;
        JCheckBox chkExtend = new JCheckBox("Extend");
        JCheckBox chkIntermediate = new JCheckBox("Intermediate");
        
        contentPaneGND.place(btn,                 0, 0, 1, 4);
        contentPaneGND.place(chkBasic,            1, 0, 1, 1);
        contentPaneGND.place(chkExtend,           2, 0, 1, 1);
        contentPaneGND.place(chkIntermediate,     3, 0, 1, 1);
        contentPaneGND.setFill(GridBagConstraints.BOTH);
        contentPaneGND.place(answerScrollPaneGND, 1, 1, 4, 2);
        contentPaneGND.setFill(GridBagConstraints.NONE);
        contentPaneGND.place(buttonSend,          1, 3, 2, 1);       
       
       JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,contentPaneUp,contentPaneGND);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        
        contentPane.place(splitPane, 1, 1, 1, 1);
        contentPane.setFill(GridBagConstraints.BOTH);       
        setContentPane(contentPane);
        setLocation(400, 300);
        pack();
    }
    
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
