package org.enderstone.server.packet.play;

import io.netty.buffer.ByteBuf;
import org.enderstone.server.chat.Message;
import org.enderstone.server.packet.Packet;

public class PacketOutPlayerDisconnect extends Packet {
	private String reason;

	public PacketOutPlayerDisconnect() {
	}

	public PacketOutPlayerDisconnect(String reason) {
		this.reason = reason;
	}
	
	public PacketOutPlayerDisconnect(Message reason) {
		this.reason = reason.toMessageJson();
	}

	@Override
	public void read(ByteBuf buf) throws Exception {
		throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " with ID 0x" + Integer.toHexString(getId()) + " cannot be read.");
	}

	@Override
	public void write(ByteBuf buf) throws Exception {
		writeString(reason, buf);
	}

	@Override
	public int getSize() throws Exception {
		return getStringSize(reason) + getVarIntSize(getId());
	}

	@Override
	public byte getId() {
		return 0x40;
	}

}
