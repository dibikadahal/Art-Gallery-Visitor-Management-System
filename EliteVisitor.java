public class EliteVisitor extends ArtGalleryVisitor{
    //private access modifier for additional variables declared
    private boolean assignedPersonalArtAdvisor; // checks whether the visitor has been assigned with personal advisor
    private boolean exclusiveEventAccess; // checks whether the visitor has access to ther personal advisor service

    //creating constructor, called superclass constructor and assigned two attributes with values
    public EliteVisitor(int visitorId, String fullName, String gender, 
        String contactNumber, String registrationDate, double ticketCost, String ticketType){
        //calling parent class constructor to initialize the inherited attributes
        super(visitorId, fullName, gender, contactNumber, registrationDate, ticketCost, ticketType);
        this.assignedPersonalArtAdvisor = false; // By default, the visitor is not assigned a personal art advisor
        this.exclusiveEventAccess = false; // By default, the visitor does not have access to exclusive events
    }

    
    //attributes with corresponding accessor method
    /*accessor method for assignedPersonalArtAdvisor.
    It returns the boolean value after checking whether the visitor has been assigned with personal advisor*/
    public boolean getAssignedPersonalArtAdvisor(){
        return this.assignedPersonalArtAdvisor;
    }

    /*accessor method for exclusiveEventAccess.
    It returns the boolean value after checking whether the visitor has access to ther personal advisor service*/
    public boolean getExclusiveEventAccess(){
        return this.exclusiveEventAccess;
    }

    
    //this method assigns a personal art advisor to the Elite Visitor
    public boolean assignPersonalArtAdvisor(){
        if(rewardPoints>5000){
            assignedPersonalArtAdvisor = true;    
        }else{
            assignedPersonalArtAdvisor = false;
        }
        return assignedPersonalArtAdvisor;
    }

    
    //this method checks whether the visitor has access to exclusive art gallery events or not
    public boolean exclusiveEventAccess(){
        if(assignedPersonalArtAdvisor){
            exclusiveEventAccess = true;
        }else{
            exclusiveEventAccess = false;
        }
        return exclusiveEventAccess;
    }

    
    /*this method allows a visitor to purchase the artwork by checking the active status of the visitor 
    and returning messages based on the conditions of the user*/
    @Override
    public String buyProduct(String artworkName, double artworkPrice){
        if(this.isActive){
            if (this.artworkName == null || !this.artworkName.equals(artworkName)){
                this.artworkName = artworkName;
                this.artworkPrice = artworkPrice;
                this.isBought = true;
                this.buyCount++;
                return "Successful purchase!";
            }else{
                return "Same artwork has already been purchased!";
            }
        }else{
            return "please log in before making a purchase!";
        }
    }

    
    //method ro calculate discount for an Elite Visitor
    @Override
    public double calculateDiscount() {
    if (!isBought) {
        return 0;
    }

    discountAmount = artworkPrice * 0.40;
    finalPrice = artworkPrice - discountAmount;
    return discountAmount;
    }

    
    //method to calculate reward point for an Elite Visitor after purchasing the product
    @Override
    public double calculateRewardPoint(){
        if (isBought){
            rewardPoints += finalPrice * 10;
        }
        return rewardPoints;
    }

    
    //this method records the purchase by the visitor
    @Override
    public void generateBill(){
        if(isBought){
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
        }else{
            System.out.println("There is no purchase made to generate bill.");
        }
    }

    
    //method to deactivate the visitor's account
    private void terminateVisitor(){
            this.isActive = false;
            this.assignedPersonalArtAdvisor = false;
            this.exclusiveEventAccess = false;
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
        else if(buyCount>0){
            if(this.artworkName.equals(artworkName))
            {
                //process cancellation
                this.artworkName = null;
                isBought = false;

                // calculate refund and update reward points
                refundableAmount = artworkPrice - (artworkPrice * 0.05);

                //update counters
                cancelCount++;
                buyCount--;

                //Cancellation reason recorded
                this.cancellationReason = cancellationReason;
                return "The cancellation is considered and refund has been issued.";
            }
            else{
                return "The Artwork name provided is incorrect!";
            }
        }
        return "There is no product to cancel!";
    }

    //Method to display member details
    @Override
    public void display(){
        super.display(); //calling the parent class to display method to show inherited attributes
        System.out.println ("Assigned Personal Art Advisor: "+assignedPersonalArtAdvisor);
        System.out.println ("Exclusive Event Access: "+exclusiveEventAccess);
    }
}




