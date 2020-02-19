package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.ExceptionsHandling.DBExceptions;
import com.example.demo.ExceptionsHandling.ServiceExceptions;
import com.example.demo.model.Quiz;

public interface QuizService {
	
	List<Quiz> getAllQuizzes()throws ServiceExceptions,DBExceptions;
	List<Quiz> getQuizByID(int id)throws ServiceExceptions,DBExceptions;
	Quiz createQuiz(Quiz quiz) throws ServiceExceptions,DBExceptions;
	int DeleteAllQuizzes();
	int DeleteById(int qid)throws ServiceExceptions,DBExceptions;
}
