package nl.fhict.s3.websocketserver.SocketMessage;


import nl.fhict.s3.websocketserver.Command.Commands.Connect;
import nl.fhict.s3.websocketserver.Command.Commands.RegisterPlayer;
import nl.fhict.s3.websocketserver.Command.Commands.UseMove;
import nl.fhict.s3.websocketserver.Interface.Command;

import java.util.HashMap;

public class Factory {

    private HashMap<String, Command> commands;

    public Factory()
    {
        commands = new HashMap<>();
      //  commands.put(new RegisterPlayer().getName(), new RegisterPlayer());
        commands.put(new Connect().getName(), new Connect());
        commands.put(new UseMove().getName(), new UseMove());
    }

    public Command getCommand(String commandName)
    {
        //search through the command list and return the command with the commandName
        return commands.get(commandName);
    }
}
