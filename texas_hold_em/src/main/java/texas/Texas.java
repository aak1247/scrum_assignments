package texas;

public interface Texas {
    public String battle(String black, String white);
    public default boolean isBlackWin(String black, String white) {
        var battleRes = battle(black, white);
        return battleRes.contains("Black");
    }
    public default boolean isWhiteWin(String black, String white) {
        var battleRes = battle(black, white);
        return battleRes.contains("White");
    }
    public default boolean isTie(String black, String white) {
        var battleRes = battle(black, white);
        return battleRes.contains("Tie");
    }
}
