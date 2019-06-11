package nl.fhict.s3.websocketclient.SocketMessage;

import nl.fhict.s3.websocketclient.Interface.Command;
import nl.fhict.s3.websocketclient.Commands.*;

import java.util.HashMap;

public class Factory {

    private HashMap<String, Command> commands;

    public Factory()
    {
        commands = new HashMap<>();
        commands.put(new RegisterPlayer().getName(), new RegisterPlayer());
        commands.put(new Connect().getName(), new Connect());

    }

    public Command getCommand(String commandName)
    {
        //search through the command list and return the command with the commandName
        return commands.get(commandName);
    }
}
