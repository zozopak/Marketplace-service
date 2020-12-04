package com.mapsa.marketplace.repository;

import com.mapsa.marketplace.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
