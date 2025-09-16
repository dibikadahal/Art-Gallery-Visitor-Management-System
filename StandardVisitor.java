public class StandardVisitor extends ArtGalleryVisitor{
    //private access modifier for additional variables declared
    private boolean isEligibleForDiscountUpgrade; // boolean variable to check the eligibility status for discount
    private final int visitLimit = 5; //the limited no. of times a visitor can visit the art gallery
    private float discountPercent; //stores the discount percentage obtained by the member

    //constructor to initialize the attributes of the StandardVisitor Class
    public StandardVisitor(int visitorId, String fullName, String gender, String contactNumber, String registrationDate,
    double ticketCost, String ticketType){
        //calling parent class constructor to initialize the inherited attributes
        super(visitorId, fullName, gender, contactNumber, registrationDate, ticketCost, ticketType); 
        this.discountPercent = 0.10f; //discount percent is set to 0.10 to the StandardVisitors by default
        this.isEligibleForDiscountUpgrade = false; //eligibility for discount is set to false by default
    }

    //accessor method for IsEligibleForDiscountUpgrade which returns the boolean value for the eligibilty of discount upgrade
    public boolean getIsEligibleForDiscountUpgrade(){
        return this.isEligibleForDiscountUpgrade;
    }

    /*accessor method for VisitLimit which returns the limited no. of times a visitor can visit the gallery after which 
    he/she can receive discount on purchase*/
    public int getVisitLimit(){
        return this.visitLimit;
    }

    //accessor method for DiscountPercent which returns the percent discount a visitor can receive on his/her purchsae
    public float getDiscountPercent(){
        return this.discountPercent;
    }

    //this method evaluates the eligibility for the visitor for discout upgrade
    public boolean checkDiscountUpgrade(){
        if (visitCount >= visitLimit){
            isEligibleForDiscountUpgrade = true;
            discountPercent = 0.15f;
        }else{
            isEligibleForDiscountUpgrade = false;
        }
        return isEligibleForDiscountUpgrade;
    }

    /*this method allows a visitor to purchase the artwork by checking the active status of the visitor 
    and returning messages based on the conditions of the user*/
    @Override
    public String buyProduct(String artworkName, double artworkPrice){
        if (this.isActive){
            if (this.artworkName == null || !this.artworkName.equals (artworkName)){
                this.artworkName = artworkName;
                this.artworkPrice = artworkPrice;
                this.isBought = true;
                this.buyCount++;
                return "Artwork "+ artworkName + " has been purchased successfully!";
            }else{
                return "The artwork " + artworkName + " has already been purchased";
            }
        } else{
            return "Please log in before making a purchase."; 
        }
    }

    //the calculateDiscount() method calculates the discount after purchasing a product based on the value returned by checkDiscountUpgrade() method
    @Override
    public double calculateDiscount()
    {
        if (!isBought){
            return 0;
        }

        checkDiscountUpgrade();
        discountAmount = artworkPrice * discountPercent;
        finalPrice = artworkPrice - discountAmount;
        return discountAmount;
    }

    //this method calculates the reward point after the product has been bought
    @Override
    public double calculateRewardPoint(){
        if (isBought){
            rewardPoints += finalPrice * 5;
        }
        return rewardPoints;
    }

    //this method records the purchase by the visitor
    @Override
public void generateBill() {
    if (isBought) {
        System.out.println("*****************************************");
        System.out.println("*          ART GALLERY BILL             *");
        System.out.println("*****************************************");
        System.out.println("Visitor ID     : " + visitorId);
        System.out.println("Visitor Name   : " + fullName);
        System.out.println("Artwork Name   : " + artworkName);
        System.out.println("Artwork Price  : " + artworkPrice);
        System.out.println("Discount Amount: " + discountAmount);
        System.out.println("Final Price    : " + finalPrice);
        System.out.println("*****************************************");
    } else {
        System.out.println("No purchase made to generate bill.");
    }
}


    //this method deactivates a visitor's account
    private void terminateVisitor(){
        this.isActive = false;
        this.isEligibleForDiscountUpgrade = false;
        this.visitCount = 0;
        this.cancelCount = 0;
        this.rewardPoints = 0;
    }

    //method to cancel the purchased product
    @Override
    public String cancelProduct(String artworkName, String cancellationReason){
        if (cancelCount == 3){
            terminateVisitor();
            return " Your account has been terminated!";
        }

        if(buyCount>0){
            if(this.artworkName.equals(artworkName))
            {
                //process cancellation
                this.artworkName = null;
                isBought = false;

                // calculate refund and update reward points
                refundableAmount = artworkPrice - (artworkPrice * 0.10);
                rewardPoints -= finalPrice * 5;

                //update counters
                cancelCount++;
                buyCount--;

                //Cancellation reason recorded
                this.cancellationReason = cancellationReason;
                return "The cancellation is considered and refund has been issued.";

            }else{
                return "The Artwork name provided is incorrect!";
            }
        }
        return "There is no product to cancel!";
    }

    //Method to display member details
    @Override
    public void display(){
        super.display(); //calling the parent class to display method to show inherited attributes
        System.out.println ("Is Eligible For Discount Upgrade?: " + isEligibleForDiscountUpgrade);
        System.out.println ("Visit Limit: " + visitLimit);
        System.out.println ("Discount Percent: " + discountPercent);
    }
}
