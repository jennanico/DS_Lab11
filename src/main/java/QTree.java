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
		root.yes = new QNode(Strings.IS_IT_A + Strings.DUCK + "?", Strings.DUCK, root);
		root.no = new QNode(Strings.IS_IT_A + Strings.ROCK + "?", Strings.ROCK, root);
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
		String newGuess = in.next();
		
		QNode newQuestionNode = new QNode(null);
		QNode newGuessNode = new QNode(Strings.IS_IT_A + newGuess + "?", newGuess, newQuestionNode);
		
		out.println(Strings.NEW_QUESTION + curr.guess +  " and a " + newGuess);
		String newQuestion1 = in.next();
		String newQuestion2 = in.nextLine();
		String newQuestion = newQuestion1 + newQuestion2;
		
		out.println("Answering yes to " + newQuestion + " means " + newGuess + "?");
		String link = in.next();
		
		if (link.equals("Y")) 
		{ 
			newQuestionNode.question = newQuestion;
			newQuestionNode.parent = curr.parent;
			newQuestionNode.yes = newGuessNode;
			newQuestionNode.no = curr;
			
			if (curr.parent.yes == curr) { curr.parent.yes = newQuestionNode; }
			else { curr.parent.no = newQuestionNode; }
			curr.parent = newQuestionNode;
		}
		else if (link.equals("N"))
		{ 
			newQuestionNode.question = newQuestion;
			newQuestionNode.parent = curr.parent;
			newQuestionNode.no = newGuessNode;
			newQuestionNode.yes = curr;

			if (curr.parent.yes == curr) { curr.parent.yes = newQuestionNode; }
			else { curr.parent.no = newQuestionNode; }
			curr.parent = newQuestionNode;
		}
		
		out.println(Strings.THANKS);
		out.println(Strings.PLAY_AGAIN);
		String response = in.next();
		
		if (response.equals("Y")) { playGame(); }
		else return;
		
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
	
	
}
