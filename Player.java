import java.util.ArrayList;

public class Player {
	private String name;
	private int chips;
	private int bet;
	private ArrayList<Card> oneRoundCard;
	public Player(String name, int chips){
		this.name = name;
		this.chips = chips;
	}
	public String getName(){
		return name;
	}
	public int makeBet(){
		if(chips==0)
			return 0;
		else
			bet++;
		return bet;
	}
	public void setOneRoundCard(ArrayList cards){
		oneRoundCard=cards;
	}
	public boolean hitMe(){
		if(getTotalValue()>17)
			return false;
		else
			return true;
	}
	public int getTotalValue(){
		
		int sum = 0;
		for(int i=0;i<oneRoundCard.size();i++)
		{
			if(oneRoundCard.get(i).getRank()>10)
				sum +=10;
			else if(oneRoundCard.get(i).getRank()==1&&oneRoundCard.get(i).getSuit()==Card.Suit.Spade)
			{
				if(sum==20)
					sum +=1;
				else if(sum<=10)
					sum +=11;
			}
			else
			sum += oneRoundCard.get(i).getRank();
		}
		return sum;
	}
	public int getCurrentChips(){
		return chips;
	}
	public void increaseChips (int diff){
		if(diff<0)
			chips = chips-bet;
		else
			chips = chips+bet;
	}
	public void sayHello(){
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}

}
