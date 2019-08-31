/*
 * Copyright (C) 2018 Key Parker from K.I.C.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package kosui.ppplocalui;

import static processing.core.PApplet.ceil;

/**
 * if a element is mouse hovered, it can show a tip. <br>
 * but only those element has a id can show it. <br>
 */
public class EcTip {
  
  /**
   * a tip in a box
   */
  public final String cmTip;
  
  /**
   * the size of the box
   */
  public int cmTipW,cmTipH;
  
  /**
   * 
   * @param pxTip can be set only once
   */
  public EcTip(String pxTip){
    cmTip=pxTip;
    cmTipW=ceil(EcRect.pbOwner.textWidth(pxTip));
    cmTipH=EcFactory.C_DEFAULT_AUTOSIZE_HEIGHT;
    for(char it:pxTip.toCharArray())
      {if(it=='\n'){cmTipH+=EcFactory.C_DEFAULT_AUTOSIZE_HEIGHT;}}
  }//+++

}//***eof