package ittalents.workplan.model.DAO;

import java.util.List;
import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

import ittalents.workplan.model.POJO.Comment;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

public interface ICommentDAO {

	public static CommentDAO getDAO(String storage)
			throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new CommentDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public int addComment(Comment comment) throws WorkPlanDAOException,
			DBException;

	Map<Comment, User> getAllCommentsForActivity(int activityID) throws DBException;
}
