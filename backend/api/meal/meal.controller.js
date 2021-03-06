const logger = require('../../services/logger.service');
const mealsService = require('./meal.service');

async function getMeals(req, res) {
  try {
    console.log(req.query)
    const meals = await mealsService.query(req.query);
    res.send(meals);
  } catch (err) {
    logger.error('Meal.Controller/getMeals - returned an error:', err);
    res.status(500).send({ error: 'Meal.Controller/getMeals - returned an error:', err });
  }
}
async function getById(req, res) {
  try {
    const meal = await mealsService.getById(req.params.id);
    res.send(meal);
  } catch (err) {
    logger.error('Cannot get meal by id', err);
    res.status(500).send({ error: 'cannot get meal by id ' });
  }
}
async function deleteMeal(req, res) {
  try {
    await mealsService.remove(req.params.id);
    res.end();
  } catch (error) {
    logger.error('Cannot cannot delete meal', err);
    res.status(500).send({ error: 'cannot delete meal' });
  }
}

async function addMeal(req, res) {
  try {
    var meal = req.body;
    meal = await mealsService.add(meal);
    res.send(meal);
  } catch (error) {
    logger.error('Cannot cannot add meal', err);
    res.status(500).send({ error: 'cannot add meal' });
  }
}

async function editMeal(req, res) {
  try {
    const meal = req.body;
    const updatedMeal = await mealsService.edit(meal);
    res.send(updatedMeal);
  } catch (error) {
    logger.error('Cannot cannot edit meal', error);
    res.status(500).send({ error: 'cannot edit meal' });
  }
}

module.exports = {
  getMeals,
  deleteMeal,
  addMeal,
  getById,
  editMeal,
};
