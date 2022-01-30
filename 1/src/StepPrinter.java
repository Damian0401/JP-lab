public class StepPrinter {

    /**
     * Constructor with params
     * @param heightOfSegment Height of the steps
     * @param widthOfSegment Width of each segment
     * @param numberOfSegments Number of segments
     * @param character The sign from which the snake will be made
     */
    public StepPrinter(int heightOfSegment, int widthOfSegment, int numberOfSegments, char character){
        if(validateInitialData(heightOfSegment, widthOfSegment, numberOfSegments, character))
            printSteps(heightOfSegment, widthOfSegment, numberOfSegments, character);
        else
            System.out.println("Invalid initial data.");
    }

    private void printSteps(int heightOfSegment, int widthOfSegment, int numberOfSegments, char character){
        int widthOfWhiteSpace = widthOfSegment > 2 ? widthOfSegment - 2 : 1;
        int heightOfBody = heightOfSegment - 2;
        int numberOfLoops = numberOfSegments * 2;
        int lengthOfLine = numberOfSegments * (widthOfSegment + widthOfWhiteSpace) + 1;

        System.out.println("\n" + "-".repeat(lengthOfLine) + "\n");
        System.out.println("Height of steps: " + heightOfSegment);
        System.out.println("Width of each segment: " + widthOfSegment);
        System.out.println("Number of segments: " + numberOfSegments);
        System.out.println("Base character: " + character + "\n");

        // Logic
        printTopOfSteps(widthOfSegment, widthOfWhiteSpace, numberOfSegments, character);
        for(int i = 0; i < heightOfBody; i++)
            printTopBodyOfSteps(widthOfWhiteSpace, numberOfLoops, character);
        printMiddleBodyOfSteps(widthOfWhiteSpace, numberOfLoops, character);
        for(int i = 0; i < heightOfBody; i++)
            printBottomBodyOfSteps(widthOfWhiteSpace, numberOfLoops, character);
        printBottomOfSteps(widthOfSegment, widthOfWhiteSpace, numberOfSegments, character);

        System.out.println("\n\n" + "-".repeat(lengthOfLine));
    }

    private void printBottomOfSteps(int widthOfSegment, int widthOfWhiteSpace, int numberOfSegments, char character){
        printWhiteSpace(1);
        for(int i = 0; i < numberOfSegments; i++){
            printWhiteSpace(widthOfWhiteSpace);
            printSpaceFromChars(widthOfSegment, character);
        }
    }

    private void printTopOfSteps(int widthOfSegment, int widthOfWhiteSpace, int numberOfSegments, char character){
        for(int i = 0; i < numberOfSegments; i++){
            printSpaceFromChars(widthOfSegment, character);
            printWhiteSpace(widthOfWhiteSpace);
        }
        System.out.println();
    }

    private void printMiddleBodyOfSteps(int widthOfWhiteSpace, int numberOfLoops, char character){
        for(int i = 0; i < numberOfLoops; i++){
            printSpaceFromChars(1, character);
            printWhiteSpace(widthOfWhiteSpace);
        }
        printSpaceFromChars(1, character);
        System.out.println();
    }

    private void printTopBodyOfSteps(int widthOfWhiteSpace, int numberOfLoops, char character){
        for(int i = 0; i < numberOfLoops; i++){
            printSpaceFromChars(1, character);
            printWhiteSpace(widthOfWhiteSpace);
        }
        System.out.println();
    }

    private void printBottomBodyOfSteps(int widthOfWhiteSpace, int numberOfLoops, char character){
        printWhiteSpace(1);
        for(int i = 0; i < numberOfLoops; i++){
            printWhiteSpace(widthOfWhiteSpace);
            printSpaceFromChars(1, character);
        }
        System.out.println();
    }

    private void printWhiteSpace(int width){
        System.out.print(" ".repeat(width));
    }

    private void printSpaceFromChars(int width, char character){
        System.out.print(Character.toString(character).repeat(width));
    }

    private boolean validateInitialData(int height, int widthOfSegment, int numberOfSegments, char character){
        if(height < 2 || widthOfSegment % 2 == 0 || widthOfSegment < 0 || numberOfSegments < 1 || character == ' ')
            return false;
        return true;
    }
}
