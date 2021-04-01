package org.academiadecodigo.javabank.dto;


import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    private Customer customer;
    private CustomerService customerService;

    public Customer customerDtoToCustomer(CustomerDto customerDto) {


        if (customerDto.getId() != null) {
            customer = customerService.get(customerDto.getId());
        }

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());

        return customer;
    }

    public CustomerDto customerToCustomerDto(Customer customer) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());

        return customerDto;
    }

    @Autowired
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
