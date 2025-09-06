package org.akee.prj25.assessment.repository;

import java.util.Optional;

import org.akee.prj25.assessment.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByName(String name);

	boolean existsByNameAndIdNot(String name, Long id);

	Page<Customer> findByNameContainingIgnoreCase(String name, Pageable pageable);

}