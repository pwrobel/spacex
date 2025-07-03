import enums.RocketStatus;

public class Main {
    public static void main(String[] args) {
        SpaceXRepository repository = new SpaceXRepository();

        // Add rockets and missions
        repository.addMission("Mars");
        repository.addMission("Luna1");
        repository.addMission("Double Landing");
        repository.addMission("Transit");
        repository.addMission("Luna2");
        repository.addMission("Vertical Landing");

        repository.addRocket("Dragon 1");
        repository.addRocket("Dragon 2");
        repository.addRocket("Red Dragon");
        repository.addRocket("Dragon XL");
        repository.addRocket("Falcon Heavy");
        repository.addRocket("Dragon 3");

        repository.assignRocketToMission("Red Dragon", "Transit");
        repository.assignRocketToMission("Dragon XL", "Transit");
        repository.assignRocketToMission("Falcon Heavy", "Transit");
        repository.changeRocketStatus("Red Dragon", RocketStatus.ON_GROUND);

        repository.assignRocketToMission("Dragon 1", "Luna1");
        repository.assignRocketToMission("Dragon 2", "Luna1");
        repository.changeRocketStatus("Dragon 2", RocketStatus.IN_REPAIR);

        repository.endMission("Double Landing");
        repository.endMission("Vertical Landing");

        System.out.println(repository.getSummary());
    }
}