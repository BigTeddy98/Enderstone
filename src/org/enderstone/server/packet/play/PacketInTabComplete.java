package org.enderstone.server.packet.play;

import org.enderstone.server.packet.NetworkManager;
import org.enderstone.server.packet.Packet;

import io.netty.buffer.ByteBuf;

/**
 *
 * @author Fernando
 */
public class PacketInTabComplete extends Packet {

	private String halfCommand;

	
	@Override
	public void read(ByteBuf buf) throws Exception {
		halfCommand = readString(buf);
	}

	@Override
	public void write(ByteBuf buf) throws Exception {
		throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " with ID 0x" + Integer.toHexString(getId()) + " cannot be written.");
	}

	@Override
	public int getSize() throws Exception {
		return getStringSize(halfCommand) + getVarIntSize(getId());
	}

	@Override
	public byte getId() {
		return 0x14;
	}
	
	public String getHalfCommand() {
		return halfCommand;
	}
	
	@Override
	public void onRecieve(final NetworkManager networkManager) {
		networkManager.player.onPlayerChatComplete(this);
	}
	
}
