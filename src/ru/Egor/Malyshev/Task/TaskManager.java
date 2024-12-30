package ru.Egor.Malyshev.Task;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskManager {

	Scanner scanner;

	static int mainTaskId;
	static double subTaskId;

	static MainTask mainTask;
	static SubTask subTask;

	static MainTask newMmainTask;
	static SubTask newSubTask;

	static String mainTaskName;
	static String subTaskName;

	static int mainTasksCount;
	static int subTasksCount;

	static TaskProgress mainTaskProgress;
	static TaskProgress subTaskProgress;

	static Map<Integer, MainTask> newMainTaskMap = new LinkedHashMap<>();
	static Map<Integer, MainTask> mainTaskMap = new LinkedHashMap<>();
	static Map<Double, SubTask> subTaskMap = new LinkedHashMap<>();

	public static void printmainTaskMap() {
		System.out.println(mainTaskMap);
	}

// считывание праметров для создания новой основной задачи
	public static String newMainTaskScanner(Scanner scanner) {
		System.out.println("Название задачи:");
		mainTaskName = scanner.nextLine();
		return mainTaskName;
	}

// считывание праметров для создания новой подзадачи
	public static String newSubTaskScanner(Scanner scanner) {
		System.out.println("Название подзадачи:");
		subTaskName = scanner.nextLine();
		return subTaskName;
	}

// создание отсновной задачу
	public static MainTask createNewMainTask(Scanner scanner) {
		newMainTaskScanner(scanner);
		subTaskMap = new LinkedHashMap<>();
		mainTask = new MainTask(++mainTaskId, mainTaskName, TaskProgress.NEW, subTaskMap);
		mainTaskMap.put(mainTaskId, mainTask);
		refactorId();
		return mainTask;
	}

// создание подзадачи
	public static SubTask addNewSubTask(Scanner scanner) {
		System.out.println("Номер основной задачи:");
		int mainTaskId = scanner.nextInt();
		scanner.nextLine();
		newSubTaskScanner(scanner);
		subTask = new SubTask(++subTaskId, subTaskName, TaskProgress.NEW);

		mainTask = mainTaskMap.get(mainTaskId);
		subTaskMap = mainTask.getSubTaskMap();
		subTaskMap.put(subTaskId, subTask);
		refactorId();
		return subTask;
	}

// перезапись ID для основной задачи и вложенных подзадач в линейный порядок (1, 2, 3 и тд.)
	public static void refactorId() {
		// запись основных задач с новыми id в newMainTaskMap
		int i = 1;
		for (int mainTaskId : mainTaskMap.keySet()) {
			Map<Double, SubTask> newSubTaskMap = new LinkedHashMap<>();
			mainTask.setMainTaskId(i++);
			int newMainTaskId = mainTask.getMainTaskId();
			mainTask = mainTaskMap.get(mainTaskId);
			mainTaskName = mainTask.getName();
			subTaskMap = mainTask.getSubTaskMap();
			TaskProgress taskProgress = mainTask.getTaskProgress();
			MainTask newTask = new MainTask(newMainTaskId, mainTaskName, taskProgress, newSubTaskMap);
			newMainTaskMap.put(newMainTaskId, newTask);

			// запись подзадач с новыми id в newMainTaskMap
			double j = 1.0;
			subTaskMap = mainTaskMap.get(mainTaskId).getSubTaskMap();
			for (double subTaskId : subTaskMap.keySet()) {

				subTaskMap = mainTask.getSubTaskMap();
				subTask = subTaskMap.get(subTaskId);
				newSubTaskMap = newMainTaskMap.get(newMainTaskId).getSubTaskMap();
				subTask.setSubTaskId((j++ / 10) + newMainTaskId);
				newSubTaskMap.put(subTask.getSubTaskId(), subTask);
			}
		} // запись данных из newMainTaskMap в mainTaskMap
		mainTaskMap = new LinkedHashMap<Integer, MainTask>(newMainTaskMap);
		newMainTaskMap.clear(); // чистим newMainTaskMap
	}

// удаление основной задачи по id задачи
	public static void removeTask(Scanner scanner) {
		System.out.println("Для удаления введите номер задачи:");
		int mainTaskId = scanner.nextInt();
		mainTaskMap.remove(mainTaskId);
		refactorId();
		checkTaskProgress();
	}

// удаление подзадачи по id подзадачи
	public static void removeSubTask(Scanner scanner) {
		System.out.println("Для удаления введите номер подзадачи:");
		double subTaskId = scanner.nextDouble();
		mainTaskMap.get((int) subTaskId).getSubTaskMap().remove(subTaskId);
		refactorId();
		checkTaskProgress();
	}

// изменение названия задачи по id задачи
	public static void editMainTaskName(Scanner scanner) {
		System.out.println("Номер основной задачи:");
		mainTaskId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Введите новое название задачи:");
		mainTaskName = scanner.nextLine();
		mainTaskMap.get(mainTaskId).setName(mainTaskName);
	}

// изменение названия подзадачи по id ползадачи
	public static void editSubTaskName(Scanner scanner) {
		System.out.println("Номер подзадачи:");
		subTaskId = scanner.nextDouble();
		scanner.nextLine();
		mainTask = mainTaskMap.get((int) subTaskId);
		subTaskMap = mainTask.getSubTaskMap();
		subTask = subTaskMap.get(subTaskId);
		System.out.println("Введите новое название подзадачи:");
		subTaskName = scanner.nextLine();
		subTask.setName(subTaskName);
	}


// изенение статуса подзадачи по id
	public static void changeSubTaskProgress(Scanner scanner) {
		System.out.println("Номер подзадачи:");
		subTaskId = scanner.nextDouble();
		scanner.nextLine();
		mainTask = mainTaskMap.get((int) subTaskId);
		subTaskMap = mainTask.getSubTaskMap();
		subTask = subTaskMap.get(subTaskId);
		System.out.println("Новый статус основной задачи\n" + "n - new\n" + "p - in progres\n" + "d - done");
		String tp = scanner.nextLine();
		

		if (tp.equals("n")) {
			subTask.setTaskProgress(TaskProgress.NEW);

		} else if (tp.equals("p")) {
			subTask.setTaskProgress(TaskProgress.IN_PROGRESS);

		} else if (tp.equals("d")) {
			subTask.setTaskProgress(TaskProgress.DONE);
		}
		checkTaskProgress();
	}

//сбор статитики по количеству задач, подзадач и их статусов
	public static void getInfo() {
		
		int mainTaskCount_NEW = 0;
		int mainTaskCount_IN_PROGRESS = 0;
		int mainTaskCount_DONE = 0;
		
		int subTaskCount_NEW = 0;
		int subTaskCount_IN_PROGRESS = 0;
		int subTaskCount_DONE = 0;

		for (int mainTaskId : mainTaskMap.keySet()) {
			mainTask = mainTaskMap.get(mainTaskId);
			mainTaskProgress = mainTask.getTaskProgress();
			subTaskMap = mainTaskMap.get(mainTaskId).getSubTaskMap();
			if (mainTaskProgress.equals(TaskProgress.NEW)) {
				mainTaskCount_NEW++;
			}
			if (mainTaskProgress.equals(TaskProgress.IN_PROGRESS)) {
				mainTaskCount_IN_PROGRESS++;
			}
			if (mainTaskProgress.equals(TaskProgress.DONE)) {
				mainTaskCount_DONE++;
			}

			for (double subTaskId : subTaskMap.keySet()) {
				subTask = subTaskMap.get(subTaskId);
				subTaskProgress = subTask.getTaskProgress();
				if (subTaskProgress.equals(TaskProgress.NEW)) {
					subTaskCount_NEW++;
				} else if (subTaskProgress.equals(TaskProgress.IN_PROGRESS)) {
					subTaskCount_IN_PROGRESS++;
				} else if (subTaskProgress.equals(TaskProgress.DONE)) {
					subTaskCount_DONE++;
				}
			}
		}
		int totalMainTasks = mainTaskCount_NEW + mainTaskCount_IN_PROGRESS + mainTaskCount_DONE;
		int totalSubTasks = subTaskCount_NEW + subTaskCount_IN_PROGRESS + subTaskCount_DONE;
		String str = "[TOTAL TASKS = " + totalMainTasks + "]\n"
					+ "[STATUS NEW = " + mainTaskCount_NEW + "]\n"
					+ "[STATUS IN_PROGRESS = " + mainTaskCount_IN_PROGRESS + "]\n"
					+ "[STATUS DONE = " + mainTaskCount_DONE + "]\n"
					+ "\n[TOTAL SUBTASKS = " + totalSubTasks + "]\n"
					+ "[STATUS NEW = " + subTaskCount_NEW + "]\n"
					+ "[STATUS IN_PROGRESS = " + subTaskCount_IN_PROGRESS + "]\n"
					+ "[STATUS DONE = " + subTaskCount_DONE + "]";
		System.out.println(str);
	}

// отслеживание изменения статуса основных задач и их подзадач
	public static void checkTaskProgress() {
		

		for (int mainTaskId : mainTaskMap.keySet()) {
			int subTaskCount_DONE = 0;
			mainTask = mainTaskMap.get(mainTaskId);
			mainTaskProgress = mainTask.getTaskProgress();
			subTaskMap = mainTaskMap.get(mainTaskId).getSubTaskMap();
			
			for (double subTaskId : subTaskMap.keySet()) {
				subTask = subTaskMap.get(subTaskId);
				subTaskProgress = subTask.getTaskProgress();

				if (subTaskProgress.equals(TaskProgress.DONE)) {
					subTaskCount_DONE++;
				}
				if (subTaskProgress.equals(TaskProgress.IN_PROGRESS)) {
					mainTask.setTaskProgress(TaskProgress.IN_PROGRESS);
				}
				if (subTaskCount_DONE == subTaskMap.size()) {
					mainTask.setTaskProgress(TaskProgress.DONE);
				}
				
			}
		}
	}

//вывод в консоль всех задачи и подзадач
	public static void printAllMainTasks() {
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("Список задач:");
		for (int mTid : mainTaskMap.keySet()) {
			System.out.println();
			int mTaskId = (mainTaskMap.get(mTid).getMainTaskId());
			String mTaskName = (mainTaskMap.get(mTid).getName());
			TaskProgress mTaskProgress = (mainTaskMap.get(mTid).getTaskProgress());

			System.out.println(mTaskId + " " + mTaskName + " " + mTaskProgress);

			if (mainTaskMap.get(mTid).getSubTaskMap() == null) {
				System.out.println("Егор404 : mainTaskMap is null");
			} else {
				Map<Double, SubTask> sTaskMap = mainTaskMap.get(mTid).getSubTaskMap();
				if (sTaskMap.isEmpty()) {
					System.out.println("Подзадач нет");
				} else {
					for (double sTid : sTaskMap.keySet()) {
						double sTaskId = sTaskMap.get(sTid).getSubTaskId();
						String sTaskName = sTaskMap.get(sTid).getName();
						TaskProgress sTaskProgress = sTaskMap.get(sTid).getTaskProgress();
						if (!(sTaskProgress == null)) {
							System.out.println(sTaskId + " " + sTaskName + " " + sTaskProgress);
						} else {
							System.out.println("Подзадач нет");
						}
					}
					
				}
			}
		}
		System.out.println("-----------------------------------");
	}
//заполнение задач и подзадач для тестов
	public static void testMode () {
		
		mainTaskId = 100;
		subTaskId = 100;
		
		SubTask subTask1 = new SubTask (1.1 , "Построить звезду смерти", TaskProgress.NEW);
		SubTask subTask2 = new SubTask (1.2 , "Построить звезду смерти", TaskProgress.NEW);
		SubTask subTask3 = new SubTask (1.3 , "Создать армию клонов", TaskProgress.NEW);
		SubTask subTask4 = new SubTask (1.4 , "Уничтожить несколько планет Республики", TaskProgress.NEW);
		SubTask subTask5 = new SubTask (1.5 , "Построить Империю", TaskProgress.NEW);
		subTaskMap = new LinkedHashMap<>();
		subTaskMap.put(1.1, subTask1);
		subTaskMap.put(1.2, subTask2);
		subTaskMap.put(1.3, subTask3);
		subTaskMap.put(1.4, subTask4);
		subTaskMap.put(1.5, subTask5);
		mainTask = new MainTask(1, "Захватить Мир!", TaskProgress.NEW, subTaskMap);
		mainTaskMap.put(1, mainTask);
		
		SubTask subTask6 = new SubTask (2.1 , "Выучить java-core", TaskProgress.NEW);
		SubTask subTask7 = new SubTask (2.2 , "Выучить SQL", TaskProgress.NEW);
		SubTask subTask8 = new SubTask (2.3 , "Выучить Spring", TaskProgress.NEW);
		SubTask subTask9 = new SubTask (2.4 , "Выучить thymeleaf или что там еще", TaskProgress.NEW);
		SubTask subTask10 = new SubTask (2.5 , "Пройти собеседование", TaskProgress.NEW);
		subTaskMap = new LinkedHashMap<>();
		subTaskMap.put(2.1, subTask6);
		subTaskMap.put(2.2, subTask7);
		subTaskMap.put(2.3, subTask8);
		subTaskMap.put(2.4, subTask9);
		subTaskMap.put(2.5, subTask10);
		mainTask = new MainTask(2, "Стать Java developer", TaskProgress.NEW, subTaskMap);
		mainTaskMap.put(2, mainTask);
	}
}
