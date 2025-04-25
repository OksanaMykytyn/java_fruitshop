package ua.edunung.se.dao;

import ua.edunung.se.entity.Fruit;
import ua.edunung.se.dao.BaseDAO;
import ua.edunung.se.config.HibernateUtil;

public class FruitDAO extends BaseDAO<Fruit> {
    public FruitDAO() {
        super(Fruit.class);
    }
}
