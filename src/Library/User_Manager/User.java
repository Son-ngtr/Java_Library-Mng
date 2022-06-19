package Library.User_Manager;

import java.util.Calendar;

public class User {
   private int id;
   private String name;
   private String gender;
   private Calendar dateOfBirth;
   private String address;
   private String phoneNumber;
   private String email;
   private Calendar expDate;
   private Calendar regisDate;
   private Long moneyFine;

   //Constructor
   public User(int id, String name, String gender, Calendar dateOfBirth, String address, String phoneNumber, String email, Calendar expDate, Calendar regisDate, Long moneyFine) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.expDate = expDate;
        this.regisDate = regisDate;
        this.moneyFine = moneyFine;
   }

   //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getExpDate() {
        return expDate;
    }

    public void setExpDate(Calendar expDate) {
        this.expDate = expDate;
    }

    public Calendar getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(Calendar regisDate) {
        this.regisDate = regisDate;
    }

    public Long getMoneyFine() {
        return moneyFine;
    }

    public void setMoneyFine(Long moneyFine) {
        this.moneyFine = moneyFine;
    }

    //Date convert
    public String dateConvert(Calendar calendar){
        String date = calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        return date;
    }

    //DateReConvert
    public void dateReConvert(String dateReConvert){
        Calendar newCalendar = Calendar.getInstance();
        String[] times = dateReConvert.split("/");
        int date = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int year = Integer.parseInt(times[2]);
        newCalendar.set(year+0, month - 1 , date+0);
        setDateOfBirth(newCalendar);
    }

    //Money convert
    public String moneyConvert(){
        String soDu_String = Long.toString(getMoneyFine());
        String soDu_String_result = "" ;
        for(int i=soDu_String.length()-1 ; i>=0; i-=3){
            if(i >= 3){
                soDu_String_result = "." + soDu_String.substring(i-2, i+1) + soDu_String_result;
            }else {
                soDu_String_result = soDu_String.substring(0, i+1) + soDu_String_result;
            }
        }
        return soDu_String_result + "VND";
    }
}