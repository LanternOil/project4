package com.company.dahms;

class Task {
    String taskTitle;
    int priority;
    String desc;

    public Task(String taskTitle, int priority, String desc) {
        this.taskTitle = taskTitle;
        this.priority = priority;
        this.desc = desc;

        if (this.priority<0){
            this.priority=0;
        }else if (this.priority>5){
            this.priority=5;
        }

    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskTitle='" + taskTitle + '\'' +
                ", priority=" + priority +
                ", desc='" + desc + '\'' +
                '}';
    }
}
