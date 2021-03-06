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
package org.enderstone.server.commands.enderstone;

import org.enderstone.server.Main;
import org.enderstone.server.api.messages.AdvancedMessage;
import org.enderstone.server.api.messages.ChatColor;
import org.enderstone.server.api.messages.SimpleMessage;
import org.enderstone.server.commands.Command;
import org.enderstone.server.commands.CommandMap;
import org.enderstone.server.commands.CommandSender;
import org.enderstone.server.commands.SimpleCommand;

public class LagCommand extends SimpleCommand {

	public LagCommand() {
		super("command.enderstone.lag", "lag", CommandMap.DEFAULT_ENDERSTONE_COMMAND_PRIORITY, "lagg");
	}

	@Override
	public int executeCommand(Command cmd, String alias, CommandSender sender, String[] args) {
		long[] lastLagg = Main.getInstance().getLastLag();
		long currentTick = Main.getInstance().getCurrentServerTick() - lastLagg.length;
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		long total = 0;
		sender.sendMessage(new SimpleMessage("Current TPS: "));
		for (int i = 0; i < lastLagg.length; i++) {
			long sleepTime = lastLagg[i];
			if(min > sleepTime) min = sleepTime;
			if(max < sleepTime) max = sleepTime;
			total += sleepTime;
			long tick = currentTick + i;
			double tps = sleepTime;
			tps -= Main.getInstance().getTickTime();
			if(tps > 0)
				tps = 0;
			tps *= -1;
			if(tps == 0) {
				tps = Main.getInstance().getTickSpeed();
			} else {
				tps = 1000 / tps;
			}
			if(tps > Main.getInstance().getTickSpeed())
				tps = Main.getInstance().getTickSpeed();
                        
			sender.sendMessage(
					new AdvancedMessage()
						.getBase()
							.setColor(sleepTime < -50 ? ChatColor.RED : sleepTime < 0 ? ChatColor.YELLOW : ChatColor.GREEN)
						.addPart("Tick: " + tick + " ")
						.addPart("TPS: " + String.format("%2.3f",tps) + " ")
						.addPart("Sleeptime: " + sleepTime)
					.build()
			);
		}
                assert min <= max;
				
				double avg = (total / (double)lastLagg.length);
		sender.sendMessage(new SimpleMessage("min: " + min + " max: " + max + " avg: " + String.format("%.3f", avg)));
		double tps = avg;
		tps /= lastLagg.length;
		tps -= Main.getInstance().getTickTime();
		if(tps > 0)
			tps = 0;
		tps *= -1;
		if(tps == 0) {
			tps = Main.getInstance().getTickSpeed();
		} else {
			tps = 1000 / tps;
		}
		if(tps > Main.getInstance().getTickSpeed())
			tps = Main.getInstance().getTickSpeed();
		sender.sendMessage(new AdvancedMessage().getBase().
				setColor(tps < Main.getInstance().getTickSpeed() / 2 ? ChatColor.RED : 
						tps < Main.getInstance().getTickSpeed() ? ChatColor.YELLOW : 
								ChatColor.GREEN).
				addPart("Average TPS: " + String.format("%.3f", tps)).build());
		return COMMAND_SUCCESS;
	}

}
