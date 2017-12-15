import java.util.ArrayList;

public class Player extends Person {
	private String name;//player's name
	private int chips;//player's chips
	private int bet;//player's bet
	private Table table = new Table();//new table(count table's card)
	public Player(String name, int chips){//playerªºµ²ºc
		this.name = name;
		this.chips = chips;
	}
	public String getName(){//know player's name
		return name;
	}
	public int makeBet(){//know player's bet
		int f1=1,f2=1,f3=0;
		f3 = f1+f2;
		f1 = f2;
		f2 = f3;
		if(chips==0)
			return 0;
		else
			bet = bet+f3;
		return bet;
		
	}

	public boolean hit_me(Table table){//ask card
		int count=0;
		for(Card c:this.table.gettable_card()){//count table's card
			if(c.getRank()<7&&c.getRank()>1)
				count++;
			else if(c.getRank()>9)
				count--;
			else if(c.getRank()==1)
				count+=2;
			else
				count+=0;
		}
		if(getTotalValue()>17||count>0)
			return false;
		else
			return true;
		
	}
	
	public int getCurrentChips(){//know how many chips player have 
		return chips;
	}
	public void increaseChips (int diff){//change chips
		if(diff<0)
			chips = chips-bet;
		else
			chips = chips+bet;
	}
	public void sayHello(){//make player say hello
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}

}
