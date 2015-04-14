/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.IDAO;

import java.util.ArrayList;
import java.util.List;
import ua.kpi.nc.entities.Answers;

/**
 *
 * @author Виктор
 */
public interface AnswersIDAO {

    public List<Answers> findAnswersByQuestionId(int questionId);

    public ArrayList<Integer> getAllAnswersId();

    public void addAnswer(Answers answers);

    public void deleteAnswer(int id);

}
