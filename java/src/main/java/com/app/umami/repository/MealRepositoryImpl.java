package com.app.umami.repository;

import com.app.umami.entity.Meal;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MealRepositoryImpl implements MealRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Meal> findMealsByTags(String tags) {
        Query query = new Query();
        query.addCriteria(Criteria.where("tags").in(tags));
        return mongoTemplate.find(query, Meal.class);
    }

    @Override
    public List<Meal> findMealsByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("hostedBy._id").is(userId));
        return mongoTemplate.find(query, Meal.class);
    }

    @Override
    public List<Meal> findMealsByType(String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cuisineType").is(type));
        return mongoTemplate.find(query, Meal.class);
    }

    @Override
    public List<Meal> findMealsByCity(String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("location.city").is(city));
        return mongoTemplate.find(query, Meal.class);
    }

    @Override
    public List<Meal> findMealsByCountry(String country) {
        Query query = new Query();
        query.addCriteria(Criteria.where("location.country").is(country));
        return mongoTemplate.find(query, Meal.class);
    }
}
