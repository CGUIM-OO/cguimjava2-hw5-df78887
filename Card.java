public class Card{
	private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */
	enum Suit {Club,Diamond,Heart,Spade};//���wSuit�u�঳Club,Diamond,Heart,Spade
	public Card(Suit s,int r){
		suit=s;
		rank=r;
		
	}
	public void printCard(){
		
		switch(suit)//�p�GCard��Suit�OClub�h���Club,�p�GCard��Suit�ODiamond�h���Diamond,�p�GCard��Suit�OHeart�h���Heart,�p�GCard��Suit�OSpade�h���Spade
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
		if(rank==1)//�p�GCard��Rank�O1�h���Ace,�_�h��ܭ�Ӫ�rank
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