
package com.aw.epayments.api;


import com.aw.epayments.models.Response.LandResponse;
import com.aw.epayments.models.Response.TransactionStatusResponse;
import com.aw.epayments.models.Response.seasonalParkingRecipt.SeasonalParkingRecipt;

public final class Const {

    private LandResponse landResponse;
    private TransactionStatusResponse transactionStatusResponse;
    private SeasonalParkingRecipt seasonalParkingRecipt;


    private Const() {
        init();
    }

    public static Const getInstance() {
        return ConstHolder.INSTANCE;
    }

    public LandResponse getLandResponse() {
        return landResponse;
    }

    public void setLandResponse(LandResponse landResponse) {
        this.landResponse = landResponse;
    }

    public SeasonalParkingRecipt getSeasonalParkingRecipt() {
        return seasonalParkingRecipt;
    }

    public void setSeasonalParkingRecipt(SeasonalParkingRecipt recipt) {
        this.seasonalParkingRecipt = recipt;
    }

    public TransactionStatusResponse getTransactionStatusResponse() {
        return transactionStatusResponse;
    }

    public void setTransactionStatusResponse(TransactionStatusResponse transactionStatusResponse) {
        this.transactionStatusResponse = transactionStatusResponse;
    }

    private void init() {
        this.getSeasonalParkingRecipt();
        this.getLandResponse();
        this.getTransactionStatusResponse();
    }

    private static class ConstHolder {
        private static final Const INSTANCE = new Const();
    }
}
