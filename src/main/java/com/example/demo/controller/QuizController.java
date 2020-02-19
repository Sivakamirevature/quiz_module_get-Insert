package com.example.demo.controller;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.ExceptionsHandling.DBExceptions;
import com.example.demo.ExceptionsHandling.ServiceExceptions;
import com.example.demo.model.Quiz;
import com.example.demo.service.QuizService;
@RestController
@RequestMapping("Quizzes")
public class QuizController {
		@Autowired
		QuizService quizservice;
		@Autowired
		Quiz q;
		
		@GetMapping(value = "/getAllQuizzes")
		public List<Quiz> getAllUser() throws DBExceptions, ServiceExceptions {
			List<Quiz> quizzes = null;
			try {
				quizzes = quizservice.getAllQuizzes();
			} catch (ServiceExceptions e) {
				e.printStackTrace();
			}
			return quizzes;
		}
		
		@GetMapping(value ="/getQuizByID/{id}")
		public List<Quiz> getQuizById(@PathVariable int id) throws Exception{
			List<Quiz> quizzesList = quizservice.getQuizByID(id);
			
			return quizzesList;
		}
		@PostMapping(value="/doCreateQuiz")
		public Quiz doCreateQuiz(@RequestBody Quiz quiz) throws DBExceptions, ServiceExceptions{
			Quiz q = new Quiz();
			
			q = quizservice.createQuiz(quiz);
			//System.out.println("from controller " +quiz.getCategory().getCategoryId());
			 return q;
		}
		
		@DeleteMapping(value="/doDeleteAllQuizzes")
		public int doDeleteAllQuizzes() {
			int id = quizservice.DeleteAllQuizzes();
			return id;
		}	
		
		@DeleteMapping(value="/doDeleteByID/{qid}")
		public int doDeleteByID(@PathVariable int qid)throws DBExceptions, ServiceExceptions {
			return quizservice.DeleteById(qid);
		}	
}
