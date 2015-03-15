/** * Copyright 2008 Caffeine-Soft Group *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package ru.caffeineim;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ru.caffeineim.gui.LoginFrame;

/**
 * Описание: Класс запускает приложение.
 * @version 0.0.1  10.12.2008
 * @author Renat Nasyrov
 */
public class Main {

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Properties systemSettings = System.getProperties();
                    systemSettings.put("http.proxyHost", "192.167.11.11");
                    systemSettings.put("http.proxyPort", "80");
//                    systemSettings.put("http.proxyUser", "");
//                    systemSettings.put("http.proxyPassword", "");
                    System.setProperties(systemSettings);
                    System.out.println(systemSettings);

                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    LoginFrame frame = new LoginFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.pack();
                    frame.setVisible(true);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
