package za.co.rideloop.Service.Imp;

import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Service.IService;

import java.util.List;


public interface ICustomerRewardsService extends IService<CustomerRewards, Long> {

    void delete(Long id);

    List<CustomerRewards> getAll();
}
