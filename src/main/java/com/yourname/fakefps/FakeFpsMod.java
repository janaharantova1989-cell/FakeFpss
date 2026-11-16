package com.yourname.fakefps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.text.Text;

public class FakeFpsMod implements ClientModInitializer {
    public static int baseFps = -1;

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("fps")
                .then(ClientCommandManager.argument("target", IntegerArgumentType.integer(1, 100000))
                    .executes(context -> {
                        baseFps = IntegerArgumentType.getInteger(context, "target");
                        context.getSource().sendFeedback(Text.literal("Fake FPS set to: " + baseFps));
                        return 1;
                    })
                )
                .executes(context -> {
                    baseFps = -1;
                    context.getSource().sendFeedback(Text.literal("Fake FPS disabled."));
                    return 1;
                })
            );
        });
    }
}
