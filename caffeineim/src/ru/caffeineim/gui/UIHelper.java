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

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

import ru.caffeineim.Main;

/**
 * Описание: Класс возвращает изображения по имени файла
 * @version 0.0.1  10.12.2008
 * @author Renat Nasyrov
 */
public class UIHelper {
    
    public static ImageIcon getIcon(String filename) {
        Image img = Toolkit.getDefaultToolkit().getImage(
                Main.class.getResource("images/" + filename));
        return new ImageIcon(img);
    }    

}
