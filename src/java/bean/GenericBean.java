package bean;

import dao.GenericDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named(value = "genericBean")
@SessionScoped
public class GenericBean <T> implements Serializable {
    private T entity;
    private GenericDAO<T> dao;
    private List<T> list;
    private Class<T> entityClass;

    public GenericBean(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.dao = new GenericDAO<>(entityClass);
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public GenericDAO<T> getDao() {
        return dao;
    }

    public void setDao(GenericDAO<T> dao) {
        this.dao = dao;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void create(String tableName, String... columns) {
        this.getDao().create(entity, tableName, columns);
        this.entity = null;
    }

    public void update(String tableName, String... columns) throws SQLException {
        this.getDao().update(entity, tableName, columns);
        this.entity = null;
    }

    public void delete(String tableName, int id) {
        this.getDao().delete(entity, tableName, id);
    }

    public void loadList(String tableName, int page, int pageSize) {
        this.list = this.getDao().getList(tableName, page, pageSize);
    }
}
