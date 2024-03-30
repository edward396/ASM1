/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;

import java.util.ArrayList;
import java.util.List;
public class PolicyHolder extends Customer{
    private List<Dependent> dependentList = new ArrayList<>();

    public PolicyHolder(List<Dependent> dependentList) {
        this.dependentList = dependentList;
    }

    public PolicyHolder(String customerID, String fullName, String insuranceCard, List<Dependent> dependentList) {
        super(customerID, fullName, insuranceCard);
        this.dependentList = dependentList;
    }
}
