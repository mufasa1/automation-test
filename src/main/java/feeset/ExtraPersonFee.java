package feeset;

import java.util.Date;

public class ExtraPersonFee {
       private Date StartDate;
       private Date EndDate;
       private String AgeCategory;
       private int PersonIndex;
       private String FeeApplicationType;
       private double Amount;

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getAgeCategory() {
        return AgeCategory;
    }

    public void setAgeCategory(String ageCategory) {
        AgeCategory = ageCategory;
    }

    public int getPersonIndex() {
        return PersonIndex;
    }

    public void setPersonIndex(int personIndex) {
        PersonIndex = personIndex;
    }

    public String getFeeApplicationType() {
        return FeeApplicationType;
    }

    public void setFeeApplicationType(String feeApplicationType) {
        FeeApplicationType = feeApplicationType;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }
}
