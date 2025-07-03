import enums.MissionStatus;

public class Mission {
    private final String name;
    private MissionStatus status;

    public Mission(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Mission name cannot be null or empty");
        }
        this.name = name;
        this.status = MissionStatus.SCHEDULED;
    }

    public String getName() {
        return name;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }
}
