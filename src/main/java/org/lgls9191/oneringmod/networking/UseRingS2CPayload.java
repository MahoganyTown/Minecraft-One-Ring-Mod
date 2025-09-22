package org.lgls9191.oneringmod.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static org.lgls9191.oneringmod.Oneringmod.MOD_ID;

public record UseRingS2CPayload(boolean active) implements CustomPayload {
    public static final Identifier USE_RING_PAYLOAD_ID = Identifier.of(MOD_ID, "use_ring");
    public static final CustomPayload.Id<UseRingS2CPayload> ID = new CustomPayload.Id<>(USE_RING_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, UseRingS2CPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOLEAN, UseRingS2CPayload::active, UseRingS2CPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
