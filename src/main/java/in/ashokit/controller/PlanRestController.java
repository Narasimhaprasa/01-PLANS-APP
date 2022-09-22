package in.ashokit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.Constants.AppConstants;
import in.ashokit.entity.Plan;
import in.ashokit.props.AppProperties;
import in.ashokit.service.IPlanService;

@RestController
public class PlanRestController {

	private IPlanService planService;

	private Map<String, String> messages;

	public PlanRestController(IPlanService planService, AppProperties props) {
		this.planService = planService;
		this.messages = props.getMessages();
		System.out.println(this.messages);
	}

	@GetMapping("/allCategories")
	public ResponseEntity<Map<Integer, String>> getAllPlanCategories() {
		Map<Integer, String> planCategories = planService.getPlanCategories();
		return new ResponseEntity<Map<Integer, String>>(planCategories, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<String> savePlans(@RequestBody Plan plan) {
		String msg = AppConstants.EMPTY_STR;

		boolean isSaved = planService.savePlan(plan);

		if (isSaved) {

			msg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		System.out.println(msg);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@GetMapping("/allPlans")
	public ResponseEntity<List<Plan>> getAllPlans() {
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<List<Plan>>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/getPlan/{planId}")
	public ResponseEntity<Plan> getPlanById(@PathVariable Integer planId) {
		Plan planById = planService.getPlanById(planId);
		return new ResponseEntity<Plan>(planById, HttpStatus.OK);
	}

	@PutMapping("/updatePlan/{plan}")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {

		boolean isSaved = planService.updatePlan(plan);

		String msg = AppConstants.EMPTY_STR;
		if (isSaved)
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		else
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@DeleteMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deletePlanById(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);
		String msg = AppConstants.EMPTY_STR;

		if (isDeleted) {
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/statusChange/{planId}/{status}")
	public ResponseEntity<String> planStatusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean isChanged = planService.planStatusChange(planId, status);
		String msg = AppConstants.EMPTY_STR;

		if (isChanged) {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
		} else {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	 
}
