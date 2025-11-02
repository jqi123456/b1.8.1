package dev.colbster937.eaglercraft.command;

import dev.colbster937.eaglercraft.SingleplayerCommands;
import dev.colbster937.eaglercraft.utils.I18n;

public class FlyCommand extends Command {
  public FlyCommand() {
    super("/fly", new String[] { "" }, "");
  }

  @Override
  public void run(String[] args) {
    if (args.length == 1) {
      this.mc.thePlayer.field_35212_aW.field_35758_c = !this.mc.thePlayer.field_35212_aW.field_35758_c;
      this.mc.thePlayer.field_35212_aW.field_35757_b = this.mc.thePlayer.field_35212_aW.field_35758_c;
      SingleplayerCommands.showChat(I18n.format("command.fly",
          this.mc.thePlayer.field_35212_aW.field_35758_c ? I18n.format("enabled") : I18n.format("disabled")));
    } else {
      this.showUsage(args[0]);
    }
  }
}