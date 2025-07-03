import java.util.*;

public class SpaceXRepository {
    private final Map<String, Rocket> rockets = new HashMap<>();
    private final Map<String, Mission> missions = new HashMap<>();

    private final Map<String, Set<String>> missionToRocketIds = new HashMap<>();
    private final Map<String, String> rocketToMissionId = new HashMap<>();

}
