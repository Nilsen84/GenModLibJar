package club.maxstats.modloader.listener.event;

import club.maxstats.modloader.ModLoaderMain;

public class InputEvent extends Event {
    private int keycode;

    public InputEvent(int keycode) { this.keycode = keycode; }

    public int getKeycode() { return this.keycode; }

    public static class KeyEvent extends InputEvent {
        public KeyEvent(int keycode) {
            super(keycode);
            ModLoaderMain.getModLoader().getEventBus().callEvent(new InputEvent(keycode));
        }
    }

    public static class MouseEvent extends InputEvent {
        public MouseEvent(int keycode) {
            super(keycode);
            ModLoaderMain.getModLoader().getEventBus().callEvent(new InputEvent(keycode));
        }
    }
}
