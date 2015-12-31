public class Command {
    private String commandWord, secondWord, thirdWord, fourthWord;

    public Command(String firstWord, String secondWord, String thirdWord,
            String fourthWord) {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
        this.fourthWord = fourthWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }
    
    public String getThirdWord() {
        return thirdWord;
    }
    
    public String getFourthWord() {
        return fourthWord;
    }

    public boolean isUnknown() {
        return (commandWord == null);
    }

    public boolean hasSecondWord() {
        return (secondWord != null);
    }
    
    public boolean hasThirdWord() {
        return (thirdWord != null);
    }
    
    public boolean hasFourthWord() {
        return (fourthWord != null);
    }
}

