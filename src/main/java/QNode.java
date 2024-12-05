
public class QNode
{
	
	String question;
	String guess;
	
	QNode parent;
	QNode yes;
	QNode no;
	
	public QNode(String question)
	{
		this.question = question;
	}
	
	public QNode(String question, String guess, QNode parent)
	{
		this.question = question;
		this.guess = guess;
		this.parent = parent;
	}
	
	public QNode(String question, QNode parent, QNode yes, QNode no)
	{
		this.question = question;
		this.parent = parent;
		this.yes = yes;
		this.no = no;
	}
	
	public QNode(String question, String guess, QNode parent, QNode yes, QNode no)
	{
		this.question = question;
		this.guess = guess;
		this.parent = parent;
		this.yes = yes;
		this.no = no;
	}

}
