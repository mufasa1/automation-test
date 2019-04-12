package feeset;

import java.util.List;

public class FeeSetUpdateEntity {
    private int FeeSetID;
    private String FeeSetInternalName;
    private List<ExtraPersonFee> ExtraPersonFees;
    private List<PerStayServiceCharge> PerStayServiceCharges;
    private List<PerPersonServiceCharge> PerPersonServiceCharges;

    public class PerStayServiceCharge{

    }

    public class PerPersonServiceCharge{

    }

    public int getFeeSetID() {
        return FeeSetID;
    }

    public void setFeeSetID(int feeSetID) {
        FeeSetID = feeSetID;
    }

    public String getFeeSetInternalName() {
        return FeeSetInternalName;
    }

    public void setFeeSetInternalName(String feeSetInternalName) {
        FeeSetInternalName = feeSetInternalName;
    }

    public List<ExtraPersonFee> getExtraPersonFees() {
        return ExtraPersonFees;
    }

    public void setExtraPersonFees(List<ExtraPersonFee> extraPersonFees) {
        ExtraPersonFees = extraPersonFees;
    }

    public List<PerStayServiceCharge> getPerStayServiceCharges() {
        return PerStayServiceCharges;
    }

    public void setPerStayServiceCharges(List<PerStayServiceCharge> perStayServiceCharges) {
        PerStayServiceCharges = perStayServiceCharges;
    }

    public List<PerPersonServiceCharge> getPerPersonServiceCharges() {
        return PerPersonServiceCharges;
    }

    public void setPerPersonServiceCharges(List<PerPersonServiceCharge> perPersonServiceCharges) {
        PerPersonServiceCharges = perPersonServiceCharges;
    }
}
