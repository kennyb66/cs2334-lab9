import java.util.Objects;

public class Card implements Comparable<Card> {
	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) throws NullPointerException {

		this.rank = rank;
		this.suit = suit;
		if (rank == null || suit == null) {
			throw new NullPointerException();
		}
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public int compareTo(Card card) {
		int comparison;
		comparison = getSuit().compareTo(card.getSuit());
		if (comparison == 0) {
			comparison = getRank().compareTo(card.getRank());
		}
		return comparison;
	}

	public String toString() {
		return rank.toString() + suit.toString();

	}

	public int hashCode() {
		return Objects.hash(rank, suit);
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if (!(obj instanceof Card)) {
			result = false;
		} else {
			Card card = (Card) obj;
			result = rank.equals(card.rank) && suit.equals(card.suit);

		}

		return result;
	}

}
