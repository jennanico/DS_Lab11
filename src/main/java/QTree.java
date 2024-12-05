import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class QTree
{
	
	
	Scanner in;
	PrintStream out;
	
	QNode root;
	
    //initializes the game
	public QTree(InputStream in, PrintStream out)
	{
		this.out=out;
		this.in=new Scanner(in);
		//Please initialize your data here
		
		this.root = new QNode(Strings.IS_IT_ALIVE);
		root.yes = new QNode(Strings.IS_IT_A + Strings.DUCK + "?", Strings.DUCK);
		root.no = new QNode(Strings.IS_IT_A + Strings.ROCK + "?", Strings.ROCK);
	}
	
    
    //plays the game, be sure to grab input from the Scanner "in", and send your output to "out".
	public void playGame()
	{
		//??
        out.println(root.question);
        String response = in.next();
        
        if (response.equals("Y")) { PlayGame(root.yes); }
        else { PlayGame(root.no); }
	}
	
	private void PlayGame(QNode curr)
	{
		out.println(curr.question);
		String response = in.next();
		
		if (response.equals("Y") && curr.yes != null) { PlayGame(curr.yes); }
		else if (response.equals("N") && curr.no != null) { PlayGame(curr.no); }
		
		else if (response.equals("Y") && curr.yes == null) { winGame(); } //win
		else if (response.equals("N") && curr.no == null) { loseGame(curr); }   //lose
		
	}
	
	public void winGame()
	{
		out.println(Strings.I_WIN);
		out.println(Strings.PLAY_AGAIN);
		String response = in.next();
		
		if (response.equals("Y")) { playGame(); }
		else return;
	}
	
	public void loseGame(QNode curr)
	{
		out.println(Strings.WHAT_IS_THE_ANSWER);
		String answer = in.next();
		
		QNode newQuestion = new QNode(Strings.IS_IT_A + answer + "?", answer);
		
		out.println(Strings.NEW_QUESTION + curr.guess +  " and a " + answer);
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
	
	
}
