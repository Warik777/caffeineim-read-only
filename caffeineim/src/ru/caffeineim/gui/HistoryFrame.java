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


/**
 * Описание: Класс предназначен для работы с историей сообщений
 * @version 0.0.1  10.12.2008
 * @author Vladimir Dvinianinov
 * @author Renat Nasyrov
 */
public class HistoryFrame extends JFrame implements ActionListener {
    public String[] contactsString = {"11111", "22222", "33333", "44444"};
    private static final String FIND_STR = "Find_string";
    
    public HistoryFrame() {
        
        setTitle("История сообщений");
         
        GridBagPanel c = new GridBagPanel();
        setContentPane(c);
        
        c.setAnchor(GridBagConstraints.WEST);
        c.setInsets(4, 4, 4, 4);
                
        JComboBox list_numbers = new JComboBox();

        Integer[] intArray = new Integer[contactsString.length];
        for (int i = 0; i < contactsString.length; i++) {
            intArray[i] = new Integer(i);
            list_numbers.addItem(contactsString[i]);
        }
        
        ImageIcon icon = UIHelper.getIcon("find.png");                
        JButton find = new JButton("Найти", icon);
        find.setActionCommand(FIND_STR);
        find.setToolTipText("Найти");
        find.addActionListener(this);
        
        JTextField str_search = new JTextField(50);
        str_search.setColumns(20);
        
        JCheckBox check_1 = new JCheckBox("Слово целиком");
        JCheckBox check_2 = new JCheckBox("Учитывать регистр");
        JRadioButton rad_1 = new JRadioButton("Вперед");
        JRadioButton rad_2 = new JRadioButton("Назад");
        ButtonGroup group = new ButtonGroup();
        group.add(rad_1);
        group.add(rad_2);
        rad_1.setSelected(true);
        
        c.place(new JLabel("ICQ #", JLabel.RIGHT), 0, 0, 1, 1);
        c.place(list_numbers,                      1, 0, 1, 1);
        c.place(str_search,                        2, 0, 1, 1);
        c.place(find,                              3, 0, 1, 1);
        c.place(check_1,                           2, 1, 1, 1);
        c.place(check_2,                           2, 2, 1, 1);
        c.place(rad_1,                             3, 1, 1, 1);
        c.place(rad_2,                             3, 2, 1, 1);
        c.setFill(GridBagConstraints.BOTH);
        
        JTextPane textPane = new JTextPane();
        JScrollPane paneScrollPane = new JScrollPane(textPane);
        paneScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScrollPane.setPreferredSize(new Dimension(250, 155));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));
        
        c.place(paneScrollPane,                   0, 4, 4, 4);
        c.setFill(GridBagConstraints.BOTH);
        setContentPane(c);
        pack();
     }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    

}
