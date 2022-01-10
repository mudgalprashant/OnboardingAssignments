package services;
import constants.Constant;
import entities.Item;
import enums.*;

// Calculates and functions tax
public class Taxation {

    // Calculate tax according to category
    public static void calculateTax(Item item) {

        // Tax for various categories
        double tax;
        double baseTax;
        double surcharge;
        double importDuty;

        if (item.getCategory() == Category.RAW){

            // Raw category tax
            tax = item.getPrice() * Constant.RAW_TAX_PERCENT / 100;

        }else if (item.getCategory() == Category.MANUFACTURED){

            // Manufactured Category tax
            baseTax = item.getPrice() * Constant.MANUFACTURED_TAX_PERCENT / 100;
            surcharge = (item.getPrice() + baseTax) * Constant.MANUFACTURED_TAX_SURCHARGE_PERCENT / 100;
            tax = baseTax + surcharge;

        }else{
            // Imported Category tax
            importDuty = item.getPrice() * Constant.IMPORT_DUTY_PERCENT / 100;
            if (item.getPrice() + importDuty <= Constant.IMPORT_SURCHARGE_LIMIT_1){
                surcharge = Constant.IMPORT_SURCHARGE_LIMIT_1_TAX;
            }else if (item.getPrice() + importDuty <= Constant.IMPORT_SURCHARGE_LIMIT_2){
                surcharge = Constant.IMPORT_SURCHARGE_LIMIT_2_TAX;
            }else{
                surcharge = (item.getPrice() + importDuty) * Constant.IMPORT_SURCHARGE_TAX_PERCENT / 100;
            }
            tax = importDuty + surcharge;
        }
        item.setTax(tax);
    }
}