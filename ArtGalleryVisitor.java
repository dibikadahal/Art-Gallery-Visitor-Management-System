import java.io.*;

public abstract class ArtGalleryVisitor implements Serializable{

    //protected access modifier for the provided attributes
    protected int visitorId; //Unique identifier for the Art Gallery visitor
    protected String fullName; //Name of the visitor
    protected String gender; //Gender of the visitor
    protected String contactNumber; //Contact number of the visitor
    protected String registrationDate; //Date of visit to the Art Gallery
    protected double ticketCost; //Cost of the ticket during entry
    protected String ticketType; // Ticket type (either "Standard" or "Elite")
    protected int visitCount; // Number of times the visitor has been to the Art Gallery
    protected double rewardPoints; // Loyalty points earned by the visitor based on their visit count
    protected final int cancelLimit = 3; //the maximum no of times a visitor can cancel the product. It cannot be changed
    protected int cancelCount; // Number of times the visitor has cancelled their purchase
    protected String cancellationReason; // The reason why the visitor has cancelled the purchase
    protected double refundableAmount; // Amount returned back after cancellation
    protected boolean isActive; //Checks the active status of the visitor
    protected boolean isBought; //Checks whether a particular item has been sold or not 
    protected int buyCount; //no of items bought by the visitor
    protected double finalPrice; // Final Price paid by the visitor after discount
    protected double discountAmount; // Discount Amount received by the visitor on their purchase
    protected String artworkName; // Name of the Artwork bought by the visitor
    protected double artworkPrice; // Price of the Artwork bought

    //defining ArtGalleryVisitor Constructor
    public ArtGalleryVisitor(int visitorId, String fullName, String gender, String contactNumber, 
    String registrationDate, double ticketCost , String ticketType)
    {
        //initialize visitor details with provided parameters
        this.visitorId = visitorId; //Set visitor ID
        this.fullName = fullName; // Set visitor's name
        this.gender = gender; // Set visitor's gender
        this.contactNumber = contactNumber; // Set visitor's Contact Number
        this.registrationDate = registrationDate; // Set the date the visitor has visited the Art Gallery
        this.ticketCost = ticketCost; // Set the ticket price before entering the Gallery
        this.ticketType = ticketType; // Set the type of ticket purchased by the visitor

        //provided with default values
        this.visitCount = 0; // Visit Count of the visitor initialized to 0
        this.rewardPoints = 0.0; // reward points initialized to 0
        this.cancelCount = 0; // no of purchase cancellation set to 0
        this.buyCount = 0; // no of purchased items set to 0
        this.discountAmount = 0.0; // discount amount initilized to 0
        this.finalPrice = 0.0; // final price not assigned. thus set to 0
        this.refundableAmount = 0.0; // amount to be refunded initialized as 0
        this.isActive = false; // setting default value of Active status of the visitor as false
        this.isBought = false; // setting default value of any item on the gallery as false (it has not been sold)
        this.cancellationReason = ""; //the reason for why the purchased has been set as EMPTY
    }

    
    //creating accessor method VisitorID which returns the unique ID assigned to the visitor
    public int getVisitorId(){ 
        return this.visitorId; 
    }

    //creating accessor method FullName which returns the full name of the visitor
    public String getFullName(){ 
        return this.fullName;
    }

    //creating accessor method Gender which returns the gender of the visitor
    public String getGender(){
        return this.gender;
    }

    //creating accessor method ContactNumber that returns the contact number of the visitor
    public String getContactNumber(){
        return this.contactNumber;
    }

    //creating accessor method RegistrationDate. It returns the date of registration.
    public String getRegistrationDate(){
        return this.registrationDate;
    }

    //creating accessor method TicketCost. It returns the cost of the ticket
    public double getTicketCost(){
        return this.ticketCost;
    }

    //creating accessor method TicketType which returns the type of ticket i.e. Standard or Elite
    public String getTicketType(){
        return this.ticketType;
    }

    //creating accessor method VisitCount which returns the no. of times the visitor has visited the gallery
    public int getVisitCount(){
        return this.visitCount;
    }

    //creating accessor method RewardType which returns the Loyalty points earned by the visitor based on their visit count
    public double getRewardPoints(){
        return this.rewardPoints;
    }

    //creating accessor method CancelLimit which returns the limit of cancellation. It is fixed
    public int getCancelLimit(){
        return this.cancelLimit;
    }

    //creating accessor method CancelCount which returns the total no. of cancelling the purchase
    public int getCancelCount(){
        return this.cancelCount;
    }

    //creating accessor method CancellationReason which returns the the reason why the purchased product has been cancelled
    public String getCancellationReason(){
        return this.cancellationReason;
    }

    //creating accessor method RefundableAmount which returns the amount refunded after the purchase has been cancelled
    public double getRefundableAmount(){
        return this.refundableAmount;
    }

    //creating accessor method IsActive which returns boolean value of where the visitor is active or not
    public boolean getIsActive(){
        return this.isActive;
    }

    //creating accessor method IsBought which returns the boolean value of whether a product has been sold or not
    public boolean getIsBought(){
        return this.isBought;
    }

    //creating accessor method BuyCount which returns the total no. of products purchsed by the visitor
    public int getBuyCount(){
        return this.buyCount;
    }

    //creating accessor method FinalPrice which returns the total price of the product bought
    public double getFinalPrice(){
        return this.finalPrice;
    }

    //creating accessor method DiscountAmount which returns the amount of discount received by the visitor on purchase
    public double getDiscountAmount(){
        return this.discountAmount;
    }

    //creating accessor method ArtworkName which returns the name of the artwork
    public String getArtworkName(){
        return this.artworkName;
    }

    //creating accessor method ArtworkPrice which returns the price of the artwork
    public double getArtworkPrice(){
        return this.artworkPrice;
    }

    
    //setters to mutate the values of the attribute
    //It sets the full name of the visitor
    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    //sets the gender of the visitor
    public void setGender(String gender){
        this.gender = gender;
    }

    //sets the contact number of the visitor
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    
    /*public method logVisit() with return type void.
    here, visit count increases by 1 each time the method is involed and is Active is set to true*/
    public void logVisit(){
        visitCount++;
        isActive = true;
    }

    /*abstract method called buyProduct() has been declared  which takes artworkName and
    artworkPrice as parameters, and have return type String*/
    public abstract String buyProduct(String artworkName, double artworkPrice);

    /*an abstract method calculateDiscount() with return type double.
    It calculates a discount based on the visitor's type once a product is purchased*/
    public abstract double calculateDiscount();

    /*abstract method calculateRewardPoint() with return type: double 
    designed to compute the reward points that a visitor earns after making a purchase */
    public abstract double calculateRewardPoint();

    /*an abstract method named cancelProduct() with return type String has been implemented
    that takes two parameters-artworkName and cancellationReason. 
    After cancellation, the visitor receives refundable amount*/
    public abstract String cancelProduct(String artworkName, String cancellationReason);

    /*an abstract method named generateBill() has been declared which has return type void*/
    public abstract void generateBill();

    
    //this prints the complete details after the visitor has purchased the product
    public void display(){
        //displays all the details
        System.out.println("Visitor ID: " + visitorId);
        System.out.println("Full Name: " + fullName); 
        System.out.println("Gender: " + gender);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Ticket cost: " + ticketCost);
        System.out.println("Ticket Type: " + ticketType);
        System.out.println("Visit Count: " + visitCount);
        System.out.println("Reward Points: " + rewardPoints);
        System.out.println("Cancel Limit: " + cancelLimit);
        System.out.println("Cancel Count: " + cancelCount);
        System.out.println("Cancellation Reason: " + cancellationReason);
        System.out.println("Refundable Amount: " + refundableAmount);
        System.out.println("Active Status: " + isActive);
        System.out.println("Purchase Status: " + isBought);
        System.out.println("Purchase Count: " + buyCount);
        System.out.println("Final Price: " + finalPrice);
        System.out.println("Discount Amount: " + discountAmount);
        System.out.println("Artwork Name: " + artworkName);
        System.out.println("Artwork Price: " + artworkPrice);
    }
}

