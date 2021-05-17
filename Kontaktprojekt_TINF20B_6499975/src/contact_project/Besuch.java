package contact_project;

import java.time.LocalDateTime;

public class Besuch {

    private final LocalDateTime StartDate;
    private final LocalDateTime EndDate;
    private final int PersonId;
    private final int LocationId;

    public Besuch(LocalDateTime startDate, LocalDateTime endDate, int personId, int locationId)
    {
        StartDate = startDate;
        EndDate = endDate;
        PersonId = personId;
        LocationId = locationId;
    }

    public LocalDateTime getStartDate() {
        return StartDate;
    }

    public LocalDateTime getEndDate() {
        return EndDate;
    }

    public int getPersonId() {
        return PersonId;
    }

    public int getLocationId() {
        return LocationId;
    }
}