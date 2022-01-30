package com.park.terminal.abstractions;

import com.park.common.abstracts.AClientApplicationService;
import com.park.common.models.Attraction;

public abstract class ATerminalService extends AClientApplicationService {
    abstract public void addAttraction(Attraction attraction);
}
