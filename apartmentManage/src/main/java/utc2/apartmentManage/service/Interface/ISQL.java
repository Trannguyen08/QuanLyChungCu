package main.java.utc2.apartmentManage.service.Interface;

public interface ISQL<T> {
    public boolean add(T object);
    public boolean update(T object);
    public boolean delete(int id);
    public int getNewID();
    public boolean isExist(int id);
    public boolean isDuplicate(T object);
    public T getObject(int id);
}
