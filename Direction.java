import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public enum Direction {
    EAST(1, 0), SOUTH(0, 1), SOUTH_EAST(1, 1), WEST(-1, 0),
    NORTH(0, -1), NORTH_WEST(-1, -1), NORTH_EAST(1, -1),
    SOUTH_WEST(-1, 1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private static final Map<String, Direction> directionMap = new HashMap<>();

    static {
        for (Direction direction : values()) {
            directionMap.put(direction.name().toLowerCase(), direction);
        }
    }

    private static final Random random = new Random();

    public static Optional<Direction> getDirection(String dirString) {
        return directionMap.get(dirString) == null ? Optional.empty() : Optional.of(directionMap.get(dirString));
    }

    public static Direction getRandomDirection() {
        return values()[random.nextInt(values().length)];
    }
}
