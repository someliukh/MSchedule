package com.example.mschedule.repository;

import com.example.mschedule.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    long countTaskByUser_Id(Integer id);

    @Query(nativeQuery = true,
            value = "select t.id from task t where t.users_id = :id")
    List<Integer> getUserTasksById(Integer id);
}