package com.maitri.customer_springbatch.repository;

import com.maitri.customer_springbatch.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
}