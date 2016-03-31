/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import java.util.Scanner;

/**
 *
 * @author pllefebvre
 */
public abstract class ServiceTest {
    
    private static final Scanner sc = new Scanner(System.in);
    
    public static boolean hasNextInt() {
        
        return sc.hasNextInt();
    }
    
    public static String next() {
        
        return sc.next();
    }
    
    public static String nextLine() {
        
        return sc.nextLine();
    }
    
    public static long nextLong() {
        
        return sc.nextLong();
    }
    
    public static int nextInt() {
        
        return sc.nextInt();
    }
}
