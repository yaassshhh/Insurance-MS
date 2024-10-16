package com.Yash.dao;



import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import com.Yash.entity.Policy;
import com.Yash.util.DatabaseConnection;

public class InsuranceServiceImpl implements IPolicyService {

    // SQL Queries 
    private static final String INSERT_POLICY_SQL = "INSERT INTO Policy (policyId, policyName, coverageAmount, terms) VALUES (?, ?, ?, ?)";
    private static final String SELECT_POLICY_BY_ID = "SELECT * FROM Policy WHERE policyId = ?";
    private static final String SELECT_ALL_POLICIES = "SELECT * FROM Policy";
    private static final String UPDATE_POLICY_SQL = "UPDATE Policy SET policyName = ?, coverageAmount = ?, terms = ? WHERE policyId = ?";
    private static final String DELETE_POLICY_SQL = "DELETE FROM Policy WHERE policyId = ?";

    // Create a new Policy in the database
    @Override
    public boolean createPolicy(Policy policy) {
        
        if (policy == null) {
            return false;  
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POLICY_SQL)) {

            preparedStatement.setInt(1, policy.getPolicyId());
            preparedStatement.setString(2, policy.getPolicyName());
            preparedStatement.setDouble(3, policy.getCoverageAmount());
            preparedStatement.setString(4, policy.getTerms());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;  

        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return false; 
    }

    // Retrieve a Policy by ID
    @Override
    public Policy getPolicy(int policyId) {
        Policy policy = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POLICY_BY_ID)) {

            preparedStatement.setInt(1, policyId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String policyName = resultSet.getString("policyName");
                double coverageAmount = resultSet.getDouble("coverageAmount");
                String terms = resultSet.getString("terms");

                policy = new Policy(policyId, policyName, coverageAmount, terms);
            }

        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return policy;
    }

    // Retrieve all Policies
    @Override
    public Collection<Policy> getAllPolicies() {
        Collection<Policy> policies = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_POLICIES);

            while (resultSet.next()) {
                int policyId = resultSet.getInt("policyId");
                String policyName = resultSet.getString("policyName");
                double coverageAmount = resultSet.getDouble("coverageAmount");
                String terms = resultSet.getString("terms");

                policies.add(new Policy(policyId, policyName, coverageAmount, terms));
            }

        } catch (SQLException e) {
            e.printStackTrace();          }
        return policies;
    }

    // Update an existing Policy
    @Override
    public boolean updatePolicy(Policy policy) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POLICY_SQL)) {

            preparedStatement.setString(1, policy.getPolicyName());
            preparedStatement.setDouble(2, policy.getCoverageAmount());
            preparedStatement.setString(3, policy.getTerms());
            preparedStatement.setInt(4, policy.getPolicyId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; 

        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return false;  
    }

    // Delete a Policy by ID
    @Override
    public boolean deletePolicy(int policyId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POLICY_SQL)) {

            preparedStatement.setInt(1, policyId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;  

        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return false;  
    }
}
