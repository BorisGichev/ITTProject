package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.ActivityStatus;
import ittalents.workplan.model.DAO.IActivityDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class IssueTypePieChartServlet
 */
@WebServlet("/IssueTypePieChartServlet")
public class IssueTypePieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IssueTypePieChartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> typeOfIssues = Arrays.asList("Bug", "Task", "Sub-task",
				"Story", "Epic");
		Map<String, ArrayList<Activity>> issuesByType = new HashMap<String, ArrayList<Activity>>();
		try {
			for (String type : typeOfIssues) {
				if (!issuesByType.containsKey(type)) {
					issuesByType.put(type, new ArrayList<Activity>());
				}

				issuesByType.get(type).addAll(
						IActivityDAO.getDAO("db")
								.getAllActivitiesWithTypeInWholeProject(
										type,
										(Integer) request.getSession()
												.getAttribute("projectID")));

			}

			DefaultPieDataset dataset = new DefaultPieDataset();
			for (String type : issuesByType.keySet()) {
				dataset.setValue(type, issuesByType.get(type).size());
			}
			List<Comparable> keys = dataset.getKeys();
			for (Comparable a : keys) {
				System.out.println(dataset.getValue(a));
				if (dataset.getValue(a) == null
						|| (double) dataset.getValue(a) == 0) {
					dataset.remove(a);
				}
			}
			JFreeChart chart = ChartFactory.createPieChart("Issues' types", // chart
					dataset, // data
					true, // include legend
					true, false);
			int width = 640; /* Width of the image */
			int height = 480; /* Height of the image */
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
					"{0} - count:{1} -{2}"));
			File statusChart = new File("D:\\images\\Types.jpeg");
			ChartUtilities.saveChartAsJPEG(statusChart, chart, width, height);
		} catch (DBException | WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
