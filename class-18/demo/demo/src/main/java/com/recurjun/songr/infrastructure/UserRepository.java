package com.recurjun.songr.infrastructure;

import com.recurjun.songr.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT name FROM User WHERE name =:name", nativeQuery = true)
    User findUserByName(@Param("name") String name);


}
