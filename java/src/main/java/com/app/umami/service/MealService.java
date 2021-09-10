package com.app.umami.service;

import com.app.umami.entity.Meal;
import com.app.umami.exception.InternalServerException;
import com.app.umami.exception.ResourceNotFoundException;
import com.app.umami.repository.MealRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

    public List<Meal> getAllMeals(String userId, String type, String tags, String city, String country) {
        List<Meal> meals = null;

        try {
            if (StringUtils.isNotBlank(userId)) {
                meals = mealRepository.findMealsByUserId(userId);
            } else if (StringUtils.isNotBlank(type)) {
                meals = mealRepository.findMealsByType(type);
            } else if (StringUtils.isNotBlank(tags)) {
                meals = mealRepository.findMealsByTags(tags);
            }else if (StringUtils.isNotBlank(city)) {
                meals = mealRepository.findMealsByCity(city);
            }else if (StringUtils.isNotBlank(country)) {
                meals = mealRepository.findMealsByCountry(country);
            }
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
