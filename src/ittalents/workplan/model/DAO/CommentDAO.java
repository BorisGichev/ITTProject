package ittalents.workplan.model.DAO;

import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.POJO.Comment;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.activation.UnsupportedDataTypeException;

public class CommentDAO extends AbstractDBConnDAO implements ICommentDAO {
	public int addComment(Comment comment) throws WorkPlanDAOException,
			DBException {
		if (comment == null) {
			throw new WorkPlanDAOException("There is no comment to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(
					"INSERT into comments values (null,default,?,?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getActivityID());
			ps.setInt(3, comment.getUserID());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(
					"The comment cannot be add right now!Try again later!");
		}

	}

	public Map<Comment, User> getAllCommentsForActivity(int activityID)
			throws DBException {
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(
							"SELECT * FROM comments where activity_id=? ORDER BY created_on DESC;");
			ps.setInt(1, activityID);
			Map<Comment, User> mapWithCommentOrderdByDate = new TreeMap<Comment, User>(
					(c1, c2) -> c2.getCreatedOn().compareTo(c1.getCreatedOn()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mapWithCommentOrderdByDate.put(
						new Comment(rs.getInt(1), rs.getTimestamp(2), rs
								.getString(3), rs.getInt(4), rs.getInt(5)),
						IUserDAO.getDAO("db").getUserById(rs.getInt(5)));
			}
			return mapWithCommentOrderdByDate;
		} catch (SQLException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(
					"Cannot get comments right now!Try again later!");
		}

	}
}
