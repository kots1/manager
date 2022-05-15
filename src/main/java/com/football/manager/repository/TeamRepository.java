package com.football.manager.repository;

import com.football.manager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface TeamRepository extends PagingAndSortingRepository<Team,Integer> {

    boolean existsByName(@NotBlank(message = "Name should not be empty") String name);

}
