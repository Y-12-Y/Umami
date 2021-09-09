package com.app.umami.controller;

import com.app.umami.entity.Meal;
import com.app.umami.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class MealController {

    private final MealService mealService;

    @PostMapping("/meal")
    public Meal addMeal(@RequestBody Meal meal) {
        return mealService.addMeal(meal);
    }

    @PutMapping("/meal/{id}")
    public Meal updateMeal(@PathVariable String id, @RequestBody Meal meal) {
        return mealService.updateMeal(id, meal);
    }

    @GetMapping("/meal/{id}")
    public Meal getMeal(@PathVariable String id) {
        return mealService.getMeal(id) ;
    }

    @GetMapping("/meal")
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    @DeleteMapping("/meal/{id}")
    public void deleteMeal(@PathVariable String id) {
        mealService.deleteMeal(id);
    }
}
