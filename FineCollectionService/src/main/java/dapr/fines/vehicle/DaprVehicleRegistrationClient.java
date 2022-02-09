package dapr.fines.vehicle;

import io.dapr.client.DaprClient;
import io.dapr.client.domain.HttpExtension;

import java.time.Duration;

public class DaprVehicleRegistrationClient implements VehicleRegistrationClient {

    private final DaprClient daprClient;

    public DaprVehicleRegistrationClient(final DaprClient daprClient) {
        this.daprClient = daprClient;
    }

    @Override
    public VehicleInfo getVehicleInfo(String licenseNumber) {

        return daprClient.invokeMethod(
                "vehicleregistrationservice",
                "vehicleinfo/" + licenseNumber,
                null,
                HttpExtension.GET,
                VehicleInfo.class
        ).block(Duration.ofMillis(1000));
    }
}
