import frames.MainFrame;
import services.AddressService;
import services.PickupPointService;
import services.ReadingService;
import utils.JsonDataContext;

public class Main  {

    public static void main(String[] args) {
        run();
    }

    private static void run(){
        var dataContext = new JsonDataContext();
        var addressService = new AddressService(dataContext);
        var pickupService = new PickupPointService(dataContext);
        var readingService = new ReadingService(dataContext);
        new MainFrame(addressService, pickupService, readingService);
    }
}
