import enums.RocketStatus;

public class Rocket {
    private String name;
    private RocketStatus status;
    private Mission assignedMission;

    public Rocket(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Rocket name cannot be null or empty");
        }
        this.name = name;
        this.status = RocketStatus.ON_GROUND;
        this.assignedMission = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RocketStatus getStatus() {
        return status;
    }

    public void setStatus(RocketStatus status) {
        this.status = status;
    }

    public Mission getAssignedMission() {
        return assignedMission;
    }

    public void setAssignedMission(Mission assignedMission) {
        this.assignedMission = assignedMission;
    }
}
