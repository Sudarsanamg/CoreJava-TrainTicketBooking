public class Passenger {

    static int id=1;
    String name;
    int age;
    char berthPreference;
    int passengerId;
    int seatNumber;
    String givenBerth;

   public Passenger(String name,int age, char berth)
    {
        this.name=name;
        this.age=age;
        this.berthPreference=berth;
        this.passengerId=id++;
        givenBerth="";
        seatNumber=-1;
    }
}
