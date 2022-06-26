package club.maxstats.modloader;

public class TickEvent extends Event {
    public static class RenderTick {
        private float partialTicks;

        public RenderTick() {
            this.partialTicks = 1.0F;
        }

        public float getPartialTicks() { return this.partialTicks; }
    }
}
