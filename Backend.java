import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.*;

public class Backend {

    static int availableLowerBerths = 1;//normally 21
    static int availableMiddleBerths = 1;//normally 21
    static int availableUpperBerths = 1;//normally 21
    static int availableRacTickets = 1;//normally 18
    static int availableWaitingList = 1;//normally 10

    static  int totalSeats;

    public static void printTheAvailableCount() {
        System.out.println("Seats in lower :"+availableLowerBerths);
        System.out.println("Middle" +availableMiddleBerths);
        System.out.println("Upper"+availableUpperBerths);
        System.out.println("RAC"+availableRacTickets);
        System.out.println("Waiting" +availableWaitingList);

    }

    public void updateSeats()
    {
        totalSeats=availableLowerBerths+availableMiddleBerths+availableUpperBerths+availableRacTickets+availableWaitingList;
    }

    static Queue<Integer> racList =  new LinkedList<>();//queu of RAC passengers
    static Queue<Integer> waitingList = new LinkedList<>();//queue of WL passengers
    static List<Integer> bookedTicketList =  new ArrayList<>();//list of bookedticket passengers

    List<Integer> AvailableLowerBerth=new ArrayList<>(Arrays.asList(1));
    List<Integer> AvailableMiddleBerth=new ArrayList<>(Arrays.asList(1));
    List<Integer> AvailableUpperBerth=new ArrayList<>(Arrays.asList(1));
    static Map<Integer,Passenger> bookedTicket=new HashMap<>();


    public void printTheBookedTickets() {
        int length=bookedTicket.size();

        Set<Integer> keys=bookedTicket.keySet();

        for (int i:keys) {
             Passenger p=bookedTicket.get(i);
             String name=p.name;
             int age=p.age;
             int passengerId=p.passengerId;
             String givenBerth=p.givenBerth;
             int seatNumber=p.seatNumber;

            System.out.println("Name :" + name +"\n passenger Id:"+passengerId+"\n Given berth:"+givenBerth+"\n Seatnumber:"+seatNumber);

        }


    }
}
