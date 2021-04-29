package com.exercise.smart4viation.flightservice.data;

import org.springframework.stereotype.Component;

@Component
public class WeightCalc {
    private static final double LBS_PER_KG = 2.20462262;
    private static final double KGS_PER_LB = 0.45359237;

    public int computeKgsToLbs(final int kgs) {
        return (int) (kgs*LBS_PER_KG);
    }

    public int computeLbsToKgs(final int lbs) {
        return (int) (lbs*KGS_PER_LB);
    }
}
