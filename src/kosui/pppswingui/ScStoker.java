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

package kosui.pppswingui;

import java.awt.print.PrinterException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import kosui.ppputil.VcConst;
import kosui.ppputil.VcStringUtility;

/**
 * basically just a text area in a scroll pane. <br>
 * but i think this is a better name for text area. <br>
 */
public class ScStoker extends JScrollPane {

  private final JTextArea cmArea;
  
  //===

  /**
   * construct JScrollPane and JTextArea with a hello message.<br>
   * @param pxHello will get nulled out
   * @param pxR max count, must be bigger than 2
   * @param pxC max count, must be bigger than 16
   */
  public ScStoker(String pxHello, int pxR, int pxC) {
    super();
    cmArea = new JTextArea(VcStringUtility.ccNulloutString(pxHello));
    ScFactory.ccSetupConsoleArea(cmArea);
    if(pxR>2 && pxC>16){
      cmArea.setRows(pxR);
      cmArea.setColumns(pxC);
    }//..?
    super.setViewportView(cmArea);
  }//..!
  
  /**
   * construct JScrollPane and JTextArea with a hello message.<br>
   * @param pxHello will get nulled out
   */
  public ScStoker(String pxHello) {
    this(pxHello,-1,-1);
  }//..!
  
  //===

  /**
   * alias for JTextArea::append().<br>
   * re-direct viewport location via text selection setting.<br>
   * @param pxTag must have something
   * @param pxValue can be any thing
   */
  synchronized public final void ccWriteln(String pxTag, Object pxValue){
    if(!VcConst.ccIsValidString(pxTag)){return;}
    if(pxValue==null){
      cmArea.append(pxTag);
      cmArea.append(VcConst.C_V_NEWLINE);
    }else{
      cmArea.append(pxTag);
      cmArea.append(":");
      cmArea.append(pxValue.toString());
      cmArea.append(VcConst.C_V_NEWLINE);
    }//..?
  }//++<
  
  /**
   * might get passed to JTextArea::append() eventually.<br>
   * @param pxLine must have some thing.
   */
  public final void ccWriteln(String pxLine) {
    ScStoker.this.ccWriteln(pxLine, null);
  }//++<
  
  /**
   * alias for JTextArea::setText("");
   */
  public final void ccClear(){
    cmArea.setText("");
  }//++<
  
  /**
   * clear with a initiation message
   * @param pxHello do not pass null
   */
  public final void ccClear(String pxHello){
    if(pxHello==null){ccClear();return;}
    if(pxHello.length()>64){ccClear();return;}//..arbitary
    cmArea.setText(pxHello);
  }//++<
  
  //===
  
  /**
   * @return the area
   */
  public final JTextArea ccGetTextArea(){
    return cmArea;
  }//++>
  
  /**
   * wrapper for JTextArea::getText
   * @return could be anything
   */
  public final String ccGetText(){
    return cmArea.getText();
  }//++>
  
  /**
   * wrapper for JTextArea::getLineCount
   * @return could be anything
   */
  public final int ccGetLineCount(){
    return cmArea.getLineCount();
  }//++>
  
  //===
  
  /**
   * calls updateUI() than repaint().<br>
   */
  public final void ccRefresh(){
    if(!ScConst.ccIsEDT()){return;}
    cmArea.updateUI();
    cmArea.repaint();
    ScConst.ccScrollToLast(cmArea);
  }//+++
  
  //===
  
  /**
   * wrapper for JTextArea::print(). <br>
   * for advanced use you should create a print job outside.
   */
  public final void ccViewPortPrint(){
    try{
      cmArea.print();
    }catch(PrinterException e){
      System.out.println("kosui.pppswingui.ScConsole.ccPrint()::"
        + "PrinterException:"+e.getLocalizedMessage());
    }//..%
  }//+++
  
}//***eof
