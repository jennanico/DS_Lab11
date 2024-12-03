import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class TestTree 
{
	
	Scanner comp;
	PrintStream me;
	boolean show = true; //if set to true, will print out respones to and from the game.  Set to false to make it faster.
	
    
    //initializes the Testing environment with piped streams (Queues)
	public TestTree() throws IOException
	{
		PipedOutputStream inputHandle = new PipedOutputStream();
		PipedInputStream input= new PipedInputStream(inputHandle);
		
		PipedOutputStream output = new PipedOutputStream();
		PipedInputStream outputHandle= new PipedInputStream(output);
		
		QTree game = new QTree(input,new PrintStream(output));

		Thread t = new Thread(()->{game.playGame();});
		t.start();

        comp = new Scanner(outputHandle);
		me = new PrintStream(inputHandle);

	
	}

    /*
        Helper methods for IO and testing
    
        These methods are beefed up versions of assert.  
    
    */
    
    //Use this to "check" if the string from the program is correct.
	public void check(String s)
	{
		String observed = comp.nextLine();
		if(show) {System.out.println("observed:"+observed);}
		//will not work with simple assert statements due to multithreading	
		if( ! observed.equals(s))
		{
			System.out.println("expected "+s+" but got "+observed);
			System.exit(1);
		}
	}
	
	public void say(String s)
	{
		me.println(s);
		me.flush(); //greatly increases speed of program, lets other side know there is new data.
		if(show) {System.out.println("said:"+s);}
	}
	
	
	public void run()
	{
        String isDuck = Strings.IS_IT_A + Strings.DUCK + "?";
        String isRock = Strings.IS_IT_A + Strings.ROCK + "?";
        
        String ladybug = "Ladybug";
        String diffLady = "Is it an insect?";
        String isLady = Strings.IS_IT_A + ladybug + "?";
        
        String grasshopper = "Grasshopper";
        String diffGrass = "Is it red?";
        String isGrass = Strings.IS_IT_A + grasshopper + "?";
        
        String waterbottle = "Waterbottle";
        String diffWater = "Can it hold water?";
        String isWater = Strings.IS_IT_A + waterbottle + "?";
        
        String bathtub = "Bathtub";
        String diffBath = "Is it handheld?";
        String isBath = Strings.IS_IT_A + bathtub + "?";
        

		check(Strings.IS_IT_ALIVE);   // 1. instant yes on duck
        say("Y");
        check(isDuck);                 
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);  // 2. instant yes on rock
        say("N");
        check(isRock); 
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);   // 3. no on duck; insertion to left of root
        say("Y");					  // "yes, no"
        check(isDuck);
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say(ladybug);
        check(Strings.NEW_QUESTION + Strings.DUCK + " and a " + ladybug);
        say(diffLady);
        check("Answering yes to " + diffLady + " means " + ladybug + "?");
        say("Y");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);   // 3. no on rock; insertion to right of root
        say("N");					  // "no, no"
        check(isRock);
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say(waterbottle);
        check(Strings.NEW_QUESTION + Strings.ROCK + " and a " + waterbottle);
        say(diffWater);
        check("Answering yes to " + diffWater + " means " + waterbottle + "?");
        say("Y");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);   // 4. no on duck/ladybug; insertion to left of root, inverse question
        say("Y");					  // "no, yes, no"
        check(diffLady);
        say("Y");
        check(isLady);
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say(grasshopper);
        check(Strings.NEW_QUESTION + ladybug + " and a " + grasshopper);
        say(diffGrass);
        check("Answering yes to " + diffGrass + " means " + grasshopper + "?");
        say("N");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);   // 5. no on rock/waterbottle; insertion to right of root, inverse question
        say("N");					  // "no, yes, no"
        check(diffWater);
        say("Y");
        check(isWater);
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say(bathtub);
        check(Strings.NEW_QUESTION + waterbottle + " and a " + bathtub);
        say(diffWater);
        check("Answering yes to " + diffWater + " means " + bathtub + "?");
        say("N");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);   // 6. win on left side
        say("Y");					  // "yes, yes, no, yes"
        check(diffLady);
        say("Y");
        check(diffGrass);
        say("N");
        check(isGrass);
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);   // 7. win on right side
        say("N");					  // "no, yes, no, yes"
        check(diffWater);
        say("Y");
        check(diffBath);
        say("N");
        check(isBath);
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);   // 8. can still access old entries on left side
        say("Y");					  // "yes, yes, yes, yes"
        check(diffLady);
        say("Y");
        check(diffGrass);
        say("Y");
        check(isLady);
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);   // 9. can still access old entries on right side
        say("N");					  // "no, yes, yes, yes"
        check(diffWater);
        say("Y");
        check(diffBath);
        say("Y");
        check(isWater);
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");

        //close the streams at the end to enrue good behavior.
		comp.close();
		me.close();
	}





	public static void main(String[] args) 
	{
		System.out.print("Test is running");
		try
		{
			TestTree test = new TestTree();
			test.run();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		System.out.print("you there halt");
		
	}
	
	
}
