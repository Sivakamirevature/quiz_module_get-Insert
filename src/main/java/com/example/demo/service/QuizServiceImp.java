package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ExceptionsHandling.DBExceptions;
import com.example.demo.ExceptionsHandling.ServiceExceptions;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Quiz;

@Service
public class QuizServiceImp implements QuizService {
	@Autowired
	QuizDao quizdao;
	@Autowired
	Quiz q;
	@Override
	public List<Quiz> getAllQuizzes() throws ServiceExceptions, DBExceptions{
		return quizdao.getAllQuizzes();
	}

	@Override
	public List<Quiz> getQuizByID(int id)throws ServiceExceptions, DBExceptions{
		ServiceExceptions serviceExceptions = new ServiceExceptions();
		try{
			
			}
		catch(IllegalArgumentException e) {
			serviceExceptions.ArgumentMismatching("ID is not valid",e);
		}
		catch(Exception e){
			throw serviceExceptions;
		}
		return quizdao.getQuizByID(id);
	}
	@Override
	public Quiz createQuiz(Quiz quiz) throws ServiceExceptions,DBExceptions{
		//System.out.println("from Service " +quiz.getCategory().getCategoryId());
		Quiz q = quizdao.createQuiz(quiz);  
		return q;
	}
	@Override
	public int DeleteAllQuizzes() {
		int id = quizdao.DeleteAllQuizzes();
		return id;
	}

	@Override
	public int DeleteById(int qid) throws ServiceExceptions, DBExceptions {
		return quizdao.deleteById(qid);
		
	}
}
