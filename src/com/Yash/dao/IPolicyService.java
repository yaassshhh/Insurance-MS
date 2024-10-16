package com.Yash.dao;

import java.util.Collection;

import com.Yash.entity.Policy;

public interface IPolicyService {
    
	
    default boolean createPolicy(Policy policy) {
    	return false;
    };

    Policy getPolicy(int policyId);

    Collection<Policy> getAllPolicies();

    boolean updatePolicy(Policy policy);

    boolean deletePolicy(int policyId);
}

