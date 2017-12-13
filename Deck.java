import java.util.ArrayList;
import java.util.Random;

public class Deck{
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList<Card> openCard;
	public int nUsed;
	public Deck(int nDeck){
		cards=new ArrayList<Card>();//存放Card
		usedCard=new ArrayList<Card>();//存放發出過的Card
		openCard=new ArrayList<Card>();//存放開出的Card
		for(int d=0;d<nDeck;d++)//產生Card
		{
			for(Card.Suit s:Card.Suit.values())
	        {
	        	for(int j=1;j<14;j++)
	        	{
	        		Card card=new Card(s,j);
	        		cards.add(card);
	        	}
	        }
		}
		shuffle();//一開始洗牌
	}
	public void printDeck(){
		for(int i=0;i<cards.size();i++)
		{
			
			Card card = new Card (cards.get(i).getSuit(),cards.get(i).getRank());
			//將每一張card的數值實體化
			card.printCard();
			//使用printCard將實體化後的card顯示在螢幕上
		}
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public void shuffle(){//洗牌
		Random rnd = new Random();
		int j = rnd.nextInt(52);
		if(!usedCard.isEmpty())//重設usedCard跟nUsed
		{
			cards = usedCard;
			usedCard.clear();
			nUsed =0;
			openCard.clear();
		}
		for(int i=0;i<52;i++)
		{
			Card tmp = cards.get(i);//設一個變數存放原來的Card
			cards.set(i,cards.get(j));//將原來的Card設為隨機產生ArrayList位置的Card
			cards.set(j,tmp);//將隨機產生ArrayList位置的Card設為原來的Card
		}
	}
	public Card getOneCard(boolean isOpened){//發牌
		Random rnd = new Random();
		int i=52;
		int j = rnd.nextInt(i);
		if(usedCard.size()==52)//如果Card發完了,則洗牌後再發一張牌
		{
			shuffle();
			i=52;
			if(isOpened)//如果要開牌,將開過的牌放在openCard
				openCard.add(cards.get(j));
			nUsed++;
			usedCard.add(cards.get(j));
			cards.remove(j);
			i--;
		}
		else
		{
			if(isOpened)
				openCard.add(cards.get(j));
			nUsed++;
			usedCard.add(cards.get(j));
			cards.remove(j);
			i--;
		}
		return cards.get(j);
	}
	public ArrayList getOpenedCard()
	{
		return openCard;
	}
}