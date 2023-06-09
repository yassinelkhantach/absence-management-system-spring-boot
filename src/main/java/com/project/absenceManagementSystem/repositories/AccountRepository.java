package com.project.absenceManagementSystem.repositories;

import java.util.List;
import java.util.Optional;

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
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deletedAt IS NULL")
    List<Account> findAll();
	
	@Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deletedAt is null and e.id = ?1")
    Optional<Account> findById(Long id);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.deletedAt is null")
    List<Account> findAllById(Iterable<Long> ids);


    //Look up deleted entities
    @Query("select e from #{#entityName} e where e.deletedAt is not null")
    @Transactional(readOnly = true)
    List<User> findAllInactive();
    
    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.deletedAt is null")
    Account getOne(Long id);

    
    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.deletedAt is null")
    long count();

    @Override
    @Transactional(readOnly = true)
    default boolean existsById(Long id) {
        return getOne(id) != null;
    }
    
    
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
