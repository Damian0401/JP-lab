package com.park.ticketmachine.abstractions;

import com.park.common.abstracts.AClientApplicationService;
import com.park.common.models.Ticket;

public abstract class ATicketMachineService extends AClientApplicationService {
    abstract public void buyTicket(Ticket ticket);
}
