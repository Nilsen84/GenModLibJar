package club.maxstats.modloader.listener.event;

import club.maxstats.modloader.ModLoaderMain;

public class RenderGameOverlayEvent extends Event {
    public static class Pre extends RenderGameOverlayEvent {
        public Pre() {
            ModLoaderMain.getModLoader().getEventBus().callEvent(new RenderGameOverlayEvent());
        }
    }

    public static class Post extends RenderGameOverlayEvent {
        public Post() {
            ModLoaderMain.getModLoader().getEventBus().callEvent(new RenderGameOverlayEvent());
        }
    }
}
