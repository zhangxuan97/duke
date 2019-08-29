package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

import java.util.ArrayList;

/**
 * deals with finding tasks by keyword
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a FindCommand object to deal with finding tasks by keyword
     *
     * @param keyword the keyword to be searched
     * @throws DukeException if keyword only contains whitespaces
     */
    public FindCommand(String keyword) throws DukeException {
        if (keyword.equals("")) {
            throw new DukeException("Please enter a keyword to find!");
        }
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks by keyword
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        ArrayList<Task> tasksContainingKeyword = tasks.findTasksByKeyword(keyword);
        String[] tasksToPrint = new String[tasksContainingKeyword.size() + 1];
        tasksToPrint[0] = "Here are the matching tasks in your list:";
        for (int i = 1; i <= tasksContainingKeyword.size(); i++) {
            tasksToPrint[i] = i + "." + tasksContainingKeyword.get(i - 1).toString();
        }
        ui.dukeEcho(tasksToPrint);
    }
}
