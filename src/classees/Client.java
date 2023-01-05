package classees;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Client {
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private BigDecimal money;

    public Client(Long id, String fullName, LocalDate dateOfBirth, String phoneNumber, BigDecimal money) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        if (phoneNumber.length() == 13){
            this.phoneNumber = phoneNumber;
        }else
            System.out.println("Ne pravilnyi nomer!!");
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "\nClient: " +
                "\nid: " + id +
                "\nfullName: " + fullName +
                "\ndateOfBirth: " + dateOfBirth +
                "\nphoneNumber: " + phoneNumber +
                "\nmoney: " + money +
                "\n~~~~~~~~~~~~~~~~~~~\n";
    }
}
