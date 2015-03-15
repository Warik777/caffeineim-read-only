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

import javax.swing.*;
import java.awt.*;

/**
 * Описание: Класс предназначен упростить работу с менеджером раскладки
 * @version 0.0.1  10.12.2008
 * @author Renat Nasyrov
 * 
 */
public class GridBagPanel extends JPanel {

	private GridBagConstraints constr = new GridBagConstraints();

	public GridBagPanel() {
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
	}

	public void place(Component cmp, int x, int y, int w, int h) {
	    constr.gridx = x;
	    constr.gridy = y;
	    constr.gridwidth = w;
	    constr.gridheight = h;
	    add(cmp, constr);
        }

	public void setAnchor(int i) {
            constr.anchor = i;
	}

	public void setFill(int i) {
            constr.fill = i;
            if (i == GridBagConstraints.HORIZONTAL) {
                setWeight(100, 0);
            } else if (i == GridBagConstraints.VERTICAL) {
                setWeight(0, 100);
            }  else if (i == GridBagConstraints.BOTH) {
                setWeight(100, 100);
            } else {
                setWeight(0, 0);
            }
	}

	public void setWeight(int x, int y) {
            constr.weightx = x;
            constr.weighty = y;
	}

	public void setInsets(int top, int left, int right, int bottom) {
            constr.insets.top = top;
            constr.insets.left = left;
            constr.insets.right = right;
            constr.insets.bottom = bottom;		
	}

	public void setInsets(Insets insets) {
            constr.insets.top = insets.top;
            constr.insets.left = insets.left;
            constr.insets.right = insets.right;
            constr.insets.bottom = insets.bottom;
	}             

}
