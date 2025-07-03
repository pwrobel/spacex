import enums.MissionStatus;
import enums.RocketStatus;

import java.util.HashSet;
import java.util.Set;

public class Mission {
    private final String name;
    private MissionStatus status;
    private final Set<Rocket> assignedRockets;

    public Mission(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Mission name cannot be null or empty");
        }
        this.name = name;
        this.status = MissionStatus.SCHEDULED;
        this.assignedRockets = new HashSet<>();
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

    public Set<Rocket> getAssignedRockets() {
        return assignedRockets;
    }
}
