import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Integer> personIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> friendIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        Collections.shuffle(personIds);
        Collections.shuffle(friendIds);


        PhoneStationManager phoneStationManager = new PhoneStationManager();

        for (int personId : personIds) {
            List<Integer> clonedFriendIds = new ArrayList<>(friendIds);
            Person person = new Person(personId, phoneStationManager, clonedFriendIds);
            person.start();
        }
    }
}