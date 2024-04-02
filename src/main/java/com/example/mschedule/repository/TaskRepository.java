package com.example.mschedule.repository;

import com.example.mschedule.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//    @Query(nativeQuery = true,
//    value = "select count(*) from task where users_id = ?1;")
//    long getTaskForUser(Long id);

    long countTaskByUser_Id(Integer id);

}