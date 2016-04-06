package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.ActivityStatus;
import ittalents.workplan.model.DAO.IActivityDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Servlet implementation class ChartServlet
 */
@WebServlet("/ChartServlet")
public class IssueStatusPieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IssueStatusPieChartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Activity> listWithAllActivitiesWithStatusDone;
		List<Activity> listWithAllActivitiesWithStatusInProgress;
		List<Activity> listWithAllActivitiesWithStatusToDo;
		try {
			listWithAllActivitiesWithStatusDone = IActivityDAO.getDAO("db")
					.getAllActivitiesWithStatusInWholeProject(
							ActivityStatus.Done,
							(Integer) request.getSession().getAttribute(
									"projectID"));
			listWithAllActivitiesWithStatusInProgress = IActivityDAO.getDAO(
					"db").getAllActivitiesWithStatusInWholeProject(
					ActivityStatus.InProgress,
					(Integer) request.getSession().getAttribute("projectID"));
			listWithAllActivitiesWithStatusToDo = IActivityDAO.getDAO("db")
					.getAllActivitiesWithStatusInWholeProject(
							ActivityStatus.ToDo,
							(Integer) request.getSession().getAttribute(
									"projectID"));

			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("ToDo", listWithAllActivitiesWithStatusToDo.size());
			dataset.setValue("InProgress",
					listWithAllActivitiesWithStatusInProgress.size());
			dataset.setValue("Done", listWithAllActivitiesWithStatusDone.size());
			List<Comparable> keys = dataset.getKeys();
			for (Comparable a : keys) {
				System.out.println(dataset.getValue(a));
				if (dataset.getValue(a) == null
						|| (double) dataset.getValue(a) == 0) {
					dataset.remove(a);
				}
			}
			JFreeChart chart = ChartFactory.createPieChart("Issues' statuses", // chart
					dataset, // data
					true, // include legend
					true, false);
			int width = 640; /* Width of the image */
			int height = 480; /* Height of the image */
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
					"{0} - count:{1} -{2}"));
			File statusChart = new File("D:\\images\\Statusess.jpeg");
			ChartUtilities.saveChartAsJPEG(statusChart, chart, width, height);

		} catch (DBException | WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
