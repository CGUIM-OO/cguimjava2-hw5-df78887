import java.util.ArrayList;

public class Table {
	final int MAXPLAYER = 4;//最多4個player
	private Deck allcard;//table's cards
	private Player[] allplayer;//4個player
	private Dealer dealer;//莊家
	private int[] pos_betArray = new int[4];//players' bets
	private ArrayList<Card> tablecard = new ArrayList<Card>();//count table's cards
	public Table(int nDeck){//Table的結構
		allcard =new Deck(nDeck);
		allplayer = new Player[MAXPLAYER];
	}
	
	public Table(){//算牌時用的Table結構
		
	}
	
	public void set_player(int pos, Player p){
		allplayer[pos] = p;
	}
	
	public Player[] get_player(){
		return allplayer;
	}
	
	public void set_dealer(Dealer d){
		dealer = d;
	}
	
	public Card get_face_up_card_of_dealer(){//show dealer's open card
		return dealer.getOneRoundCard().get(1);
	}
	
	private void ask_each_player_about_bets(){//know players' bets
		for(int i=0;i<MAXPLAYER;i++){
			allplayer[i].sayHello();
			pos_betArray[i] = allplayer[i].makeBet();
		}
		
	}
	
	private void distribute_cards_to_dealer_and_players(){//發2張牌
		for(int i=0;i<MAXPLAYER;i++){
			allplayer[i].getOneRoundCard().add(allcard.getOneCard(true));
			tablecard.add(allplayer[i].getOneRoundCard().get(0));
			allplayer[i].getOneRoundCard().add(allcard.getOneCard(true));
			tablecard.add(allplayer[i].getOneRoundCard().get(1));
			allplayer[i].setOneRoundCard(allplayer[i].getOneRoundCard());
		}
		dealer.getOneRoundCard().add(allcard.getOneCard(true));
		tablecard.add(dealer.getOneRoundCard().get(0));
		dealer.getOneRoundCard().add(allcard.getOneCard(false));
		System.out.print("Dealer's face up card is ");
		get_face_up_card_of_dealer().printCard();
	}
	
	private void ask_each_player_about_hits(){//player ask card
		for(int i=0;i<MAXPLAYER;i++){
			boolean hit=false;
			System.out.println(allplayer[i].getName()+"'s Cards now:");
			for(Card c : allplayer[i].getOneRoundCard()){
				c.printCard();
			}
			do{
				hit=allplayer[i].hit_me(this); //this
				if(hit){
					allplayer[i].getOneRoundCard().add(allcard.getOneCard(true));
					allplayer[i].setOneRoundCard(allplayer[i].getOneRoundCard());
					System.out.print("Hit! ");
					System.out.println(allplayer[i].getName()+"'s Cards now:");
					for(Card c : allplayer[i].getOneRoundCard()){
						c.printCard();
					}
				}
				else{
					System.out.println("Pass hit!");
					System.out.println(allplayer[i].getName()+"'s hit is over!");
				}
			}while(hit);
		}
	}
	
	private void ask_dealer_about_hits(){//dealer ask card
		boolean hit=false;
		do{
			hit=dealer.hit_me(this); //this call table
			if(hit){
				dealer.getOneRoundCard().add(allcard.getOneCard(true));
				dealer.setOneRoundCard(dealer.getOneRoundCard());
			}
			else{
				
				System.out.println("Dealer's hit is over!");
			}
		}while(hit);
	}
	
	private void calculate_chips(){//know who win or lose
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");
		dealer.printAllCard();
		for(int i=0;i<MAXPLAYER;i++){
			if(dealer.getTotalValue()>21 && allplayer[i].getTotalValue()>21){
				System.out.println(allplayer[i].getName()+"'s Cards:");
				for(Card c : allplayer[i].getOneRoundCard()){
					c.printCard();
				}
				System.out.println(allplayer[i].getName()+" card value is "+allplayer[i].getTotalValue()+",chips have no change! The Chips now is: "+allplayer[i].getCurrentChips());
			}else if(dealer.getTotalValue()<=21&&allplayer[i].getTotalValue()>21){
				System.out.println(allplayer[i].getName()+"'s Cards:");
				for(Card c : allplayer[i].getOneRoundCard()){
					c.printCard();
				}
				allplayer[i].increaseChips(-pos_betArray[i]);
				System.out.println(allplayer[i].getName()+" card value is "+allplayer[i].getTotalValue()+", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+allplayer[i].getCurrentChips());
			}else if(dealer.getTotalValue()>21&&allplayer[i].getTotalValue()<=21){
				System.out.println(allplayer[i].getName()+"'s Cards:");
				for(Card c : allplayer[i].getOneRoundCard()){
					c.printCard();
				}
				allplayer[i].increaseChips(pos_betArray[i]);
				System.out.println(allplayer[i].getName()+" card value is "+allplayer[i].getTotalValue()+",Get "+pos_betArray[i]+" Chips, the Chips now is: "+allplayer[i].getCurrentChips());
			}else if(dealer.getTotalValue()>allplayer[i].getTotalValue()&&dealer.getTotalValue()<=21){
				System.out.println(allplayer[i].getName()+"'s Cards:");
				for(Card c : allplayer[i].getOneRoundCard()){
					c.printCard();
				}
				allplayer[i].increaseChips(-pos_betArray[i]);
				System.out.println(allplayer[i].getName()+" card value is "+allplayer[i].getTotalValue()+", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+allplayer[i].getCurrentChips());
			}else if(dealer.getTotalValue()<allplayer[i].getTotalValue()&&allplayer[i].getTotalValue()<=21){
				System.out.println(allplayer[i].getName()+"'s Cards:");
				for(Card c : allplayer[i].getOneRoundCard()){
					c.printCard();
				}
				allplayer[i].increaseChips(pos_betArray[i]);
				System.out.println(allplayer[i].getName()+" card value is "+allplayer[i].getTotalValue()+",Get "+pos_betArray[i]+" Chips, the Chips now is: "+allplayer[i].getCurrentChips());
			}else{
				System.out.println(allplayer[i].getName()+"'s Cards:");
				for(Card c : allplayer[i].getOneRoundCard()){
					c.printCard();
				}
				System.out.println(allplayer[i].getName()+" card value is "+allplayer[i].getTotalValue()+",chips have no change! The Chips now is: "+allplayer[i].getCurrentChips());
			}
		}
		
	}
	
	public int[] get_players_bet(){
		return pos_betArray;
	}
	
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	
	public ArrayList<Card> gettable_card(){
		return tablecard;
	}
}
