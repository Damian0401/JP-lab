package models;

public class TimeSettings {
    private volatile int moverDelay;
    private volatile int chefDelay;
    private volatile int guestAppearingDelay;

    public TimeSettings(int moverDelay, int chefDelay, int guestAppearingDelay) {
        if (moverDelay < 1 || chefDelay < 1 || guestAppearingDelay < 1)
            throw new IllegalArgumentException("All time settings must be positive numbers");
        this.moverDelay = moverDelay;
        this.chefDelay = chefDelay;
        this.guestAppearingDelay = guestAppearingDelay;
    }

    public int getMoverDelay() {
        return moverDelay;
    }

    public void setMoverDelay(int moverDelay) {
        this.moverDelay = moverDelay;
    }

    public int getChefDelay() {
        return chefDelay;
    }

    public void setChefDelay(int chefDelay) {
        this.chefDelay = chefDelay;
    }

    public int getGuestAppearingDelay() {
        return guestAppearingDelay;
    }

    public void setGuestAppearingDelay(int guestAppearingDelay) {
        this.guestAppearingDelay = guestAppearingDelay;
    }
}
