package com.example.demo.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.List;
import javax.management.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Null;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.ExceptionsHandling.DBExceptions;
import com.example.demo.model.Quiz;

@Repository
public class QuizDaoImp implements QuizDao {
	@Autowired
	SessionFactory SessionFactory;
	@Autowired
	Quiz quiz;
	Transaction transaction;
	//private static final Logger LOGGER=(Logger) LoggerFactory.getLogger(QuizDao.class);

	@Override
	public List<Quiz> getAllQuizzes() throws DBExceptions {
		DBExceptions dbExceptions = new DBExceptions();
		List<Quiz> quizzes = null;
		try {
			Session session = SessionFactory.getCurrentSession();
			String queryString = "From Quiz";
			TypedQuery<Quiz> query = session.createQuery(queryString);
			quizzes = ((org.hibernate.query.Query) query).list();
			if(quizzes.isEmpty()) {
				System.out.println("quizzes list is: "+quizzes);
				dbExceptions.IDNotFound("Given Id is not found");
			}
		} catch (NullPointerException e) {
			dbExceptions.IDNotFound("Nothing will fetched",e);
		}
		catch (Exception e) {
			throw new DBExceptions("Fetching the Data Failed", e);
		}
		return quizzes;
	}

	@Override
	public List<Quiz> getQuizByID(int id) throws DBExceptions {
		DBExceptions dbExceptions = new DBExceptions();
		List<Quiz> quizzesList = null;
		try {
			Session session = SessionFactory.getCurrentSession();
			String queryString = "From Quiz where id=:id";
			TypedQuery<Quiz> query = (TypedQuery<Quiz>) session.createQuery(queryString);
			query.setParameter("id", id);
			quizzesList = ((org.hibernate.query.Query) query).list();
			if(quizzesList.isEmpty()) {
				System.out.println("quizzes list is: "+quizzesList);
				dbExceptions.IDNotFound("Given Id is not found");
			}
		} catch (NullPointerException e) {
			dbExceptions.IDNotFound("Given Id is not found",e);
		}
		catch(Exception e) {
			throw new DBExceptions("Exceptions",e);
		}
		return quizzesList;
	}
	@Override
	public Quiz createQuiz(Quiz quiz) throws DBExceptions {
		Session session = null;
		//System.out.println("from DAO " +quiz.getCategory().getCategoryId());
		try {
			session = SessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Timestamp ts=new Timestamp(System.currentTimeMillis()); 
			//LOGGER.info("time is now: "+ts);
			quiz.setCreated_on(ts);
			System.out.println("value "+quiz.getCategory().getCategoryId());
			session.saveOrUpdate(quiz);
			transaction.commit();
		} catch (Exception e) {
			throw new DBExceptions("Inserting the data failed", e);
		} finally {
			session.close();
		}
		return quiz;
	}
	@Override
	public int DeleteAllQuizzes() {
		Session session =null;
		DBExceptions dbExceptions = new DBExceptions();
		int id = 0;
		try {
			session = SessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			org.hibernate.query.Query query =  session.createQuery("truncate table Quiz");
			 id = query.executeUpdate();	
		}
		catch(NullPointerException e) {
			dbExceptions.IDNotFound("Nothing will deleted");
		}
		transaction.commit();
		session.close();
		return id;
	}
	@Override
	public int deleteById(int qid) {
		Session session = SessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		String hql = "delete Quiz where id = :id";
	    org.hibernate.query.Query q = session.createQuery(hql).setParameter("id", qid);
	    int id = q.executeUpdate();
	    transaction.commit();
	    session.close();
	    return id;
	}
}
