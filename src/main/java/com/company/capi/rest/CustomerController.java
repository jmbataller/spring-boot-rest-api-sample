package com.company.capi.rest;

import com.company.capi.domain.Customer;
import com.company.capi.domain.Points;
import com.company.capi.exception.DateFormatException;
import com.company.capi.exception.NotFoundException;
import com.company.capi.service.CustomerService;
import com.company.capi.service.CustomerServiceImpl;
import com.company.myloyal.Booking;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by jmbataller on 08/12/14.
 */
@Controller
@RequestMapping("/customer")
@Api(value = "Customer", description = "Customer API")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method= RequestMethod.GET, value = "/{id}")
    public @ResponseBody Customer getCustomer(@RequestHeader(value="API_KEY") String apiKey, @PathVariable(value="id") String id) throws NotFoundException {
        return customerService.getCustomer(id);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/{id}/profile")
    public @ResponseBody com.company.myloyal.Customer getCustomerProfile(@RequestHeader(value="API_KEY") String apiKey, @PathVariable(value="id") String id) throws NotFoundException {
        return customerService.getProfile(id);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/{id}/bookings")
    public @ResponseBody List<Booking> getBookings(@RequestHeader(value="API_KEY") String apiKey, @PathVariable("id") String id,
                                              @RequestParam(value="fromDate", required=false) String fromDate,
                                              @RequestParam(value="toDate", required=false) String toDate) throws NotFoundException {

        if(fromDate != null && toDate != null) {
            return customerService.getBookings(id, fromDate, toDate);
        }

        return customerService.getBookings(id);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/{id}/points")
    public @ResponseBody
    Points getPoints(@RequestHeader(value="API_KEY") String apiKey, @PathVariable("id") String id) throws NotFoundException {
        return customerService.getPoints(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(NotFoundException.class)
    void handleIllegalArgumentException(NotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(DateFormatException.class)
    void handleDateFormatException(DateFormatException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
