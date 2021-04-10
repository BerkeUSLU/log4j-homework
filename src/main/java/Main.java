import org.apache.logging.log4j.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final Marker ASSERTION_MARKER = MarkerManager.getMarker("ASSERTION");
    private static final Marker GIBBERISH_MARKER = MarkerManager.getMarker("GIBBERISH");

    public static void main(String[] args) {

        String[] poem = {"Twinkle, twinkle little star. Twinkle, twinkle, little star,",
                "I'm a Little Tea Pot. I'm a little teapot, short and stout.",
                "London Bridge is Falling Down (Short Version)",
                "Mary Had a Little Lamb.",
                "Humpty Dumpty.",
                "Hey diddle diddle.",
                "Baa baa black sheep.",
                "One, two, three, four."};

        int input = Integer.valueOf(args[0]);
        if(input > poem.length){
            input = poem.length;
        }

        for (int i = 0; i < input; i++){
            ThreadContext.push(String.valueOf(i));

            if(i == 0){
                logger.warn(poem[i]);
            }
            else if(poem[i].contains("diddle") || poem[i].contains("Humpty")){
                logger.error(GIBBERISH_MARKER,poem[i]);
            }
            else{
                logger.debug(ASSERTION_MARKER,poem[i]);
            }

            ThreadContext.clearAll();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
