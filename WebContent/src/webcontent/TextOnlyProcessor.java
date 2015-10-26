/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcontent;

import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author rnavarro
 */
public class TextOnlyProcessor extends HTMLEditorKit.ParserCallback {   

    public void handleText(char[] text, int position) {    
            System.out.print(position);           
            System.out.println(": " + String.copyValueOf(text));   
    }   

}
