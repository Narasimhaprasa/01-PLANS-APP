package in.ashokit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Plan;
import in.ashokit.service.PlanServiceImpl;

@RestController
public class PlanRestController {
	@Autowired
	private PlanServiceImpl planService;

	@GetMapping("/allCategories")
	public ResponseEntity<Map<Integer, String>> getAllPlanCategories() {
		Map<Integer, String> planCategories = planService.getPlanCategories();
		return new ResponseEntity<Map<Integer, String>>(planCategories, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<String> savePlans(@RequestBody Plan plan) {
		String msg = "";
		boolean isSaved = planService.savePlan(plan);
		if (isSaved) {
			msg = "Plan is saved";
		} else {
			msg = "Plan is not saved";
		}
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
		String msg = "";
		if (isSaved)
			msg = "plan is updated";
		else
			msg = "Plan is not updated";
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@DeleteMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deletePlanById(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);
		String msg = "";
		if (isDeleted) {
			msg = "Plan is deleted";
		} else {
			msg = "Plan is not deleted";
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/statusChange/{planId}/{status}")
	public ResponseEntity<String> planStatusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean isChanged = planService.planStatusChange(planId, status);
		String msg = "";
		if (isChanged) {
			msg = "plan status is changed";
		} else {
			msg = "Plan status is not changed";
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
