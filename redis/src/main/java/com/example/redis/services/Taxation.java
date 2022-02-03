package com.example.redis.services;

import com.example.redis.constants.Constant;
import com.example.redis.models.Item;

/**
 * The type Taxation.
 * Handles tax functionalities
 */
public class Taxation {

  /**
   * Calculate tax.
   *
   * @param item the item for which tax is to calculated
   * @return the double
   */
  public double calculateTax(Item item) {

    // Tax for various categories
    double tax;
    double baseTax;
    double surcharge;
    double importDuty;

    switch (item.getCategory()) {
      case RAW:

        tax = item.getPrice() * Constant.RAW_TAX_PERCENT / Constant.DIVISOR_FOR_PERCENT;
        return tax;

      case MANUFACTURED:

        baseTax = item.getPrice() * Constant.MANUFACTURED_TAX_PERCENT / Constant.DIVISOR_FOR_PERCENT;
        surcharge = (item.getPrice() + baseTax) * Constant.MANUFACTURED_TAX_SURCHARGE_PERCENT / Constant.DIVISOR_FOR_PERCENT;
        tax = baseTax + surcharge;
        return tax;

      case IMPORTED:

        importDuty = item.getPrice() * Constant.IMPORT_DUTY_PERCENT / Constant.DIVISOR_FOR_PERCENT;
        if (item.getPrice() + importDuty <= Constant.IMPORT_SURCHARGE_LIMIT_1) {
          surcharge = Constant.IMPORT_SURCHARGE_LIMIT_1_TAX;
        } else if (item.getPrice() + importDuty <= Constant.IMPORT_SURCHARGE_LIMIT_2) {
          surcharge = Constant.IMPORT_SURCHARGE_LIMIT_2_TAX;
        } else {
          surcharge = (item.getPrice() + importDuty) * Constant.IMPORT_SURCHARGE_TAX_PERCENT / Constant.DIVISOR_FOR_PERCENT;
        }
        tax = importDuty + surcharge;
        return tax;
      default:
        return 0;
    }
  }
}