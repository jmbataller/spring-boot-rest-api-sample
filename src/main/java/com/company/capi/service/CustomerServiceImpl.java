package com.company.capi.service;

import com.company.capi.domain.Customer;
import com.company.capi.domain.Points;
import com.company.capi.exception.NotFoundException;
import com.company.myloyal.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

/**
 * Created by jmbataller on 08/12/14.
 */
public class CustomerServiceImpl extends WebServiceGatewaySupport implements CustomerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final static String NAMESPACE = "http://company.com/myloyal";

    @Value("${error.date.format.code}")
    private String DATE_FORMAT_ERROR_CODE;
    @Value("${error.date.format}")
    private String DATE_FORMAT_ERR_MSG;
    @Value("${error.customer.notfound.code}")
    private String CUSTOMER_NOT_FOUND_ERR_CODE;
    @Value("${error.customer.notfound}")
    private String CUSTOMER_NOT_FOUND_ERR_MSG;

    /**
     * Get Customer information (profile + bookings + loyalty points)
     * @param id
     * @return
     * @throws NotFoundException
     */
    public Customer getCustomer(String id) throws NotFoundException {
        Customer customer = new Customer();
        customer.setProfile(getProfile(id));
        customer.setBookings(getBookings(id));
        customer.setLoyaltyPoints(getPoints(id));
        return customer;
    }

    /**
     * Get customer profile
     * @param id
     * @return
     * @throws NotFoundException
     */
    public com.company.myloyal.Customer getProfile(String id) throws NotFoundException {
        GetCustomerProfileData request = new GetCustomerProfileData();
        request.setCin(id);

        GetCustomerProfileDataResponse response = (GetCustomerProfileDataResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(NAMESPACE));

        if(response.getError() != null) {
            throw new NotFoundException(String.format(CUSTOMER_NOT_FOUND_ERR_MSG, id));
        }

        return response.getCustomerProfile();
    }

    /**
     * Get list of booking for customerId
     * If no bookings, returns a empty list
     * @param id
     * @return
     * @throws NotFoundException
     */
    public List<Booking> getBookings(String id) throws NotFoundException {

        RetrieveCustomerBookings request = new RetrieveCustomerBookings();
        request.setCin(id);
        RetrieveCustomerBookingsResponse response = (RetrieveCustomerBookingsResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(NAMESPACE));

        if(response.getError() != null) {
            if(response.getError().getCode().equals(CUSTOMER_NOT_FOUND_ERR_CODE)) {
                throw new NotFoundException(String.format(CUSTOMER_NOT_FOUND_ERR_MSG, id));
            }
            else if(response.getError().getCode().equals(DATE_FORMAT_ERROR_CODE)) {
                throw new NotFoundException(String.format(DATE_FORMAT_ERR_MSG, id));
            }
        }

        return response.getBookingList().getBooking();
    }

    /**
     * Get list of customer bookings filtered by a date interval
     * @param id
     * @param fromDate
     * @param toDate
     * @return
     * @throws NotFoundException
     */
    public List<Booking> getBookings(String id, String fromDate, String toDate) throws NotFoundException {

        SearchCustomerBookings request = new SearchCustomerBookings();
        request.setCin(id);
        request.setFromDate(fromDate);
        request.setToDate(toDate);
        SearchCustomerBookingsResponse response = (SearchCustomerBookingsResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(NAMESPACE));

        if(response.getError() != null) {
            throw new NotFoundException(String.format(CUSTOMER_NOT_FOUND_ERR_MSG, id));
        }

        return response.getBookingList().getBooking();
    }

    /**
     * Get loyalty points for a customer
     * @param id
     * @return
     * @throws NotFoundException
     */
    public Points getPoints(String id) throws NotFoundException {

        RetrieveCustomerLoyaltyPoints request = new RetrieveCustomerLoyaltyPoints();
        request.setCin(id);
        RetrieveCustomerLoyaltyPointsResponse response = (RetrieveCustomerLoyaltyPointsResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(NAMESPACE));

        if(response.getError() != null) {
            throw new NotFoundException(String.format(CUSTOMER_NOT_FOUND_ERR_MSG, id));
        }

        Points points = new Points();
        points.setLoyaltyPoints(response.getLoyaltyPointsList().getLoyaltyPoints());
        for(LoyaltyPoints p : points.getLoyaltyPoints()) {
            points.setTotalPoints(points.getTotalPoints() + p.getNumPoints());
        }

        return points;
    }
}
