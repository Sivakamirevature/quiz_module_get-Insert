package com.example.demo.dao;

import java.util.List;
import javax.management.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.ExceptionsHandling.DBExceptions;
import com.example.demo.model.Quiz;

@Repository
public class QuizDaoImp implements QuizDao {
	@Autowired
	SessionFactory SessionFactory;

	@Override
	public List<Quiz> getAllQuizzes() throws DBExceptions {
		List<Quiz> quizzes;
		try {
			Session session = SessionFactory.getCurrentSession();
			String queryString = "From Quiz";
			TypedQuery<Quiz> query = session.createQuery(queryString);
			quizzes = ((org.hibernate.query.Query) query).list();
		} catch (Exception e) {
			throw new DBExceptions("Fetching the Data Failed", e);
		}
		return quizzes;
	}

	@Override
	public List<Quiz> getQuizByID(int id) throws DBExceptions {
		List<Quiz> quizzesList;
		try {
			Session session = SessionFactory.getCurrentSession();
			String queryString = "From Quiz where id=:id";
			TypedQuery<Quiz> query = (TypedQuery<Quiz>) session.createQuery(queryString);
			query.setParameter("id", id);
			quizzesList = ((org.hibernate.query.Query) query).list();
		} catch (Exception e) {
			throw new DBExceptions("Fetching the Data Failed", e);
		}
		return quizzesList;
	}

	@Override
	public String createQuiz(Quiz quiz) throws DBExceptions {
		Session session = null;
		System.out.println("name "+quiz.getQuiz_name());
	try {
		session = SessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();	
		String queryString = "insert into quizzes(quiz_id, "
				+ "quiz_name,"
				+ "tags, "
				+ "activity_points, "
				+ "duration, "
				+ "max_no_of_attempts, "
				+ "level_override, "
				+ "slug, "
				+ "description, "
				+ "meta_keywords, "
				+ "meta_description, "
				+ "icon, "
				+ "instructions, "
				+ "pass_percentage, "
				+ "is_available_pre_signup, "
				+ "is_available_via_slug, "
				+ "is_available_dashboard, "
				+ "is_timer_enabled, "
				+ "is_shuffle_questions, "
				+ "is_shuffle_answers, "
				+ "is_display_score, "
				+ "is_allow_attempt_review, "
				+ "is_show_correct_answers_passed, "
				+ "is_show_correct_answers_failed, "
				+ "is_show_answer_explanations, "
				+ "is_enable_save_resume, "
				+ "created_on,"
				+ "created_by, "
				+ "modified_on, "
				+ "modified_by, "
				+ "modified_count,"
				+ "category_id, "
				+ "level_id, "
				+ "status)"
				+"values(:quiz_id,:quiz_name,:tags,:activity_points,:duration,:max_no_of_attempts,:level_override,:slug,:description,:meta_keywords,:meta_description,:icon,:instructions, :pass_percentage, :is_available_pre_signup, :is_available_via_slug, :is_available_dashboard, :is_timer_enabled, :is_shuffle_questions, :is_shuffle_answers, :is_display_score, :is_allow_attempt_review, :is_show_correct_answers_passed, :is_show_correct_answers_failed, :is_show_answer_explanations, :is_enable_save_resume, :created_on, :created_by, :modified_on, :modified_by, :modified_count, :category_id, :level_id, :status)";
		TypedQuery<Quiz> query = (TypedQuery<Quiz>) session.createSQLQuery(queryString);
		query.setParameter("quiz_id", quiz.getQuiz_id());
		query.setParameter("quiz_name",quiz.getQuiz_name());
		query.setParameter("tags",quiz.getTags());
		query.setParameter("activity_points",quiz.getActivity_points());
		query.setParameter("duration",quiz.getDuration());
		query.setParameter("max_no_of_attempts",quiz.getMax_no_of_attempts());
		query.setParameter("level_override",quiz.isLevel_override());
		query.setParameter("slug",quiz.getSlug());
		query.setParameter("description",quiz.getDescription());
		query.setParameter("meta_keywords",quiz.getMeta_keywords());
		query.setParameter("meta_description",quiz.getMeta_description());
		query.setParameter("icon",quiz.getIcon());
		query.setParameter("instructions", quiz.getInstructions());
		query.setParameter("pass_percentage",quiz.getPass_percentage());
		query.setParameter("is_available_pre_signup",quiz.getIs_available_pre_signup());
		query.setParameter("is_available_via_slug",quiz.getIs_available_via_slug());
		query.setParameter("is_available_dashboard",quiz.getIs_available_dashboard());
		query.setParameter("is_timer_enabled",quiz.getIs_timer_enabled());
		query.setParameter("is_shuffle_questions",quiz.getIs_shuffle_questions());
		query.setParameter("is_shuffle_answers",quiz.getIs_shuffle_answers());
		query.setParameter("is_display_score",quiz.getIs_display_score());
		query.setParameter("is_allow_attempt_review",quiz.getIs_allow_attempt_review());
		query.setParameter("is_show_correct_answers_passed", quiz.getIs_show_correct_answers_passed());
		query.setParameter("is_show_correct_answers_failed",quiz.getIs_show_correct_answers_failed());
		query.setParameter("is_show_answer_explanations",quiz.getIs_show_answer_explanations());
		query.setParameter("is_enable_save_resume",quiz.getIs_enable_save_resume());
		query.setParameter("created_on",quiz.getCreated_on());
		query.setParameter("created_by",quiz.getCreated_by());
		query.setParameter("modified_on",quiz.getModified_on());
		query.setParameter("modified_by",quiz.getModified_by());
		query.setParameter("modified_count",quiz.getModified_count());
		query.setParameter("category_id",quiz.getCategory().getCategoryId());
		query.setParameter("level_id",quiz.getLevel().getLevelId());
		query.setParameter("status",quiz.getStatus());
		query.executeUpdate();
		tx.commit();
		}
		catch(Exception e){
			throw new DBExceptions("Inserting the data failed", e);
		}
		finally {
			session.close();
		}
		return "Inserted SuccessfullY";
	}
}