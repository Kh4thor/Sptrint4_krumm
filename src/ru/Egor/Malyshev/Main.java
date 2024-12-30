package ru.Egor.Malyshev;

import java.util.Scanner;

import ru.Egor.Malyshev.Task.TaskManager;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		while (true) {
			Menu.printMainTaskMenu();

			var cmd = scanner.nextLine();
			if (cmd.equals("1")) { // ready
				TaskManager.createNewMainTask(scanner);
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("2")) { // ready
				TaskManager.addNewSubTask(scanner);
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("3")) {// ready
				TaskManager.removeTask(scanner);
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("4")) { // ready
				TaskManager.removeSubTask(scanner);
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("5")) { // ready
				TaskManager.editMainTaskName(scanner);
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("6")) { // ready
				TaskManager.editSubTaskName(scanner);
				TaskManager.printAllMainTasks();
//			} else if (cmd.equals("7")) {
//				TaskManager.changeMainTaskProgress(scanner);
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("8")) {
				TaskManager.changeSubTaskProgress(scanner);
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("9")) {
				TaskManager.printmainTaskMap();
			} else if (cmd.equals("0")) {
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("!")) {
				TaskManager.getInfo();
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("%")) {
				TaskManager.testMode();
				TaskManager.printAllMainTasks();
			} else if (cmd.equals("exit")) {
				System.out.println("Программа завершена!");
				break;
			}
		}
	}
}
