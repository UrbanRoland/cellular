package com.bilingjob.Processors;

import com.bilingjob.Records.BillingData;
import com.bilingjob.Records.ReportingData;
import com.bilingjob.services.PricingService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

public class BillingDataProcessor implements ItemProcessor<BillingData, ReportingData> {
    
    @Value("${spring.cellular.pricing.data:0.01}")
    private float dataPricing;
    
    @Value("${spring.cellular.pricing.call:0.5}")
    private float callPricing;
    
    @Value("${spring.cellular.pricing.sms:0.1}")
    private float smsPricing;
    
    @Value("${spring.cellular.spending.threshold:150}")
    private float spendingThreshold;
    
    private final PricingService pricingService;
    
    public BillingDataProcessor(PricingService pricingService) {
        this.pricingService = pricingService;
    }
    
    @Override
    public ReportingData process(BillingData item) {
        double billingTotal =
            item.dataUsage() * pricingService.getDataPricing() +
                item.callDuration() * pricingService.getCallPricing() +
                item.smsCount() * pricingService.getSmsPricing();
        if (billingTotal < spendingThreshold) {
            return null;
        }
        return new ReportingData(item, billingTotal);
    }
}