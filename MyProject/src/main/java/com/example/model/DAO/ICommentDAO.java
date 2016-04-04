package com.example.model.DAO;

import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

import com.example.model.POJO.Comment;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

public interface ICommentDAO {

	public static CommentDAO getDAO(String storage) throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new CommentDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public int addComment(Comment comment) throws WorkPlanDAOException, DBException;

	Map<Comment, User> getAllCommentsForActivity(int activityID) throws DBException;
}
