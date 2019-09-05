package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HttpSearch {

    @SerializedName("search_criteria")
    @Expose
    private int customerSearchCriteria;
    @SerializedName("search_value")
    @Expose
    private String searchValue;

    public int getCustomerSearchCriteria() {
        return customerSearchCriteria;
    }

    public void setCustomerSearchCriteria(int customerSearchCriteria) {
        this.customerSearchCriteria = customerSearchCriteria;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

}
