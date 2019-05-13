/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import java.util.Random;

/**
 *
 * @author xabie
 */
public class Agent {
    private int bits[];
    
    public int[] getBits() {
        return bits;
    }
    
    public void mutate(int index) {
        if (index <= bits.length) bits[index] = 1 - bits[index];
    }
    
    public Agent(int s) {
        bits = new int[s];
        
        Random r = new Random();
        for (int i = 0; i < bits.length; i++)
            bits[i] = r.nextInt(2);
    }
    
    public Agent(int bits[]) {
        this.bits = bits;
    }
    
    public String getString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < bits.length; i++) {
            sb.append(bits[i]);
        }
        
        return sb.toString();
    }
}
