package me.nepnep.safemode;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

@Mod("safemode")
public class SafeMode {
    final KeyBinding keybind = new KeyBinding("Toggle SafeMode", GLFW.GLFW_KEY_EQUAL, "SafeMode");
    public static boolean isOn = false;

    public SafeMode() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.addListener(this::onKey);
    }

    private void setup(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(keybind);
    }

    private void onKey(InputEvent.KeyInputEvent event) {
        if (event.getKey() == keybind.getKey().getValue() && event.getAction() == GLFW.GLFW_PRESS) {
            isOn = !isOn;
        }
    }
}
