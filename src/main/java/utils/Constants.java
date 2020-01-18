package utils;

public class Constants {
	
	 /**
     * The Enum LoanFor
     */
    public enum LoanFor {
	    CAR ("Car"),
	    HOME_IMPROVEMENT("Home improvement"),
	    DEBT_CONSOLIDATION("Debt consolidation"),
	    OTHER("Other");
    	
	    /** The option. */
	    private String option;
	    
	    LoanFor(String option) {
    		this.option = option;
    	}
		
		public String getOptionName() {
			return option;
		} 		
    }
    
    /**
     * The Enum Title
     */
    public enum Title {
	    MR("Mr"),
	    MS("Ms"),
	    MISS("Miss"),
	    MRS("Mrs");
    	
	    /** The option. */
	    private String option;
	    
	    Title(String option) {
    		this.option = option;
    	}
		
		public String getOptionName() {
			return option;
		} 		
    }
    
    /**
     * The Enum Title
     */
    public enum EmploymentStatus {
	    FULL_TIME("Employed full-time"),
	    SELF("Self-employed"),
	    DIRECTOR_LIMITED_COMPANY("Director of a limited company"),
	    PART_TIME("Employed part-time"),
    	UNEMPLOYED("Currently unemployed"),
	    RETIRED("Retired, not working"),
	    HOUSE("Housewife, househusband or homemaker");
    	
	    /** The option. */
	    private String option;
	    
	    EmploymentStatus(String option) {
    		this.option = option;
    	}
		
		public String getOptionName() {
			return option;
		} 		
    }
    
    /**
     * The Enum Title
     */
    public enum ResidentialStatus {
	    OWNER("Yes, outright owner"),
	    MORTAGE("Yes, with mortage"),
	    NO("No");
    	
	    /** The option. */
	    private String option;
	    
	    ResidentialStatus(String option) {
    		this.option = option;
    	}
		
		public String getOptionName() {
			return option;
		} 		
    }

}
