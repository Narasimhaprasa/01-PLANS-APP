package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Plan;
import in.ashokit.entity.PlanCategory;
import in.ashokit.repo.PlanCategoryRepo;
import in.ashokit.repo.PlanRepo;

@Service
public class PlanServiceImpl implements IPlanService {
	@Autowired
	private PlanCategoryRepo plancategoryRepo;
	@Autowired
	private PlanRepo planRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> findAll = plancategoryRepo.findAll();
		Map<Integer, String> planCategories = new HashMap<Integer, String>();

		findAll.forEach(category -> {
			planCategories.put(category.getCategoryId(), category.getCategoryName());
		});
		return planCategories;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepo.save(plan);
		/* if(saved!=null) {
			 return true;
		 }else {
			 return false;
		 }*/
		return (saved != null) ? true : false;
	}

	@Override
	public List<Plan> getAllPlans() {
		List<Plan> findAll = planRepo.findAll();
		return findAll;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		List<Plan> findAll = planRepo.findAll();
		if (findAll != null) {
			Plan save = planRepo.save(plan);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			planRepo.deleteById(planId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			Plan plan = findById.get();
            plan.setActiveSwitch(status);
            planRepo.save(plan);
			return true;
		} else {
			return false;
		}
	}

}
