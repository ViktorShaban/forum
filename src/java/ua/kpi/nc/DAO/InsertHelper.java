/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.DAO;

import java.util.ArrayList;

/**
 *
 * @author Виктор
 */
public class InsertHelper {

    public static int getFreeId(ArrayList<Integer> list) {
        int counter = 0;
        while (true) {
            if (list.contains(counter)) {
                counter++;
            } else {
                return counter;
            }
        }
    }

}
