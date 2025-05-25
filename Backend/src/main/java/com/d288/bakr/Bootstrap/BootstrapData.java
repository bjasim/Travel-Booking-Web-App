package com.d288.bakr.Bootstrap;

import com.d288.bakr.dao.CustomerRepository;
import com.d288.bakr.dao.DivisionRepository;
import com.d288.bakr.entities.Customer;
import com.d288.bakr.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootstrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
        System.out.println("BootstrapData constructor called");
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("==========================================");
            System.out.println("BootstrapData.run() method started");

            // Check if sample customers already exist by looking for specific names
            boolean johnExists = customerRepository.findAll().stream()
                    .anyMatch(c -> "Norbert".equalsIgnoreCase(c.getFirstName()) && "Alex".equalsIgnoreCase(c.getLastName()));
            boolean janeExists = customerRepository.findAll().stream()
                    .anyMatch(c -> "Jane".equalsIgnoreCase(c.getFirstName()) && "Smith".equalsIgnoreCase(c.getLastName()));
            boolean robertExists = customerRepository.findAll().stream()
                    .anyMatch(c -> "Robert".equalsIgnoreCase(c.getFirstName()) && "Johnson".equalsIgnoreCase(c.getLastName()));
            boolean emilyExists = customerRepository.findAll().stream()
                    .anyMatch(c -> "Emily".equalsIgnoreCase(c.getFirstName()) && "Williams".equalsIgnoreCase(c.getLastName()));
            boolean michaelExists = customerRepository.findAll().stream()
                    .anyMatch(c -> "Michael".equalsIgnoreCase(c.getFirstName()) && "Brown".equalsIgnoreCase(c.getLastName()));

            System.out.println("Sample customer Norbert Alex exists: " + johnExists);
            System.out.println("Sample customer Jane Smith exists: " + janeExists);
            System.out.println("Sample customer Robert Johnson exists: " + robertExists);
            System.out.println("Sample customer Emily Williams exists: " + emilyExists);
            System.out.println("Sample customer Michael Brown exists: " + michaelExists);

            // Check if any sample customers are missing
            boolean anySampleCustomerMissing = !johnExists || !janeExists || !robertExists || !emilyExists || !michaelExists;

            if (anySampleCustomerMissing) {
                System.out.println("Some sample customers are missing, will add them now");

                // Get all divisions to see what's available
                List<Division> allDivisions = divisionRepository.findAll();
                System.out.println("Found " + allDivisions.size() + " divisions in database");

                if (allDivisions.isEmpty()) {
                    System.out.println("ERROR: No divisions found in database. Cannot create sample customers.");
                    return;
                }

                // Print all divisions for debugging
                for (Division div : allDivisions) {
                    System.out.println("Division ID: " + div.getId() + ", Name: " + div.getDivision_name());
                }

                // Get specific divisions
                Division division1 = divisionRepository.findById(1L).orElse(null);
                Division division2 = divisionRepository.findById(2L).orElse(null);
                Division division3 = divisionRepository.findById(3L).orElse(null);

                System.out.println("Division 1 exists: " + (division1 != null));
                System.out.println("Division 2 exists: " + (division2 != null));
                System.out.println("Division 3 exists: " + (division3 != null));

                if (division1 == null || division2 == null || division3 == null) {
                    System.out.println("WARNING: Required divisions not found. Will use available divisions instead.");

                    // Use available divisions instead
                    division1 = allDivisions.get(0);
                    division2 = allDivisions.size() > 1 ? allDivisions.get(1) : division1;
                    division3 = allDivisions.size() > 2 ? allDivisions.get(2) : division1;

                    System.out.println("Using divisions: " +
                            "ID: " + division1.getId() + ", " +
                            "ID: " + division2.getId() + ", " +
                            "ID: " + division3.getId());
                }

                // Add each missing sample customer individually
                if (!johnExists) {
                    try {
                        System.out.println("Creating customer: Norbert Alex");
                        Customer john = new Customer();
                        john.setFirstName("Norbert");
                        john.setLastName("Alex");
                        john.setAddress("123 Main St");
                        john.setPostal_code("12345");
                        john.setPhone("555-123-4567");
                        john.setDivision(division1);

                        customerRepository.save(john);
                        System.out.println("Norbert Alex saved successfully");
                    } catch (Exception e) {
                        System.out.println("ERROR saving Norbert Alex: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                if (!janeExists) {
                    try {
                        System.out.println("Creating customer: Jane Smith");
                        Customer jane = new Customer();
                        jane.setFirstName("Jane");
                        jane.setLastName("Smith");
                        jane.setAddress("456 Oak Ave");
                        jane.setPostal_code("67890");
                        jane.setPhone("555-234-5678");
                        jane.setDivision(division1);

                        customerRepository.save(jane);
                        customerRepository.save(jane);
                        System.out.println("Jane Smith saved successfully");
                    } catch (Exception e) {
                        System.out.println("ERROR saving Jane Smith: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                if (!robertExists) {
                    try {
                        System.out.println("Creating customer: Robert Johnson");
                        Customer robert = new Customer();
                        robert.setFirstName("Robert");
                        robert.setLastName("Johnson");
                        robert.setAddress("789 Pine Blvd");
                        robert.setPostal_code("34567");
                        robert.setPhone("555-345-6789");
                        robert.setDivision(division2);

                        customerRepository.save(robert);
                        System.out.println("Robert Johnson saved successfully");
                    } catch (Exception e) {
                        System.out.println("ERROR saving Robert Johnson: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                if (!emilyExists) {
                    try {
                        System.out.println("Creating customer: Emily Williams");
                        Customer emily = new Customer();
                        emily.setFirstName("Emily");
                        emily.setLastName("Williams");
                        emily.setAddress("321 Cedar Ln");
                        emily.setPostal_code("89012");
                        emily.setPhone("555-456-7890");
                        emily.setDivision(division2);

                        customerRepository.save(emily);
                        System.out.println("Emily Williams saved successfully");
                    } catch (Exception e) {
                        System.out.println("ERROR saving Emily Williams: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                if (!michaelExists) {
                    try {
                        System.out.println("Creating customer: Michael Brown");
                        Customer michael = new Customer();
                        michael.setFirstName("Michael");
                        michael.setLastName("Brown");
                        michael.setAddress("654 Maple Dr");
                        michael.setPostal_code("45678");
                        michael.setPhone("555-567-8901");
                        michael.setDivision(division3);

                        customerRepository.save(michael);
                        System.out.println("Michael Brown saved successfully");
                    } catch (Exception e) {
                        System.out.println("ERROR saving Michael Brown: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                System.out.println("Final customer count: " + customerRepository.count());
            } else {
                System.out.println("All sample customers already exist, skipping sample data loading.");
            }
            System.out.println("BootstrapData.run() method completed");
            System.out.println("==========================================");
        } catch (Exception e) {
            System.out.println("CRITICAL ERROR in BootstrapData.run(): " + e.getMessage());
            e.printStackTrace();
        }
    }
}
