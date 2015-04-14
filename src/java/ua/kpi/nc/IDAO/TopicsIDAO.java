/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.IDAO;

import java.util.ArrayList;
import java.util.List;
import ua.kpi.nc.entities.Messages;
import ua.kpi.nc.entities.Topics;

/**
 *
 * @author Виктор
 */
public interface TopicsIDAO {

    public List<Messages> findAllTopics();

    public ArrayList<Integer> getAllTopicsId();

    public void addTopic(Topics topics);
    
    public void deleteRecord(int id);

}
