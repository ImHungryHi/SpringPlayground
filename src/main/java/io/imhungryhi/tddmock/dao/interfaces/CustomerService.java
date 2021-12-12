package io.imhungryhi.tddmock.dao.interfaces;

import io.imhungryhi.tddmock.model.Customer;

public interface CustomerService {
    Customer findById(int i);
}
