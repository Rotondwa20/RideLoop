package za.co.rideloop.Service;

/**
 * IService.java
 * IService model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
public interface IService <T , ID>{
    T create(T t);
    T read(ID id);
    T update(T t);
    void delete(ID id);
}
