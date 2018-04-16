package com.stalary.personfilter.repo.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author Stalary
 * @description
 * @date 2018/04/14
 */
@NoRepositoryBean
public interface BaseRepo<T, Long extends Serializable> extends JpaRepository<T, Long> {

}