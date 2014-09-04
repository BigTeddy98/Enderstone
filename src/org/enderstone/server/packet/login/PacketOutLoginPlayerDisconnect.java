/* 
 * Enderstone
 * Copyright (C) 2014 Sander Gielisse and Fernando van Loenhout
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.enderstone.server.packet.login;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.enderstone.server.chat.Message;
import org.enderstone.server.packet.Packet;
import static org.enderstone.server.packet.Packet.getStringSize;
import static org.enderstone.server.packet.Packet.getVarIntSize;
import static org.enderstone.server.packet.Packet.writeString;

/**
 *
 * @author Fernando
 */
public class PacketOutLoginPlayerDisconnect extends Packet {
	private String reason;
	private Message message;

	public PacketOutLoginPlayerDisconnect() {
	}

	public PacketOutLoginPlayerDisconnect(String reason) {
		this.reason = reason;
	}
	
	public PacketOutLoginPlayerDisconnect(Message reason) {
		this.message = reason;
	}

	@Override
	public void read(ByteBuf buf) throws IOException {
		throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " with ID 0x" + Integer.toHexString(getId()) + " cannot be read.");
	}

	@Override
	public void write(ByteBuf buf) throws IOException {
		if(this.reason == null) this.reason = message.toMessageJson();
		writeString(reason, buf);
	}

	@Override
	public int getSize() throws IOException {
		if(this.reason == null) this.reason = message.toMessageJson();
		return getStringSize(reason) + getVarIntSize(getId());
	}

	@Override
	public byte getId() {
		return 0x00;
	}

}