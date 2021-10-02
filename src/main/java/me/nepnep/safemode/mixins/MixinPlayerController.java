package me.nepnep.safemode.mixins;

import me.nepnep.safemode.SafeMode;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerController.class)
public class MixinPlayerController {
    @Inject(method = "startDestroyBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Direction;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerController;sendBlockAction(Lnet/minecraft/network/play/client/CPlayerDiggingPacket$Action;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Direction;)V", ordinal = 0),
            cancellable = true)
    public void startDestroyBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (SafeMode.isOn) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "continueDestroyBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Direction;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerController;sendBlockAction(Lnet/minecraft/network/play/client/CPlayerDiggingPacket$Action;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Direction;)V", ordinal = 0),
            cancellable = true)
    public void continueDestroyBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (SafeMode.isOn) {
            cir.setReturnValue(false);
        }
    }
}
