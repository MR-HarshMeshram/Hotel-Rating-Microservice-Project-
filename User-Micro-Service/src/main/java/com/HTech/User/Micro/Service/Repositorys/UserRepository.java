package com.HTech.User.Micro.Service.Repositorys;

import com.HTech.User.Micro.Service.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
}
