package com.app.umami.service;

import com.app.umami.entity.Meal;
import com.app.umami.exception.InternalServerException;
import com.app.umami.exception.ResourceNotFoundException;
import com.app.umami.repository.MealRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    public Meal addMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal updateMeal(String id, Meal meal) {
        Meal oldMeal = mealRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal not found"));

        oldMeal = meal;
        return mealRepository.save(oldMeal);
    }

    public Meal getMeal(String id) {
        return mealRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal not found"));
    }

    public List<Meal> getAllMeals() {
        List<Meal> meals = null;

        try {
            meals = mealRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException("Error occurred while getting all meals");
        }

        return meals;
    }

    public void deleteMeal(String id) {
        Meal meal = mealRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal not found"));
        mealRepository.delete(meal);
    }

}
