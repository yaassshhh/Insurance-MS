package com.Yash.main;


import java.util.Scanner;

import com.Yash.dao.IPolicyService;
import com.Yash.dao.InsuranceServiceImpl;
import com.Yash.entity.Policy;

import java.util.Collection;

public class Main{

    private static IPolicyService policyService = new InsuranceServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Displaying the menu options
            System.out.println("\n===== Insurance Management System =====");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy by ID");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createPolicy(scanner);
                    break;
                case 2:
                    getPolicy(scanner);
                    break;
                case 3:
                    getAllPolicies();
                    break;
                case 4:
                    updatePolicy(scanner);
                    break;
                case 5:
                    deletePolicy(scanner);
                    break;
                case 6:
                    System.out.println("Bye Bye");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 6);

        scanner.close(); 
    }

//     create a policy
    private static void createPolicy(Scanner scanner) {
        System.out.println("\n===== Create Policy =====");

        System.out.print("Enter Policy ID: ");
        int policyId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Policy Name: ");
        String policyName = scanner.nextLine();

        System.out.print("Enter Coverage Amount: ");
        double coverageAmount = scanner.nextDouble();
        scanner.nextLine(); 
        
        System.out.print("Enter Policy Terms: ");
        String terms = scanner.nextLine();

        
        Policy newPolicy = new Policy(policyId, policyName, coverageAmount, terms);

        //  create the policy 
        try {
            if (policyService.createPolicy(newPolicy)) {
                System.out.println("Policy created successfully: " + newPolicy);
            } else {
                System.out.println("Failed to create policy. Policy ID might already exist.");
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //  get a policy by ID
    private static void getPolicy(Scanner scanner) {
        System.out.println("\n===== Get Policy by ID =====");

        System.out.print("Enter Policy ID: ");
        int policyId = scanner.nextInt();

        
        Policy policy = policyService.getPolicy(policyId);

        if (policy != null) {
            System.out.println("Policy Details: " + policy);
        } else {
            System.out.println("Policy with ID " + policyId + " not found.");
        }
    }

    // get all policies
    private static void getAllPolicies() {
        System.out.println("\n===== Get All Policies =====");

        // Fetch all policies
        Collection<Policy> policies = policyService.getAllPolicies();

        if (policies.isEmpty()) {
            System.out.println("No policies found.");
        } else {
            for (Policy policy : policies) {
                System.out.println(policy);
            }
        }
    }

    // Method to update a policy
    private static void updatePolicy(Scanner scanner) {
        System.out.println("\n===== Update Policy =====");

        System.out.print("Enter Policy ID to update: ");
        int policyId = scanner.nextInt();
        scanner.nextLine(); 

        // Fetching the existing policy
        Policy existingPolicy = policyService.getPolicy(policyId);
        if (existingPolicy == null) {
            System.out.println("Policy with ID " + policyId + " not found.");
            return;
        }

        System.out.print("Enter new Policy Name: ");
        String newPolicyName = scanner.nextLine();

        System.out.print("Enter new Coverage Amount: ");
        double newCoverageAmount = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Enter new Policy Terms: ");
        String newTerms = scanner.nextLine();

        // Updating the existing policy details
        existingPolicy.setPolicyName(newPolicyName);
        existingPolicy.setCoverageAmount(newCoverageAmount);
        existingPolicy.setTerms(newTerms);

        //  update the policy
        if (policyService.updatePolicy(existingPolicy)) {
            System.out.println("Policy updated successfully: " + existingPolicy);
        } else {
            System.out.println("Failed to update policy.");
        }
    }

    //  delete a policy by ID
    private static void deletePolicy(Scanner scanner) {
        System.out.println("\n===== Delete Policy =====");

        System.out.print("Enter Policy ID to delete: ");
        int policyId = scanner.nextInt();

        
        if (policyService.deletePolicy(policyId)) {
            System.out.println("Policy deleted successfully.");
        } else {
            System.out.println("Policy with ID " + policyId + " not found.");
        }
    }
}

