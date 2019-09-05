package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentsRequest {
    @SerializedName("customer_search_criteria")
    @Expose
    private Integer customerSearchCriteria;
    @SerializedName("search_value")
    @Expose
    private String searchValue;
    @SerializedName("records_count")
    @Expose
    private Integer recordsCount;

    public Integer getCustomerSearchCriteria() {
        return customerSearchCriteria;
    }

    public void setCustomerSearchCriteria(Integer customerSearchCriteria) {
        this.customerSearchCriteria = customerSearchCriteria;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Integer getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
    }
}
