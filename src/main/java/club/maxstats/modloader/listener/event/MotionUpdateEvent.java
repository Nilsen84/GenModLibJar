package club.maxstats.modloader.listener.event;

import club.maxstats.modloader.ModLoaderMain;

public class MotionUpdateEvent extends Event {
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround;
    private State state;

    public MotionUpdateEvent(double x, double y, double z, float yaw, float pitch, boolean onGround, State state) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.state = state;
        ModLoaderMain.getModLoader().getEventBus().callEvent(this);
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public double getZ() { return this.z; }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

    public void setZ(double z) { this.z = z; }

    public void setYaw(float yaw) { this.yaw = yaw; }

    public void setPitch(float pitch) { this.pitch = pitch; }

    public void setOnGround(boolean onGround) { this.onGround = onGround; }

    public float getYaw() { return this.yaw; }

    public float getPitch() { return this.pitch; }

    public boolean onGround() { return this.onGround; }

    public State getState() { return this.state; }

    public void setState(State state) { this.state = state; }

    public enum State {
        PRE, POST;
    }
}
