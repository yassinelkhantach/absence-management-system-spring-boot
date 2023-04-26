package com.project.absenceManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.absenceManagementSystem.entities.Account;
import com.project.absenceManagementSystem.entities.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Override
    @Transactional
    @Modifying
    @Query("UPDATE #{#entityName} u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id")
    void deleteById(@Param("id") Long id);

    @Override
    @Transactional
    default void delete(Account entity) {
        deleteById(entity.getId());
    }

    @Transactional
    default void delete(Iterable<? extends User> entities) {
        entities.forEach(entity -> deleteById(entity.getId()));
    }

    @Override
    @Query("update #{#entityName} e set e.deletedAt =  CURRENT_TIMESTAMP")
    @Transactional
    @Modifying
    void deleteAll();
   
   
}
