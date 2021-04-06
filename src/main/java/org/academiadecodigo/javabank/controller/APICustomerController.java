package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.command.CustomerDto;
import org.academiadecodigo.javabank.converters.CustomerDtoToCustomer;
import org.academiadecodigo.javabank.converters.CustomerToCustomerDto;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class APICustomerController {

    private CustomerService customerService;
    private CustomerDtoToCustomer customerDtoToCustomer;
    private CustomerToCustomerDto customerToCustomerDto;
    private CustomerDto customerDto;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @Autowired
    public void setCustomerToCustomerDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<CustomerDto>> getCustomerList() {

        return new ResponseEntity<>(customerToCustomerDto.convert(customerService.list()), HttpStatus.ACCEPTED);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer id) {

        Customer customer = customerService.get(id);

        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerToCustomerDto.convert(customer), HttpStatus.ACCEPTED);
    }


    @RequestMapping(method = RequestMethod.POST, value = {"", "/"})
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer newCustomer = customerService.save(customerDtoToCustomer.convert(customerDto));

        return new ResponseEntity<>(newCustomer, HttpStatus.ACCEPTED);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<CustomerDto> editCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {

        Customer editCustomer = customerService.get(id);

        if(editCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        editCustomer.setFirstName(customerDto.getFirstName());
        editCustomer.setLastName(customerDto.getLastName());
        editCustomer.setEmail(customerDto.getEmail());
        editCustomer.setPhone(customerDto.getPhone());

        Customer savedCustomer = customerService.save(editCustomer);

        return new ResponseEntity<>(customerToCustomerDto.convert(savedCustomer), HttpStatus.ACCEPTED);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {

        Customer deletedCustomer = customerService.get(id);

        if(deletedCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerService.delete(id);

        return new ResponseEntity<>("Customer deleted", HttpStatus.ACCEPTED);
    }

}
