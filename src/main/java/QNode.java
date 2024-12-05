
public class QNode
{
	
	String question;
	String guess;
	QNode yes;
	QNode no;
	
	public QNode(String question)
	{
		this.question = question;
	}
	
	public QNode(String question, String guess)
	{
		this.question = question;
		this.guess = guess;
	}
	
	public QNode(String question, QNode yes, QNode no)
	{
		this.question = question;
		this.yes = yes;
		this.no = no;
	}
	
	public QNode(String question, String guess, QNode yes, QNode no)
	{
		this.question = question;
		this.guess = guess;
		this.yes = yes;
		this.no = no;
	}

}
