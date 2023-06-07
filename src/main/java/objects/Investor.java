package main.java.objects;

import java.util.Date;
import java.util.Random;

public class Investor {

	private String investorTitle;
	private String investorLastName;
	private String investorFirstName;
	private String investorGender;
	private String investorType;
	private String investorEmail;
	private String investorPassword;

	//data for random generation
	private String[] investorTitles = new String[]{"MR", "MRS"};
	private String[] investorLastNames = new String[]{"Francis xavier", "Marco polo", "O'Ramen"};
	private String[] investorFirstNames = new String[]{"Qakalli", "Qakundi", "Qaxilla's"};
	private String[] investorGenders = new String[]{"Herr", "Frau", "Ohne Anrede"};
	private String[] investorTypes = new String[]{"Privatanleger:in", "Unternehmen"};
	private static final String DEFAULT_PASSWORD = "P@ssw0rd";

	public Investor() throws Exception {
		investorLastName = getRandomValueFromArray(investorLastNames);
		investorFirstName = getRandomValueFromArray(investorFirstNames);
		investorTitle = getRandomValueFromArray(investorTitles);
		investorGender= getRandomValueFromArray(investorGenders);
		investorType = getRandomValueFromArray(investorTypes);
		investorEmail = randomEmailAddress();
		setPasswordToDefault();
		investorPassword = getInvestorPassword();
	}

	//Set password back to default
	public void setPasswordToDefault() {
		this.setInvestorPassword(DEFAULT_PASSWORD);
	}

	//Set specific password for investor
	public void setInvestorPassword(String specificPassword) {
		this.investorPassword = specificPassword;
	}

	public String getInvestorPassword() {
		return investorPassword;
	}

	public void setEmailAddress(String email) {
		this.investorEmail = email;
	}

	public String getInvestorEmail() {
		return investorEmail;
	}

	public void setInvestorEmail(String email) {
		this.investorEmail = email;
	}

	public String getInvestorSurname() {
		return investorLastName;
	}

	public void setInvestorSurname(String investorSurname) {
		this.investorLastName = investorSurname;
	}

	public String getInvestorFirstName() {
		return investorFirstName;
	}

	public void setInvestorFirstName(String investorFirstName) {
		this.investorFirstName = investorFirstName;
	}

	public String getInvestorTitle() {
		return investorTitle;
	}

	public void setInvestorTitle(String investorTitle) {
		this.investorTitle = investorTitle;
	}

	public String getInvestorGender() {
		return investorGender;
	}

	public void setInvestorGender(String investorGender) {
		this.investorGender = investorGender;
	}

	public String getInvestorType() {
		return investorType;
	}

	public void setInvestorTypes(String investorType) {
		this.investorType = investorType;
	}


	//Generate a random string
    private String randomAlphaNumeric(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ._0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

	//Generate a random long string contains special characters
    public String randomLongStringContainsSpecialCharacters(int amountOfChars) {
        String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String NUMERIC_STRING = "0123456789";
		String SPECIAL_CHARS = "._!@#$%^&*()-+={}[]|~`:,<>?";
        StringBuilder builder = new StringBuilder();
        while (amountOfChars-- != 0) {
            int character = (int) (Math.random() * ALPHA_STRING.length());
            builder.append(ALPHA_STRING.charAt(character));
        }
		Random rand = new Random();
		builder.append(NUMERIC_STRING.charAt(rand.nextInt(NUMERIC_STRING.length())));
		builder.append(SPECIAL_CHARS.charAt(rand.nextInt(SPECIAL_CHARS.length())));
        return builder.toString();
    }

    //Get Current Time of System
    private long getCurrentTime() {
        Date date = new Date();
        long time = date.getTime();
        return time;
    }

    //Generate a random email base on the random string and current time
    private String randomEmailAddress() {
        Random rand = new Random();
        String newEmail = randomAlphaNumeric(rand.nextInt(10));
        long currentTime = getCurrentTime();
        newEmail = newEmail + currentTime + "@gmail.com";
        return newEmail.toString();
    }

	private String getRandomValueFromArray(String[] array) {
		String result = null;
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);
        result = array[randomIndex].toString();
		return result;
	}
}