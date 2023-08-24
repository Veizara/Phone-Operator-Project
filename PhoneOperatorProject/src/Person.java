import java.util.List;

class Person extends Thread {
    private int personId;
    private PhoneStationManager phoneStationManager;
    private List<Integer> friendIdList;


    public Person(int personId, PhoneStationManager phoneStationManager, List<Integer> friendIdList) {
        this.personId = personId;
        this.phoneStationManager = phoneStationManager;
        this.friendIdList = friendIdList;
    }

    @Override
    public void run() {
        try {
            while (!friendIdList.isEmpty()) {
                int randomFriendIndex = (int) (Math.random() * friendIdList.size());
                int friendId = this.friendIdList.get(randomFriendIndex);
                friendIdList.remove(randomFriendIndex);
                phoneStationManager.makeCall(personId, friendId);
                Thread.sleep(1000); // Pause between making each call
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}