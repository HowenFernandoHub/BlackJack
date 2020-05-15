public interface DeckOperations<T> {
    void populate();

    void shuffle();

    T draw();

    void clearCards();
}
