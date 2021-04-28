/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale;

import ttt.utils.console.input.ConsoleInput;

/**
 *
 * @author gabri
 */
public class CodiceFiscaleMain {

    public static void main(String[] args) {
        ConsoleInput ci = ConsoleInput.getInstance();
        int i = ci.readInteger("d");
        System.out.println(i);

    }
    
}
