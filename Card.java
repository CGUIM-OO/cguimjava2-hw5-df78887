public class Card{
	private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */
	enum Suit {Club,Diamond,Heart,Spade};//指定Suit只能有Club,Diamond,Heart,Spade
	public Card(Suit s,int r){
		suit=s;
		rank=r;
		
	}
	public void printCard(){
		
		switch(suit)//如果Card的Suit是Club則顯示Club,如果Card的Suit是Diamond則顯示Diamond,如果Card的Suit是Heart則顯示Heart,如果Card的Suit是Spade則顯示Spade
		{
		case Club:
			{
				System.out.print("Club ");
				break;
			}
		case Diamond:
			{
				System.out.print("Diamond ");
				break;
			}
		case Heart:
			{
				System.out.print("Heart ");
				break;
			}
		case Spade:
			{
				System.out.print("Spade ");
				break;
			}
		}
		if(rank==1)//如果Card的Rank是1則顯示Ace,否則顯示原來的rank
		{
			System.out.println("Ace");
		}
		else
		System.out.println(rank);
		
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}