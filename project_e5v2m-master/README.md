# TaskaDo 

## Just Make Your Day-To-Day Life Easier^_^

My *proposal* of this project:
- What will the application do?<br>
    A to-do list application called **TaskaDo**.<br><br>
- Who will use it?<br>
    Everyone can use it for time management and tasks organization, especially for those who need to be **self-disciplined**, such like students, office workers, fitness and meal management people.<br><br>
- Why is this project of interest to me?<br>
    It is very useful in people's real life. As a student, I really need a to-do-list to help me **organize** my courses' work. <br><br>


The *User Stories* of my project:<br><br>
1. As a user, I want to be able to **add** a task to my to-do list. <br><br>
2. As a user, I want to be able to **delete** a task to my to-do list. <br><br>
3. As a user, I want to be able to **save** all of my tasks after adding the new task --- **save** my to-do list to file. <br><br> 
4. As a user, I want to be able to **load** my to-do list from the file and resume exactly where I left off before.<br><br> 
5. In the "add" menu, I want to be able to input the property of my adding task, including:<br><br>
      - Task Name <br><br>
      - Task Description <br><br>
      - Task Due in Days <br><br>
      - Task Properties (urgent or not?)<br><br>
      - Task Tags<br><br>

The *Phase#4* of my project:<br><br>
1. **Phase#4 Task2:** In task 2, I choose to **test and design a class in my model package that is robust**. I created an exception called "InvalidInputException", which will check the input of "days" value is negative or not when the user adding a task. (The start point is from the Task class - task constructor. The exception will be thrown if it the negative value comes up, and the error will be catch in the persistent/ui classes(eg.JsonList.loadData(), AddNew.addTask(), ToDoListApp.loadToDoList) where the task method called.) <br>

In addition, I created an ExceptionTest which including two tests: one for the case where the exception has been thrown, and another one for the exception has not been thrown.

2. **Phase#4 Task3:** In task3, I used Hierarchy, Association, Aggregation and Dependency in my design. 
- Writable is an interface class. Both of regular classes Task&TodoList implement it. 
- ToDoList and Task have aggregation relationship as one ToDoList can have 0 or more tasks. I think Task is a part of ToDoList.
- There are many other association relationships. For instance, TaskadoGUI is associated with one ToDoList object. The rest shows on the picture.
- There are many other dependency relationships. For instance, TaskadoGUI has dependency with one MainPageGUI object. The rest shows on the picture.
                                 
                                 



