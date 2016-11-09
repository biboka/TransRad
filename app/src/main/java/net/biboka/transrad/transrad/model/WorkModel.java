package net.biboka.transrad.transrad.model;

/**
 * Created by biboka on 2016.11.08..
 */

public class WorkModel {
    private String workerName;
    private String workSpace;

    public WorkModel(String workerName, String workSpace) {
        this.workerName = workerName;
        this.workSpace = workSpace;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getWorkSpace() {
        return workSpace;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public void setWorkSpace(String workSpace) {
        this.workSpace = workSpace;
    }
}
