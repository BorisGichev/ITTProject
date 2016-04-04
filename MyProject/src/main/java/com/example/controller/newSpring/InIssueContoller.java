package com.example.controller.newSpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InIssueContoller {

	@RequestMapping(value = "/IssueComments", method = RequestMethod.GET)
	public String comments(Model model) {

		return "IssueCommentsPage";
	}
	
	@RequestMapping(value = "/IssueComments", method = RequestMethod.POST)
	public String commPost(Model model) {

		return "IssueCommentsPage";
	}

	@RequestMapping(value = "/IssueAll", method = RequestMethod.GET)
	public String all(Model model) {

		return "IssueAllPage";
	}

	@RequestMapping(value = "/IssueWorkLog", method = RequestMethod.GET)
	public String worklog(Model model) {

		return "IssueWorkLogPage";
	}

	@RequestMapping(value = "/IssueHistory", method = RequestMethod.GET)
	public String issueHistory(Model model) {

		return "IssueHistoryPage";
	}

	@RequestMapping(value = "/IssueActivity", method = RequestMethod.GET)
	public String IssueActivity(Model model) {

		return "IssueActivityPage";
	}

}
