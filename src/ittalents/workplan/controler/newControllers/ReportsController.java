package com.example.controller.newSpring;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.DAO.IActivityDAO;
import com.example.model.POJO.Activity;
import com.example.model.POJO.ActivityStatus;
import com.example.model.POJO.Project;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

@Controller
@SessionAttributes({ "project" })
public class ReportsController {

	@RequestMapping(value = "/AllReports", method = RequestMethod.GET)
	public String allReports(Project project) {
		typeReport(project);
		statusReport(project);
		return "reports";
	}

	@RequestMapping(value = "/ReportsType", method = RequestMethod.GET)
	public void typeReport(Project project) {
		List<String> typeOfIssues = Arrays.asList("Bug", "Task", "Sub-task",
				"Story", "Epic");
		System.out.println(project);
		Map<String, ArrayList<Activity>> issuesByType = new HashMap<String, ArrayList<Activity>>();
		try {
			for (String type : typeOfIssues) {
				if (!issuesByType.containsKey(type)) {
					issuesByType.put(type, new ArrayList<Activity>());
				}

				issuesByType.get(type).addAll(
						IActivityDAO.getDAO("db")
								.getAllActivitiesWithTypeInWholeProject(type,
										project.getId()));

			}

			DefaultPieDataset dataset = new DefaultPieDataset();
			for (String type : issuesByType.keySet()) {
				dataset.setValue(type, issuesByType.get(type).size());
			}
			List<Comparable> keys = dataset.getKeys();
			for (Comparable a : keys) {
				// System.out.println(dataset.getValue(a));
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
			File typeChart = new File("C:\\FPfiles\\Images\\Reports\\type.jpeg");
			ChartUtilities.saveChartAsJPEG(typeChart, chart, width, height);
		} catch (DBException | WorkPlanDAOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/ReportsStatus", method = RequestMethod.GET)
	public void statusReport(Project project) {
		List<Activity> listWithAllActivitiesWithStatusDone;
		List<Activity> listWithAllActivitiesWithStatusInProgress;
		List<Activity> listWithAllActivitiesWithStatusToDo;
		try {
			listWithAllActivitiesWithStatusDone = IActivityDAO.getDAO("db")
					.getAllActivitiesWithStatusInProject(ActivityStatus.Done,
							(Integer) project.getId());
			listWithAllActivitiesWithStatusInProgress = IActivityDAO.getDAO(
					"db").getAllActivitiesWithStatusInProject(
					ActivityStatus.InProgress, project.getId());
			listWithAllActivitiesWithStatusToDo = IActivityDAO.getDAO("db")
					.getAllActivitiesWithStatusInProject(ActivityStatus.ToDo,
							project.getId());

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
			File statusChart = new File(
					"C:\\FPfiles\\Images\\Reports\\status.jpeg");
			ChartUtilities.saveChartAsJPEG(statusChart, chart, width, height);

		} catch (DBException | WorkPlanDAOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/ReportsDaysToDone", method = RequestMethod.GET)
	public void DaysToDoneReport(Project project) {
		
		
	}
}
