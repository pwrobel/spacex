import enums.RocketStatus;

public class Rocket {
    private final String name;
    private RocketStatus status;

    public Rocket(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Rocket name cannot be null or empty");
        }
        this.name = name;
        this.status = RocketStatus.ON_GROUND;
    }

    public String getName() {
        return name;
    }

    public RocketStatus getStatus() {
        return status;
    }

    public void setStatus(RocketStatus status) {
        this.status = status;
    }
}
