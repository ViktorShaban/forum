/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.IDAO;

import java.util.ArrayList;
import java.util.List;
import ua.kpi.nc.entities.Questions;

/**
 *
 * @author Виктор
 */
public interface QuestionsIDAO {

    public List<Questions> findQuestionsByTopicId(int topicId);

    public void addQuestion(Questions questions);

    public ArrayList<Integer> getAllQuestionsId();
    public void deleteQuestion(int id);

}
