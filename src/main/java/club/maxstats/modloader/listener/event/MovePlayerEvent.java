package club.maxstats.modloader.listener.event;

public class MovePlayerEvent extends Event {
    private double x, y, z;
    private boolean forceSneak;
    private State state;

    public MovePlayerEvent(double x, double y, double z, State state) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.state = state;
    }

    public double getX() { return x; }

    public double getY() { return y; }

    public double getZ() { return z; }

    /* Used to force sneak checks / updates */
    public boolean isForceSneak() { return this.forceSneak; }

    public State getState() { return this.state; }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

    public void setZ(double z) { this.z = z; }

    public void setForceSneak(boolean forceSneak) { this.forceSneak = forceSneak; }

    public void setState(State state) { this.state = state; }

    public enum State {
        PRE,
        PRESNEAK,
        POSTSNEAK,
        POST
    }
}
