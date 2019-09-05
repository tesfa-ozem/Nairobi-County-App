
package com.aw.epayments.models.PenaltyResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Charge {

    @SerializedName("charge_code")
    @Expose
    private String chargeCode;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
