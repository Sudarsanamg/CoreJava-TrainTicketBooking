import java.util.Scanner;

public class Main {

    private static void printTheAvailableCount() {
        Backend.printTheAvailableCount();
    }

    private static  void  bookTicketFor(Passenger p)
    {
        Backend backend=new Backend();
        if(Backend.availableWaitingList==0) {
            System.out.println("Sorry No tickets is available");
            return;
        }
        //book the desired berth
        else if (p.berthPreference=='U'&& Backend.availableUpperBerths >0 || p.berthPreference=='M' && Backend.availableMiddleBerths >0 || p.berthPreference=='L' && Backend.availableLowerBerths>0) {

            if(p.berthPreference=='U'&& Backend.availableUpperBerths >0)
            {
                p.givenBerth="U";
                p.seatNumber=backend.AvailableUpperBerth.get(0);
                backend.AvailableUpperBerth.remove(0);
                Backend.bookedTicket.put(p.passengerId,p);
                --Backend.availableUpperBerths;
                System.out.println("Ticked booked");

            }
            else if ( p.berthPreference=='M' && Backend.availableMiddleBerths >0) {
                p.givenBerth="M";
                p.seatNumber=backend.AvailableMiddleBerth.get(0);
                backend.AvailableMiddleBerth.remove(0);
                Backend.bookedTicket.put(p.passengerId,p);
                --Backend.availableMiddleBerths;
                System.out.println("Ticked booked");

            }
            else
            {
                p.givenBerth="L";
                p.seatNumber=backend.AvailableLowerBerth.get(0);
                backend.AvailableLowerBerth.remove(0);
                Backend.bookedTicket.put(p.passengerId,p);
                --Backend.availableLowerBerths;

                System.out.println("Ticked booked");
            }
            return;

        }
        //RAC
        else if ( Backend.availableLowerBerths+Backend.availableMiddleBerths+Backend.availableUpperBerths <= 0)
        {
            System.out.println("RAC block");
            if(Backend.availableRacTickets!=0)
            {
                p.givenBerth="RAC";
                --Backend.availableRacTickets;
                int id=p.passengerId;
                Backend.racList.add(id);
                Backend.bookedTicket.put(id,p);
                System.out.println("Ticket booked successfully");
            }
            else
            {
                p.givenBerth="WL";
                --Backend.availableWaitingList;
                int id=p.passengerId;
                Backend.waitingList.add(id);
                Backend.bookedTicket.put(id,p);
                System.out.println("Ticket booked successfully");

            }
            return;

        }
//        book the available berth
        else if (p.berthPreference=='U'&& Backend.availableUpperBerths == 0 || p.berthPreference=='M' && Backend.availableMiddleBerths ==0 || p.berthPreference=='L' && Backend.availableLowerBerths == 0) {

            if(p.berthPreference=='U'&& Backend.availableUpperBerths ==0)
            {
                if(Backend.availableMiddleBerths !=0)
                {
                    p.givenBerth="M";
                    p.seatNumber=backend.AvailableMiddleBerth.get(0);
                    backend.AvailableMiddleBerth.remove(0);
                    Backend.bookedTicket.put(p.passengerId,p);
                    --Backend.availableMiddleBerths;
                    System.out.println("Ticked booked");
                }
                else if(Backend.availableLowerBerths !=0)
                {
                    p.givenBerth="L";
                    p.seatNumber=backend.AvailableLowerBerth.get(0);
                    backend.AvailableLowerBerth.remove(0);
                    Backend.bookedTicket.put(p.passengerId,p);
                    --Backend.availableLowerBerths;

                    System.out.println("Ticked booked");

                }
                return;

            }
            else if ( p.berthPreference=='M' && Backend.availableMiddleBerths ==0) {
                if(Backend.availableLowerBerths!=0)
                {
                    p.givenBerth="L";
                    p.seatNumber=backend.AvailableLowerBerth.get(0);
                    backend.AvailableLowerBerth.remove(0);
                    Backend.bookedTicket.put(p.passengerId,p);
                    --Backend.availableLowerBerths;

                    System.out.println("Ticked booked");
                }
                else if(Backend.availableUpperBerths !=0)
                {
                    p.givenBerth="U";
                    p.seatNumber=backend.AvailableUpperBerth.get(0);
                    backend.AvailableUpperBerth.remove(0);
                    Backend.bookedTicket.put(p.passengerId,p);
                    --Backend.availableUpperBerths;
                    System.out.println("Ticked booked");
                }
                return;

            }
            else if(p.berthPreference=='L' && Backend.availableLowerBerths==0)
            {
                if(Backend.availableUpperBerths !=0)
                {
                    p.givenBerth="U";
                    p.seatNumber=backend.AvailableUpperBerth.get(0);
                    backend.AvailableUpperBerth.remove(0);
                    Backend.bookedTicket.put(p.passengerId,p);
                    --Backend.availableUpperBerths;
                    System.out.println("Ticked booked");
                }
                else if(Backend.availableMiddleBerths !=0)
                {
                    p.givenBerth="M";
                    p.seatNumber=backend.AvailableMiddleBerth.get(0);
                    backend.AvailableMiddleBerth.remove(0);
                    Backend.bookedTicket.put(p.passengerId,p);
                    --Backend.availableMiddleBerths;
                    System.out.println("Ticked booked");
                }
                return;
            }

        }

        // book the RAC and the waiting list

        else
        {
            System.out.println("No tickets is available");
            return;

        }
    }

    private static void cancelTheTicketFor(String name, int id) {
        Passenger cancelledPassenger=Backend.bookedTicket.get(id);
        String berth= cancelledPassenger.givenBerth;
        if(Backend.racList.isEmpty())
        {
            Backend.bookedTicket.remove(id);
            switch (berth)
            {
                case "L":
                    ++Backend.availableLowerBerths;
                    break;
                case "M":
                    ++Backend.availableMiddleBerths;
                    break;
                case "U":
                    ++Backend.availableUpperBerths;
                    break;
            }

        }
        else
        {
            Backend.bookedTicket.remove(id);
            String givenBerth= cancelledPassenger.givenBerth;
            int seatNo= cancelledPassenger.seatNumber;
            int firstPersonId=Backend.racList.peek();
            Backend.racList.remove();
            Passenger p=Backend.bookedTicket.get(firstPersonId);
            p.seatNumber=seatNo;
            p.givenBerth=givenBerth;
            Backend.bookedTicket.put(firstPersonId,p);
            ++Backend.availableRacTickets;

            if(!Backend.waitingList.isEmpty())
            {
                --Backend.availableRacTickets;
                int waitingListId=Backend.waitingList.peek();
                Passenger passengerShiftingToRAC=Backend.bookedTicket.get(waitingListId);
                passengerShiftingToRAC.givenBerth="RAC";
                Backend.racList.add(waitingListId);
                Backend.waitingList.remove();
                ++Backend.availableWaitingList;
            }


        }


    }

    private static void printTheBookedTickets()
    {
        Backend backend=new Backend();
        backend.printTheBookedTickets();

    }

    public static void main(String[] args) {
        String name;

        while(true) {
            System.out.println("Welcome to Railway Booking system");
            System.out.println("\n 1.Booking the ticket \n 2.Cancel The ticket \n 3.Print the Booked Ticket \n 4.Print the available ticket \n 5.Exit\n");

            //get input from user

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //book
                        System.out.println("Enter your name");
                        sc.nextLine();
                        name = sc.nextLine();

                        System.out.println("Enter your age:");
                        int age = sc.nextInt();
//                    int age = 18;
                    System.out.println("Enter berth preference L M U ");
                    char berth = sc.next().charAt(0);
//                    char berth = 'L';
                    Passenger p = new Passenger(name, age, berth);
                    bookTicketFor(p);
                    break;

                case 2:
                    System.out.println("Enter your Name");
                    sc.nextLine();
                    name=sc.nextLine();
                    System.out.println("Enter your ID");
                    int id=sc.nextInt();

                    cancelTheTicketFor(name,id);
                    break;

                case 3:
                    printTheBookedTickets();
                    break;
                case 4:
                    printTheAvailableCount();
                    break;

                case 5:
                    System.exit(0);

            }
        }
        

    }




}
