import enums.MissionStatus;
import enums.RocketStatus;

import java.util.*;

public class SpaceXRepository {
    private final Map<String, Rocket> rockets = new HashMap<>();
    private final Map<String, Mission> missions = new HashMap<>();

    private final Map<String, Set<String>> missionToRocketIds = new HashMap<>();
    private final Map<String, String> rocketToMissionId = new HashMap<>();

    public void addRocket(String rocketId) {
        rockets.computeIfAbsent(rocketId, Rocket::new);
    }

    public void addMission(String missionId) {
        missions.computeIfAbsent(missionId, Mission::new);
    }

    public void assignRocketToMission(String rocketId, String missionId) {
        Rocket rocket = rockets.get(rocketId);
        Mission mission = missions.get(missionId);

        if (rocket == null) throw new NoSuchElementException("Rocket '" + rocketId + "' not found.");
        if (mission == null) throw new NoSuchElementException("Mission '" + missionId + "' not found.");
        if (rocketToMissionId.containsKey(rocketId)) throw new IllegalStateException("Rocket '" + rocketId + "' is already assigned.");
        if (mission.getStatus() == MissionStatus.ENDED) throw new IllegalStateException("Cannot assign to an ended mission.");

        rocketToMissionId.put(rocketId, missionId);
        missionToRocketIds.computeIfAbsent(missionId, k -> new HashSet<>()).add(rocketId);

        rocket.setStatus(RocketStatus.IN_SPACE);
        updateMissionStatus(mission);
    }

    private void updateMissionStatus(Mission mission) {
        if (mission.getStatus() == MissionStatus.ENDED) {
            return;
        }

        Set<String> assignedRocketIds = missionToRocketIds.getOrDefault(mission.getName(), Collections.emptySet());

        if (assignedRocketIds.isEmpty()) {
            mission.setStatus(MissionStatus.SCHEDULED);
            return;
        }

        boolean hasRocketInRepair = assignedRocketIds.stream()
                .map(rockets::get)
                .anyMatch(rocket -> rocket.getStatus() == RocketStatus.IN_REPAIR);

        mission.setStatus(hasRocketInRepair ? MissionStatus.PENDING : MissionStatus.IN_PROGRESS);
    }

    public void changeRocketStatus(String rocketId, RocketStatus newStatus) {
        Rocket rocket = rockets.get(rocketId);
        if (rocket == null) throw new NoSuchElementException("Rocket '" + rocketId + "' not found.");

        rocket.setStatus(newStatus);

        String missionId = rocketToMissionId.get(rocketId);
        if (missionId != null) {
            updateMissionStatus(missions.get(missionId));
        }
    }

    public void endMission(String missionId) {
        Mission mission = missions.get(missionId);
        if (mission == null) throw new NoSuchElementException("Mission '" + missionId + "' not found.");

        mission.setStatus(MissionStatus.ENDED);

        Set<String> assignedRocketIds = missionToRocketIds.getOrDefault(missionId, Collections.emptySet());
        for (String rocketId : assignedRocketIds) {
            rocketToMissionId.remove(rocketId);
            Rocket rocket = rockets.get(rocketId);
            if(rocket != null) {
                rocket.setStatus(RocketStatus.ON_GROUND);
            }
        }
        missionToRocketIds.remove(missionId);
    }
}
