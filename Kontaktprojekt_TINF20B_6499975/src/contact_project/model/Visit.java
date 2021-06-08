package contact_project.model;

import java.time.LocalDateTime;

public class Visit {

    private final LocalDateTime StartDate;
    private final LocalDateTime EndDate;
    private final int PersonId;
    private final int LocationId;

    //constructor for creating new visit object
    public Visit(LocalDateTime startDate, LocalDateTime endDate, int personId, int locationId)
    {
        StartDate = startDate;
        EndDate = endDate;
        PersonId = personId;
        LocationId = locationId;
    }

    //getter methods
    public LocalDateTime getStartDate() {
        return StartDate;
    }

    public LocalDateTime getEndDate() { return EndDate; }

    public int getPersonId() {
        return PersonId;
    }

    public int getLocationId() {
        return LocationId;
    }
}