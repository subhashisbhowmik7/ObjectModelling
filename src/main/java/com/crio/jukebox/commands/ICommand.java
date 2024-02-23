package com.crio.jukebox.commands;

import java.util.*;

public interface ICommand {
    public void execute(List<String> tokens);
}
