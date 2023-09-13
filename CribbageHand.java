import java.util.*;

public class CribbageHand {
	public final List<Card> cards;
	public static final Map<Rank, Integer> CARD_VALUES = Map.ofEntries(Map.entry(Rank.ACE, 1), Map.entry(Rank.TWO, 2),
			Map.entry(Rank.THREE, 3), Map.entry(Rank.FOUR, 4), Map.entry(Rank.FIVE, 5), Map.entry(Rank.SIX, 6),
			Map.entry(Rank.SEVEN, 7), Map.entry(Rank.EIGHT, 8), Map.entry(Rank.NINE, 9), Map.entry(Rank.TEN, 10),
			Map.entry(Rank.JACK, 10), Map.entry(Rank.QUEEN, 10), Map.entry(Rank.KING, 10));

	public CribbageHand(Card c1, Card c2, Card c3, Card c4) throws NullPointerException {
		if (c1 == null || c2 == null || c3 == null || c4 == null) {
			throw new NullPointerException();
		}
		Card[] cardArray = { c1, c2, c3, c4 };
		cards = List.<Card>of(cardArray);
	}

	public static Set<Set<Card>> powerSet(List<Card> cards) {
		Set<Set<Card>> powerCards = new HashSet<Set<Card>>();
		if (cards.isEmpty()) {
			powerCards.add(new HashSet<Card>());
			return powerCards;
		}
		List<Card> list = new ArrayList<Card>(cards);
		Card head = list.get(0);
		Set<Card> rest = new HashSet<Card>(list.subList(1, list.size()));
		List<Card> restList = new ArrayList<Card>(rest);
		for (Set<Card> set : powerSet(restList)) {
			Set<Card> newSet = new HashSet<Card>();
			newSet.add(head);
			newSet.addAll(set);
			powerCards.add(newSet);
			powerCards.add(set);
		}
		return powerCards;
	}

	public Set<Set<Card>> fifteens(Card Starter) {
		Set<Set<Card>> fifteenSets = new HashSet<Set<Card>>();
		int sum = 0;
		Set<Set<Card>> cardsSet = powerSet(cards);
		for (Set<Card> subSet : cardsSet) {
			for (Card card : subSet) {
				sum += CARD_VALUES.get(card.getRank());
			}
			if (sum + CARD_VALUES.get(Starter.getRank()) == 15) {
				subSet.add(Starter);
				fifteenSets.add(subSet);
			} else if (sum == 15)
				fifteenSets.add(subSet);
			sum = 0;
		}
		return fifteenSets;
	}
}