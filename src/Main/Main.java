package Main;

import java.util.Arrays;
import java.util.Date;

import Classes.Claim;
import Classes.InsuranceCard;

public class Main {
    public static void main(String[] args) {
        InsuranceCard IC = new InsuranceCard("a", "b", "c",new Date(2002,11,31));

        System.out.println(IC.toString());
    }
}