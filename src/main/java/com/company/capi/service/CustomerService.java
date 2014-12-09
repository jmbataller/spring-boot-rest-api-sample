package com.company.capi.service;

import com.company.capi.domain.Customer;
import com.company.capi.domain.Points;
import com.company.capi.exception.NotFoundException;
import com.company.myloyal.Booking;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;

/**
 * Created by jmbataller on 08/12/14.
 */
@Service
public interface CustomerService {

    Customer getCustomer(String id) throws NotFoundException;
    com.company.myloyal.Customer getProfile(String id) throws NotFoundException;
    List<Booking> getBookings(String id) throws NotFoundException;
    List<Booking> getBookings(String id, String fromDate, String toDate) throws NotFoundException;
    Points getPoints(String id) throws NotFoundException;
}
